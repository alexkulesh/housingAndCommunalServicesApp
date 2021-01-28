package by.grodno.pvt.site.housingAndCommunalServicesApp.service.impl;

import java.util.List;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.User;
import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkScale;
import by.grodno.pvt.site.housingAndCommunalServicesApp.repo.RequestFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.grodno.pvt.site.housingAndCommunalServicesApp.service.RequestFormService;
import org.springframework.beans.factory.InitializingBean;


@Service
@Transactional
public class JPARequestFormService implements RequestFormService, InitializingBean {
    @Autowired
    private RequestFormRepo repo;

    @Override
    public void addRequestForm(List<RequestForm> requestForms) {
        repo.saveAll(requestForms);
    }

    @Override
    public List<RequestForm> getRequestForms() {
        return repo.findAll();
    }

    @Override
    public void deleteRequestForm(Integer number) {
        repo.deleteById(number);
    }

    @Override
    public List<RequestForm> findByExample(RequestForm requestFormSample) {
        Example<RequestForm> exp = Example.of(requestFormSample,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repo.findAll(exp);
    }

    @Override
    public Page<RequestForm> getPage(Integer pageNum, Integer pageSize) {
        return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "user"));
    }

    @Override
    public List<RequestForm> findByUser(User user) {
        return repo.findByUser(user);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        RequestForm requestForm = new RequestForm(null, null, null, WorkScale.NONE, WorkScale.FOUR,  WorkScale.TWO);
        RequestForm requestForm2 = new RequestForm(null, null, null, WorkScale.FOUR, WorkScale.NONE,  WorkScale.TWO);
        RequestForm requestForm3 = new RequestForm(null, null, null, WorkScale.NONE, WorkScale.FOUR,  WorkScale.TWO);
        repo.save(requestForm);
        repo.save(requestForm2);
        repo.save(requestForm3);
    }

    @Override
    public RequestForm getRequestForm(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void saveRequestForm(RequestForm requestForm) {
        repo.save(requestForm);

    }
}
