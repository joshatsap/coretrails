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
package de.hybris.platform.cuppytrail.workflow;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.MailService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob;
import de.hybris.platform.workflow.model.WorkflowActionModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 */
public abstract class AbstractPlayerRegistrationActionJob implements AutomatedWorkflowTemplateJob
{
	@Autowired
	ModelService modelService;
	@Autowired
	MailService mailService;

	protected PlayerModel getAttachedPlayer(final WorkflowActionModel action)
	{
		final List<ItemModel> attachments = action.getAttachmentItems();
		if (attachments != null)
		{
			for (final ItemModel item : attachments)
			{
				if (item instanceof PlayerModel)
				{
					return (PlayerModel) item;
				}
			}
		}

		return null;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the mailService
	 */
	public MailService getMailService()
	{
		return mailService;
	}

	/**
	 * @param mailService
	 *           the mailService to set
	 */
	public void setMailService(final MailService mailService)
	{
		this.mailService = mailService;
	}

}
