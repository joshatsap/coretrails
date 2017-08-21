/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.cuppytrail.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 *
 */
@Target(
{ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyCuppyValidator.class)
@Documented
public @interface NotEmptyCuppy
{
	String message() default "{de.hybris.platform.cuppytrail.constraints.NotEmptyCuppy.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
