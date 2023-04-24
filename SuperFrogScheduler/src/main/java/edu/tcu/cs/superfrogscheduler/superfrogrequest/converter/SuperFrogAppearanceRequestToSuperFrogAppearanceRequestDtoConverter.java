package edu.tcu.cs.superfrogscheduler.superfrogrequest.converter;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrog.converter.SuperfrogToSuperfrogDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.dto.SuperFrogAppearanceRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter implements Converter<SuperFrogAppearanceRequest, SuperFrogAppearanceRequestDto> {


    private final SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter;
    public SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter(SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter) {
        this.superfrogToSuperfrogDtoConverter = superfrogToSuperfrogDtoConverter;
    }

    @Override
    public SuperFrogAppearanceRequestDto convert(SuperFrogAppearanceRequest source) {
        return new SuperFrogAppearanceRequestDto(
                source.getRequestId(),
                source.getEventType(),
                source.getAddress(),
                source.getMileage(),
                source.getEventDate(),
                source.getStartTime(),
                source.getEndTime(),
                source.getStatus(),
                source.getStudent() != null ? this.superfrogToSuperfrogDtoConverter.convert(source.getStudent()) : null);
    }
}