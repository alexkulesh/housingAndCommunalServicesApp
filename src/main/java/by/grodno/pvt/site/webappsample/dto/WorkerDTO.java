package by.grodno.pvt.site.webappsample.dto;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.domain.WorkerRole;
import lombok.Data;

@Data
public class WorkerDTO {
    private Integer id;
    private User user;
    private WorkerRole workerRole;
    private Boolean isBusy;
}
