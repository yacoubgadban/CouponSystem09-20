package com.johnbryce.CouponSystem.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class CUSTOMERS {

	private long id;
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	private Collection<COUPONS>coupon =new ArrayList<COUPONS>();
	
	
	public CUSTOMERS() {
		
	}

	public CUSTOMERS(long id, String firstName, String lastName, String email, String password,
			Collection<COUPONS> coupon) {
		super();
		this.id = id;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		Password = password;
		this.coupon = coupon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	@Column
	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}
	@Column
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	@Column
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	
	@ManyToMany
	public Collection<COUPONS> getCoupon() {
		return coupon;
	}

	public void setCoupon(Collection<COUPONS> coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "CUSTOMERS [id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email
				+ ", Password=" + Password + ", coupon=" + coupon + "]";
	}

	
	
}
