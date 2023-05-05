package edu.tcu.cs.superfrogscheduler.system;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.RequestStatus;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUser;
import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private SuperFrogAppearanceRequestRepository requestRepository;

    private SuperFrogStudentRepository studentRepository;

    private final SuperFrogUserService superFrogUserService;


    public DBDataInitializer(SuperFrogAppearanceRequestRepository requestRepository, SuperFrogStudentRepository studentRepository, SuperFrogUserService superFrogUserService) {
        this.requestRepository = requestRepository;
        this.studentRepository = studentRepository;
        this.superFrogUserService = superFrogUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        SuperFrogStudent student1 = new SuperFrogStudent("Jane", "Smith", 1001, "jane.smith@tcu.edu", "1234567890", "address1", true);
        SuperFrogStudent student2 = new SuperFrogStudent("John", "Doe", 1004, "john.doe@tcu.edu", "1234567890", "address2", true);
        SuperFrogStudent student3 = new SuperFrogStudent("Tim", "Johnson", 1012, "tim.johnson@tcu.edu", "1234567890", "address3", true);
        SuperFrogStudent student5 = new SuperFrogStudent("Kurisu", "Makise", 1002, "kurisu.makise@tcu.edu", "1234567890", "address4", true);

        this.studentRepository.save(student1);
        this.studentRepository.save(student2);
        this.studentRepository.save(student3);
        this.studentRepository.save(student5);

        SuperFrogAppearanceRequest request1 = new SuperFrogAppearanceRequest(
                5,
                EventType.TCU,
                "Event address 1",
                1.4,
                LocalDate.of(2023, 4, 6),
                LocalTime.of(13, 0),
                LocalTime.of(15, 30),
                RequestStatus.PENDING,
                "FirstName",
                "LastName",
                "1234567890",
                "Email",
                "Title",
                "Org",
                "Desc",
                "Special",
                "OutsideOrg",
                "Expenses",
                student1);
        SuperFrogAppearanceRequest request2 = new SuperFrogAppearanceRequest(
                6,
                EventType.PUBLIC,
                "Event address 2",
                2.0,
                LocalDate.of(2023, 4, 9),
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                RequestStatus.COMPLETED,
                "FirstName",
                "LastName",
                "1234567890",
                "Email",
                "Title",
                "Org",
                "Desc",
                "Special",
                "OutsideOrg",
                "Expenses",
                student1);
        SuperFrogAppearanceRequest request3 = new SuperFrogAppearanceRequest(
                12,
                EventType.PRIVATE,
                "Event address 3",
                99.0,
                LocalDate.of(2023, 4, 16),
                LocalTime.of(19, 30),
                LocalTime.of(21, 30),
                RequestStatus.COMPLETED,
                "FirstName",
                "LastName",
                "1234567890",
                "Email",
                "Title",
                "Org",
                "Desc",
                "Special",
                "OutsideOrg",
                "Expenses",
                student1);
        SuperFrogAppearanceRequest request4 = new SuperFrogAppearanceRequest(
                16,
                EventType.PRIVATE,
                "Tucker Technology Center",
                18.0,
                LocalDate.of(2023, 4, 17),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                RequestStatus.COMPLETED,
                "Bingyang",
                "Wei",
                "8172577166",
                "bwei@tcu.edu",
                "TCU Women in Computing",
                "WICS (MAGICS) Club",
                "We are going to have a meeting",
                "We need a frog to come to our meeting and take a picture with us.",
                "No",
                "No",
                student2);
        SuperFrogAppearanceRequest request5 = new SuperFrogAppearanceRequest(
                17,
                EventType.PUBLIC,
                "SuperFrog Statue",
                25.0,
                LocalDate.of(2023, 4, 19),
                LocalTime.of(14, 30),
                LocalTime.of(15, 30),
                RequestStatus.APPROVED,
                "Spike",
                "Spiegel",
                "1234567890",
                "spiegel@bebop.org",
                "Bounty Hunter Meet and Greet",
                "Bebop",
                "Desc",
                "Ed and Ein will be there!",
                "OutsideOrg",
                "Expenses",
                student2);
        SuperFrogAppearanceRequest request6 = new SuperFrogAppearanceRequest(
                20,
                EventType.PRIVATE,
                "Event address 6",
                50.0,
                LocalDate.of(2023, 4, 22),
                LocalTime.of(9, 30),
                LocalTime.of(14, 30),
                RequestStatus.COMPLETED,
                "Nagito",
                "Komaeda",
                "7777777777",
                "nkomaeda@tcu.edu",
                "Ultimate Lucky Student Class 77-B",
                "Hope's Peak Academy",
                "Birthday Party",
                "Despair",
                "OutsideOrg",
                "Expenses",
                student2);
        SuperFrogAppearanceRequest request7 = new SuperFrogAppearanceRequest(
                22,
                EventType.TCU,
                "Event address 7",
                0.6,
                LocalDate.of(2023, 4, 26),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                RequestStatus.COMPLETED,
                "Yu",
                "Narukami",
                "1234567890",
                "yunarukami@yasogamihigh.edu",
                "Investigation Team Meeting",
                "Yasogami High School",
                "Investigation Team Meeting",
                "Bring Teddie",
                "OutsideOrg",
                "Expenses",
                student3);
        SuperFrogAppearanceRequest request8 = new SuperFrogAppearanceRequest(
                23,
                EventType.PUBLIC,
                "Event address 8",
                0.0,
                LocalDate.of(2023, 4, 27),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                RequestStatus.PENDING,
                "Makoto",
                "Naegi",
                "11037",
                "mnaegi@hopespeak.org",
                "Ultimate Lucky Student Celebration",
                "Hope's Peak Academy",
                "Ultimate Lucky Student Celebration",
                "Bring Hope",
                "Hope's Peak Academy",
                "Expenses",
                student3);

        this.requestRepository.save(request1);
        this.requestRepository.save(request2);
        this.requestRepository.save(request3);
        this.requestRepository.save(request4);
        this.requestRepository.save(request5);
        this.requestRepository.save(request6);
        this.requestRepository.save(request7);
        this.requestRepository.save(request8);

        SuperFrogUser u1 = new SuperFrogUser();
        u1.setId(1);
        u1.setUsername("spiritdirector@tcu.edu");
        u1.setPassword("spiritdirector");
        u1.setActive(true);
        u1.setRoles("spiritdirector superfrogstudent");

        SuperFrogUser u2 = new SuperFrogUser();
        u2.setId(2);
        u2.setUsername("jane.smith@tcu.edu");
        u2.setPassword("superfrogstudent");
        u2.setActive(true);
        u2.setRoles("superfrogstudent");

        SuperFrogUser u3 = new SuperFrogUser();
        u3.setId(3);
        u3.setUsername("john.doe@tcu.edu");
        u3.setPassword("superfrogstudent");
        u3.setActive(true);
        u3.setRoles("superfrogstudent");

        SuperFrogUser u4 = new SuperFrogUser();
        u4.setId(4);
        u4.setUsername("tim.johnson@tcu.edu");
        u4.setPassword("superfrogstudent");
        u4.setActive(true);
        u4.setRoles("superfrogstudent");

        SuperFrogUser u5 = new SuperFrogUser();
        u5.setId(5);
        u5.setUsername("kurisu.makise@tcu.edu");
        u5.setPassword("superfrogstudent");
        u5.setActive(true);
        u5.setRoles("superfrogstudent");

        this.superFrogUserService.save(u1);
        this.superFrogUserService.save(u2);
        this.superFrogUserService.save(u3);
        this.superFrogUserService.save(u4);
        this.superFrogUserService.save(u5);

    }

}
