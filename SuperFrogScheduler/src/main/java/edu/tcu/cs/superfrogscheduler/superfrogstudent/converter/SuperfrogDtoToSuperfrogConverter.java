package edu.tcu.cs.superfrogscheduler.superfrogstudent.converter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
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
