package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkersRepo extends JpaRepository<Worker, Integer> {
    List<Worker> findByIsBusy(Boolean busy);
}
