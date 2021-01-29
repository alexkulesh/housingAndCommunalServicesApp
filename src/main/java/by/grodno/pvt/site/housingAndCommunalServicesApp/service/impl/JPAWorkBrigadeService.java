package by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkBrigade;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Worker;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.WorkBrigadeRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.WorkersRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkBrigadeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JPAWorkBrigadeService implements WorkBrigadeService, InitializingBean {
    @Autowired
    private WorkBrigadeRepo repo;
    @Autowired
    private WorkersRepo workersRepo;
    @Override
    public void addWorkBrigade(List<WorkBrigade> workBrigades) {
        repo.saveAll(workBrigades);
    }

    @Override
    public List<WorkBrigade> getWorkBrigades() {
        return repo.findAll();
    }

    @Override
    public void deleteWorkBrigade(Integer number) {
        repo.deleteById(number);
    }

    @Override
    public List<WorkBrigade> findByExample(WorkBrigade workBrigade) {
        Example<WorkBrigade> exp = Example.of(workBrigade,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repo.findAll(exp);
    }

    @Override
    public Page<WorkBrigade> getPage(Integer pageNum, Integer pageSize) {
        return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "workBrigade"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public WorkBrigade getWorkBrigade(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveWorkBrigade(WorkBrigade workBrigade) {
        repo.save(workBrigade);

    }
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

    public void hireWorkers(Integer plumber, Integer electrician, Integer repairer, Date start,  Date end) {
        Worker findPlumber = workersRepo.findById(plumber).get();
        Worker findElectrician = workersRepo.findById(electrician).get();
        Worker findRepairer = workersRepo.findById(repairer).get();
        WorkBrigade workBrigade = new WorkBrigade();
        workBrigade.setPlumber(findPlumber);
        workBrigade.setElectrician(findElectrician);
        workBrigade.setRepairer(findRepairer);
        workBrigade.setWorkStartTime(start);
        workBrigade.setWorkEndTime(end);
        repo.save(workBrigade);
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
