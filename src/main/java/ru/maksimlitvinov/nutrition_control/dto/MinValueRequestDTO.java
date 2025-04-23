package ru.maksimlitvinov.nutrition_control.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinValueRequestDTO {
    private String filePath;
    private int index;
}
