package borrowingservice;

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
    public Queue borrowingServiceQueue() {
        return new Queue("borrowing-service.queue");
    }

    @Bean
    public Binding borrowingServiceBinding(Queue borrowingServiceQueue, TopicExchange exchange) {
        return BindingBuilder.bind(borrowingServiceQueue).to(exchange).with("library.borrowing-service.#");
    }
}
