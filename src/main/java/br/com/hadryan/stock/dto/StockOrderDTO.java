package br.com.hadryan.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockOrderDTO {

    private Long productId;
    private Integer quantity;

}
