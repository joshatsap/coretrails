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
package de.hybris.platform.cuppytrailfrontend.controller;

import de.hybris.platform.cuppytrail.data.StadiumData;
import de.hybris.platform.cuppytrail.facades.StadiumFacade;
import de.hybris.platform.cuppytrailfrontend.StadiumsNameEncoded;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.security.core.server.csi.util.URLDecoder;


/**
 *
 */
@Controller
public class StadiumsController
{
	private StadiumFacade stadiumFacade;

	@RequestMapping(value = "/stadiums")
	public String showStadiums(final Model model)
	{
		final List<StadiumData> stadiums = stadiumFacade.getStadiums();
		model.addAttribute("stadiums", stadiums);
		return "StadiumListing";
	}

	@RequestMapping(value = "/stadiums/{stadiumName}")
	public String showStadiumDetails(@PathVariable String stadiumName, final Model model) throws UnsupportedEncodingException
	{
		stadiumName = URLDecoder.decode(stadiumName, "UTF-8");
		final StadiumData stadium = stadiumFacade.getStadium(stadiumName);
		stadium.setName(StadiumsNameEncoded.getNameEncoded(stadium.getName()));
		model.addAttribute("stadium", stadium);
		return "StadiumDetails";
	}

	@Autowired
	public void setFacade(final StadiumFacade facade)
	{
		this.stadiumFacade = facade;
	}

}
