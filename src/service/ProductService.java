package service;

import dao.ProductDao;
import dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    private final ProductDao productDao = ProductDao.getInstance();
    private ProductService(){};
    public List<ProductDto> findAllByManufacturerId(String manufacturerId) {
        return productDao.findAllByManufacturerId(manufacturerId).stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getManufacturerId()
                ))
                .collect(Collectors.toList());
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
