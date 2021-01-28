package by.grodno.pvt.site.webappsample.converter;

import by.grodno.pvt.site.webappsample.domain.RequestForm;
import by.grodno.pvt.site.webappsample.dto.RequestFormDTO;
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
        requestFormDTO.setHeatSupplyWorkScale(source.getHeatSupplyWorkScale());
        requestFormDTO.setPowerSupplyWorkScale(source.getPowerSupplyWorkScale());
        requestFormDTO.setWaterSupplyWorkScale(source.getWaterSupplyWorkScale());
        return requestFormDTO;
    }
}
