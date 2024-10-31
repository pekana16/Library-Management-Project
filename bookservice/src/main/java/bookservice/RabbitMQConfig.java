package bookservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("library.exchange");
    }

    @Bean
    public Queue bookServiceQueue() {
        return new Queue("book-service.queue");
    }

    @Bean
    public Binding bookServiceBinding(Queue bookServiceQueue, TopicExchange exchange) {
        return BindingBuilder.bind(bookServiceQueue).to(exchange).with("library.book-service.#");
    }
}
