package entity;

import java.util.Objects;

/**
 * Класс entity (сущности) Manufacturer,
 */
public class Manufacturer {
    private String id;
    private String name;

    public Manufacturer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}
