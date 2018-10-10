package com.multithreading;

public class SynchronizedExample3
{
	public static class Sync
	{
		public void f()
		{
			try {
				System.out.println(
						Thread.currentThread().getName() + " inizia");
				Thread.sleep(1000);
				
				System.out.println("Sync.f()");
				Thread.sleep(1000);
				
				System.out.println(
						Thread.currentThread().getName() + " termina");
			}
			catch(InterruptedException e)
			{
				System.out.println(e);
			}
		}
	}

	static Sync sync = new Sync();

	public static class MyThread implements Runnable
	{
		public void run()
		{
			synchronized (sync) {

					sync.f();

			}

		}
	}

	public static void main(String[] argv)
	{
		Thread[] threads = new Thread[3];
		for(int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread( new MyThread() );
			threads[i].start();
		}
		
		
		for(int i = 0; i < threads.length; i++)
		{
			try {
				threads[i].join();
			}
			catch(InterruptedException e)
			{
				System.out.println(e);
			}
		}
	}
}