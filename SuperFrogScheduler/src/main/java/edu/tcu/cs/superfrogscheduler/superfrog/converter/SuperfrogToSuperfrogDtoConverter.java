package edu.tcu.cs.superfrogscheduler.superfrog.converter;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperFrogStudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SuperfrogToSuperfrogDtoConverter  implements Converter<SuperFrogStudent, SuperFrogStudentDto> {

    public SuperfrogToSuperfrogDtoConverter() {
    }

    @Override
    public SuperFrogStudentDto convert(SuperFrogStudent source) {
       return new SuperFrogStudentDto(
               source.getId(),
               source.getFirstName(),
               source.getLastName());
//               source.getUsername(),
//               source.getEmail(),
//               source.getPassword(),
//               source.isActive());
    }
}
    
