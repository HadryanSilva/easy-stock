package br.com.hadryan.stock.exception;

public class StockNotRegisteredException extends RuntimeException {

    public StockNotRegisteredException() {
        super("There is no stock registered for this product");
    }

}
