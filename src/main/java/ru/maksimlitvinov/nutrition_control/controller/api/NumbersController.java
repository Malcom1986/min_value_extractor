package ru.maksimlitvinov.nutrition_control.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maksimlitvinov.nutrition_control.dto.MinValueRequestDTO;
import ru.maksimlitvinov.nutrition_control.dto.ResultDTO;
import ru.maksimlitvinov.nutrition_control.service.NumbersService;


@RestController
@RequestMapping("/api/minimum")
@RequiredArgsConstructor
public class NumbersController {

    private final NumbersService numbersService;

    @PostMapping
    @Operation(summary = "Find N-th minimum value from a list of numbers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the N-th minimum value"),
        @ApiResponse(responseCode = "400", description = "Invalid input, such as negative index"),
        @ApiResponse(responseCode = "422", description = "Unable to process the file")
    })
    public ResultDTO findMinValue(@RequestBody MinValueRequestDTO minValueRequest) {
        return numbersService.findNthMin(minValueRequest);
    }
}
