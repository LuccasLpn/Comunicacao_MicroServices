package academy.modules.supplier.service;

import academy.exceptions.ValidationException;
import academy.modules.supplier.domain.Supplier;
import academy.modules.supplier.dto.SupplierRequest;
import academy.modules.supplier.dto.SupplierResponse;
import academy.modules.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository repository;

    public Supplier findById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ValidationException("There no supplier for given ID: "));
    }


    public SupplierResponse saveSupplier(SupplierRequest request){
        validateSupplierNameInforme(request);
        var supplier = repository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    private void validateSupplierNameInforme(SupplierRequest request){
        if(isEmpty(request.getName())){
            throw new ValidationException("The Supplier name was not informed");
        }
    }

}
