package ru.maksimlitvinov.nutrition_control.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.maksimlitvinov.nutrition_control.dto.MinValueRequestDTO;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@SpringBootTest
@AutoConfigureMockMvc
public class MinValueExtractorTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MockMvc mockMvc;

    private static Stream<Arguments> provideDataForTest() {
        return Stream.of(
                Arguments.of(1, -20),
                Arguments.of(2, 0),
                Arguments.of(3, 2),
                Arguments.of(5, 3),
                Arguments.of(10, 12),
                Arguments.of(15, 45)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDataForTest")
    void testMinValueExtractor(int index, int expectedMinValue) throws Exception {
        var data = new MinValueRequestDTO();
        var filePath = getFixturePath("numbers.xlsx");
        data.setFilePath(filePath);
        data.setIndex(index);

        var request = post("/api/minimum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                v -> v.node("minValue").isEqualTo(expectedMinValue)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-2, 0, 10000})
    void testWithWrongIndex(int index) throws Exception {
        var data = new MinValueRequestDTO();
        var filePath = getFixturePath("numbers.xlsx");
        data.setFilePath(filePath);
        data.setIndex(index);

        var request = post("/api/minimum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        var result = mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void testWithUnexistingFile() throws Exception {
        var data = new MinValueRequestDTO();
        var filePath = getFixturePath("wrong.xlsx");
        data.setFilePath(filePath);
        data.setIndex(1);

        var request = post("/api/minimum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        var result = mockMvc.perform(request)
                .andExpect(status().isUnprocessableEntity());
    }

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().toString();
    }
}
