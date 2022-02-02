package academy.modules.category.controller;

import academy.modules.category.dto.CategoryRequest;
import academy.modules.category.dto.CategoryResponse;
import academy.modules.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request){
        return categoryService.saveCategory(request);
    }


}
