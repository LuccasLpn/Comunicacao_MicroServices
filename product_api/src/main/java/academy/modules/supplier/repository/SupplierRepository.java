package academy.modules.supplier.repository;

import academy.modules.supplier.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    
    List<Supplier> findByNameIgnoreCaseContaining(String name);
}
