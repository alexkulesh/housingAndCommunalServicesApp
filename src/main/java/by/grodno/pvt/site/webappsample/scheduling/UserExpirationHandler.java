package by.grodno.pvt.site.webappsample.scheduling;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import by.grodno.pvt.site.webappsample.domain.Worker;
import by.grodno.pvt.site.webappsample.service.impl.JPAWorkBrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserExpirationHandler {
    @Autowired
    private JPAWorkBrigadeService service;
    @Scheduled(fixedRate = 10 * 1000)
    public void invalidateUsers() {
        List<Worker> busyPlumbers = service.busyPlumbers();
        busyPlumbers.forEach(c -> c.setIsBusy(false));
        service.saveWorkers(busyPlumbers);
        List<Worker> busyElectricians = service.busyElectricians();
        busyElectricians.forEach(c -> c.setIsBusy(false));
        service.saveWorkers(busyElectricians);
        List<Worker> busyRepairers = service.busyRepairers();
        busyRepairers.forEach(c -> c.setIsBusy(false));
        service.saveWorkers(busyPlumbers);
        service.saveWorkers(busyElectricians);
        service.saveWorkers(busyRepairers);

        log.info("Worker released: " + busyPlumbers.stream().map(Worker::getWorkerRole).collect(Collectors.toList()));
        log.info("Worker released: " + busyPlumbers.stream().map(Worker::getWorkerRole).collect(Collectors.toList()));
        log.info("Worker released: " + busyPlumbers.stream().map(Worker::getWorkerRole).collect(Collectors.toList()));
    }

}
