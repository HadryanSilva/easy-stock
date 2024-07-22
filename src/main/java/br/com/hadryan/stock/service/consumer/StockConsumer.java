package br.com.hadryan.stock.service.consumer;

import br.com.hadryan.stock.dto.StockDTO;
import br.com.hadryan.stock.exception.ProductNotRegisteredException;
import br.com.hadryan.stock.mapper.StockMapper;
import br.com.hadryan.stock.repository.ProductRepository;
import br.com.hadryan.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class StockConsumer {

    private final StockRepository stockRepository;

    private final ProductRepository productRepository;

    private final StockMapper stockMapper;

    @RabbitListener(queues = "${rabbitmq.stock.queue}")
    public void registerStock(StockDTO message) {
        try {
            productRepository.findById(message.getProductId())
                    .orElseThrow(ProductNotRegisteredException::new);
            var stockToSave = stockMapper.toEntity(message);
            log.info("Saving stock: '{}'", stockToSave);
            stockRepository.save(stockToSave);
        } catch (ProductNotRegisteredException e) {
            log.error("Error registering stock: '{}'", e.getMessage());
        }
    }

}
