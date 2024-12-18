package bookservice.producer;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
// MessageProducer class will help us with publishing messages
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    public MessageProducer(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    /* here we are creating a void which is going to help us
       with sending a message to the "library.book-service.borrowed" routing key
       when the user has borrowed a book
     */
    public void sendBookIsBorrowedMessage(String message) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "library.borrowing-service", message);
        System.out.println("Message sent: " + message);
    }
}
