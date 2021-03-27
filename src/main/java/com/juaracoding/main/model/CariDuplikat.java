package com.juaracoding.main.model;

public class CariDuplikat {
	
	private String first_name;
	private String last_name;
	private int salary;
	private int jumlah;
	
	public CariDuplikat() {
		
	}
	
	public CariDuplikat(String first_name, String last_name, int salary, int jumlah) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.salary = salary;
		this.jumlah = jumlah;
	}



	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}
	
	

}
