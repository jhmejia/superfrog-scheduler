package edu.tcu.cs.superfrogscheduler.superfrog.converter;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperfrogDto;

@Component
public class SuperfrogToSuperfrogDtoConverter  implements Converter<SuperFrogStudent, SuperfrogDto> {

    public SuperfrogToSuperfrogDtoConverter() {
    }

    @Override
    public SuperfrogDto convert(SuperFrogStudent source) {
        return null;
       // return new SuperfrogDto(source.getId(), source.getUsername(), source.getFirstName(), source.getLastName(), source.getEmail(), source.getPassword(), source.isActive());
    }
}
    
