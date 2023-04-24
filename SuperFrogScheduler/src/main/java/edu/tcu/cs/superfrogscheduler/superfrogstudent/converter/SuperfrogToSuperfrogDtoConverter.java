package edu.tcu.cs.superfrogscheduler.superfrogstudent.converter;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
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
               source.getLastName(),
               source.getEmail(),
               source.getPhoneNumber(),
               source.getAddress(),
               source.isActive());
    }
}
    
