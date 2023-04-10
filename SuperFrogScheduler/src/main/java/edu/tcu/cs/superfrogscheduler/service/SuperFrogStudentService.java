package edu.tcu.cs.superfrogscheduler.service;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {

    private final SuperFrogStudentRepository superFrogStudentRepository;

    public SuperFrogStudentService(SuperFrogStudentRepository superFrogStudentRepository) {
        this.superFrogStudentRepository = superFrogStudentRepository;
    }

    public SuperFrogStudent findById(int superfrogId) {
        return this.superFrogStudentRepository.findById(superfrogId)
                .orElseThrow(()-> new ObjectNotFoundException("superfrog", superfrogId));
    }

    public List<SuperFrogStudent> findAll() {
        return this.superFrogStudentRepository.findAll();
    }

}
