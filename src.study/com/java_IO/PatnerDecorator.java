package com.java_IO;


/**
 * 
 * @author sabaja
 * 
 * 	https://dellabate.wordpress.com/2011/02/28/gof-patterns-decorator/
 * 
 *	Component 							-	-	-	-	-	-	-	-	InputStream	-	-	-	-	-	-
 *										|																 |
 *										|																 |		
 *	Concrete		ByteArrayInputStream, FileInputStream,												 |	
 *	Component		ObjectInputStream, PipedInputStream, 												 |
 *					SequenceInputStream, StringBufferInputStream										 |
 *																										 |
 *																										 |
 *	Decorator																					  FileInputStream
 *																										 |
 *																										 |
 *																									     |
 *	Concrete  																		  BufferedInputStream, DataInputStream, 
 *	Decorator																		  LineNumberInputStream, PushbackInputStream
 *	
 */


/**
 * Prendiamo l’esempio iniziale: 
 * pensiamo al caso in cui abbiamo l’esigenza di monitorare l’invocazione di un metodo ma non abbiamo
 * la possibilità di modificare il codice. 
 * Utilizziamo il pattern Decorator per esigenze di debug pertanto “wrappiamo” un metodo
 * con delle semplici istruzioni print-screen.
 * 
 * Creiamo l’interfaccia Component che dichiara il metodo interessanto.
 */
interface MyComponent {
    public void operation();
}

/**
 * 
 * Implementiamo il metodo dichiarato nell’interfaccia MyComponent creando 
 * la classe ConcreteComponent.
 *
 */
class ConcreteComponent implements MyComponent {
    @Override
	public void operation(){
        System.out.println("- Concrete Component -");
    }
}

/**
* Definiamo l’interfaccia MyDecorator che si occupa di ereditare il
* metodo interessato da MyComponent e di interporsi con le classi 
* di decorazione concrete.
*/
interface MyDecorator extends MyComponent {
}

/**
 * 
 * Creiamo la classe LoggingDecorator che implementa l’interfaccia 
 * MyDecorator ed aggiunge le informazioni di debug prima e dopo 
 * l’esecuzione del metodo interessato.
 *
 */
class LoggingDecorator implements MyDecorator {
	 
    MyComponent myComponent = null;
 
    public LoggingDecorator(MyComponent myComponent){
        this.myComponent = myComponent;
    }
 
    public void operation() {
        System.out.println("First Logging");
        myComponent.operation();
        System.out.println("Last Logging");
    }
}

/**
 * 
 * La classe Client (PatnerDecorator) invoca la classe concreta LoggingDecorator passando
 * al costruttore il componente concreto, successivamente invoca il metodo
 * operation().
 *
 */
class PatnerDecorator {
    public static void main(String[] args) {
        MyComponent myComponent = new LoggingDecorator(new ConcreteComponent());
        myComponent.operation();
    }
}