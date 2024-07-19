package br.com.hadryan.stock.mapper;

import br.com.hadryan.stock.dto.StockDTO;
import br.com.hadryan.stock.model.Stock;
import org.mapstruct.Mapper;

@Mapper
public interface StockMapper {

    StockDTO toDTO(Stock stock);

    Stock toEntity(StockDTO stockDTO);

}
