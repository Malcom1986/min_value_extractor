package ru.maksimlitvinov.nutrition_control.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExelParser {
    public static List<Integer> parseExcelFile(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (var inputStream = new FileInputStream(filePath); var workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (var row : sheet) {
                for (var cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        numbers.add((int) cell.getNumericCellValue());
                    }
                }
            }
        }
        return numbers;
    }
}
