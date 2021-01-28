package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.Worker;
import by.grodno.pvt.site.webappsample.dto.WorkerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkerToDTOConverter implements Converter<Worker, WorkerDTO> {
    @Override
    public WorkerDTO convert(Worker source) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(source.getId());
        workerDTO.setUser(source.getUser());
        workerDTO.setWorkerRole(source.getWorkerRole());
        workerDTO.setIsBusy(source.getIsBusy());
        return workerDTO;
    }
}
