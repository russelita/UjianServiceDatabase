package com.juaracoding.main.model;

public class InfoDepartment {
	
	private String departmen;
	private int result;
	
	public InfoDepartment(){
		
	}

	public InfoDepartment(String departmen, int result) {
		super();
		this.departmen = departmen;
		this.result = result;
	}

	public String getDepartmen() {
		return departmen;
	}

	public void setDepartmen(String departmen) {
		this.departmen = departmen;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
