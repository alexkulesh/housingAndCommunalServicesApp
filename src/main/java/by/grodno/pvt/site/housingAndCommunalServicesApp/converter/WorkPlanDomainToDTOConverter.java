package by.grodno.pvt.site.housingAndCommunalServicesApp.converter;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.WorkPlan;

import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.WorkPlanDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WorkPlanDomainToDTOConverter  implements Converter<WorkPlan, WorkPlanDTO> {
    @Override
    public WorkPlanDTO convert(WorkPlan source) {
        WorkPlanDTO workPlanDTO = new  WorkPlanDTO();
        workPlanDTO.setId(source.getId());
        workPlanDTO.setRequestForm(source.getRequestForm());
        workPlanDTO.setWorkBrigade(source.getWorkBrigade());
        workPlanDTO.setWork_completed(source.getWork_completed());
        return workPlanDTO;
    }
}
