package service;

import dao.ManufacturerDao;
import dto.ManufacturerDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс ManufacturerService является сервисным слоем
 * Метод findAll возвращает список объектов DTO для передачи на уровень сервлета.
 */
public class ManufacturerService {
    private static final ManufacturerService INSTANCE = new ManufacturerService();
    private final ManufacturerDao manufacturerDao = ManufacturerDao.getInstance();

    private ManufacturerService() {
    }

    public static ManufacturerService getInstance() {
        return INSTANCE;
    }

    public List<ManufacturerDto> findAll() {
        return manufacturerDao.findAll().stream()
                .map(manufacturer -> new ManufacturerDto(
                        manufacturer.getId(),
                        manufacturer.getName()
                )).collect(Collectors.toList());
    }
}
