package by.grodno.pvt.site.housingAndCommunalServicesApp.controller;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkBrigade;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkerRole;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.WorkBrigadeDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.WorkerDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.scheduling.UserExpirationHandler;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkBrigadeService;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkerService;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl.JPAWorkBrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkBrigadeController {
    @Autowired
    private WorkBrigadeService workBrigadeService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private ConversionService convertionService;
    @Autowired
    private JPAWorkBrigadeService jpaWorkBrigadeService;
    @Autowired
    UserExpirationHandler userExpirationHandler;
    @GetMapping("/workBrigades")
    public String getAllWorkBrigades(Model model) {

        List<WorkBrigadeDTO> workBrigades = workBrigadeService.getWorkBrigades().stream().map(u -> convertionService.convert(u, WorkBrigadeDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("workBrigades", workBrigades);

        return "workBrigades";
    }
    @GetMapping("forDispatcher/addWorkBrigade")
    public String newWorkBrigade(Model model){
        WorkBrigade workBrigade = new WorkBrigade();
        List<WorkerDTO> workers = workerService.getWorkers().stream().map(u -> convertionService.convert(u, WorkerDTO.class))
                .collect(Collectors.toList());
        WorkBrigadeDTO workBrigadeDTO = new WorkBrigadeDTO();
        model.addAttribute("workBrigades", workBrigade);
        model.addAttribute("workers", workers);
        model.addAttribute("dto", workBrigadeDTO);
        model.addAttribute("rolePlumber", WorkerRole.PLUMBER);
        model.addAttribute("roleElectrician", WorkerRole.ELECTRICIAN);
        model.addAttribute("roleRepairer", WorkerRole.REPAIRER);
        model.addAttribute("isBusy", Boolean.TRUE);
        return "forDispatcher/addWorkBrigade";
    }

    @PostMapping("/saveWorkBrigade")
    public String saveWorkBrigade(@ModelAttribute("workBrigades") WorkBrigade workBrigade){
        Date startDate = new Date();
        WorkBrigadeDTO workBrigadeDTO = new WorkBrigadeDTO();
        Date endDate = new Date(startDate.getTime() + 20 * 1000);
        jpaWorkBrigadeService.hireWorkers(workBrigade.getPlumber().getId(),workBrigade.getElectrician().getId(), workBrigade.getRepairer().getId(),startDate, endDate);
        return "redirect:/workBrigades";
    }

}
