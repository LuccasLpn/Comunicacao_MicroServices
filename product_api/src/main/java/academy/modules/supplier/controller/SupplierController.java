package academy.modules.supplier.controller;

import academy.modules.supplier.dto.SupplierRequest;
import academy.modules.supplier.dto.SupplierResponse;
import academy.modules.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;


    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request){
        return service.saveSupplier(request);
    }
}
