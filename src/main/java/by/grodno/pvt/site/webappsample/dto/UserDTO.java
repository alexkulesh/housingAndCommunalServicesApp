package by.grodno.pvt.site.webappsample.dto;

import by.grodno.pvt.site.webappsample.domain.UserRole;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDTO {

	private Integer id;
	@Length(min = 3, max = 20)
	private String firstName;
	@Length(min = 3, max = 20)
	private String lastName;
	private String phoneNumber;
	private UserRole role;

}
