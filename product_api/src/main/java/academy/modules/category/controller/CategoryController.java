package academy.modules.category.controller;

import academy.modules.category.dto.CategoryRequest;
import academy.modules.category.dto.CategoryResponse;
import academy.modules.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request){
        return categoryService.saveCategory(request);
    }

    @GetMapping(path = "")
    public List<CategoryResponse> findall(){
        return categoryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public CategoryResponse findById(@PathVariable Integer id){
        return categoryService.findByIdResponse(id);
    }

    @GetMapping(path = "/description/{description}")
    public List<CategoryResponse> findByDescription(@PathVariable String description){
        return categoryService.findByDescription(description);
    }



}
