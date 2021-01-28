package by.grodno.pvt.site.housingAndCommunalServicesApp.dto;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Worker;
import lombok.Data;


@Data
public class WorkBrigadeDTO {
    private Integer id;
    private Worker plumber;
    private Worker electrician;
    private Worker repairer;
}
