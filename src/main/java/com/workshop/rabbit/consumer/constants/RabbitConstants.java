package com.workshop.rabbit.consumer.constants;

public final class RabbitConstants {

	private RabbitConstants() {
		// restrict instantiation
	}

	// Topic exchange name
	public static final String TOPIC_EXCHANGE = "workshop.topic";

	// Prefix to queue names
	private static final String QUEUE_NAME_PREFIX = "workshop.";

	// Suffix to dead letter queue names
	private static final String DEAD = ".dead";

	// Routes
	public static final String CREATE_ORDER_ROUTING_KEY = "order.create";
	public static final String CANCEL_ORDER_ROUTING_KEY = "order.cancel";

	// Queues
	public static final String CREATE_ORDER_QUEUE = QUEUE_NAME_PREFIX + CREATE_ORDER_ROUTING_KEY;
	public static final String CANCEL_ORDER_QUEUE = QUEUE_NAME_PREFIX + CANCEL_ORDER_ROUTING_KEY;

	// Dead letter queues
	public static final String CREATE_ORDER_DLQ = CREATE_ORDER_QUEUE + DEAD;
	public static final String CANCEL_ORDER_DLQ = CANCEL_ORDER_QUEUE + DEAD;
}
