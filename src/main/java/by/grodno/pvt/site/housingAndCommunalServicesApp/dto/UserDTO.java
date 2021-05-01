package by.grodno.pvt.site.housingAndCommunalServicesApp.dto;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.UserRole;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public class UserDTO {

	private Integer id;
	@Length(min = 3, max = 20)
	private String firstName;
	@Length(min = 3, max = 20)
	private String lastName;
	private Date birthDate;
	private String phoneNumber;
	private UserRole role;

}
