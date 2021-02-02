package by.grodno.pvt.site.housingAndCommunalServicesApp.controller;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkScale;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.RequestFormDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.UserDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.UserRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.RequestFormService;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.UserService;
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
public class RequestFormListController {
    @Autowired
    private RequestFormService requestFormService;
    @Autowired
    private ConversionService convertionService;
    @Autowired
    private UserService userService;
    @GetMapping("/requestForms")
    public String getAllRequestForms(Model model) {

        List<RequestFormDTO> requestForms = requestFormService.getRequestForms().stream().map(u -> convertionService.convert(u, RequestFormDTO.class))
                .collect(Collectors.toList());

        model.addAttribute("requestForms", requestForms);

        return "requestForms";
    }

    @GetMapping("forUser/addRequestForm")
    public String newRequestForm(Model model){
        RequestForm requestForm = new RequestForm();
        List<UserDTO> users = userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("requestForm", requestForm);
        model.addAttribute("userList", users);
        model.addAttribute("workScale", WorkScale.values());

        return "forUser/addRequestForm";
    }

    @PostMapping("/saveRequestForm")
    public String saveRequestForm(@ModelAttribute("requestForm") RequestForm requestForm){
        requestFormService.saveRequestForm(requestForm);
        return "redirect:/workPlans";
    }

}
