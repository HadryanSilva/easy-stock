package br.com.hadryan.stock.service.consumer;

import br.com.hadryan.stock.dto.StockOrderDTO;
import br.com.hadryan.stock.model.Stock;
import br.com.hadryan.stock.model.StockOrder;
import br.com.hadryan.stock.model.enums.OrderType;
import br.com.hadryan.stock.repository.ProductRepository;
import br.com.hadryan.stock.repository.StockOrderRepository;
import br.com.hadryan.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class StockOrderConsumer {

    private final StockOrderRepository stockOrderRepository;

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @RabbitListener(queues = "${rabbitmq.stock-order.queue}")
    public void createSaleStockOrder(StockOrderDTO stockOrderDTO) {
        var stockOrder = new StockOrder();
        var stockFound = stockRepository.findByProductId(stockOrderDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        if (checkAvailableStock(stockOrderDTO)) {
            log.info("Saving stock order with product id: {}", stockOrderDTO.getProductId());
            stockOrder.setOrderType(OrderType.SALE);
            stockOrder.setStockId(stockFound.getId());
            stockOrder.setQuantity(stockOrderDTO.getQuantity());
            stockOrder.setProductId(stockOrderDTO.getProductId());
            stockOrder.setCreatedAt(LocalDateTime.now());
            stockOrderRepository.save(stockOrder);
            updateStock(stockFound, stockOrderDTO);
        } else {
            log.error("Stock not available for product id: {}", stockOrderDTO.getProductId());
        }
    }

    private boolean checkAvailableStock(StockOrderDTO stockOrderDTO) {
        productRepository.findById(stockOrderDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        var stock = stockRepository.findByProductId(stockOrderDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        return stock.getQuantity() >= stockOrderDTO.getQuantity();
    }

    private void updateStock(Stock stock, StockOrderDTO stockOrderDTO) {
        log.info("Updating stock with id: {}", stock.getId());
        stock.setQuantity(stock.getQuantity() - stockOrderDTO.getQuantity());
        stockRepository.save(stock);
    }

}
