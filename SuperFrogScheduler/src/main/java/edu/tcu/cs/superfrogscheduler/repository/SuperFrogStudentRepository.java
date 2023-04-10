package edu.tcu.cs.superfrogscheduler.repository;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperFrogStudentRepository extends JpaRepository<SuperFrogStudent, Integer> {
}
