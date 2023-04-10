package edu.tcu.cs.superfrogscheduler.superfrog.converter;
import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperFrogStudentDto;
import org.springframework.core.convert.converter.Converter;

public class SuperfrogDtoToSuperfrogConverter implements Converter<SuperFrogStudentDto, SuperFrogStudent>{
    @Override
    public SuperFrogStudent convert(SuperFrogStudentDto source) {
        SuperFrogStudent superFrogStudent = new SuperFrogStudent();
        superFrogStudent.setId(source.id());
        superFrogStudent.setFirstName(source.firstName());
        superFrogStudent.setLastName(source.lastName());
//        superfrog.setUsername(source.username());
//        superfrog.setEmail(source.email());
//        superfrog.setPassword(source.password());
//        superfrog.setActive(source.active());
        return superFrogStudent;
    }
    
}
