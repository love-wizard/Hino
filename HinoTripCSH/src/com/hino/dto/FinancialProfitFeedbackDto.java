package com.hino.dto;

public class FinancialProfitFeedbackDto {
	private double sales_in = 0;
	private double t_sales_in = 0;
	private int point_exp = 0;
	private int point_in = 0;
	private int rep_order = 0;
	private int total_order = 0;
	
	public int getTotal_order() {
		return total_order;
	}
	public void setTotal_order(int totalOrder) {
		total_order = totalOrder;
	}
	public int getRep_order() {
		return rep_order;
	}
	public void setRep_order(int repOrder) {
		rep_order = repOrder;
	}
	public double getT_sales_in() {
		return t_sales_in;
	}
	public void setT_sales_in(double tSalesIn) {
		t_sales_in = tSalesIn;
	}
	public double getSales_in() {
		return sales_in;
	}
	public void setSales_in(double salesIn) {
		sales_in = salesIn;
	}
	public int getPoint_exp() {
		return point_exp;
	}
	public void setPoint_exp(int pointExp) {
		point_exp = pointExp;
	}
	public int getPoint_in() {
		return point_in;
	}
	public void setPoint_in(int pointIn) {
		point_in = pointIn;
	}
	
}
