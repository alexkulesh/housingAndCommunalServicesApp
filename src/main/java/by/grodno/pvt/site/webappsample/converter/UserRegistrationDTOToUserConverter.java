package by.grodno.pvt.site.webappsample.converter;

import java.util.Collections;
import java.util.Date;

import by.grodno.pvt.site.webappsample.domain.Credentials;
import by.grodno.pvt.site.webappsample.domain.UserRole;
import by.grodno.pvt.site.webappsample.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import by.grodno.pvt.site.webappsample.dto.UserRegistrationDTO;

@Component
public class UserRegistrationDTOToUserConverter implements Converter<UserRegistrationDTO, User> {
    @Override
    public User convert(UserRegistrationDTO source) {
        User user = new User();
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());
        user.setRole(UserRole.TENANT);
        Credentials creds = new Credentials(null,  source.getPassword(), new Date(), false);
        user.setCredentials(Collections.singletonList(creds));
        return user;
    }
}
