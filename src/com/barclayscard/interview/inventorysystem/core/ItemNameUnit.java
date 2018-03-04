package com.barclayscard.interview.inventorysystem.core;

public class ItemNameUnit implements Comparable<ItemNameUnit> {

	String itemName = null;
	Boolean isDeleted = false;

	/**
	 * @param itemName
	 * @param isDeleted
	 */
	public ItemNameUnit(String itemName, Boolean isDeleted) {
		super();
		this.itemName = itemName;
		this.isDeleted = isDeleted;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isDeleted == null) ? 0 : isDeleted.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
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
		ItemNameUnit other = (ItemNameUnit) obj;
		if (isDeleted == null) {
			if (other.isDeleted != null)
				return false;
		} else if (!isDeleted.equals(other.isDeleted))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

	@Override
	public int compareTo(ItemNameUnit arg0) {
		return (this.getItemName()).compareTo(arg0.getItemName());
	}

	
	
}
