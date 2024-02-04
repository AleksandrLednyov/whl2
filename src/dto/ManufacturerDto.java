package dto;

import java.util.Objects;

/**
 * Класс ManufacturerDto является транспортным классом для передачи информации от сервисного слоя в сервлет.
 */
public class ManufacturerDto {
    private final String id;
    private final String name;

    public ManufacturerDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDto that = (ManufacturerDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ManufacturerDto{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}
