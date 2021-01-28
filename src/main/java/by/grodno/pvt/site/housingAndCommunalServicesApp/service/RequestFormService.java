package by.grodno.pvt.site.housingAndCommunalServicesApp.service;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RequestFormService {
    List<RequestForm> getRequestForms();

    RequestForm getRequestForm(Integer id);

    void addRequestForm(List<RequestForm> requestForms);

    void saveRequestForm(RequestForm requestForm);

    void deleteRequestForm(Integer number);

    List<RequestForm> findByExample(RequestForm requestFormSample);

    Page<RequestForm> getPage(Integer pageNum, Integer pageSize);

    List<RequestForm> findByUser(User user);
}
