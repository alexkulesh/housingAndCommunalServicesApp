package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.WorkBrigade;
import by.grodno.pvt.site.webappsample.domain.Worker;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorkBrigadeService {
    List<WorkBrigade> getWorkBrigades();

    WorkBrigade getWorkBrigade(Integer id);

    void addWorkBrigade(List<WorkBrigade> workBrigades);

    void saveWorkBrigade(WorkBrigade workBrigade);

    void deleteWorkBrigade(Integer number);

    List<WorkBrigade> findByExample(WorkBrigade workBrigade);

    Page<WorkBrigade> getPage(Integer pageNum, Integer pageSize);

    public List<Worker> freeWorkers();

    void saveWorkers(List<Worker> workers);
}
