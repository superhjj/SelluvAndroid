package com.company.selluv.model;

import java.util.Date;

public class OrderDetailViewVO {
	private String profileImg;
	private String sellerId;
	private String buyerId;
	private String formTitle;
	private Date orderDate;
	private String formCode;
	
	public OrderDetailViewVO() {
	
	}
	public OrderDetailViewVO(String profileImg,String sellerId,String buyerId,String formTitle,Date orderDate,String formCode){
		this.profileImg=profileImg;
		this.sellerId=sellerId;
		this.buyerId=buyerId;
		this.formTitle=formTitle;
		this.orderDate=orderDate;
		this.formCode=formCode;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public String getSellerId() {
		return sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public String getFormCode() {
		return formCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyerId == null) ? 0 : buyerId.hashCode());
		result = prime * result + ((formCode == null) ? 0 : formCode.hashCode());
		result = prime * result + ((formTitle == null) ? 0 : formTitle.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((profileImg == null) ? 0 : profileImg.hashCode());
		result = prime * result + ((sellerId == null) ? 0 : sellerId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailViewVO other = (OrderDetailViewVO) obj;
		if (buyerId == null) {
			if (other.buyerId != null)
				return false;
		} else if (!buyerId.equals(other.buyerId))
			return false;
		if (formCode == null) {
			if (other.formCode != null)
				return false;
		} else if (!formCode.equals(other.formCode))
			return false;
		if (formTitle == null) {
			if (other.formTitle != null)
				return false;
		} else if (!formTitle.equals(other.formTitle))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (profileImg == null) {
			if (other.profileImg != null)
				return false;
		} else if (!profileImg.equals(other.profileImg))
			return false;
		if (sellerId == null) {
			if (other.sellerId != null)
				return false;
		} else if (!sellerId.equals(other.sellerId))
			return false;
		return true;
	}
	public String toString() {
		return "OrderDetailViewVO [profileImg=" + profileImg + ", sellerId=" + sellerId + ", buyerId=" + buyerId
				+ ", formTitle=" + formTitle + ", orderDate=" + orderDate + ", formCode=" + formCode + "]";
	}
	
}
