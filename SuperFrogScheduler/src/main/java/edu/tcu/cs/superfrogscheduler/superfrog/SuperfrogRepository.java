package edu.tcu.cs.superfrogscheduler.superfrog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperfrogRepository extends JpaRepository<Superfrog, Integer> {
}
