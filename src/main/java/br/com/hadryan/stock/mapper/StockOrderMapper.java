package br.com.hadryan.stock.mapper;

import br.com.hadryan.stock.dto.StockOrderDTO;
import br.com.hadryan.stock.model.StockOrder;
import org.mapstruct.Mapper;

@Mapper
public interface StockOrderMapper {

    StockOrderDTO toDTO(StockOrder stockOrder);

    StockOrder toEntity(StockOrderDTO stockOrderDTO);

}
