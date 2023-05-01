package edu.tcu.cs.superfrogscheduler.performance;

import edu.tcu.cs.superfrogscheduler.superfrogrequest.RequestStatus;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/performance")
public class SuperfrogPerformanceController {
    private final SuperfrogPerformanceService superfrogPerformanceService;

    private final SuperFrogStudentService superFrogStudentService;

    public SuperfrogPerformanceController(SuperfrogPerformanceService superfrogPerformanceService, SuperFrogStudentService superFrogStudentService) {
        this.superfrogPerformanceService = superfrogPerformanceService;
        this.superFrogStudentService = superFrogStudentService;
    }

    @GetMapping("/superfrogstudents/{studentId}/")
    public ResponseEntity<Integer> getSuperfrogPerformance(@PathVariable RequestStatus status, @PathVariable Integer studentId) {
        SuperFrogStudent student = superFrogStudentService.findById(studentId);
        int completedRequests = superfrogPerformanceService.getCompletedRequests(status, student);
        return ResponseEntity.ok(completedRequests);
    }
}

