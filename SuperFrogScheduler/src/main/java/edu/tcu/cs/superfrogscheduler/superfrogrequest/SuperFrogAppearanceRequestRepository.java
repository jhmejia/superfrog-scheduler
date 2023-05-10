package edu.tcu.cs.superfrogscheduler.superfrogrequest;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperFrogAppearanceRequestRepository extends JpaRepository<SuperFrogAppearanceRequest, Integer> {

    List<SuperFrogAppearanceRequest> findByRequestIdIn(List<Integer> appearanceRequestIdList);

    List<SuperFrogAppearanceRequest> findByStatus(RequestStatus status);

    List<SuperFrogAppearanceRequest> findByStatusAndStudent(RequestStatus status, SuperFrogStudent student);

}
