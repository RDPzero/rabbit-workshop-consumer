package com.workshop.rabbit.consumer.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

@Component
public class RabbitExceptionHandler implements RabbitListenerErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(RabbitExceptionHandler.class);

	@Override
	public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception)
			throws Exception {
		Throwable cause = exception.getCause();
		Class<?> causeClass = cause.getClass();
			logger.error("exception: {}", cause.getMessage(), cause);
		if (InvalidOrderException.class.equals(causeClass)) {
			InvalidOrderException e = (InvalidOrderException) cause;
		} else {
			throw exception;
		}
		return null;
	}

}
