package br.com.hadryan.stock.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {

    private Long productId;

    private Integer quantity;

    private Integer minQuantity;

    private Integer maxQuantity;

    private Double unitPurchasePrice;

    private Double unitSellPrice;

}
