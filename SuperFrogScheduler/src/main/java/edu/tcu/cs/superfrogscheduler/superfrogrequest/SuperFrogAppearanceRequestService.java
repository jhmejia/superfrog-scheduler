package edu.tcu.cs.superfrogscheduler.superfrogrequest;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogAppearanceRequestService {
    private final SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository;

    public SuperFrogAppearanceRequestService(SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository) {
        this.superFrogAppearanceRequestRepository = superFrogAppearanceRequestRepository;
    }

    public SuperFrogAppearanceRequest findById(Integer requestId) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }


    public List<SuperFrogAppearanceRequest> findAll() {
        return this.superFrogAppearanceRequestRepository.findAll();
    }

    public List<SuperFrogAppearanceRequest> findByStatus(RequestStatus status) {
        return this.superFrogAppearanceRequestRepository.findByStatus(status);
    }

    public List<SuperFrogAppearanceRequest> findByStatusAndStudent(RequestStatus status, SuperFrogStudent student) {
        return this.superFrogAppearanceRequestRepository.findByStatusAndStudent(status, student);
    }
    public SuperFrogAppearanceRequest save(SuperFrogAppearanceRequest newSuperFrogAppearanceRequest) {
        return this.superFrogAppearanceRequestRepository.save(newSuperFrogAppearanceRequest);
    }

    public SuperFrogAppearanceRequest update(Integer requestId, SuperFrogAppearanceRequest update) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setEventType(update.getEventType());
                    oldRequest.setAddress(update.getAddress());
                    oldRequest.setMileage(update.getMileage());
                    oldRequest.setEventDate(update.getEventDate());
                    oldRequest.setStartTime(update.getStartTime());
                    oldRequest.setEndTime(update.getEndTime());
                    oldRequest.setStatus(update.getStatus());
                    oldRequest.setContactFirstName(update.getContactFirstName());
                    oldRequest.setContactLastName(update.getContactLastName());
                    oldRequest.setPhoneNumber(update.getPhoneNumber());
                    oldRequest.setEmail(update.getEmail());
                    oldRequest.setTitle(update.getTitle());
                    oldRequest.setNameOfOrg(update.getNameOfOrg());
                    oldRequest.setDescription(update.getDescription());
                    oldRequest.setSpecialInstructions(update.getSpecialInstructions());
                    oldRequest.setOutsideOrgs(update.getOutsideOrgs());
                    oldRequest.setExpenses(update.getExpenses());
                    return this.superFrogAppearanceRequestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }

    public void delete(Integer requestId) {
        this.superFrogAppearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
        this.superFrogAppearanceRequestRepository.deleteById(requestId);
    }

    public SuperFrogAppearanceRequest updateStatus(Integer requestId, RequestStatus status) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .map(oldRequest -> {
                    oldRequest.setStatus(status);
                    return this.superFrogAppearanceRequestRepository.save(oldRequest);
                })
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }
}
