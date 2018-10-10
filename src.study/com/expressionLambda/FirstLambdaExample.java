package com.expressionLambda;



public class FirstLambdaExample {

	public static void main(String[] args) {
		NoLambdaThread.noLambdaThread();
		LamdaThread.lambdaThread();
		LamdaThread.alternativeSemanticlambdaThread();

	}

}

class NoLambdaThread {

	public static void noLambdaThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("No Lambda");
			}
		}).start();
	}
}

/**
 * 
 * @author sabaja
 * 
 *         Le lambda sono l'implemantazioni delle interfacce funzionali (Single
 *         Abstract Method)
 * 
 *         ([lista di parametri])->{codice funzionale};
 */
class LamdaThread {

	public static void lambdaThread() {
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {;}
			System.out.println("Here lambda");
		}).start();

	}
	
	public static void alternativeSemanticlambdaThread() {
		Runnable r = null;
		new Thread(r = ()-> {
			try{
				Thread.sleep(1400);
			} catch (InterruptedException e) {;}
			System.out.println("alternative lambda expression");
		}).start();
	}
}
