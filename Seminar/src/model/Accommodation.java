package model;

import java.io.Serializable;

public class Accommodation implements Serializable {

	private boolean isHotel;
	private boolean isParking;
	private Integer userId;

	public Accommodation() {
		isHotel = false;
		isParking = false;
		userId = null;
	}

	public Accommodation(boolean isHotel, boolean isParking, Integer userId) {
		this.isHotel = isHotel;
		this.isParking = isParking;
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean getIsHotel() {
		return isHotel;
	}

	public void setHotel(boolean isHotel) {
		this.isHotel = isHotel;
	}

	public boolean getIsParking() {
		return isParking;
	}

	public void setParking(boolean isParking) {
		this.isParking = isParking;
	}
}
