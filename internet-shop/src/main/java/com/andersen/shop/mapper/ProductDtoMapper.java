package com.andersen.shop.mapper;

import com.andersen.shop.dto.ProductDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDtoMapper implements RowMapper<ProductDto> {
    public static String BASE_SQL = "SELECT pib.product_id, pib.quantity FROM products_in_basket pib";

    @Override
    public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
        long productId = resultSet.getLong("product_id");
        int quantity = resultSet.getInt("quantity");
        return new ProductDto(productId, quantity);
    }
}
