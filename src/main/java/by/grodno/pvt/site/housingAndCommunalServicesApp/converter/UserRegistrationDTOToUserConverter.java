package by.grodno.pvt.site.housingAndCommunalServicesApp.converter;

import java.util.Collections;
import java.util.Date;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.Credentials;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.UserRole;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.UserRegistrationDTO;

@Component
public class UserRegistrationDTOToUserConverter implements Converter<UserRegistrationDTO, User> {
    @Override
    public User convert(UserRegistrationDTO source) {
        User user = new User();
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setBirthDate(source.getBirthDate());
        user.setEmail(source.getEmail());
        user.setRole(UserRole.TENANT);
        user.setPhoneNumber(source.getPhoneNumber());
        Credentials creds = new Credentials(null,  source.getPassword(), new Date(), false);
        user.setCredentials(Collections.singletonList(creds));
        return user;
    }
}
