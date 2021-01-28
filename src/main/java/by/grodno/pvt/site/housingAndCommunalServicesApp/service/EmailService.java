package by.grodno.pvt.site.housingAndCommunalServicesApp.service;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;

public interface EmailService {
    void sendPersonActivationEmail(User user);
}
