package com.javaLang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

class Clone_DeepCopy {
 
	public static void main(String []args){
		Van c = new Van("pip", 3D), d;
		try{
			d = (Van)c.cloneDeepCopy();
			System.out.println(d.toString());
			d.printCol();
			ArrayList <String> ll = new ArrayList<>();
			ll.addAll(0, Arrays.asList("HHh","ell"));
			d.setCol(ll);
			d.printCol();
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		
	}
}

class Van {
	private String name;
	private double wheels;
	public ArrayList<String> col = new ArrayList<>(); 
	
	public Van(String name, double wheels){
		this.name = name;
		this.wheels = wheels;
		col.add("A"); col.add("B"); col.add("C");
	}
	
	public Van(String name, double wheels, ArrayList<String> list){
		this.name = name;
		this.wheels = wheels;
		col = list;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWheels() {
		return wheels;
	}

	public void setWheels(double wheels) {
		this.wheels = wheels;
	}
	
	public Collection<String> getCol() {
		return col;
	}

	public void setCol(ArrayList<String> col) {
		this.col = col;
	}

	public Object cloneDeepCopy() throws CloneNotSupportedException, NullPointerException {
		if(Objects.isNull(this))
			throw new NullPointerException();
		return new Van(this.name,this.wheels,this.col);
	}
	
	@Override
	public String toString(){
		return name + " " + wheels; 
	}
	
	public void printCol(){
		if(col != null){
			int len = col.size();
			Iterator<String> i = col.listIterator();
			while(i.hasNext()){
				if (((ListIterator<String>)i).nextIndex() == len-1)
					System.out.print(i.next() + "-END-");
				else
					System.out.print(i.next() + "." );
			}
			System.out.println();
		}
			
	}
}

