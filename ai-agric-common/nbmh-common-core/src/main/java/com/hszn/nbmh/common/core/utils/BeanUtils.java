package com.hszn.nbmh.common.core.utils;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class BeanUtils {


    /**
     * The constant validator.
     */
    private final static Validator validator = Validation.byDefaultProvider().configure()
            .messageInterpolator(new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("ValidationMessages_zh_CN")))
            .buildValidatorFactory().getValidator();

    /**
     * 验证bean
     *
     * @param <T>    the type parameter
     * @param object the object
     * @param groups the groups
     */
    public static <T> void validBean(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
        if (constraintViolations.size() > 0) {

            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
