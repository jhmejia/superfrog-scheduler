package edu.tcu.cs.superfrogscheduler.superfrog.converter;
import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperfrogDto;
import org.springframework.core.convert.converter.Converter;

public class SuperfrogDtoToSuperfrogConverter implements Converter<SuperfrogDto, SuperFrogStudent>{
    @Override
    public SuperFrogStudent convert(SuperfrogDto source) {
//        Superfrog superfrog = new Superfrog();
//        superfrog.setId(source.id());
//        superfrog.setUsername(source.username());
//        superfrog.setFirstName(source.firstName());
//        superfrog.setLastName(source.lastName());
//        superfrog.setEmail(source.email());
//        superfrog.setPassword(source.password());
//        superfrog.setActive(source.active());
//        return superfrog;
        return null;
    }
    
}
