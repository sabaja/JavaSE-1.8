package com.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * http://www.baeldung.com/java-completablefuture
 *
 */
public class CompletableFutureExample {
	
	public static void main(String[] args) {
		CompletableFutureExample exe = new CompletableFutureExample();
		System.out.println("START");
		try {
			Future<String> completableFuture = exe.calculateAsync();
			String result = completableFuture.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(1000);
			return completableFuture.complete("Hello");
		});

		return completableFuture;
	}
	/**
	 * with cancellation
	 * @return
	 * @throws InterruptedException
	 */
	public Future<String> calculateAsyncWithCancellation() throws InterruptedException {
	    CompletableFuture<String> completableFuture = new CompletableFuture<>();
	 
	    Executors.newCachedThreadPool().submit(() -> {
	        Thread.sleep(500);
	        completableFuture.cancel(false);
	        return null;
	    });
	 
	    return completableFuture;
	}
}
