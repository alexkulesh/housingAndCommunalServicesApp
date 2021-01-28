package by.grodno.pvt.site.housingAndCommunalServicesApp.repo;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestFormRepo extends JpaRepository<RequestForm, Integer> {
    List<RequestForm> findByUser(User user);
}
