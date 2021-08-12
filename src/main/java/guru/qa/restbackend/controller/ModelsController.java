package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.ModelInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.stream.Collectors;

@RestController
public class ModelsController {

    private String modelsPath = "app/models/";
    private String fileExtension = ".fbx";

    private Map<String, ModelInfo> models = Map.of (
            "Уфа модель", ModelInfo.builder()
                    .modelName("Уфа модель")
                    .modelPath(modelsPath + "Уфа модель" + fileExtension)
                    .ownerId(2)
                    .build(),
            "Корабль", ModelInfo.builder()
                    .modelName("Корабль")
                    .modelPath(modelsPath + "Корабль" + fileExtension)
                    .ownerId(21)
                    .build(),
            "Комната 1", ModelInfo.builder()
                    .modelName("Комната 1")
                    .modelPath(modelsPath + "Комната 1" + fileExtension)
                    .ownerId(2)
                    .build(),
            "Комната 2", ModelInfo.builder()
                    .modelName("Комната 2")
                    .modelPath(modelsPath + "Комната 2" + fileExtension)
                    .ownerId(8)
                    .build()

    );

    @GetMapping("models/getAll")
    @ApiOperation("Получение списка моделей")
    public List<ModelInfo> getAllModelsInfo() {
        List <ModelInfo> result = new ArrayList<>();
        for (Map.Entry<String, ModelInfo> entry : models.entrySet()) {
            result.add(entry.getValue());
        }
        return models.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @PostMapping("models/add")
    @ApiOperation("Добавление модели")
    public ModelInfo addModel(@RequestBody ModelInfo modelInfo) {
        return ModelInfo.builder()
                .ownerId(modelInfo.getOwnerId())
                .modelName(modelInfo.getModelName())
                .modelPath(modelInfo.getModelPath())
                .message ("Модель успешно добавлена")
                .build();
    }

    @PutMapping("models/put/{oldName}/{newName}")
    @ApiOperation("Изменение названия модели (заглушка)")
    public String putModel(@PathVariable String oldName, @PathVariable String newName) {
        return "Имя модели " + oldName + " успешно изменено на " + newName;
    }

    @DeleteMapping("models/put/{modelName}")
    @ApiOperation("Удаление модели (заглушка)")
    public String deleteModel(@PathVariable String modelName) {
        return "Модель " + modelName + " успешна удалена";
    }

    @GetMapping("models/getByName/{modelName}")
    @ApiOperation("Фильтр списка моделей по имени")
    public List<ModelInfo> getModelsByName(@PathVariable String modelName) {
        List <ModelInfo> result = new ArrayList<>();
        for (Map.Entry<String, ModelInfo> entry : models.entrySet()) {
            result.add(entry.getValue());
        }
        return models.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(a -> a.getModelName().equals(modelName))
                .collect(Collectors.toList());
    }

}
