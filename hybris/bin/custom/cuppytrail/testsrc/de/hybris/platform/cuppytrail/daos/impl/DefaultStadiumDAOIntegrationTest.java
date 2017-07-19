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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;


/**
 * This class belongs to the Source Code Trail documented at https://wiki.hybris.com/display/pm/Source+Code+Tutorial
 *
 * The purpose of this test is to illustrate DAO best practices and behaviour.
 *
 * The DAO logic is factored into a separate POJO. Stepping into these will illustrate how to write and execute
 * FlexibleSearchQueries - the basis on which DAOs operate.
 *
 * @see "https://wiki.hybris.com/display/pm/Trail+-+DAOs"
 */
public class DefaultStadiumDAOIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource
	private StadiumDAO stadiumDAO;

	@Resource
	private ModelService modelService;

	private static final String STADIUM_NAME = "wembley";

	private static final Integer STADIUM_CAPACITY = Integer.valueOf(12345);

	@Test
	public void stadiumDAOTest()
	{
		List<StadiumModel> stadiumsByCode = stadiumDAO.findStadiumsByCode(STADIUM_NAME);
		assertTrue("No Stadium should be returned", stadiumsByCode.isEmpty());

		List<StadiumModel> allStadiums = stadiumDAO.findStadiums();
		final int size = allStadiums.size();

		final StadiumModel stadiumModel = new StadiumModel();
		stadiumModel.setCode(STADIUM_NAME);
		stadiumModel.setCapacity(STADIUM_CAPACITY);
		modelService.save(stadiumModel);

		allStadiums = stadiumDAO.findStadiums();
		assertEquals(size + 1, allStadiums.size());
		assertEquals("Unexpected stadium found", stadiumModel, allStadiums.get(allStadiums.size() - 1));

		stadiumsByCode = stadiumDAO.findStadiumsByCode(STADIUM_NAME);
		assertEquals("Did not find the Stadium we just saved", 1, stadiumsByCode.size());
		assertEquals("Retrieved Stadium's name attribute incorrect", STADIUM_NAME, stadiumsByCode.get(0).getCode());
		assertEquals("Retrieved Stadium's capacity attribute incorrect", STADIUM_CAPACITY, stadiumsByCode.get(0).getCapacity());

	}

	@Test
	public void testFindStadiumsEmptyString()
	{
		final List stadiums = stadiumDAO.findStadiumsByCode("");
		assertTrue("No stadiums should be returned", stadiums.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindStadiumsNull()
	{
		stadiumDAO.findStadiumsByCode(null);
	}

}
