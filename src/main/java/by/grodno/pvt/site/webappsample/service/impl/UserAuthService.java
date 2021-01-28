package by.grodno.pvt.site.webappsample.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import by.grodno.pvt.site.webappsample.domain.Credentials;
import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return service.findByEmail(username).map(userFromBd -> {
            Optional<Credentials> findAny = userFromBd.getCredentials().stream().filter(Credentials::getActive)
                    .findAny();
            String password = findAny.map(Credentials::getPassword).orElseThrow(() -> new UserNotFoundException());

            return new org.springframework.security.core.userdetails.User(userFromBd.getEmail(), password, toAuthorities(userFromBd));
        }).orElse(null);
    }


    private Collection<? extends GrantedAuthority> toAuthorities(User findByUserName) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + findByUserName.getRole()));
    }
}
