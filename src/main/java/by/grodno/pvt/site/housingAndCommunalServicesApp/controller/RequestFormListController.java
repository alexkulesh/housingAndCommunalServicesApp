package by.grodno.pvt.site.housingAndCommunalServicesApp.controller;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.RequestFormDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.RequestFormService;
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
        model.addAttribute("requestForms", requestForm);
        return "forUser/addRequestForm";
    }

    @PostMapping("/saveRequestForm")
    public String saveRequestForm(@ModelAttribute("requestForm") RequestForm requestForm){
        requestFormService.saveRequestForm(requestForm);
        return "redirect:/workPlans";
    }

}
