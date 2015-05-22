package com.example.rodrigo.fuzz.model;

/**
 * Created by Rodrigo Cericatto on 22/05/2015.
 */
public class Fuzz {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------

	private String id;
	private String type;
	//private String date;
	private String data;

	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------

	public Fuzz() {}

	public Fuzz(String id, String type, /*String date,*/ String data) {
		this.id = id;
		this.type = type;
		//this.date = date;
		this.data = data;
	}

	//--------------------------------------------------
	// To String
	//--------------------------------------------------

	@Override
	public String toString() {
		return "Fuzz{" +
			"id=" + id +
			", type='" + type + '\'' +
			//", date='" + date + '\'' +
			", data='" + data + '\'' +
			'}';
	}

	//--------------------------------------------------
	// Getters and Setters
	//--------------------------------------------------

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/*
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	*/
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}