package edu.tcu.cs.superfrogscheduler.controller;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.service.SuperFrogAppearanceRequestService;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.converter.SuperFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.converter.SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.dto.SuperFrogAppearanceRequestDto;
import edu.tcu.cs.superfrogscheduler.system.HttpStatusCode;
import edu.tcu.cs.superfrogscheduler.system.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperFrogAppearanceController {

    private final SuperFrogAppearanceRequestService superFrogAppearanceRequestService;

    private final SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter;

    private final SuperFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter;

    public SuperFrogAppearanceController(SuperFrogAppearanceRequestService superFrogAppearanceRequestService, SuperFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter, SuperFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter) {
        this.superFrogAppearanceRequestService = superFrogAppearanceRequestService;
        this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter = superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter;
        this.superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter = superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter;
    }

    @GetMapping("/api/superfrogappearancerequests/{requestId}")
    public Result findSuperFrogAppearanceById(@PathVariable int requestId) {
        SuperFrogAppearanceRequest foundSuperFrogAppearance = this.superFrogAppearanceRequestService.findById(requestId);
        SuperFrogAppearanceRequestDto superFrogAppearanceRequestDto = this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter.convert(foundSuperFrogAppearance);
        return new Result(true, HttpStatusCode.SUCCESS, "Find One Success", superFrogAppearanceRequestDto);
    }

    @GetMapping("/api/superfrogappearancerequests")
    public Result findAllSuperFrogAppearances() {
        List<SuperFrogAppearanceRequest> foundAppearance = this.superFrogAppearanceRequestService.findAll();

        List<SuperFrogAppearanceRequestDto> appearanceRequestDtos = foundAppearance.stream()
                .map(this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter::convert)
                .collect(Collectors.toList());
        return new Result(true, HttpStatusCode.SUCCESS, "Find All Success", appearanceRequestDtos);
    }

    @PostMapping("/api/superfrogappearancerequests")
    public Result addSuperFrogAppearanceRequest(@Valid @RequestBody SuperFrogAppearanceRequestDto appearanceRequestDto) {
        SuperFrogAppearanceRequest newAppearance = this.superFrogAppearanceRequestDtoToSuperFrogAppearanceRequestConverter.convert(appearanceRequestDto);
        SuperFrogAppearanceRequest savedAppearance = this.superFrogAppearanceRequestService.save(newAppearance);
        SuperFrogAppearanceRequestDto savedAppearanceDto = this.superFrogAppearanceRequestToSuperFrogAppearanceRequestDtoConverter.convert(savedAppearance);
        return new Result(true, HttpStatusCode.SUCCESS, "Add Success", savedAppearanceDto);
    }
}
