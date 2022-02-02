package academy.modules.category.service;

import academy.modules.category.dto.CategoryRequest;
import academy.modules.category.dto.CategoryResponse;
import academy.modules.category.repository.CategoryRepository;
import academy.modules.category.domain.Category;
import academy.exceptions.ValidationException;
import academy.modules.supplier.domain.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;


    public Category findById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("There no supplier for given ID: "));
    }


    public CategoryResponse saveCategory(CategoryRequest request){
        validateCategoryNameInforme(request);
        var category = repository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInforme(CategoryRequest request){
        if(isEmpty(request.getDescription())){
            throw new ValidationException("The Category description was not informed");
        }
    }


}
