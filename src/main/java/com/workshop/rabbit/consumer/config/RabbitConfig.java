package com.workshop.rabbit.consumer.config;

import static com.workshop.rabbit.consumer.constants.RabbitConstants.CANCEL_ORDER_DLQ;
import static com.workshop.rabbit.consumer.constants.RabbitConstants.CANCEL_ORDER_QUEUE;
import static com.workshop.rabbit.consumer.constants.RabbitConstants.CANCEL_ORDER_ROUTING_KEY;
import static com.workshop.rabbit.consumer.constants.RabbitConstants.CREATE_ORDER_DLQ;
import static com.workshop.rabbit.consumer.constants.RabbitConstants.CREATE_ORDER_QUEUE;
import static com.workshop.rabbit.consumer.constants.RabbitConstants.CREATE_ORDER_ROUTING_KEY;
import static com.workshop.rabbit.consumer.constants.RabbitConstants.TOPIC_EXCHANGE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	private static final String XDLE = "x-dead-letter-exchange";
	private static final String XDLRK = "x-dead-letter-routing-key";

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}

	@Bean
	public Queue createOrderQueue() {
		return QueueBuilder.durable(CREATE_ORDER_QUEUE).withArgument(XDLE, "").withArgument(XDLRK, CREATE_ORDER_DLQ).build();
	}

	@Bean
	public Queue createOrderDlq() {
		return QueueBuilder.durable(CREATE_ORDER_DLQ).build();
	}

	@Bean
	public Queue cancelOrderQueue() {
		return QueueBuilder.durable(CANCEL_ORDER_QUEUE).withArgument(XDLE, "").withArgument(XDLRK, CANCEL_ORDER_DLQ).build();
	}

	@Bean
	public Queue cancelOrderDlq() {
		return QueueBuilder.durable(CANCEL_ORDER_DLQ).build();
	}

	@Bean
	Binding createOrderBinding() {
		return BindingBuilder.bind(createOrderQueue()).to(topicExchange()).with(CREATE_ORDER_ROUTING_KEY);
	}

	@Bean
	Binding cancelOrderBinding() {
		return BindingBuilder.bind(cancelOrderQueue()).to(topicExchange()).with(CANCEL_ORDER_ROUTING_KEY);
	}

}
