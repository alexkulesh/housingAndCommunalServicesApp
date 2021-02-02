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
public class JPAWorkBrigadeService implements WorkBrigadeService {
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
    public WorkBrigade getWorkBrigade(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveWorkBrigade(WorkBrigade workBrigade) {
        repo.save(workBrigade);

    }

}
