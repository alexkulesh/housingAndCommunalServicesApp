package by.grodno.pvt.site.housingAndCommunalServicesApp.controller;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkPlan;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.RequestFormDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.WorkBrigadeDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.WorkPlanDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.RequestFormService;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkBrigadeService;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkPlanController {
    @Autowired
    private WorkPlanService workPlanService;
    @Autowired
    private RequestFormService requestFormService;
    @Autowired
    private WorkBrigadeService workBrigadeService;
    @Autowired
    private ConversionService convertionService;
    @GetMapping("/workPlans")
    public String getAllWorkPlans(Model model) {

        List<WorkPlanDTO> workPlans = workPlanService.getWorkPlans().stream().map(u -> convertionService.convert(u, WorkPlanDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("workPlans", workPlans);

        return "workPlans";
    }

    @GetMapping("forDispatcher/addWorkPlan")
    public String newWorkPlan(Model model){
        WorkPlan workPlan = new WorkPlan();
        List<RequestFormDTO> requestForms = requestFormService.getRequestForms().stream().map(u -> convertionService.convert(u, RequestFormDTO.class))
                .collect(Collectors.toList());
        List<WorkBrigadeDTO> workBrigades = workBrigadeService.getWorkBrigades().stream().map(u -> convertionService.convert(u, WorkBrigadeDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("workPlans", workPlan);
        model.addAttribute("requestForms", requestForms);
        model.addAttribute("workBrigades", workBrigades);
        return "forDispatcher/addWorkPlan";
    }

    @PostMapping("/saveWorkPlan")
    public String saveWorkPlan(@ModelAttribute("workPlans") WorkPlan workPlan){
        workPlanService.saveWorkPlan(workPlan);
        return "redirect:/workPlans";
    }
}
