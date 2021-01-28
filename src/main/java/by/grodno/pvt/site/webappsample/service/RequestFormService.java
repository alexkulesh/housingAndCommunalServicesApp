package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.RequestForm;
import by.grodno.pvt.site.webappsample.domain.User;
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
