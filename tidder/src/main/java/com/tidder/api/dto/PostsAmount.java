package com.tidder.api.dto;

import java.io.Serializable;

public class PostsAmount implements Serializable {

	private static final long serialVersionUID = 3205045925934853133L;
	private int ammount;
	
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

}
