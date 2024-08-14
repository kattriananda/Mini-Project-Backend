package com.example.Mini.Project.Prodemi.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImplPassValid.class)
@Documented
public @interface PasswordValidation {
    String message() default "Password must be at least 6 characters long and contain at least one uppercase letter and one symbol";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
