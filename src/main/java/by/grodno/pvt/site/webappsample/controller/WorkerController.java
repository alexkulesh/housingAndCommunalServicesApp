package by.grodno.pvt.site.webappsample.controller;

import by.grodno.pvt.site.webappsample.domain.UserRole;
import by.grodno.pvt.site.webappsample.domain.Worker;
import by.grodno.pvt.site.webappsample.domain.WorkerRole;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import by.grodno.pvt.site.webappsample.dto.WorkerDTO;
import by.grodno.pvt.site.webappsample.repo.UserRepo;
import by.grodno.pvt.site.webappsample.service.UserService;
import by.grodno.pvt.site.webappsample.service.WorkBrigadeService;
import by.grodno.pvt.site.webappsample.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkerController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private WorkBrigadeService workBrigadeService;
    @Autowired
    private ConversionService convertionService;
    @GetMapping("/workers")
    public String getAllWorkers(Model model, @AuthenticationPrincipal UserDetails currentUser) {

        List<WorkerDTO> workers = workerService.getWorkers().stream().map(u -> convertionService.convert(u, WorkerDTO.class))
                .collect(Collectors.toList());
        Integer userId = userRepo.findByEmail(currentUser.getUsername()).getId();

        model.addAttribute("workers", workers);
        model.addAttribute("currentUser", userService.getUser(userId));
        model.addAttribute("freeWorkers", workBrigadeService.freeWorkers());
        return "workers";
    }
    @GetMapping("forWorker/addWorker")
    public String newWorker(Model model){
        Worker worker = new Worker();
        List<UserDTO> users = userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("workers", worker);
        model.addAttribute("userList", users);
        model.addAttribute("userRole", UserRole.WORKER);
        model.addAttribute("workerRoles", WorkerRole.values());
        return "forWorker/addWorker";
    }

    @PostMapping("/saveWorker")
    public String saveWorker(@ModelAttribute("workers") Worker worker){
        workerService.saveWorker(worker);
        return "redirect:/workers";
    }

    @GetMapping("/workers/edit/{id}")
    public String editWorkerForm(@PathVariable Integer id, WorkerDTO workerDTO, Model model) {
        model.addAttribute("worker", workerService.getWorker(id));
        model.addAttribute("user", workerDTO.getUser());
        model.addAttribute("currentRole",workerDTO.getWorkerRole());
        model.addAttribute("workerRoles", WorkerRole.values());
        return "forWorker/editWorker";
    }

    @PostMapping("/workers/edit/{id}")
    public String editWorker(@PathVariable Integer id,  @Valid WorkerDTO workerDTO, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("workerDTO", workerDTO);
            return "forWorker/editWorker";
        }
        Worker worker = new Worker();
		worker.setId(id);
		worker.setUser(workerDTO.getUser());
		worker.setWorkerRole(workerDTO.getWorkerRole());
        worker.setIsBusy(workerDTO.getIsBusy());
        workerService.edit(workerDTO);

        return "redirect:/workers";
    }

}
