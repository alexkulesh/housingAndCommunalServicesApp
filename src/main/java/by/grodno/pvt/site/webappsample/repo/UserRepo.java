package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
