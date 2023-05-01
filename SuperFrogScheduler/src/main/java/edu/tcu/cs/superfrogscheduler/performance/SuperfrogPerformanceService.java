package edu.tcu.cs.superfrogscheduler.performance;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.RequestStatus;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequestService;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SuperfrogPerformanceService {


    private final SuperFrogAppearanceRequestService superFrogAppearanceRequestService;

    public SuperfrogPerformanceService(SuperFrogAppearanceRequestService superFrogAppearanceRequestService) {
        this.superFrogAppearanceRequestService = superFrogAppearanceRequestService;
    }

    public int getCompletedRequests(RequestStatus status, SuperFrogStudent student) {
        List<SuperFrogAppearanceRequest> completedCount = superFrogAppearanceRequestService.findByStatusAndStudent(status, student);
        return completedCount.size();
    }
}


