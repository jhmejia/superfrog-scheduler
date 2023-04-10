package edu.tcu.cs.superfrogscheduler.superfrog.converter;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperfrogDto;
import org.springframework.core.convert.converter.Converter;
import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;

public class SuperfrogDtoToSuperfrogConverter implements Converter<SuperfrogDto, Superfrog>{
    @Override
    public Superfrog convert(SuperfrogDto source) {
        Superfrog superfrog = new Superfrog();
        superfrog.setId(source.id());
        superfrog.setUsername(source.username());
        superfrog.setFirstName(source.firstName());
        superfrog.setLastName(source.lastName());
        superfrog.setEmail(source.email());
        superfrog.setPassword(source.password());
        superfrog.setActive(source.active());
        return superfrog;
    }
    
}
