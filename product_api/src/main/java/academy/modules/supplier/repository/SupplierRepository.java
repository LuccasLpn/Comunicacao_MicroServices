package academy.modules.supplier.repository;

import academy.modules.supplier.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
