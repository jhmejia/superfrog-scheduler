package edu.tcu.cs.superfrogscheduler.service;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperFrogAppearanceRequestService {
    private final SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository;

    private final IdWorker idWorker;

    public SuperFrogAppearanceRequestService(SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository, IdWorker idWorker) {
        this.superFrogAppearanceRequestRepository = superFrogAppearanceRequestRepository;
        this.idWorker = idWorker;
    }

    public SuperFrogAppearanceRequest findById(Integer requestId) {
        return this.superFrogAppearanceRequestRepository.findById(requestId)
                .orElseThrow(() -> new ObjectNotFoundException("superfrogappearancerequest", requestId));
    }


    public List<SuperFrogAppearanceRequest> findAll() {
        return this.superFrogAppearanceRequestRepository.findAll();
    }

    public SuperFrogAppearanceRequest save(SuperFrogAppearanceRequest newSuperFrogAppearanceRequest) {
        newSuperFrogAppearanceRequest.setRequestId((int) idWorker.nextId());
        return this.superFrogAppearanceRequestRepository.save(newSuperFrogAppearanceRequest);
    }
}
