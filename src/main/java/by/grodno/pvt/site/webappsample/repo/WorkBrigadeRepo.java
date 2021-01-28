package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.WorkBrigade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WorkBrigadeRepo extends JpaRepository<WorkBrigade, Integer> {

    List<WorkBrigade> findByWorkEndTimeBefore(Date date);

    List<WorkBrigade> findByWorkEndTimeBeforeAndIsBusy(Date date, Boolean b);


}
