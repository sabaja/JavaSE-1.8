/**
Simulare con del codice funzionante la seguente situazione con quanto appreso in
questo modulo.
Un gruppo di 10 persone è all’osservatorio astronomico per ammirare il passaggio
di una cometa sfruttando il potente telescopio messo a disposizione dalla struttura. 
Solo una persona alla volta può usare il telescopio, e i responsabili concedono
solo tre minuti a persona per completare l’osservazione. Non esiste una fila, i partecipanti
accederanno al telescopio in maniera casuale. Ogni partecipante quindi attraverserà degli stati:

	 lo stato “In attesa” quando starà aspettando il suo turno. 
	Dura un tempo indefinito, che dipende da quando inizierà il turno del partecipante;

	 lo stato “Osservazione” quando starà osservando la cometa tramite il telescopio.
	Dura esattamente 3 minuti (ma è ovviamente possibile abbreviare questo
	tempo per eseguire l’esercizio);

	 lo stato “Finito” quando il turno è finito.
	Gli stati possono essere caratterizzati da delle stampe significative.
*/

package com.multithreadsExercises;

public class TelescopeTest {

	public static void main(String[] args) {
		Telescope telescope = new Telescope();
		Observer[] observer = { 
				new Observer(new StringBuffer("Andrea"), telescope), 
				new Observer(new StringBuffer("Gaia"), telescope),
				new Observer(new StringBuffer("Marta"), telescope),
				new Observer(new StringBuffer("Luca"), telescope),
				new Observer(new StringBuffer("Elena"), telescope),
				new Observer(new StringBuffer("Francesca"), telescope),
				new Observer(new StringBuffer("Luif"), telescope),
				new Observer(new StringBuffer("John"), telescope),
				new Observer(new StringBuffer("Paola"), telescope),
				new Observer(new StringBuffer("Samanta"), telescope) };

		for (Observer o : observer) {
			Thread t = new Thread(o);
			t.start();
		}

	}

}

enum Status{
	WAITING("\"I\'m wating for watching the stars by telescope\""),
	WATCHING("\"I\'m watching the stars by telescope\""),
	END("\"My turn is end, bye bye!\"");
	
	private String sentence;
	
	private Status(String sentence){
		this.sentence = sentence;
	}

	public String getSentence() {
		return sentence;
	}
	
}

class Telescope {
	/**
	 * watching is a {@link Boolean} variable and its default is false, Its
	 * scope is a flag-guard for single access to telescope object
	 */

	public synchronized void watchStar(Observer observer) {

		System.out.println("\nIt's the turn of " + observer.getMyName() + " observer!" );
		observer.setStatus(Status.WATCHING);
		observer.printStatus();
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		observer.setStatus(Status.END);
		observer.printStatus();

	}
}

/**
 * è possibile estendere la classe thread al posto dell'interfaccia Runnable
 * cosi si potrà lanciare il thread direttamente sull'oggetto Observer
 * observer.start().
 * Con l'implementazione dell'interfaccia Runnable invece sono costretto a 
 * creare un nuovo thread e a passare l'oggetto Observer al costruttore
 * del thread per lanciare il thread:
 * Thread t = new Thread(observer);
 * t.start(); 
 */
class Observer implements Runnable {

	// private StringBuffer status;
	private Telescope telescope;
	private StringBuffer name;
	private Status status;

	public Observer(StringBuffer name, Telescope telescope) {
		super();
		this.telescope = telescope;
		this.name = name;
		setStatus(Status.WAITING); 
		printStatus();
	}

	@Override
	public void run() {
		telescope.watchStar(this);
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void printStatus(){
		System.out.println("I'm " + name + " and " + status.getSentence());
	}

	public StringBuffer getMyName() {
		return name;
	}
}