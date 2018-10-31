package com.miscellaneus;

public class TryWithResourceGetSuppressed {

	public static void main(String[] args) throws Exception {

		try (OpenDoor door = new OpenDoor()) {
			door.swing(); /* this throws a SwingExecption */
		} catch (Exception e) {
			System.out.println("Is there a draft? " + e.getClass());
			Throwable[] throwables = e.getSuppressed();
//			final int LEN = throwables.length; 
//			for(int i = 0; i < LEN ; i++){
//				System.out.println("Error " + throwables[i].toString());
//			}
			for(Throwable t : throwables){
				System.out.println(t.toString());
			}
		} finally {
			System.out.println("I'm putting a sweater on, regardless. ");
		}
	}
}

class OpenException extends Exception {
}

class SwingException extends Exception {
}

class CloseException extends Exception {
}

class OpenDoor implements AutoCloseable {

	public OpenDoor() throws Exception {
		System.out.println("The door is open.");
	}

	public void swing() throws Exception {
		System.out.println("The door is becoming unhinged.");
		throw new SwingException();
	}

	public void close() throws Exception {
		System.out.println("The door is closed.");
		throw new CloseException(); // throwing CloseException
	}
}