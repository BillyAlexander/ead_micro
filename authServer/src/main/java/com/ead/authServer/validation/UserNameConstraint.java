package com.ead.authServer.validation;

import com.ead.authServer.validation.Impl.UserNameConstraintImpl;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameConstraintImpl.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameConstraint {
    String message() default "userName invalid";
    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};
}
