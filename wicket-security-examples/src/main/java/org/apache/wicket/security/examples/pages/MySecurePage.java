/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.security.examples.pages;

import org.apache.wicket.Application;
import org.apache.wicket.PageParameters;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.security.WaspSession;
import org.apache.wicket.security.components.SecureWebPage;
import org.apache.wicket.security.hive.authentication.LoginContext;
import org.apache.wicket.security.swarm.SwarmWebApplication;

/**
 * Base page for all my other pages.
 * 
 * @author marrink
 *
 */
public abstract class MySecurePage extends SecureWebPage
{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MySecurePage()
	{
		init();
	}

	/**
	 * @param parameters
	 */
	public MySecurePage(PageParameters parameters)
	{
		super(parameters);
		init();
	}

	/**
	 * @param model
	 */
	public MySecurePage(IModel model)
	{
		super(model);
		init();
	}
	protected void init()
	{
		//not a secure link because everyone can logoff.
		Link logoff = new Link("logoff")
		{

			private static final long serialVersionUID = 1L;

			public void onClick()
			{
				WaspSession waspSession = ((WaspSession)getSession());
				if(waspSession.logoff(getLogoffContext()))
				{
					//homepage is not allowed anymore so we end up at the loginpage
					setResponsePage(Application.get().getHomePage());
					waspSession.invalidate();
				}
				else
					error("A problem occured during the logoff process, please try again or contact support");
			}
		};
		add(logoff);
	}
	protected final WaspSession getSecureSession()
	{
		return (WaspSession)Session.get();
	}
	protected final SwarmWebApplication getSecureApplication()
	{
		return (SwarmWebApplication)Application.get();
	}

	/**
	 * @return
	 */
	protected abstract LoginContext getLogoffContext();
}
