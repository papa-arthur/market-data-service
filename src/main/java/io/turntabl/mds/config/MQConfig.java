package io.turntabl.mds.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static String ORDER_EXCHANGE = "order.exchange";
    public static String TRACKING_QUEUE = "order.tracking.queue";
    public static String TRACKING_ROUTING_KEY = "order.tracking.key";


    @Bean
    public Queue trackingQueue() {
        return new Queue(TRACKING_QUEUE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(trackingQueue())
                .to(topicExchange())
                .with(TRACKING_ROUTING_KEY);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setMessageConverter(converter());
        return template;

    }

}
