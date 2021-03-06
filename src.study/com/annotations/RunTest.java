package com.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


class RunTest {
	public static void main(String[] args) throws Exception {

		System.out.println("Testing...");

		int passed = 0, failed = 0, count = 0, ignore = 0;

		Class<TestExample> obj = TestExample.class;

		// Process @TesterInfo
		if (obj.isAnnotationPresent(TesterInfo.class)) {

			Annotation annotation = obj.getAnnotation(TesterInfo.class);
			TesterInfo testerInfo = (TesterInfo) annotation;

			System.out.printf("%nPriority :%s", testerInfo.priority());
			System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
			System.out.printf("%nTags :");

			int tagLength = testerInfo.tags().length;
			for (String tag : testerInfo.tags()) {
				if (tagLength > 1) {
					System.out.print(tag + ", ");
				} else {
					System.out.print(tag);
				}
				tagLength--;
			}

			System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());

		}

		// Process @TestScientist
		for (Method method : obj.getDeclaredMethods()) {

			// if method is annotated with @TestScientist
			if (method.isAnnotationPresent(Test.class)) {

				Annotation annotation = method.getAnnotation(Test.class);
				Test test = (Test) annotation;

				// if enabled = true (default)
				if (test.enabled()) {

				  try {
					method.invoke(obj.newInstance());
					System.out.printf("%s - TestScientist '%s' - passed %n", ++count, method.getName());
					passed++;
				  } catch (Throwable ex) {
					System.out.printf("%s - TestScientist '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
					failed++;
				  }

				} else {
					System.out.printf("%s - TestScientist '%s' - ignored%n", ++count, method.getName());
					ignore++;
				}

			}

		}
		System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);

		}
}

@TesterInfo(
		priority = TesterInfo.Priority.HIGH, 
		createdBy = "mkyong.com",  
		tags = {"sales","test" }
	)
	 class TestExample {

		@Test
		void testA() {
		  if (true)
			throw new RuntimeException("This test always failed");
		}

		@Test(enabled = false)
		void testB() {
		  if (false)
			throw new RuntimeException("This test always passed");
		}

		@Test(enabled = true)
		void testC() {
		  if (10 > 1) {
			// do nothing, this test always passed.
		  }
		}

	}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
@interface TesterInfo {

	enum Priority {
	   LOW, MEDIUM, HIGH
	}

	Priority priority() default Priority.MEDIUM;
	
	String[] tags() default "";
	
	String createdBy() default "Mkyong";
	
	String lastModified() default "03/01/2014";

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
@interface Test {
	
	//should ignore this test?
	public boolean enabled() default true;
	
}