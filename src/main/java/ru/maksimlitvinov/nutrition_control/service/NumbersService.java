package ru.maksimlitvinov.nutrition_control.service;

import org.springframework.stereotype.Service;
import ru.maksimlitvinov.nutrition_control.dto.MinValueRequestDTO;
import ru.maksimlitvinov.nutrition_control.dto.ResultDTO;
import ru.maksimlitvinov.nutrition_control.exceprion.FileProcessingException;
import ru.maksimlitvinov.nutrition_control.util.ExelParser;
import ru.maksimlitvinov.nutrition_control.util.QuickSelect;

import java.io.IOException;
import java.util.List;

@Service
public class NumbersService {
    public ResultDTO findNthMin(MinValueRequestDTO minValueRequest) {
        var filePath = minValueRequest.getFilePath();
        var index = minValueRequest.getIndex();

        List<Integer> numbers;

        try {
            numbers = ExelParser.parseExcelFile(filePath);
        } catch (IOException e) {
            throw new FileProcessingException("Problem with the file on the path " + filePath + " : " + e.getMessage());
        }

        if (index < 1 || index > numbers.size()) {
            throw new IndexOutOfBoundsException("Element number " + index + " is out of range");
        }

        var minNumber = QuickSelect.findNthMin(numbers, minValueRequest.getIndex());
        var dto = new ResultDTO();
        dto.setMinValue(minNumber);

        return dto;
    }
}
