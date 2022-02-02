package academy.modules.Product.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private Integer quantityAvailable;
    private Integer supplierId;
    private Integer categoryId;
}