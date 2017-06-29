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
package de.hybris.platform.cuppytrail.setup;

import static de.hybris.platform.cuppytrail.constants.CuppytrailConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.cuppytrail.constants.CuppytrailConstants;
import de.hybris.platform.cuppytrail.service.CuppytrailService;


@SystemSetup(extension = CuppytrailConstants.EXTENSIONNAME)
public class CuppytrailSystemSetup
{
	private final CuppytrailService cuppytrailService;

	public CuppytrailSystemSetup(final CuppytrailService cuppytrailService)
	{
		this.cuppytrailService = cuppytrailService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailSystemSetup.class.getResourceAsStream("/cuppytrail/sap-hybris-platform.png");
	}
}
