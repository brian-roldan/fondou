package nz.co.fondou.command.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy=TimePeriodValidator.class)
public @interface ValidateTimePeriod {

	String message() default "Invalid time period.";

	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
}
