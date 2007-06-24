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
package org.apache.wicket.security.examples.customactions;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.security.examples.customactions.entities.Department;
import org.apache.wicket.security.examples.customactions.entities.Organization;
import org.apache.wicket.security.examples.customactions.factories.MyActionFactory;
import org.apache.wicket.security.examples.customactions.pages.LoginPage;
import org.apache.wicket.security.examples.customactions.pages.OverviewPage;
import org.apache.wicket.security.hive.HiveMind;
import org.apache.wicket.security.hive.config.PolicyFileHiveFactory;
import org.apache.wicket.security.swarm.SwarmWebApplication;

/**
 * default implementation of a swarm app with a custom actionfactory.
 * @author marrink
 *
 */
public class MyApplication extends SwarmWebApplication
{

	public final List DEPARTMENTS;

	/**
	 * 
	 */
	public MyApplication()
	{
		super();
		Organization organization = new Organization();
		organization.name = "Bee Hive: Honey Production (inc)";
		String[] departments = new String[] { "Tracking", "Tracks swarm movements", "false",
				"H.I.E", "Honey Industrial Espionage", "true", "C.B.I.A",
				"Counter Bee Interrogation Agency", "true", "Honey Gathering",
				"Gathers honey from all the swarms", "false", "Storage", "Stores all the honey",
				"false" };
		int size = 5;
		List data = new ArrayList(size);
		for (int i = 0; i < size; i++)
		{
			data.add(new Department(organization, departments[i * 3], departments[(i * 3) + 1],
					Boolean.valueOf(departments[(i * 3) + 2]).booleanValue()));
		}
		DEPARTMENTS=data;
	}
	
	/**
	 * @see org.apache.wicket.security.swarm.SwarmWebApplication#init()
	 */
	protected void init()
	{
		super.init();
//		misc settings
		getMarkupSettings().setCompressWhitespace(true);
		getMarkupSettings().setStripComments(true);
		getMarkupSettings().setStripWicketTags(true);
		getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
	}

	/**
	 * @see org.apache.wicket.security.swarm.SwarmWebApplication#getHiveKey()
	 */
	protected Object getHiveKey()
	{
		//if you are using servlet api 2.5 i would suggest using:
		//return getServletContext().getContextPath();
		
		//if not you have several options:
		//-an initparam in web.xml
		//-a static object
		//-a random object
		//-whatever you can think of
		
		//for this example we will be using a fixed string
		return "custom-actions";
		
	}

	/**
	 * @see org.apache.wicket.security.swarm.SwarmWebApplication#setUpHive()
	 */
	protected void setUpHive()
	{
		//create factory
		PolicyFileHiveFactory factory = new PolicyFileHiveFactory();
		try
		{
			//this example uses 1 policy file but you can add as many as you like
			factory.addPolicyFile(getServletContext().getResource("/WEB-INF/customactions.hive"));
		}
		catch (MalformedURLException e)
		{
			throw new WicketRuntimeException(e);
		}
		//register factory
		HiveMind.registerHive(getHiveKey(), factory);

	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class getHomePage()
	{
		return OverviewPage.class;
	}

	/**
	 * @see org.apache.wicket.security.WaspApplication#getLoginPage()
	 */
	public Class getLoginPage()
	{
		return LoginPage.class;
	}

	/**
	 * @see org.apache.wicket.security.swarm.SwarmWebApplication#setupActionFactory()
	 */
	protected void setupActionFactory()
	{
		setActionFactory(new MyActionFactory());
	}
	
}
