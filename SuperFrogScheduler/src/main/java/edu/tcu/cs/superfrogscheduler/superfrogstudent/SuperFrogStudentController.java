package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SuperfrogDtoToSuperfrogConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.converter.SuperfrogToSuperfrogDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.superfroguser.MyUserPrincipal;
import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUser;
import edu.tcu.cs.superfrogscheduler.superfroguser.SuperFrogUserService;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.HttpStatusCode;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperFrogStudentController {

    private final SuperFrogStudentService superFrogStudentService;

    private final SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter;

    private final SuperfrogDtoToSuperfrogConverter superfrogDtoToSuperfrogConverter;

    private final SuperFrogUserService superFrogUserService;

    public SuperFrogStudentController(SuperFrogStudentService superFrogStudentService, SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter, SuperfrogDtoToSuperfrogConverter superfrogDtoToSuperfrogConverter, SuperFrogUserService superFrogUserService) {
        this.superFrogStudentService = superFrogStudentService;
        this.superfrogToSuperfrogDtoConverter = superfrogToSuperfrogDtoConverter;
        this.superfrogDtoToSuperfrogConverter = superfrogDtoToSuperfrogConverter;
        this.superFrogUserService = superFrogUserService;
    }

    @GetMapping("/api/superfrogstudents/{superfrogId}")
    public Result findSuperfrogById(@PathVariable int superfrogId) {
        SuperFrogStudent foundSuperfrog = this.superFrogStudentService.findById(superfrogId);
        SuperFrogStudentDto superfrogDto = this.superfrogToSuperfrogDtoConverter.convert(foundSuperfrog);
        return new Result(true, HttpStatusCode.SUCCESS, "Find One Success", superfrogDto);
    }


    @GetMapping("/api/superfrogstudents")
    public Result findAllSuperFrogStudents() {
        List<SuperFrogStudent> foundSuperFrogStudents = this.superFrogStudentService.findAll();
        // Convert foundSuperFrogStudents to a list of SuperFrogStudentDtos
        List<SuperFrogStudentDto> superFrogStudentDtos = foundSuperFrogStudents.stream().map(this.superfrogToSuperfrogDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, HttpStatusCode.SUCCESS, "Find All Success", superFrogStudentDtos);
    }

    @GetMapping("/api/superfrogstudents/active")
    public Result findAllActiveSuperFrogStudents() {
        List<SuperFrogStudent> foundSuperFrogStudents = this.superFrogStudentService.findByActive();
        // Convert foundSuperFrogStudents to a list of SuperFrogStudentDtos
        List<SuperFrogStudentDto> superFrogStudentDtos = foundSuperFrogStudents.stream().map(this.superfrogToSuperfrogDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, HttpStatusCode.SUCCESS, "Find All Active Success", superFrogStudentDtos);
    }

    @GetMapping("/api/superfrogstudents/email/{email}")
    public Result findByEmail(@PathVariable String email) {
        SuperFrogStudent foundSuperFrogStudent = this.superFrogStudentService.findByEmail(email);
        SuperFrogStudentDto superfrogDto = this.superfrogToSuperfrogDtoConverter.convert(foundSuperFrogStudent);
        return new Result(true, HttpStatusCode.SUCCESS, "Find by Email Success", superfrogDto);
    }

    @PostMapping("/api/superfrogstudents")
    public Result addSuperFrogStudent(@Valid @RequestBody SuperFrogStudentDto superFrogStudentDto)
    {
        SuperFrogStudent newSuperFrogStudent = this.superfrogDtoToSuperfrogConverter.convert(superFrogStudentDto);
        SuperFrogStudent savedSuperFrogStudent = this.superFrogStudentService.save(newSuperFrogStudent);
        SuperFrogUser newStudentUser = new SuperFrogUser();
        newStudentUser.setUsername(savedSuperFrogStudent.getEmail());
        newStudentUser.setActive(true);
        newStudentUser.setRoles("superfrogstudent");
        newStudentUser.setPassword("superfrogstudent");
        UserDetails newStudentUserDetails = new MyUserPrincipal(newStudentUser);
        this.superFrogUserService.save(newStudentUser);
        SuperFrogStudentDto savedSuperFrogStudentDto = this.superfrogToSuperfrogDtoConverter.convert(savedSuperFrogStudent);
        return new Result(true, HttpStatusCode.SUCCESS, "Add Success", savedSuperFrogStudentDto);
    }

    @PutMapping("/api/superfrogstudents/{superfrogId}")
    public Result updateSuperFrogStudent(@PathVariable int superfrogId,@Valid @RequestBody SuperFrogStudentDto superFrogStudentDto){
        SuperFrogStudent update = this.superfrogDtoToSuperfrogConverter.convert(superFrogStudentDto);
        SuperFrogStudent updatedStudent = this.superFrogStudentService.update(superfrogId, update);
        SuperFrogStudentDto updatedStudentDto = this.superfrogToSuperfrogDtoConverter.convert(updatedStudent);
        return new Result(true, HttpStatusCode.SUCCESS, "Update Success", updatedStudentDto);
    }

    @PutMapping("api/superfrogstudents/{superfrogId}/assign/superfrogappearancerequests/{requestId}")
    public Result assignAppearanceRequest(@PathVariable Integer requestId, @PathVariable Integer superfrogId) {
        this.superFrogStudentService.assignAppearanceRequest(requestId, superfrogId);
        return new Result(true, HttpStatusCode.SUCCESS, "Appearance Request Assignment Success");
    }

    @PutMapping("api/superfrogstudents/{superfrogId}/unassign/superfrogappearancerequests/{requestId}")
    public Result unAssignAppearanceRequest(@PathVariable Integer requestId, @PathVariable Integer superfrogId) {
        this.superFrogStudentService.unAssignAppearanceRequest(requestId, superfrogId);
        return new Result(true, HttpStatusCode.SUCCESS, "Appearance Request Unassignment Success");
    }
}
