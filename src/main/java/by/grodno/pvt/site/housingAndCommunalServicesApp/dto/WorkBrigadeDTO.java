package by.grodno.pvt.site.housingAndCommunalServicesApp.dto;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Worker;
import lombok.Data;

import java.util.Date;


@Data
public class WorkBrigadeDTO {
    private Integer id;
    private RequestForm requestForm;
    private Date workStartTime;
    private Date workEndTime;
    private Worker plumber;
    private Worker electrician;
    private Worker repairer;
}
