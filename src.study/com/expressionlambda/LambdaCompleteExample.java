package com.expressionlambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaCompleteExample {

	public static void main(String[] args) {

		ActorsManager manager = new ActorsManager();
		Movie movie = new Movie();
		movie.viewActors(manager.getActorsByAge(30, QueryOperator.HIGHER));
		System.out.println();
		movie.viewActors(manager.getActorsBySalary(3_000_000D, QueryOperator.HI_EQ));
		movie.viewActors(manager.getActorsByName("Jacopo"));
		List<String> exemple = new ArrayList<>(Arrays.asList("Ele1","ele2"));
		TreeSet<Integer> is = exemple.stream(). 
		// peek: debug streams without changes
		peek(e -> System.out.println("exemple ->" + e)). 
		// map: convert every element into something  
		map(e -> e.hashCode()). 
		// filter: pass some elements through
		filter(e -> ((e.hashCode() % 2) == 0)).   
		// collect the stream into a collection 
		collect(Collectors.toCollection(TreeSet::new));
		is.stream().forEach(i -> System.out.println("treeset ->" + i)); 
	}

}

class Actor {
	private String name;
	private int age;
	private double salary;

	public Actor(String name, int age, double d) {
		super();
		this.name = name;
		this.age = age;
		this.salary = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Actor [name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
}

/**
 * 
 * @author SabatiniJa
 * 
 * @param <T>
 *            Same of Predicate
 */
@FunctionalInterface
interface Filter<T> {
	boolean test(T actor);
}

/**
 * 
 * @author SabatiniJa Simula il dB degli attori
 */
class CastingCache {

	private static CastingCache instance;
	private static List<Actor> actors = new ArrayList<>();

	static {
		instance = new CastingCache();
		actors.add(new Actor("James Hook", 45, 1_500_000D));
		actors.add(new Actor("Cristine Roming", 23, 500_000D));
		actors.add(new Actor("Foster Ruby", 52, 2_000_000D));
		actors.add(new Actor("Francy Corbin", 32, 1_000_000D));
		actors.add(new Actor("Scarlet Johansson", 35, 3_000_000D));
		actors.add(new Actor("Natalie Portman", 26, 5_000_000D));
	}

	public static List<Actor> getActors() {
		if (Objects.isNull(instance)) {
			instance = new CastingCache();
		}

		return CastingCache.actors;
	}
}

class UtilityCastingCache extends CastingCache {

}

/**
 * 
 * @author SabatiniJa ActorsFilter è una classe contenente una serie di metodi
 *         che filtrano sulle variabili d'isanza della classe Actor. Tali metodi
 *         filtrano su una List non modificabile di Attori e hanno lo scopo di
 *         creare una sottoinsieme di attori inserendoli in un'istanza di
 *         List<Actor> definita come ArrayList<> in base al @Param
 *         Predicate<Actor> filter, il cui valore è passato a runtime da parte
 *         del chiamante.
 */
class ActorsFilter {
	private final static List<Actor> listActors;

	static {
		listActors = Collections.unmodifiableList(CastingCache.getActors());
	}

	public List<Actor> getActorsByAge(Predicate<Actor> filter) {
		List<Actor> actors = getDataByFilter(filter);
		Comparator<Actor> comp = (a1, a2) -> Integer.compare(a1.getAge(), a2.getAge());
		Collections.sort(actors, comp);
		return actors;
	}

	public List<Actor> getActorsByName(Predicate<Actor> filter) {
		return getDataByFilter(filter);
	}

	public List<Actor> getActorsBySalary(Predicate<Actor> filter) {
		List<Actor> actors = getDataByFilter(filter);
		Comparator<Actor> comp = (a1, a2) -> Double.compare(a1.getSalary(), a2.getSalary());
		Collections.sort(actors, comp);
		return getDataByFilter(filter);
	}

	private List<Actor> getDataByFilter(Predicate<Actor> filter) {
		List<Actor> actors = new ArrayList<>();
		Iterator<Actor> iAct = listActors.iterator();
		while (iAct.hasNext()) {
			Actor actor = iAct.next();
			if (filter.test(actor)) {
				actors.add(actor);
			}
		}
		return actors;
	}

}

enum QueryOperator {
	HIGHER(">"), MINOR("<"), EQUAL("=="), HI_EQ(">="), MIN_EQ("<="), NOT_EQ("!=");
	private String operator;

	QueryOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}
}

class ActorsFilterFacade {
	private ActorsFilter actorsFilter;
	private List<Actor> actors = new ArrayList<>();

	public ActorsFilterFacade() {
		this.actorsFilter = new ActorsFilter();// da rivedere con la DI in
												// spring
	}

