package com.collections.streamAPI;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;



/**
 * 
 * @author sabaja
 * Implementations of Collector that implement various useful reduction operations,
 * such as accumulating elements into collections, summarizing elements 
 * according to various criteria, etc.
 * The following are examples of using the predefined 
 * collectors to perform common mutable reduction tasks:
 * 
 * // Accumulate names into a List
     List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());

     // Accumulate names into a TreeSet
     Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));

     // Convert elements to strings and concatenate them, separated by commas
     String joined = things.stream()
                           .map(Object::toString)
                           .collect(Collectors.joining(", "));

     // Compute sum of salaries of employee
     int total = employees.stream()
                          .collect(Collectors.summingInt(Employee::getSalary)));

     // Group employees by department
     Map<Department, List<Employee>> byDept
         = employees.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment));

     // Compute sum of salaries by department
     Map<Department, Integer> totalByDept
         = employees.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment,
                                                   Collectors.summingInt(Employee::getSalary)));

     // Partition students into passing and failing
     Map<Boolean, List<Student>> passingFailing =
         students.stream()
                 .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));

 */
public class StreamCollectors {
	public static void main(String[] args) {
		Collection<SmartPhone_> phones = new HashSet<>();
		phones.add(new SmartPhone_("Iphone", "Apple", 890.587));
		phones.add(new SmartPhone_("IPad", "Apple",999.22));
		phones.add(new SmartPhone_("One", "Htc", 543.86));
		phones.add(new SmartPhone_("Galaxy 3", "Samsung", 589.56));
		phones.add(new SmartPhone_("Galaxy Note", "Samsung", 300.56));
		phones.add(new SmartPhone_("Galaxy TabA", "Samsung", 213.56));
		
		//Uniamo in una lista di Stringhe tutti i nomi degli smartphone 
		List<String> listPhones = 
			phones.stream().map(SmartPhone_::getName).collect(Collectors.toList());
		System.out.println(listPhones);
		
		//Otteniamo una lista ordinata (TreeSet) ,quindi senza duplicati, di tutti i nomi
		Set<String> orderedList = phones.stream().map(SmartPhone_::getName).
				collect(Collectors.toCollection(TreeSet::new));
		System.out.println(orderedList);
		
		//Separare gli oggetti con un ; o altro utilizzando il metodo joining() 
		//di Collectors	
		String objSeparted = phones.stream().map(SmartPhone_::toString).
				collect(Collectors.joining("\n"));
		System.out.println(objSeparted);
		
		//Raggruppare per marca in una collection Map
		Map<String, List<SmartPhone_>> map =  
				phones.stream().collect(Collectors.groupingBy(SmartPhone_::getName));
		System.out.println("Mappa\n"+ map);
		
		//Statistiche di gruppo
		DoubleSummaryStatistics stat = phones.stream().collect(Collectors.summarizingDouble(SmartPhone_::getPrice));
		System.out.println(stat);
	}
}
