package by.grodno.pvt.site.webappsample.dto;

import by.grodno.pvt.site.webappsample.domain.Worker;
import lombok.Data;

import java.util.List;


@Data
public class WorkBrigadeDTO {
    private Integer id;
    private Worker plumber;
    private Worker electrician;
    private Worker repairer;
}
