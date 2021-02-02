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
public class JPAUserService implements UserService{

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
