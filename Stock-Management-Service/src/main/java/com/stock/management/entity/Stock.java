package com.stock.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Stock() {
		super();

	}

	public Stock(Long id, String name, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

}
