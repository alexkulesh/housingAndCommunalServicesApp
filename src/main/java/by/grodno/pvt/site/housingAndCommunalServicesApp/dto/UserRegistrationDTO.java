package by.grodno.pvt.site.housingAndCommunalServicesApp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
public class UserRegistrationDTO {
    @Length(min = 3, max = 20)
    private String firstName;

    @Length(min = 3, max = 20)
    private String lastName;
    private Date birthDate;
    @Email
    private String email;

    @Length(min = 5, max = 20)
    private String password;

    private String phoneNumber;
}
