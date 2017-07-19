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
package de.hybris.platform.cuppytrailfrontend.setup;

import static de.hybris.platform.cuppytrailfrontend.constants.CuppytrailfrontendConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import de.hybris.platform.cuppytrailfrontend.constants.CuppytrailfrontendConstants;
import de.hybris.platform.cuppytrailfrontend.service.CuppytrailfrontendService;


@SystemSetup(extension = CuppytrailfrontendConstants.EXTENSIONNAME)
public class CuppytrailfrontendSystemSetup
{
	private final CuppytrailfrontendService cuppytrailfrontendService;

	public CuppytrailfrontendSystemSetup(final CuppytrailfrontendService cuppytrailfrontendService)
	{
		this.cuppytrailfrontendService = cuppytrailfrontendService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		cuppytrailfrontendService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return CuppytrailfrontendSystemSetup.class.getResourceAsStream("/cuppytrailfrontend/sap-hybris-platform.png");
	}
}
