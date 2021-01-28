package by.grodno.pvt.site.housingAndCommunalServicesApp.dto;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkerRole;
import lombok.Data;

@Data
public class WorkerDTO {
    private Integer id;
    private User user;
    private WorkerRole workerRole;
    private Boolean isBusy;
}
