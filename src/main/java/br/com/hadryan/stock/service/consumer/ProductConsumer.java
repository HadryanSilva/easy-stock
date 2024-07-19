package br.com.hadryan.stock.service.consumer;

import br.com.hadryan.stock.dto.ProductDTO;
import br.com.hadryan.stock.mapper.ProductMapper;
import br.com.hadryan.stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductConsumer {

    private final ProductRepository productRepository;

    private final ProductMapper mapper;

    @RabbitListener(queues = "${rabbitmq.product.queue}")
    public void registerProduct(ProductDTO productDTO) {
        var productToSave = mapper.toEntity(productDTO);
        log.info("Saving product: '{}'", productDTO.getName());
        productRepository.save(productToSave);
    }

}
