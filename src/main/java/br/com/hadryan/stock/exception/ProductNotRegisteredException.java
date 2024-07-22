package br.com.hadryan.stock.exception;

public class ProductNotRegisteredException extends RuntimeException {

    public ProductNotRegisteredException() {
        super("Product not registered on stock");
    }

}
