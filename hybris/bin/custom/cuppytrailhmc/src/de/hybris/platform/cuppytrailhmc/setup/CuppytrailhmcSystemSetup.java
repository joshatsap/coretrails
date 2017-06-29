/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.cuppytrailhmc.setup;

import static de.hybris.platform.cuppytrailhmc.constants.CuppytrailhmcConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.cuppytrailhmc.constants.CuppytrailhmcConstants;
import de.hybris.platform.cuppytrailhmc.service.CuppytrailhmcService;


@SystemSetup(extension = CuppytrailhmcConstants.EXTENSIONNAME)
public class CuppytrailhmcSystemSetup
{
	private final CuppytrailhmcService cuppytrailhmcService;

	public CuppytrailhmcSystemSetup(final CuppytrailhmcService cuppytrailhmcService)
	{
		this.cuppytrailhmcService = cuppytrailhmcService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailhmcService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailhmcSystemSetup.class.getResourceAsStream("/cuppytrailhmc/sap-hybris-platform.png");
	}
}
