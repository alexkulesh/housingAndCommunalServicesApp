package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.RequestForm;
import by.grodno.pvt.site.webappsample.domain.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPlanRepo extends JpaRepository<WorkPlan, Integer> {
    List<WorkPlan> findByRequestForm(RequestForm requestForm);
}
