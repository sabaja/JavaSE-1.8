

package com.expressionLambda;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MangingException_withLambdaAndThreadCALLABLEInterface {

	public static void main(String[] args) {
		MyCallable cll = ()-> {
			System.out.println("Hellone");
			Thread.sleep(2000);
			System.out.println("Hellone's end");
			return null;
		};
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.submit(cll);
		service.shutdown();
		
		//Alternativa diretta con Callable<Void>
		
		Callable<Void> cll_1 = () ->{
			System.out.println("PD");
			Thread.sleep(2111);
			System.out.println("PD's end");
			return null;
		};
		ExecutorService _serv = Executors.newFixedThreadPool(1);
		_serv.submit(cll_1);
		_serv.shutdown();
		
	}
}

@FunctionalInterface
interface MyCallable extends Callable<Void>{
	@Override
	Void call() throws InterruptedException;
}

