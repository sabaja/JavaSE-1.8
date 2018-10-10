package com.annotations;

import java.lang.annotation.*;

class RepeatableAnnotations {

}


//Annotazione da ripetere
@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.TYPE)
@Inherited
@Repeatable(TestTriggers.class)//Inserire l'annotazione contenitore come classe dell'annotazione 
@interface TestTrigger{
	String when();
	enum Test{JUNIT,GUI,JMETER,SOUPUI}
	Test whatUse();
}

@Documented
@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.TYPE)
@Inherited
//@Repeatable(value = null)//Inserire l'annotazione contenitore come valore
@interface TestTriggers{
	TestTrigger[] value();
}
