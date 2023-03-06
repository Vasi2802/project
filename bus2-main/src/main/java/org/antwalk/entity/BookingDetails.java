package org.antwalk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="booking_details")
public class BookingDetails {
	
	@Id
	@GeneratedValue
	@Column(name="booking_id")
	private long bookingId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", referencedColumnName = "eid")
	private Employee e;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bus_id", referencedColumnName = "bid")
	private Bus b;
	
	@Column(name="booking_for_month")
	private String bookingForMonth;

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public Employee getE() {
		return e;
	}

	public void setE(Employee e) {
		this.e = e;
	}

	public Bus getB() {
		return b;
	}

	public void setB(Bus b) {
		this.b = b;
	}

	public String getBookingForMonth() {
		return bookingForMonth;
	}

	public void setBookingForMonth(String bookingForMonth) {
		this.bookingForMonth = bookingForMonth;
	}

	public BookingDetails(long bookingId, Employee e, Bus b, String bookingForMonth) {
		super();
		this.bookingId = bookingId;
		this.e = e;
		this.b = b;
		this.bookingForMonth = bookingForMonth;
	}

	public BookingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
