package com.test1.entity;

public class Destination {
	public int x, y;
	public boolean isReached(float x, float y) {
		return (Math.abs(x - this.x + y - this.y) < 1) ? true : false;
		
	}
	Destination(float x, float y){
		this.x = (int) x;
		this.y = (int) y;
	}
}
