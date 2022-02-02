package academy.modules.Product.service;

import academy.exceptions.ValidationException;
import academy.modules.Product.domain.Product;
import academy.modules.Product.dto.ProductRequest;
import academy.modules.Product.dto.ProductResponse;
import academy.modules.Product.repository.ProductRepository;
import academy.modules.category.domain.Category;
import academy.modules.category.dto.CategoryResponse;
import academy.modules.category.service.CategoryService;
import academy.modules.supplier.domain.Supplier;
import academy.modules.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Integer ZERO = 0;

    private final ProductRepository repository;

    private final SupplierService supplierService;

    private final CategoryService categoryService;


    public ProductResponse saveProduct(ProductRequest request){
        validateProductDataInforme(request);
        validateCategoryIdAndSupplierId(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = repository.save(Product.of(request, category, supplier));
        return ProductResponse.of(product);
    }


    private void validateProductDataInforme(ProductRequest request){
        if(isEmpty(request.getName())){
            throw new ValidationException("The Product name was not informed: ");
        }
        if(isEmpty(request.getQuantityAvailable())){
            throw new ValidationException("The product´s quantity as not informed: ");
        }
        if(request.getQuantityAvailable() < ZERO){
            throw new ValidationException("The quantity should not be less or equal to zero: ");
        }
    }

    private void validateCategoryIdAndSupplierId(ProductRequest request){
        if(isEmpty(request.getSupplierId())){
            throw new ValidationException("The Supplier id was not informed: ");
        }
        if(isEmpty(request.getCategoryId())){
            throw new ValidationException("The Category id was not informed: ");
        }
    }


}
