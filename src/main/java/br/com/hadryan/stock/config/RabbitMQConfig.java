package br.com.hadryan.stock.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.stock-order.queue}")
    private String stockOrderQueue;

    @Value("${rabbitmq.product.queue}")
    private String productQueue;

    @Value("${rabbitmq.stock.queue}")
    private String stockQueue;

    @Value("${rabbitmq.stock-order.exchange}")
    private String stockOrderExchange;

    @Value("${rabbitmq.product.exchange}")
    private String productExchange;

    @Value("${rabbitmq.stock.exchange}")
    private String stockExchange;

    @Bean
    public Queue stockOrderQueue() {
        return new Queue(stockOrderQueue, true);
    }

    @Bean
    public Queue productQueue() {
        return new Queue(productQueue, true);
    }

    @Bean
    public Queue stockQueue() {
        return new Queue(stockQueue, true);
    }

    @Bean
    public FanoutExchange stockOrderExchange() {
        return new FanoutExchange(stockOrderExchange);
    }

    @Bean
    public FanoutExchange productExchange() {
        return new FanoutExchange(productExchange);
    }

    @Bean
    public FanoutExchange stockExchange() {
        return new FanoutExchange(stockExchange);
    }

    @Bean
    public Binding stockOrderBinding() {
        return BindingBuilder.bind(stockOrderQueue())
                .to(stockOrderExchange());
    }

    @Bean
    public Binding productBinding() {
        return BindingBuilder.bind(productQueue())
                .to(productExchange());
    }

    @Bean
    public Binding stockBinding() {
        return BindingBuilder.bind(stockQueue())
                .to(stockExchange());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
