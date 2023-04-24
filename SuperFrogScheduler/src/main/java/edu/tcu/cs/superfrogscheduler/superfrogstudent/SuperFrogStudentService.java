package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogStudentService {

    private final SuperFrogStudentRepository superFrogStudentRepository;

    private final SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository;

    private final IdWorker idWorker;

    public SuperFrogStudentService(SuperFrogStudentRepository superFrogStudentRepository, SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository, IdWorker idWorker) {
        this.superFrogStudentRepository = superFrogStudentRepository;
        this.superFrogAppearanceRequestRepository = superFrogAppearanceRequestRepository;
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

    public SuperFrogStudent update(int superFrogId, SuperFrogStudent updatedFrog){
       return this.superFrogStudentRepository.findById(superFrogId)
               .map(oldFrog -> {
                   oldFrog.setFirstName(updatedFrog.getFirstName());
                   oldFrog.setLastName(updatedFrog.getLastName());
                   return this.superFrogStudentRepository.save(oldFrog);
               }).orElseThrow(() -> new ObjectNotFoundException("superfrog", superFrogId ));


    }

    public void assignAppearanceRequest(Integer requestId, Integer superfrogId){
        // Find this artifact by Id from DB.
        SuperFrogAppearanceRequest requestToBeAssigned = this.superFrogAppearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));

        // Find this wizard by Id fromm DB.
        SuperFrogStudent superFrogStudent = this.superFrogStudentRepository.findById(superfrogId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrog", superfrogId));

        // Artifact assignment
        // We need to see if the artifact is already owned by some wizard.
        if (requestToBeAssigned.getStudent() != null) {
            requestToBeAssigned.getStudent().removeAppearanceRequest(requestToBeAssigned);
        }
        superFrogStudent.addAppearanceRequest(requestToBeAssigned);
    }

}
