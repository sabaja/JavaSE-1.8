package com.annotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TargetAnnotation {
    String description();
    String assignTo() default "da assegnare";
    enum Priority {ALTA, MEDIA, BASSA};
    Priority priorita() default Priority.ALTA;
}