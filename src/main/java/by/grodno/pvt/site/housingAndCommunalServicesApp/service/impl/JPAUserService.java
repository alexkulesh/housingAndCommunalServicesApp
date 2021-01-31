package by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Credentials;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.UserRole;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.UserDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.exception.UserNotFoundException;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.CredentialRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.EmailService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.UserRepo;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.UserService;

@Service
@Transactional
public class JPAUserService implements UserService, InitializingBean{

	@Autowired
	private UserRepo repo;

	@Autowired
	private CredentialRepo credRepo;

	@Autowired
	private EmailService emailService;

	@Override
	public void addUser(List<User> userList) {
		repo.saveAll(userList);
	}

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public void deleteUser(Integer number) {
		repo.deleteById(number);
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		User user = new User(null, "Maxim", "Naumovich","+375334000100", "maximnaum@gmail.com", new Date(), UserRole.DISPATCHER,  null);
		User user2 = new User(null, "Sasha", "Siaroga","+375337000100", "sashakulesh@gmail.com", new Date(),  UserRole.WORKER,  null);
		User user3 = new User(null, "Siaroga", "Sasha","+375339000100" ,"sergeykulesh@gmail.com", new Date(),  UserRole.TENANT,  null);
		User user4 = new User(null, "Laxim", "3aumovich","+375335111100", "laximnaum@gmail.com", new Date(),  UserRole.DISPATCHER,  null);
		User user5 = new User(null, "Saxim", "4aumovich", "+375332000100", "saximnaum@gmail.com", new Date(),  UserRole.DISPATCHER,  null);
		User user6 = new User(null, "Caxim", "5aumovich", "+375336	000100", "caximnaum@gmail.com", new Date(), UserRole.DISPATCHER,  null);
		Credentials credentials = new Credentials(null, "max", new Date(),true);
		Credentials credentials2 = new Credentials(null, "sasha", new Date(),true);
		Credentials credentials3 = new Credentials(null, "siaroga", new Date(),true);
		Credentials credentials4 = new Credentials(null, "max", new Date(),true);
		Credentials credentials5 = new Credentials(null, "sasha", new Date(),true);
		Credentials credentials6 = new Credentials(null, "siaroga", new Date(),true);
		user.setCredentials(Collections.singletonList(credentials));
		user2.setCredentials(Collections.singletonList(credentials2));
		user3.setCredentials(Collections.singletonList(credentials3));
		user4.setCredentials(Collections.singletonList(credentials4));
		user5.setCredentials(Collections.singletonList(credentials5));
		user6.setCredentials(Collections.singletonList(credentials6));
		repo.save(user);
		repo.save(user2);
		repo.save(user3);
		repo.save(user4);
		repo.save(user5);
		repo.save(user6);
	}

	@Override
	public User getUser(Integer id) {
		return repo.getOne(id);
	}

	@Override
	public void saveUser(User user) {
		repo.save(user);

	}
	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(repo.findByEmail(email));
	}

	@Override
	public void activateUser(Integer id) {
		Optional<User> findById = repo.findById(id);

		findById.map(person -> {
			Credentials next = person.getCredentials().iterator().next();
			next.setActive(true);
			credRepo.save(next);
			return person;
		}).orElseThrow(() -> new UserNotFoundException());

	}

	@Override
	public Page<User> getUsersPage(Integer pageNum, Integer size, String fieldName, Sort.Direction direction) {
		Pageable pagable;
		if (fieldName != null) {
			if (direction == null)
				direction = Sort.Direction.ASC;
			pagable = PageRequest.of(pageNum, size, direction, fieldName);
		} else {
			pagable = PageRequest.of(pageNum, size);
		}

		return repo.findAll(pagable);
	}

	@Override
	public void edit(UserDTO userDTO) {
		User findById = repo.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException());
		findById.setFirstName(userDTO.getFirstName());
		findById.setLastName(userDTO.getLastName());
		repo.save(findById);
	}


}
