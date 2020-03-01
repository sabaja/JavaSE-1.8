package com.io;

import java.io.*;

public class CloneCopyWithSerialization {

	public static void main(String[] args) {
		

	}

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * Attraverso ByteArray in e output e poi decorati tramite ObjectIN e Out
	 * prima scriviamo copiando l'oggetto passato come argomento poi
	 * creiamo tramite lettura un oggetto nuovo.
	 */
	public static <O extends Serializable> O cloneCopy(O obj) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oops = new ObjectOutputStream(out); //scrittura
		oops.writeObject(obj);
		oops.close();
		
		//crea una nuova allocazione per un array di byte
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(in); //lettura
		O objCloned = (O) ois.readObject();
		ois.close();
		return objCloned;
	}
}
