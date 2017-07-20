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
package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.cuppy.model.NewsModel;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 */
public class StadiumEventListener extends AbstractEventListener<CapacityEvent>
{
	Logger LOG = Logger.getLogger(StadiumEventListener.class);

	@Autowired
	ModelService modelService;

	@Override
	protected void onEvent(final CapacityEvent event)
	{
		try
		{
			LOG.debug("### Entering event handler ###");
			Thread.sleep(2000);

			final NewsModel news = modelService.create(NewsModel.class);
			final String content = "New big stadium. Code: " + event.getCode() + ", capacity: " + event.getCapacity();
			news.setContent(content, Locale.ENGLISH);
			modelService.save(news);
			LOG.debug("### News created: " + content + " ###");
			LOG.debug("### Leaving event handler ###");
		}
		catch (final InterruptedException e)
		{
			// do nothing if thread is interrupted
		}

	}

}
