package by.grodno.pvt.site.housingAndCommunalServicesApp.dto;


import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkBrigade;
import lombok.Data;


@Data
public class WorkPlanDTO {
    private Integer id;
    private RequestForm requestForm;
    private WorkBrigade workBrigade;
    private Boolean work_completed;
}
