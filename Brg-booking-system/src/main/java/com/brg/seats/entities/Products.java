package com.brg.seats.entities;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name = "products")
public class Products {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="productid")
	private int productId;
	@Column(name="eventid")
	private int eventId;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Madrid")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name="startd")
	private LocalDateTime start;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Europe/Madrid")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name="end")
	private LocalDateTime end;
	@Column(name="seatsavailable")
	private int seatsAvailable;
	@Column(name="seatstotal")
	private int seatsTotal;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	private Set<Properties> properties=new HashSet<>();

	public Products() {
		super();
	}

	public Products(int productId, int eventId, LocalDateTime start, LocalDateTime end, int seatsAvailable,
			int seatsTotal) {
		super();
		this.productId = productId;
		this.eventId = eventId;
		this.start = start;
		this.end = end;
		this.seatsAvailable = seatsAvailable;
		this.seatsTotal = seatsTotal;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public int getSeatsTotal() {
		return seatsTotal;
	}

	public void setSeatsTotal(int seatsTotal) {
		this.seatsTotal = seatsTotal;
	}

	public Set<Properties> getProperties() {
		return properties;
	}

	public void setProperties(Set<Properties> properties) {
		this.properties = properties;
	}

	public int getProductId() {
		return productId;
	}

	public int getId() {
		return id;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", productId=" + productId + ", eventId=" + eventId + ", start=" + start
				+ ", end=" + end + ", seatsAvailable=" + seatsAvailable + ", seatsTotal=" + seatsTotal + ", properties="
				+ properties + "]";
	}

}
