package academy.modules.Product.controller;

import academy.modules.Product.dto.ProductRequest;
import academy.modules.Product.dto.ProductResponse;
import academy.modules.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request){
        return productService.saveProduct(request);
    }


}
