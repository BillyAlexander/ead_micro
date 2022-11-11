package com.ead.authServer.validation.Impl;

import com.ead.authServer.validation.UserNameConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameConstraintImpl implements ConstraintValidator<UserNameConstraint,String> {
    @Override
    public void initialize(UserNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        if(userName==null || userName.trim().isEmpty() || userName.contains(" "))
            return false;
        return  true;
    }


}
