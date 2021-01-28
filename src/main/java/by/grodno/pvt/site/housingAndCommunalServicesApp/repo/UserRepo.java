package by.grodno.pvt.site.housingAndCommunalServicesApp.repo;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
