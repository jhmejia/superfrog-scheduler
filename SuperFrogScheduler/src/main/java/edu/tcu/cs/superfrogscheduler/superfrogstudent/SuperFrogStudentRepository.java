package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, Integer> {

    List<SuperFrogStudent> findSuperFrogStudentByActive(boolean active);

    SuperFrogStudent findSuperFrogStudentByEmail(String email);

    @Query("SELECT COUNT(r) FROM SuperFrogAppearanceRequest r WHERE r.student.id = :superFrogId AND r.status = 'COMPLETED'")
    int countCompletedRequestsBySuperFrogId(Integer superFrogId);

}
