package edu.tcu.cs.superfrogscheduler.superfrogrequest.converter;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.dto.SuperFrogAppearanceRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter implements Converter<SuperFrogAppearanceRequestDto, SuperFrogAppearanceRequest> {

    @Override
    public SuperFrogAppearanceRequest convert(SuperFrogAppearanceRequestDto source) {
        SuperFrogAppearanceRequest superFrogAppearanceRequest = new SuperFrogAppearanceRequest();
        superFrogAppearanceRequest.setRequestId(source.requestId());
        superFrogAppearanceRequest.setEventType(source.eventType());
        superFrogAppearanceRequest.setAddress(source.address());
        superFrogAppearanceRequest.setMileage(source.mileage());
        superFrogAppearanceRequest.setEventDate(source.eventDate());
        superFrogAppearanceRequest.setStartTime(source.startTime());
        superFrogAppearanceRequest.setEndTime(source.endTime());
        superFrogAppearanceRequest.setStatus(source.status());
        superFrogAppearanceRequest.setContactFirstName(source.contactFirstName());
        superFrogAppearanceRequest.setContactLastName(source.contactLastName());
        superFrogAppearanceRequest.setPhoneNumber(source.phoneNumber());
        superFrogAppearanceRequest.setEmail(source.email());
        superFrogAppearanceRequest.setTitle(source.title());
        superFrogAppearanceRequest.setNameOfOrg(source.nameOfOrg());
        superFrogAppearanceRequest.setDescription(source.description());
        superFrogAppearanceRequest.setOutsideOrgs(source.outsideOrgs());
        superFrogAppearanceRequest.setSpecialInstructions(source.specialInstructions());
        superFrogAppearanceRequest.setExpenses(source.expenses());
        return superFrogAppearanceRequest;
    }
}