	public ActorsFilterFacade(ActorsFilter actorsFilter) {
		this.actorsFilter = actorsFilter;// DI sarebbe cosi..e da togliere
											// costruttore di default
	}

	public List<Actor> getActorsByAge(int age, QueryOperator operator) {
		switch (operator) {
		case HIGHER:
			actors = actorsFilter.getActorsByAge((Actor actor) -> actor.getAge() > age);
			if (actors == null || actors.isEmpty())
				defaultAddActorByAge();
			break;

		case MINOR:
			actors = actorsFilter.getActorsByAge((Actor actor) -> actor.getAge() < age);
			if (actors == null || actors.isEmpty())
				defaultAddActorByAge();
			break;

		case EQUAL:
			actors = actorsFilter.getActorsByAge((Actor actor) -> actor.getAge() == age);
			if (actors == null || actors.isEmpty())
				defaultAddActorByAge();
			break;

		case HI_EQ:
			actors = actorsFilter.getActorsByAge((Actor actor) -> actor.getAge() >= age);
			if (actors == null || actors.isEmpty())
				defaultAddActorByAge();
			break;

		case MIN_EQ:
			actors = actorsFilter.getActorsByAge((Actor actor) -> actor.getAge() <= age);
			if (actors == null || actors.isEmpty())
				defaultAddActorByAge();
			break;

		case NOT_EQ:
			actors = actorsFilter.getActorsByAge((Actor actor) -> actor.getAge() < age && actor.getAge() > age);
			if (actors == null || actors.isEmpty())
				defaultAddActorByAge();
			break;

		default:
			actors.add(new Actor("No Actors found by age", 0, 0));
			break;
		}
		return actors;

	}

	public List<Actor> getActorsByName(String name) {
		actors = actorsFilter.getActorsByName(actor -> actor.getName().equals(name));
		if (actors == null || actors.isEmpty()) {
			defaultAddActorByName();
			return actors;
		}
		return actors;

	}

	public List<Actor> getActorsBySalary(double salary, QueryOperator operator) {
		switch (operator) {
		case HIGHER:
			actors = actorsFilter.getActorsBySalary((Actor actor) -> actor.getSalary() > salary);
			if (actors == null || actors.isEmpty())
				defaultAddActorBySalary();
			break;

		case MINOR:
			actors = actorsFilter.getActorsBySalary((Actor actor) -> actor.getSalary() < salary);
			if (actors == null || actors.isEmpty())
				defaultAddActorBySalary();
			break;

		case EQUAL:
			actors = actorsFilter.getActorsBySalary((Actor actor) -> actor.getSalary() == salary);
			if (actors == null || actors.isEmpty())
				defaultAddActorBySalary();
			break;

		case HI_EQ:
			actors = actorsFilter.getActorsBySalary((Actor actor) -> actor.getSalary() >= salary);
			if (actors == null || actors.isEmpty())
				defaultAddActorBySalary();
			break;

		case MIN_EQ:
			actors = actorsFilter.getActorsBySalary((Actor actor) -> actor.getSalary() <= salary);
			if (actors == null || actors.isEmpty())
				defaultAddActorBySalary();
			break;

		case NOT_EQ:
			actors = actorsFilter
					.getActorsBySalary((Actor actor) -> actor.getSalary() < salary && actor.getSalary() > salary);
			if (actors == null || actors.isEmpty())
				defaultAddActorBySalary();
			break;

		default:
			defaultAddActorByAge();
			break;
		}
		return actors;

	}

	private void defaultAddActorByAge() {
		actors.add(new Actor("No Actors found by age", 0, 0));
	}

	private void defaultAddActorByName() {
		actors.add(new Actor("No Actors found by Name", 0, 0));
	}

	private void defaultAddActorBySalary() {
		actors.add(new Actor("No Actors found by Salary", 0, 0));
	}
}

class ActorsManager {
	private ActorsFilterFacade actorsFilterFacade;

	public ActorsManager() {
		this.actorsFilterFacade = new ActorsFilterFacade();
	}

	public List<Actor> getActorsByAge(int age, QueryOperator operator) {
		return actorsFilterFacade.getActorsByAge(age, operator);
	}

	public List<Actor> getActorsByName(String name) {
		return actorsFilterFacade.getActorsByName(name);
	}

	public List<Actor> getActorsBySalary(double salary, QueryOperator operator) {
		return actorsFilterFacade.getActorsBySalary(salary, operator);
	}
}

class Movie {
	// private List<Actor> actors;

	public Movie() {
	}

	public void viewActors(List<Actor> actors) {
		actors.stream().forEach(actor -> System.out.println(actor.toString()));
	}

}
