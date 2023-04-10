package edu.tcu.cs.superfrogscheduler.superfrog.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperfrogDto;
import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
@Component
public class SuperfrogToSuperfrogDtoConverter  implements Converter<Superfrog, SuperfrogDto> {

    public SuperfrogToSuperfrogDtoConverter() {
    }

    @Override
    public SuperfrogDto convert(Superfrog source) {
        return new SuperfrogDto(source.getId(), source.getUsername(), source.getFirstName(), source.getLastName(), source.getEmail(), source.getPassword(), source.isActive());
    }
}
    
