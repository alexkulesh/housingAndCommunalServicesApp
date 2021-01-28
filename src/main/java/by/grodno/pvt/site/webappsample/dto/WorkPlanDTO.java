package by.grodno.pvt.site.webappsample.dto;


import by.grodno.pvt.site.webappsample.domain.RequestForm;
import by.grodno.pvt.site.webappsample.domain.WorkBrigade;
import lombok.Data;


@Data
public class WorkPlanDTO {
    private Integer id;
    private RequestForm requestForm;
    private WorkBrigade workBrigade;
    private Boolean work_completed;
}
