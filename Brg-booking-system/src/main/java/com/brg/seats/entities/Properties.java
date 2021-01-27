package com.brg.seats.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="properties")
public class Properties {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="typeid")
	private int typeId;
	@Column(name="label")
	private String label;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="productid")
	private Products product;

	public Properties() {
		super();
	}

	public Properties(int typeId, String label) {
		super();
		this.typeId = typeId;
		this.label = label;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Properties [id=" + id + ", typeId=" + typeId + ", label=" + label
				+ "]";
	}

}
