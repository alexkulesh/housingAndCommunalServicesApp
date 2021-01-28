package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.RequestForm;
import by.grodno.pvt.site.webappsample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestFormRepo extends JpaRepository<RequestForm, Integer> {
    List<RequestForm> findByUser(User user);
}
