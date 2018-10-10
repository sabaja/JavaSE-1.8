package com.multithreading;

public class MutexExample_IncrementThanDecrement 
{

	public static void main(String[] args) 
	{
			UtilsOperations uo = new UtilsOperations();
			Thread t1 = new Thread(new Thread1(uo), "T_somma\t");
			Thread t2 = new Thread(new Thread2(uo), "T_sottrazione\t");
			
			t1.start();
			t2.start();
 
	}

}

enum NUM
{
	POSVAL(5), NEGVAL(-5);
	private final int val;
	
	private NUM(int val){
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

class UtilsOperations
{
	private int data = 0;
	
	public void doOperation(NUM val)
	{
		synchronized (this) 
		{
			System.out.print("Il valore dato dal thread " +
							Thread.currentThread().getName() +" è di : ");
			for(int i = 0; i < 5; i++)
			{
				this.data += val.getVal();
				try 
				{
					Thread.sleep(1000);
					System.out.print(i != 4 ? getData() + " " : getData() + "\n");
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public int getData() 
	{
		return data;
	}
}

class Thread1 implements Runnable
{
	private UtilsOperations utils;
	
	public Thread1(UtilsOperations utils) 
	{
		super();
		this.utils = utils;
	}

	@Override
	public void run() 
	{
		utils.doOperation(NUM.POSVAL);
	} 
	
}

class Thread2 implements Runnable
{
	private UtilsOperations utils;
	
	public Thread2(UtilsOperations utils) 
	{
		super();
		this.utils = utils;
	}
	
	@Override
	public void run() 
	{
		utils.doOperation(NUM.NEGVAL);
	} 
	
}