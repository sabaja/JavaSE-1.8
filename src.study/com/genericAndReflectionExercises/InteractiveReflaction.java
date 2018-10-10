package com.genericAndReflectionExercises;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

class InteractiveReflaction {

	public static void main(String[] args) {
		
		System.out.println("This program will show you the contetns of these classes/interfaces: ");
		for(ClassFST classFST : ClassFST.values())
			System.out.println("- " + classFST.getName());
		System.out.println("Which Class do you want to show?");
		System.out.println( "Plesa enter:\n" 
				+ "1 for " + ClassFST.FIRST.getName() + '\n'
				+ "2 for " + ClassFST.SECOND.getName() + '\n'
				+ "3 for " + ClassFST.THIRD.getName() + '\n'
				+ "4 for " + ClassFST.FST.getName() + '\n');
		
		final String first = "b.com.exercises." + ClassFST.FIRST.getName();
		final String second = "b.com.exercises." + ClassFST.SECOND.getName();
		final String third = "b.com.exercises." + ClassFST.THIRD.getName();
		final String fst = "b.com.exercises." + ClassFST.FST.getName();
		
		int input = 0;//input
		Scanner in = new Scanner(System.in);
		input = in.nextInt();
		while(input < 1 || input > 4){
			System.out.println("Error - Wrong choice[ " + input +" ]");
			System.out.println( "Plesa enter:\n" 
					+ "1 for " + ClassFST.FIRST.getName() + '\n'
					+ "2 for " + ClassFST.SECOND.getName() + '\n'
					+ "3 for " + ClassFST.THIRD.getName() + '\n'
					+ "4 for " + ClassFST.FST.getName() + '\n');
			input = in.nextInt();
			
		}
		
		switch(input){
			case 1:
				printClass(first);
				break;
			case 2:
				printClass(second);
				break;
			case 3:
				printClass(third);
				break;
			case 4:
				printClass(fst);
				break;
		}
		in.close();
	}
	
	static void printClass(String cls)
	{		
		try 
		{
			Class<?> temp = Class.forName(cls);
			Constructor<?>[] constructor = temp.getConstructors();
			final int lenCon = constructor.length;
			if(lenCon > 0){  
				System.out.println("Constructor/s of class " + cls
						+ ":\n");
				
				for(int i = 0; i < lenCon; ++i){
					System.out.print(constructor[i].getName() + " (");
					Class<?>[] paramClass = constructor[i].getParameterTypes();
					final int lenParam = paramClass.length;
					if(lenParam > 0){
						for(int y = 0 ; y < lenParam; ++y){
							System.out.print(paramClass[y].getSimpleName() + " ");
						}
					System.out.println(")\n");
					}
				}
				
			}
			Method[] method = temp.getDeclaredMethods();
			final int lenMethod = method.length;
			if(lenMethod > 0){
				System.out.println("Method/s of class " + cls
						+ ":\n");
				for(int i = 0; i < lenMethod; i++ ){
					System.out.print(method[i].getReturnType().getSimpleName() + " "
									 + method[i].getName() + "(");
					final int lenParam = method[i].getParameterCount();
					if(lenParam > 0){
						Parameter[] p = method[i].getParameters();
						for(int y = 0 ; y < lenParam; ++y){
							System.out.print(p[y].getClass().getName() + " " 
								+ p[y].getName());
						}
					System.out.println(");\n");
					}
				}
			}
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}
}

enum ClassFST{
	FIRST(First.class.getSimpleName()), 
	SECOND(Second.class.getSimpleName()), 
	THIRD(Third.class.getSimpleName()), 
	FST(FST.class.getSimpleName());
	
	private  String name;
	
	private ClassFST(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

	

class First{
	public int id;
	public String name;
	
	public First(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int temp = 1;
		temp = temp * prime + id;
		temp = temp * prime + ((name == null) ? 0 : name.hashCode());
		return temp;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null) return false;
		if(obj == this)	return true;
		if(this.getClass() != obj.getClass()) return false;
		
		First other = (First) obj;
		if(this.id != other.id)
			return false;
		
		if(this.name == null){
			if(other.name != null) return false;
		}
		else if(!this.name.equals(other.name)) return false;
		
		return true;
	}
}

class Second{
	public int num;
	public String name;
	private ArrayList<Integer> list;

	public Second(int num, String name, ArrayList<Integer> list) {
		super();
		this.num = num;
		this.name = name;
		this.list = list;
	}

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Integer> getList() {
		return list;
	}
	
	public void setList(ArrayList<Integer> list) {
		this.list = list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
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
		Second other = (Second) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	
}

class Third<T> implements FST{
	public T foo;
	private T as;
	public int id;
	
	public Third(T foo, T as, int id) {
		super();
		this.foo = foo;
		this.as = as;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Third [foo=" + foo + ", as=" + as + ", id=" + id + "]";
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Number> int countEle(T element) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

interface FST{
	public static final double NUM = 234.12;
	public void print();
	public <T extends Number> int countEle(T element);
}