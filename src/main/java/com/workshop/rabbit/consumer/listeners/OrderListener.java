package com.workshop.rabbit.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.workshop.rabbit.consumer.constants.RabbitConstants;
import com.workshop.rabbit.consumer.dto.Order;
import com.workshop.rabbit.consumer.exceptions.handlers.InvalidOrderException;

@Component
public class OrderListener {
	private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);

	@RabbitListener(queues = RabbitConstants.CREATE_ORDER_QUEUE, errorHandler = "rabbitExceptionHandler")
	public void createOrder(Order order) {
		logger.info("createOrder - Order: {}", order);
		if (order.getId() == 4) {
			throw new InvalidOrderException();
		}
	}

	@RabbitListener(queues = RabbitConstants.CANCEL_ORDER_QUEUE)
	public void cancelOrder(Order order) {
		logger.info("cancelOrder - Order: {}", order);
	}
}
