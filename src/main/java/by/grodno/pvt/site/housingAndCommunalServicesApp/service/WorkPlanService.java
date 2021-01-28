package by.grodno.pvt.site.housingAndCommunalServicesApp.service;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkPlan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkPlanService {
    List<WorkPlan> getWorkPlans();

    WorkPlan getWorkPlan(Integer id);

    void addWorkPlan(List<WorkPlan> workPlans);

    void saveWorkPlan(WorkPlan workPlan);

    void deleteWorkPlan(Integer number);

    List<WorkPlan> findByExample(WorkPlan workPlan);

    Page<WorkPlan> getPage(Integer pageNum, Integer pageSize);

    List<WorkPlan> findByRequestForm(RequestForm requestForm);
}
