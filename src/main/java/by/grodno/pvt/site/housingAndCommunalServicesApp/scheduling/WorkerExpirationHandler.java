package by.grodno.pvt.site.housingAndCommunalServicesApp.scheduling;


import java.util.List;
import java.util.stream.Collectors;


import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Worker;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl.WorkerHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkerExpirationHandler {
    @Autowired
    private WorkerHireService service;
    @Scheduled(fixedRate = 10 * 1000)
    public void invalidateWorkers() {
        List<Worker> busyPlumbers = service.busyPlumbers();
        busyPlumbers.forEach(c -> c.setIsBusy(false));
        service.saveWorkers(busyPlumbers);
        List<Worker> busyElectricians = service.busyElectricians();
        busyElectricians.forEach(c -> c.setIsBusy(false));
        service.saveWorkers(busyElectricians);
        List<Worker> busyRepairers = service.busyRepairers();
        busyRepairers.forEach(c -> c.setIsBusy(false));
        service.saveWorkers(busyRepairers);

        log.info("Workers released: " + busyPlumbers.stream().map(Worker::getWorkerRole).collect(Collectors.toList()) +
                busyElectricians.stream().map(Worker::getWorkerRole).collect(Collectors.toList())+
                busyRepairers.stream().map(Worker::getWorkerRole).collect(Collectors.toList()));
    }
}
