package academy.modules.supplier.domain;

import academy.modules.category.domain.Category;
import academy.modules.category.dto.CategoryRequest;
import academy.modules.supplier.dto.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "NAME", nullable = false)
    private String name;


    public static Supplier of(SupplierRequest request){
        var supplier = new Supplier();
        BeanUtils.copyProperties(request, supplier);
        return supplier;
    }

}
