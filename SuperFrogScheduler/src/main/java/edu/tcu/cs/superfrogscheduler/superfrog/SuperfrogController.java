package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.superfrog.converter.SuperfrogToSuperfrogDtoConverter;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperfrogDto;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SuperfrogController {

    private final SuperfrogService superfrogService;

    private final SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter;

    public SuperfrogController(SuperfrogService superfrogService, SuperfrogToSuperfrogDtoConverter superfrogToSuperfrogDtoConverter) {
        this.superfrogService = superfrogService;
        this.superfrogToSuperfrogDtoConverter = superfrogToSuperfrogDtoConverter;
    }

    @GetMapping("/api/superfrogs/{superfrogId}")
    public Result findSuperfrogById(@PathVariable int superfrogId) {
        Superfrog foundSuperfrog = this.superfrogService.findById(superfrogId);
        SuperfrogDto superfrogDto = this.superfrogToSuperfrogDtoConverter.convert(foundSuperfrog);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", superfrogDto);
    }

    @GetMapping("/api/superfrogs/")
    public Result findAllSuperfrogs() {
        List<Superfrog> foundSuperfrogs = this.superfrogService.findAll();
        // Convert foundSuperfrogs to a list of SuperfrogDtos
        List<SuperfrogDto> superfrogDtos = foundSuperfrogs.stream()
                .map(this.superfrogToSuperfrogDtoConverter::convert)
                .collect(Collectors.toList());

        return new Result(true, StatusCode.SUCCESS, "Find All Success", superfrogDtos);
    }
}
