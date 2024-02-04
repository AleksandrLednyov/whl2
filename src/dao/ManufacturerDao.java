package dao;

import entity.Manufacturer;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDao {
    private static final ManufacturerDao INSTANCE  = new ManufacturerDao();
    private static final String FIND_ALL = """
            SELECT *
            FROM manufacturer
            """;

    private ManufacturerDao() {
    }

    public static ManufacturerDao getInstance() {
        return INSTANCE;
    }

    public List<Manufacturer> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(buildManufacturer(resultSet));
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Manufacturer buildManufacturer(ResultSet resultSet) throws SQLException {
        return new Manufacturer(
                resultSet.getObject("id", String.class),
                resultSet.getObject("name", String.class)
        );
    }
}
