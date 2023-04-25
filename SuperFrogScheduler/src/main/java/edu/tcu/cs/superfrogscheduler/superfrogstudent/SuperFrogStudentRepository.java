package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, Integer> {

    List<SuperFrogStudent> findSuperFrogStudentByActive(boolean active);

    SuperFrogStudent findSuperFrogStudentByEmail(String email);
}
