package edu.tcu.cs.superfrogscheduler.controller;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.service.SuperFrogStudentService;
import edu.tcu.cs.superfrogscheduler.superfrog.converter.SuperfrogDtoToSuperfrogConverter;
import edu.tcu.cs.superfrogscheduler.superfrog.converter.SuperfrogToSuperfrogDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperFrogStudentDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.HttpStatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperFrogStudentController {

    private final SuperFrogStudentService superFrogStudentService;

    private final SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter;

    private final SuperfrogDtoToSuperfrogConverter superfrogDtoToSuperfrogConverter;

    public SuperFrogStudentController(SuperFrogStudentService superFrogStudentService, SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter, SuperfrogDtoToSuperfrogConverter superfrogDtoToSuperfrogConverter) {
        this.superFrogStudentService = superFrogStudentService;
        this.superfrogToSuperfrogDtoConverter = superfrogToSuperfrogDtoConverter;
        this.superfrogDtoToSuperfrogConverter = superfrogDtoToSuperfrogConverter;
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

    @PostMapping("/api/superfrogstudents")
    public Result addSuperFrogStudent(@Valid @RequestBody SuperFrogStudentDto superFrogStudentDto)
    {
        SuperFrogStudent newSuperFrogStudent = this.superfrogDtoToSuperfrogConverter.convert(superFrogStudentDto);
        SuperFrogStudent savedSuperFrogStudent = this.superFrogStudentService.save(newSuperFrogStudent);
        SuperFrogStudentDto savedSuperFrogStudentDto = this.superfrogToSuperfrogDtoConverter.convert(savedSuperFrogStudent);
        return new Result(true, HttpStatusCode.SUCCESS, "Add Success", savedSuperFrogStudentDto);
    }

}
