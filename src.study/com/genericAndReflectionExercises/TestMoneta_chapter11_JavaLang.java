package com.genericAndReflectionExercises;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class TestMoneta_chapter11_JavaLang {

	public static void main(String[] args) {
        Moneta monetaDaVentiCentesimi = new Moneta(Valore.VENTI_CENTESIMI);
        Moneta monetaDaUnCentesimo = new Moneta(Valore.UN_CENTESIMO);
        Moneta monetaDaUnEuro = new Moneta(Valore.UN_EURO);
  
        // Creaiamo un portamonete con 8 monete
        PortaMonete portaMonete = new PortaMonete(Valore.DUE_CENTESIMI,
                Valore.CINQUE_CENTESIMI, Valore.UN_EURO, Valore.DIECI_CENTESIMI,
                Valore.CINQUANTA_CENTESIMI, Valore.DIECI_CENTESIMI, Valore.UN_EURO,
                Valore.DUE_EURO);
        portaMonete.stato();

        Class<Moneta> classMoneta = Moneta.class;
        Constructor<Moneta> constructor;
		try {
			constructor = classMoneta.getConstructor(Valore.class);
			constructor.newInstance(Valore.DUE_EURO);
		} catch (NoSuchMethodException | SecurityException |
				InstantiationException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}



class Moneta {

    /**
     * La valuta è una costante settata al valore EURO.
     */
    public final static String VALUTA = "EURO";

    static {
        System.out.println("Caricata la classe Moneta con valuta = " + VALUTA);
    }
    /**Jacccccc*/
    static{
    	System.out.println("Blocco statico qualunque");
    }
    /**
     * Rappresenta il valore della moneta in centesimi.
     */
    private final Valore valore;

    /**
     * Costruttore che prende in input il valore della moneta.
     *
     * @param valore il valore della moneta.
     */
    public Moneta(Valore valore) {
        this.valore = valore;
        System.out.println("Creata una " + getDescrizione());
    }

    /**
     * Restituisce il valore della variabile d'istanza valore.
     *
     * @return il valore della variabile d'istanza valore.
     */
    public Valore getValore() {
        return valore;
    }

    /**
     * Ritorna una descrizione della moneta corrente.
     *
     * @return una descrizione della moneta corrente.
     */
    public String getDescrizione() {
        String descrizione = "moneta da " + valore.getStringaDescrittiva()
                + VALUTA;
        return descrizione;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valore == null) ? 0 : valore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moneta other = (Moneta) obj;
		if (valore != other.valore)
			return false;
		return true;
	}



}

enum Valore {

    UN_CENTESIMO(1) {
                @Override
                public String getStringaDescrittiva() {
                    return getValore() + " centesimi di ";
                }
            },
    DUE_CENTESIMI(2),
    CINQUE_CENTESIMI(5),
    DIECI_CENTESIMI(10),
    VENTI_CENTESIMI(20),
    CINQUANTA_CENTESIMI(50),
    UN_EURO(1) {
                @Override
                public String getStringaDescrittiva() {
                    return getValore() + " ";
                }
            },
    DUE_EURO(2) {
                @Override
                public String getStringaDescrittiva() {
                    return getValore() + " ";
                }
            };

    private int valore;

    private Valore(int valore) {
        this.valore = valore;
    }

    public String getStringaDescrittiva() {
        return getValore() + " centesimi di ";
    }

    public int getValore() {
        return valore;
    }
}

@SuppressWarnings("serial")
class MonetaNonTrovataException extends Exception {
    public MonetaNonTrovataException(String message) {
        super(message);
    }
}

@SuppressWarnings("serial")
class MonetaNullException extends RuntimeException{
    public MonetaNullException() {
        super("La moneta passata era null");
    }    
}

@SuppressWarnings("serial")
class PortaMonetePienoException extends Exception {
    public PortaMonetePienoException(String message) {
        super(message);
    }
}
/**
 * Astrae il concetto di portamonete che può contenere un numero limitato di
 * monete.
 *
 * @author Claudio De Sio Cesari
 */
class PortaMonete {

    /**
     * Costante che rappresenta il numero massimo di monete che questo portamonete
     * può contenere.
     */
    private static final int DIMENSIONE = 10;
    /**
     * Un array list che contiene un numero limitato di monete.
     */
    private final List<Moneta> monete = new ArrayList<>(DIMENSIONE);

    /**
     * Crea un oggetto portamonete contenente monete i cui valori sono
     * specificati dal varargs valori. numero di valori che il portamonete. Se
     * il numero di elementi del varargs valori è maggiore del numero massimo di
     * monete che il portamonete corrente può contenere, allora viene gestita
     * un'eccezione.
     *
     * @param valori un varargs di valori di monete.
     */
    public PortaMonete(Valore... valori) {
        assert monete.size() <= DIMENSIONE;
        try {
            int numeroMonete = valori.length;
            for (int i = 0; i < numeroMonete; i++) {
                if (i >= DIMENSIONE) {
                    throw new PortaMonetePienoException("Sono state inserite solo le prime + " + DIMENSIONE + " monete!");
                }
                monete.add(new Moneta(valori[i]));
            }
            //   } catch (PortaMonetePienoException | NullPointerException exc) {
        } catch (PortaMonetePienoException exc) {
            System.out.println(exc.getMessage());
        } catch (NullPointerException exc) {
            System.out.println("Il portamonete è stato creato vuoto");
        }
        assert monete.size() <= DIMENSIONE;
    }

    /**
     * Aggiunge una moneta al portamonete. Se questo è pieno la moneta non è
     * aggiunta e viene stampato un errore significativo.
     *
     * @param moneta la moneta da aggiungere.
     * @throws PortaMonetePienoException
     */
    public void aggiungi(Moneta moneta) throws PortaMonetePienoException {
        try {
            System.out.println("Proviamo ad aggiungere una " + moneta.getDescrizione());
        } catch (NullPointerException exc) {
            throw new MonetaNullException();
        }
        int indiceLibero = primoIndiceLibero();
        if (indiceLibero == -1) {
            throw new PortaMonetePienoException("Portamonete pieno! La moneta "
                    + moneta.getDescrizione() + " non è stata aggiunta...");
        } else {
            monete.set(indiceLibero, moneta);
            System.out.println("E' stata aggiunta una " + moneta.getDescrizione());
        }
    }

    /**
     * Esegue un prelievo della moneta specificata dal portamonete corrente. Nel
     * caso non sia presente la moneta specificata, un errore significativo
     * verrà stampato e null verrà ritornato.
     *
     * @param moneta la moneta da prelevare.
     * @return la moneta trovata o null se non trovata.
     * @throws MonetaNonTrovataException
     */
    public Moneta preleva(Moneta moneta) throws MonetaNonTrovataException {
        try {
            System.out.println("Proviamo a prelevare una " + moneta.getDescrizione());
        } catch (NullPointerException exc) {
            throw new MonetaNullException();
        }
        Moneta monetaTrovata = null;
        int indiceMonetaTrovata = indiceMonetaTrovata(moneta);
        if (indiceMonetaTrovata == -1) {
            throw new MonetaNonTrovataException("Moneta non trovata!");
        } else {
            monetaTrovata = moneta;
            monete.set(indiceMonetaTrovata, moneta);
            System.out.println("Una " + moneta.getDescrizione() + " prelevata");
        }
        return monetaTrovata;
    }

    /**
     * Stampa il contenuto del portamonete.
     */
    public void stato() {
        System.out.println("Il portamonete contiene:");
        for (Moneta moneta : monete) {
            if (moneta == null) {
                break;
            }
            System.out.println("Una " + moneta.getDescrizione());
        }
    }

    /**
     * Restituisce il primo indice libero nell'array delle monete o -1 se il
     * portamonete è pieno.
     *
     * @return il primo indice libero nell'array delle monete o -1 se il
     * portamonete è pieno.
     */
    private int primoIndiceLibero() {
        int indice = monete.indexOf(null);
        return indice;
    }

    private int indiceMonetaTrovata(Moneta moneta) {
        int indiceMonetaTrovata = -1;
        final int size = monete.size();
        for (int i = 0; i < size; i++) {
            if (monete.get(i) == null) {
                break;
            }
            if (monete.get(i).equals(moneta)) {
                indiceMonetaTrovata = i;
                break;
            }
        }
        return indiceMonetaTrovata;
    }
}
