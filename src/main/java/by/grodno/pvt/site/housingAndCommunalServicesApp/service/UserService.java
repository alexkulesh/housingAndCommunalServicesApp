package by.grodno.pvt.site.housingAndCommunalServicesApp.service;

import java.util.List;
import java.util.Optional;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;


public interface UserService {

	List<User> getUsers();
	
	User getUser(Integer id);
	
	void addUser(List<User> users);

	void saveUser(User user);
	
	void deleteUser(Integer number);

	Optional<User> findByEmail(String email);

	void activateUser(Integer id);

	Page<User> getUsersPage(Integer pageNum, Integer size, String fieldName, Sort.Direction direction);

	/**
	 * Updates FirstName and LastName only.
	 *
	 * @param userDTO
	 */
	void edit(UserDTO userDTO);
}
