package br.com.hadryan.stock.mapper;

import br.com.hadryan.stock.dto.ProductDTO;
import br.com.hadryan.stock.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);

}
