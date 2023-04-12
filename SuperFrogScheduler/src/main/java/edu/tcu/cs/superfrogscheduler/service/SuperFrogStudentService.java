package edu.tcu.cs.superfrogscheduler.service;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {

    private final SuperFrogStudentRepository superFrogStudentRepository;

    private final IdWorker idWorker;

    public SuperFrogStudentService(SuperFrogStudentRepository superFrogStudentRepository, IdWorker idWorker) {
        this.superFrogStudentRepository = superFrogStudentRepository;
        this.idWorker = idWorker;
    }

    public SuperFrogStudent findById(int superfrogId) {
        return this.superFrogStudentRepository.findById(superfrogId)
                .orElseThrow(()-> new ObjectNotFoundException("superfrog", superfrogId));
    }

    public List<SuperFrogStudent> findAll() {
        return this.superFrogStudentRepository.findAll();
    }

    public SuperFrogStudent save(SuperFrogStudent newSuperFrogStudent) {
        newSuperFrogStudent.setId((int) idWorker.nextId());
        return this.superFrogStudentRepository.save(newSuperFrogStudent);
    }

    public SuperFrogStudent update(int superfrogId, SuperFrogStudent updatedFrog){
       SuperFrogStudent oldFrog= this.superFrogStudentRepository.findById(superfrogId).get();
       oldFrog.setFirstName(updatedFrog.getFirstName);
       oldFrog.setLastName(updatedFrog.getLastName);
       oldFrog.setId(updatedFrog.getId);
       SuperFrogStudent updateFrog = this.superFrogStudentRepository.save(oldFrog);
       return updateFrog;
    }


}
