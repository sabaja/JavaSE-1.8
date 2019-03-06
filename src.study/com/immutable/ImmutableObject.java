package com.immutable;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Set;

/**
 * Una classe immutabile è ThreadSafe è meglio dichararla final come i suoi
 * metodi regole:
 * 
 * rendere tutti i campi final; rendere la classe final o comunque impedire
 * l’estensione al di fuori del package di appartenenza (per mantenere un certo
 * grado di flessibilità); eliminare tutti i metodi che modifichino lo stato
 * interno dell’oggetto (detti mutatori); ogni variabile membro che fa
 * riferimento a oggetti mutabili (come ad esempio array, collezioni o la classe
 * java.util.Date): va resa privata e non deve mai essere restituita all’esterno
 * se non attraverso una sua copia; deve avere reference solo all’interno della
 * classe; quindi nel caso in cui la variabile membro sia inizializzata nel
 * costruttore con un oggetto mutabile, va memorizza una copia dell’oggetto al
 * posto dell’oggetto originale; non deve essere variata dopo la costruzione
 * dell’oggetto (si vedrà una possibile eccezione a questa regola nel codice di
 * Pizza).
 * 
 * link: http://codingjam.it/le-classi-immutabili-in-java/
 * 
 * @author sabaja
 *
 */

final class ImmutableObject {
	private final int integer; // variabile d'istanza primitiva
	private final String string; // variabile d'istanza complessa immutabile
	private final Calendar calendar; // variabile d'istanza complessa
	// Costruttore che prende le variabili dall'esterno

	public ImmutableObject(int integer, String string, Calendar calendar) {
		this.integer = integer;
		this.string = string;
		// segue punto 3
		this.calendar = (Calendar) calendar.clone();
	}

	// Questo metodo segue il punto 4
	public final Calendar getDate() {
		return (Calendar) calendar.clone();
	}

	// Metodo di test
	public final void stampaCalendar() {
		System.out.println(calendar);
	}
}

/**
 * esempio + serio dal sito http://codingjam.it/le-classi-immutabili-in-java/
 */

final class Pizza {

	public static final Pizza MARGHERITA = new Pizza(Formato.NORMALE, EnumSet.noneOf(Ingrediente.class));
	public static final Pizza PROSCIUTTO = new Pizza(Formato.NORMALE, EnumSet.of(Ingrediente.PROSCIUTTO));
	public static final Pizza CALZONE_PROSCIUTTO = new Pizza(Formato.CALZONE, EnumSet.of(Ingrediente.PROSCIUTTO));

	public enum Formato {
		NORMALE, BABY, MAXI, CALZONE
	}

	public enum Ingrediente {
		PROSCIUTTO, FUNGHI, PATATE, SALSICCIA, CARCIOFI, SPECK
	}

	private final Formato formato;
	private final Set<Ingrediente> ingredienti;
	private Double prezzo;
	private String toString;

	public Formato getFormato() {
		return formato;
	}

	public Set<Ingrediente> getIngredienti() {
		return EnumSet.copyOf(ingredienti);
	}

	private Pizza(Formato formato, Set<Ingrediente> ingredienti) {
		this.formato = formato;
		this.ingredienti = EnumSet.copyOf(ingredienti);
	}

	public double getPrezzo() {
		if (prezzo == null) {
			prezzo = 0.0;
			switch (formato) {
			case BABY:
				prezzo += 2.0;
				break;
			case NORMALE:
			case CALZONE:
				prezzo += 4.0;
				break;
			case MAXI:
				prezzo += 7.0;
				break;
			}
			prezzo += ingredienti.size() * 1.0;
		}
		return prezzo;
	}

	public String toString() {
		if (toString == null) {
			toString = formato + " " + ingredienti + " " + new DecimalFormat("#,##").format(getPrezzo());
		}
		return toString;
	}
}

/**
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
 *
 * A Strategy for Defining Immutable Objects
 * 
 * The following rules define a simple strategy for creating immutable objects.
 * Not all classes documented as "immutable" follow these rules. This does not
 * necessarily mean the creators of these classes were sloppy — they may have
 * good reason for believing that instances of their classes never change after
 * construction. However, such strategies require sophisticated analysis and are
 * not for beginners.
 * 
 * Don't provide "setter" methods — methods that modify fields or objects
 * referred to by fields. Make all fields final and private. Don't allow
 * subclasses to override methods. The simplest way to do this is to declare the
 * class as final. A more sophisticated approach is to make the constructor
 * private and construct instances in factory methods. If the instance fields
 * include references to mutable objects, don't allow those objects to be
 * changed: Don't provide methods that modify the mutable objects. Don't share
 * references to the mutable objects. Never store references to external,
 * mutable objects passed to the constructor; if necessary, create copies, and
 * store references to the copies. Similarly, create copies of your internal
 * mutable objects when necessary to avoid returning the originals in your
 * methods. Applying this strategy to SynchronizedRGB results in the following
 * steps:
 * 
 * There are two setter methods in this class. The first one, set, arbitrarily
 * transforms the object, and has no place in an immutable version of the class.
 * The second one, invert, can be adapted by having it create a new object
 * instead of modifying the existing one. All fields are already private; they
 * are further qualified as final. The class itself is declared final. Only one
 * field refers to an object, and that object is itself immutable. Therefore, no
 * safeguards against changing the state of "contained" mutable objects are
 * necessary. After these changes, we have ImmutableRGB:
 */

final class ImmutableRGB {

    // Values must be between 0 and 255.
    final private int red;
    final private int green;
    final private int blue;
    final private String name;

    private void check(int red,
                       int green,
                       int blue) {
        if (red < 0 || red > 255
            || green < 0 || green > 255
            || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ImmutableRGB(int red,
                        int green,
                        int blue,
                        String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }


    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    public ImmutableRGB invert() {
        return new ImmutableRGB(255 - red,
                       255 - green,
                       255 - blue,
                       "Inverse of " + name);
    }
}