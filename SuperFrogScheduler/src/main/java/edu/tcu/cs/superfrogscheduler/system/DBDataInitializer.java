package edu.tcu.cs.superfrogscheduler.system;

import edu.tcu.cs.superfrogscheduler.superfrog.Superfrog;
import edu.tcu.cs.superfrogscheduler.superfrog.SuperfrogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBDataInitializer implements CommandLineRunner {


    private final SuperfrogRepository superfrogRepository;

    public DBDataInitializer(SuperfrogRepository superfrogRepository) {
        this.superfrogRepository = superfrogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Superfrog s1 = new Superfrog();
        s1.setId(1);
        s1.setUsername("superfrog1");
        s1.setFirstName("John");
        s1.setLastName("Mejyen");
        s1.setEmail("example@gmail.com");
        s1.setPassword("password");
        s1.setActive(true);

        Superfrog s2 = new Superfrog();
        s2.setId(2);
        s2.setUsername("superfrog2");
        s2.setFirstName("Joey");
        s2.setLastName("Quinn");
        s2.setEmail("iamacat@gmail.com");
        s2.setPassword("password2");
        s2.setActive(true);

        Superfrog s3 = new Superfrog();
        s3.setId(3);
        s3.setUsername("superfrog3");
        s3.setFirstName("Ethan");
        s3.setLastName("Edinboro");
        s3.setEmail("jo@gmail.com");
        s3.setPassword("password3");
        s3.setActive(false);

        superfrogRepository.save(s1);
        superfrogRepository.save(s2);
        superfrogRepository.save(s3);

    }
}
