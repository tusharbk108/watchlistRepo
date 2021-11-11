package com.atyeti.tradingApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class HistoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "index_no")
	private int index;

	@Column(name = "sno")
	private int sno;

	@Column(name = "user_id")
	private String user_id;

	@Column(name = "company_id")
	private int company_id;

	@Column(name = "company_name")
	private String company_name;

	@Column(name = "price")
	private int price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;

	@Column(name = "type")
	private String type;

	public HistoryModel() {

	}

	public HistoryModel(int sno, String user_id, int company_id, String company_name, int price, int quantity, String date,
			String time, String type) {
		super();
		this.sno = sno;
		this.user_id = user_id;
		this.company_id = company_id;
		this.company_name = company_name;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
		this.time = time;
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
