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
package de.hybris.platform.cuppytrail.service.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.cuppytrail.service.StadiumService;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 *
 */
public class DefaultStadiumService implements StadiumService
{

	private StadiumDAO stadiumDAO;

	/**
	 * Gets all stadiums by delegating to {@link StadiumDAO#findStadiums()}.
	 */
	@Override
	public List<StadiumModel> getStadiums()
	{
		return stadiumDAO.findStadiums();
	}

	/**
	 * Gets all stadiums for given code by delegating to {@link StadiumDAO#findStadiumsByCode(String)} and then assuring
	 * uniqueness of result.
	 */
	@Override
	public StadiumModel getStadiumForCode(final String code) throws UnknownIdentifierException, AmbiguousIdentifierException
	{
		final List<StadiumModel> result = stadiumDAO.findStadiumsByCode(code);
		if (result.isEmpty())
		{
			throw new UnknownIdentifierException("Stadium with code " + code + " not found");
		}
		else if (result.size() > 1)
		{
			throw new AmbiguousIdentifierException(result.size() + " Stadiums found with code " + code);
		}

		return result.get(0);
	}

	@Required
	public void setStadiumDAO(final StadiumDAO dao)
	{
		this.stadiumDAO = dao;
	}

}
