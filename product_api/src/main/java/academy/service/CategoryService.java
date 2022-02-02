package academy.service;

import academy.domain.Category;
import academy.dto.CategoryRequest;
import academy.dto.CategoryResponse;
import academy.exceptions.ValidationException;
import academy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryResponse saveCategory(CategoryRequest request){
        validateCategoryNameInforme(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInforme(CategoryRequest request){
        if(isEmpty(request.getDescription())){
            throw new ValidationException("The Category description was not informed");
        }
    }


}
