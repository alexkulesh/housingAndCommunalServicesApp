package by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkBrigade;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Worker;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.WorkBrigadeRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.WorkersRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkBrigadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WorkerHireService {
    @Autowired
    private WorkBrigadeRepo repo;
    @Autowired
    private WorkBrigadeService service;
    @Autowired
    private WorkersRepo workersRepo;

    public List<Worker> freeWorkers() {
        return workersRepo.findByIsBusy(false);
    };

    public List<Worker> busyPlumbers() {
        List<WorkBrigade> findByWorkEndTimeBefore = repo.findByWorkEndTimeBefore(new Date());
        return findByWorkEndTimeBefore.stream().map(WorkBrigade::getPlumber).collect(Collectors.toList());
    };
    public List<Worker> busyElectricians() {
        List<WorkBrigade> findByWorkEndTimeBefore = repo.findByWorkEndTimeBefore(new Date());
        return findByWorkEndTimeBefore.stream().map(WorkBrigade::getElectrician).collect(Collectors.toList());
    };
    public List<Worker> busyRepairers() {
        List<WorkBrigade> findByWorkEndTimeBefore = repo.findByWorkEndTimeBefore(new Date());
        return findByWorkEndTimeBefore.stream().map(WorkBrigade::getRepairer).collect(Collectors.toList());
    };

    public void hireWorkers(Integer plumber, Integer electrician, Integer repairer) {
        Worker findPlumber = workersRepo.findById(plumber).get();
        Worker findElectrician = workersRepo.findById(electrician).get();
        Worker findRepairer = workersRepo.findById(repairer).get();
        findPlumber.setIsBusy(true);
        findElectrician.setIsBusy(true);
        findRepairer.setIsBusy(true);
        workersRepo.save(findPlumber);
        workersRepo.save(findElectrician);
        workersRepo.save(findRepairer);
    }


    public void saveWorkers(List<Worker> busy) {
        workersRepo.saveAll(busy);
    }
}
