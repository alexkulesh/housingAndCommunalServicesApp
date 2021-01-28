package by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl;

import java.util.List;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkPlan;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.WorkPlanRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.WorkPlanService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JPAWorkPlanService implements WorkPlanService, InitializingBean{
    @Autowired
    private WorkPlanRepo repo;

    @Override
    public void addWorkPlan(List<WorkPlan> workPlans) {
        repo.saveAll(workPlans);
    }

    @Override
    public List<WorkPlan> getWorkPlans() {
        return repo.findAll();
    }

    @Override
    public void deleteWorkPlan(Integer number) {
        repo.deleteById(number);
    }

    @Override
    public List<WorkPlan> findByExample(WorkPlan workPlanSample) {
        Example<WorkPlan> exp = Example.of(workPlanSample,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repo.findAll(exp);
    }

    @Override
    public Page<WorkPlan> getPage(Integer pageNum, Integer pageSize) {
        return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "firstName"));
    }

    @Override
    public List<WorkPlan> findByRequestForm(RequestForm requestForm) {
        return repo.findByRequestForm(requestForm);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        WorkPlan workPlan = new WorkPlan(null, null, null, null );
        WorkPlan workPlan2 = new WorkPlan(null, null, null, null);
        WorkPlan workPlan3 = new WorkPlan(null, null, null, null);
        repo.save(workPlan);
        repo.save(workPlan2);
        repo.save(workPlan3);
    }

    @Override
    public WorkPlan getWorkPlan(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveWorkPlan(WorkPlan workPlan) {
        repo.save(workPlan);

    }
}
