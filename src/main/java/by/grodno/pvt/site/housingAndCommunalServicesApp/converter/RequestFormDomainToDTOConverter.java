package by.grodno.pvt.site.housingAndCommunalServicesApp.converter;

import by.grodno.pvt.site.housingAndCommunalServicesApp.domain.RequestForm;
import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.RequestFormDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestFormDomainToDTOConverter implements Converter<RequestForm, RequestFormDTO> {
    @Override
    public RequestFormDTO convert(RequestForm source) {
        RequestFormDTO requestFormDTO = new RequestFormDTO();
        requestFormDTO.setId(source.getId());
        requestFormDTO.setCompletionDate(source.getCompletionDate());
        requestFormDTO.setUser(source.getUser());
        requestFormDTO.setWaterSupplyWorkScale(source.getWaterSupplyWorkScale());
        requestFormDTO.setPowerSupplyWorkScale(source.getPowerSupplyWorkScale());
        requestFormDTO.setRepairWorkScale(source.getRepairWorkScale());
        return requestFormDTO;
    }
}
