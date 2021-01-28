package by.grodno.pvt.site.housingAndCommunalServicesApp.repo;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPlanRepo extends JpaRepository<WorkPlan, Integer> {
    List<WorkPlan> findByRequestForm(RequestForm requestForm);
}
