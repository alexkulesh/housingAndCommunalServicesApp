package by.grodno.pvt.site.webappsample.repo;

import by.grodno.pvt.site.webappsample.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CredentialRepo extends JpaRepository<Credentials, Integer> {

}
