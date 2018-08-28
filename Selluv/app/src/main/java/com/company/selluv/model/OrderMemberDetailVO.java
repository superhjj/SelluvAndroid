package com.company.selluv.model;

import java.util.Arrays;

public class OrderMemberDetailVO {
	private String order_date;
	private String order_member;
	private String[] answer;
	
	public OrderMemberDetailVO(){	
	}
	
	public OrderMemberDetailVO(String order_date, String order_member, String[] answer) {
		super();
		this.order_date = order_date;
		this.order_member = order_member;
		this.answer = answer;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_member() {
		return order_member;
	}

	public void setOrder_member(String order_member) {
		this.order_member = order_member;
	}

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "OrderMemberDetailVO [order_date=" + order_date + ", order_member=" + order_member + ", answer="
				+ Arrays.toString(answer) + "]";
	}
}
