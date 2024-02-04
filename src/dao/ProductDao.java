package dao;

import entity.Product;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final ProductDao INSTANCE = new ProductDao();
    public static final String FIND_ALL_BY_MANUFACTURER_ID = """
        SELECT * FROM product WHERE manufacturerId=?
        """;

    private ProductDao() {}
    public static ProductDao getInstance() {
        return INSTANCE;
    }

    public List<Product> findAllByManufacturerId(String manufacturerId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_MANUFACTURER_ID)) {
            preparedStatement.setObject(1, manufacturerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(buildProduct(resultSet));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product buildProduct(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("manufacturerId", String.class)
        );
    }
}
