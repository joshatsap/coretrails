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
package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component(value = "stadiumDAO")
public class DefaultStadiumDAO implements StadiumDAO
{
	/**
	 * We use hybris' FlexibleSearchService for running queries against the database
	 *
	 * @see "https://wiki.hybris.com/display/release5/FlexibleSearch"
	 */
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<StadiumModel> findStadiums()
	{
		//query
		final String queryString = "SELECT {p:" + StadiumModel.PK + "}" + "FROM {" + StadiumModel._TYPECODE + " AS p}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		// Note that we could specify paginating logic by providing a start and count variable (commented out below)
		// This can provide a safeguard against returning very large amounts of data, or hogging the database when there are
		// for example millions of items being returned.
		// As we know that there are only a few persisted stadiums in this use case we do not need to provide this.

		//query.setStart(start);
		//query.setCount(count);

		return flexibleSearchService.<StadiumModel> search(query).getResult();
	}

	@Override
	public List<StadiumModel> findStadiumsByCode(final String code)
	{
		final String queryString = //
				"SELECT {p:" + StadiumModel.PK + "}" //
						+ "FROM {" + StadiumModel._TYPECODE + " AS p} "//
						+ "WHERE " + "{p:" + StadiumModel.CODE + "}=?code ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("code", code);

		return flexibleSearchService.<StadiumModel> search(query).getResult();
	}

}
