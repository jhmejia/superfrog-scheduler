package edu.tcu.cs.superfrogscheduler.datainitializer;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.domain.RequestStatus;
import edu.tcu.cs.superfrogscheduler.domain.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private SuperFrogAppearanceRequestRepository requestRepository;

    private SuperFrogStudentRepository studentRepository;


    public DBDataInitializer(SuperFrogAppearanceRequestRepository requestRepository, SuperFrogStudentRepository studentRepository) {
        this.requestRepository = requestRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SuperFrogStudent student1 = new SuperFrogStudent("Jane", "Smith", 1001);
        SuperFrogStudent student2 = new SuperFrogStudent("John", "Doe", 1004);
        SuperFrogStudent student3 = new SuperFrogStudent("Tim", "Johnson", 1012);

        this.studentRepository.save(student1);
        this.studentRepository.save(student2);
        this.studentRepository.save(student3);

        SuperFrogAppearanceRequest request1 = new SuperFrogAppearanceRequest(
                5,
                EventType.TCU,
                "Event address 1",
                1.4,
                LocalDate.of(2023, 4, 6),
                LocalTime.of(13, 0),
                LocalTime.of(15, 30),
                RequestStatus.COMPLETED,
                student1);
        SuperFrogAppearanceRequest request2 = new SuperFrogAppearanceRequest(
                6,
                EventType.NONPROFIT,
                "Event address 2",
                2.0,
                LocalDate.of(2023, 4, 9),
                LocalTime.of(9, 0),
                LocalTime.of(12, 0),
                RequestStatus.COMPLETED,
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
                student1);
        SuperFrogAppearanceRequest request4 = new SuperFrogAppearanceRequest(
                16,
                EventType.PRIVATE,
                "Event address 4",
                18.0,
                LocalDate.of(2023, 4, 17),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                RequestStatus.COMPLETED,
                student2);
        SuperFrogAppearanceRequest request5 = new SuperFrogAppearanceRequest(
                17,
                EventType.NONPROFIT,
                "Event address 5",
                25.0,
                LocalDate.of(2023, 4, 19),
                LocalTime.of(14, 30),
                LocalTime.of(15, 30),
                RequestStatus.COMPLETED,
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
                student3);

        this.requestRepository.save(request1);
        this.requestRepository.save(request2);
        this.requestRepository.save(request3);
        this.requestRepository.save(request4);
        this.requestRepository.save(request5);
        this.requestRepository.save(request6);
        this.requestRepository.save(request7);
    }

}
