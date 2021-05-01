package by.grodno.pvt.site.housingAndCommunalServicesApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.UserDTO;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.UserService;

@Controller
public class UserController {

	static public final Integer SIZE = 5;

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ConversionService convertionService;

	@GetMapping("/users")
	public String getAllUsers(@RequestParam(required = false, name = "pn") Integer pageNum,
							  @RequestParam(required = false, name = "sort") Sort.Direction sortDirection,
							  @RequestParam(required = false, name = "fieldName") String sortField,
							  @AuthenticationPrincipal UserDetails currentUser, Model model) {
		if (pageNum == null) {
			pageNum = Integer.valueOf(0);
		} else {
			pageNum -= 1;
		}

		Page<User> usersPage = userService.getUsersPage(pageNum, SIZE, sortField, sortDirection);

		List<UserDTO> users = usersPage.get().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());
		Integer userId = userRepo.findByEmail(currentUser.getUsername()).getId();

		model.addAttribute("users", users);
		model.addAttribute("currentUser",userService.getUser(userId).getId());
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", usersPage.getTotalPages());
		model.addAttribute("fieldName", sortField);
		model.addAttribute("sort", sortDirection);

		return "userList";
	}


	@GetMapping("/apis/v1/users")
	@ResponseBody
	public List<UserDTO> getAllUsers() {
		return userService.getUsers().stream().map(p -> convertionService.convert(p, UserDTO.class))
				.collect(Collectors.toList());
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	//for editing user's data from navigation bar
	@GetMapping("/editUserForm")
	public String editUserView(@Valid UserDTO userDTO, @AuthenticationPrincipal UserDetails currentUser, Model model){
		List<UserDTO> users = userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());
		Integer userId = userRepo.findByEmail(currentUser.getUsername()).getId();
		model.addAttribute("users", users);
		model.addAttribute("currentUser",userService.getUser(userId).getId());
		model.addAttribute("user", userService.getUser(userId));
		return "editUserView";
	}

	//for editing user's data from list
	@GetMapping("/users/edit/{id}")
	@PreAuthorize("@editUserVouter.checkUserId(authentication,#id)")
	public String editUserForm(@Valid UserDTO userDTO, @PathVariable Integer id, @AuthenticationPrincipal UserDetails currentUser, Model model) {
		List<UserDTO> users = userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());
		id = userRepo.findByEmail(currentUser.getUsername()).getId();
		model.addAttribute("users", users);
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("currentUser",userService.getUser(id).getId());
		return "editUserView";
	}

	@PostMapping("/users/edit/{id}")
	public String editUser(@PathVariable Integer id, @Valid UserDTO userDTO, @AuthenticationPrincipal UserDetails currentUser, BindingResult br, Model model) {

		if (br.hasErrors()) {
			model.addAttribute("userDTO", userDTO);
			return "editUserView";
		}

		User user = new User();
		user.setId(id);
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setBirthDate(userDTO.getBirthDate());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		userService.edit(userDTO);
		return "redirect:/";
	}

	@GetMapping("/deleteUserView")
	public String deleteUserView(@Valid UserDTO userDTO, @AuthenticationPrincipal UserDetails currentUser, BindingResult br, Model model){
		if (br.hasErrors()) {
			model.addAttribute("userDTO", userDTO);
			return "deleteUserView";
		}
		Integer userId = userRepo.findByEmail(currentUser.getUsername()).getId();
		model.addAttribute("user", userService.getUser(userId));
		return "deleteUserView";
	}


	@RequestMapping(path = "/users/deleteUser/{userId}")
	public String deleteUser(@PathVariable Integer userId, @AuthenticationPrincipal UserDetails currentUser, Model model){
		List<UserDTO> users = userService.getUsers().stream().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());
		userId = userRepo.findByEmail(currentUser.getUsername()).getId();
		model.addAttribute("users", users);
		model.addAttribute("currentUser",userService.getUser(userId).getId());
		model.addAttribute("user", userService.getUser(userId));
		model.addAttribute("userId", userId);
		userRepo.deleteById(userId);
		return "redirect:/";
	}

}
