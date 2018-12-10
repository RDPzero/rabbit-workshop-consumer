package com.workshop.rabbit.consumer.dto;

public class Order {

	private int id;

	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "id: " + this.id + ", description: " + this.description;
	}

}
