package com.company.selluv.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OrderMemberViewVO implements Serializable{
	private String form_title;
	private String form_code;
	private String member_id;
	private String order_date;
	private String profile_img;
	
	public OrderMemberViewVO() {	
	}
	
	public OrderMemberViewVO(String form_title, String form_code, String member_id,
			String order_date, String profile_img) {
		super();
		this.form_title = form_title;
		this.form_code = form_code;
		this.member_id = member_id;
		this.order_date = order_date;
		this.profile_img = profile_img;
	}



	public String getForm_title() {
		return form_title;
	}

	public void setForm_title(String form_title) {
		this.form_title = form_title;
	}

	public String getForm_code() {
		return form_code;
	}

	public void setForm_code(String form_code) {
		this.form_code = form_code;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	@Override
	public String toString() {
		return "OrderMemberViewVO [form_title=" + form_title + ", form_code=" + form_code + ", member_id=" + member_id
				+ ", order_date=" + order_date + ", profile_img=" + profile_img + "]";
	}
}
