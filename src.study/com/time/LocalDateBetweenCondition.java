package com.time;

import java.time.LocalDate;
import java.time.Month;

public class LocalDateBetweenCondition {
	
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate computeDate;
	
	public LocalDateBetweenCondition() {
		super();
		this.startDate = LocalDate.of(2019, Month.MAY, 21);
		this.endDate = LocalDate.of(2019, Month.AUGUST, 31);
		this.computeDate = LocalDate.now().plusDays(26L);
	}
	
	public boolean checkPromotionDate() {
		return (computeDate.isAfter(startDate) && computeDate.isBefore(endDate)) 
			|| (endDate.isAfter(startDate) && endDate.isBefore(computeDate));
	}

	public LocalDate getComputeDate() {
		return computeDate;
	}

	public void setComputeDate(LocalDate computeDate) {
		this.computeDate = computeDate;
	}

	public static void main(String[] args) {
		LocalDateBetweenCondition obj = new LocalDateBetweenCondition();
		System.out.println(obj.getComputeDate());
		System.out.println(obj.checkPromotionDate());
	}

}
