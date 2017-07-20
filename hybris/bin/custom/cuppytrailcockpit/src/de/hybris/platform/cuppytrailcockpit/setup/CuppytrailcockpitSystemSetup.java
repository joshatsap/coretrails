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
package de.hybris.platform.cuppytrailcockpit.setup;

import static de.hybris.platform.cuppytrailcockpit.constants.CuppytrailcockpitConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.cuppytrailcockpit.constants.CuppytrailcockpitConstants;
import de.hybris.platform.cuppytrailcockpit.service.CuppytrailcockpitService;


@SystemSetup(extension = CuppytrailcockpitConstants.EXTENSIONNAME)
public class CuppytrailcockpitSystemSetup
{
	private final CuppytrailcockpitService cuppytrailcockpitService;

	public CuppytrailcockpitSystemSetup(final CuppytrailcockpitService cuppytrailcockpitService)
	{
		this.cuppytrailcockpitService = cuppytrailcockpitService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailcockpitService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailcockpitSystemSetup.class.getResourceAsStream("/cuppytrailcockpit/sap-hybris-platform.png");
	}
}
