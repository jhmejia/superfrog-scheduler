package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuperfrogController {

    private final SuperfrogService superfrogService;


    public SuperfrogController(SuperfrogService superfrogService) {
        this.superfrogService = superfrogService;
    }

    @GetMapping("/api/superfrogs/{superfrogId}")
    public Result findSuperfrogById(@PathVariable int superfrogId) {
        Superfrog foundSuperfrog = this.superfrogService.findById(superfrogId);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", foundSuperfrog);
    }
}
