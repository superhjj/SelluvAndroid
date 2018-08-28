package com.company.selluv.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ItemVO implements Serializable{

	String itemNum;
	String itemTitle;
	String itemDescript;
	String itemNecessry;
	String itemType;
	String options;
	
	public ItemVO(){
		this.itemNum = "항목 제목";
		this.itemType = "SA";
		this.itemTitle = "항목 설명";
		this.itemDescript = "";
		this.itemNecessry = "N";
		this.options = "";
	}
	
	public ItemVO(String itemNum, String itemTitle, String itemDescript, String itemNecessary, String itemType){
		this.itemNum = itemNum;
		this.itemTitle = itemTitle;
		this.itemDescript = itemDescript;
		this.itemType = itemType;
		this.itemNecessry = itemNecessary;
		this.options = "";
	}
	
	public ItemVO(String itemNum, String itemTitle, String itemDescript, String itemNecessary, String itemType, String options){
		this.itemNum = itemNum;
		this.itemTitle = itemTitle;
		this.itemDescript = itemDescript;
		this.itemType = itemType;
		this.itemNecessry = itemNecessary;
		this.options = options;
	}


	public String getItemDescript() {
		return itemDescript;
	}

	public void setItemDescript(String itemDescript) {
		this.itemDescript = itemDescript;
	}

	public String getItemNecessry() {
		return itemNecessry;
	}

	public void setItemNecessry(String itemNecessry) {
		this.itemNecessry = itemNecessry;
	}

	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "ItemVO [itemNum=" + itemNum + ", itemTitle=" + itemTitle + ", itemDescript=" + itemDescript
				+ ", itemNecessry=" + itemNecessry + ", itemType=" + itemType + ", options=" + options + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemDescript == null) ? 0 : itemDescript.hashCode());
		result = prime * result + ((itemNecessry == null) ? 0 : itemNecessry.hashCode());
		result = prime * result + ((itemNum == null) ? 0 : itemNum.hashCode());
		result = prime * result + ((itemTitle == null) ? 0 : itemTitle.hashCode());
		result = prime * result + ((itemType == null) ? 0 : itemType.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
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
		ItemVO other = (ItemVO) obj;
		if (itemDescript == null) {
			if (other.itemDescript != null)
				return false;
		} else if (!itemDescript.equals(other.itemDescript))
			return false;
		if (itemNecessry == null) {
			if (other.itemNecessry != null)
				return false;
		} else if (!itemNecessry.equals(other.itemNecessry))
			return false;
		if (itemNum == null) {
			if (other.itemNum != null)
				return false;
		} else if (!itemNum.equals(other.itemNum))
			return false;
		if (itemTitle == null) {
			if (other.itemTitle != null)
				return false;
		} else if (!itemTitle.equals(other.itemTitle))
			return false;
		if (itemType == null) {
			if (other.itemType != null)
				return false;
		} else if (!itemType.equals(other.itemType))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		return true;
	}

	
	
	
}
