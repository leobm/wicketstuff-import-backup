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
package org.apache.wicket.security.hive.authorization.permissions;


import org.apache.wicket.Component;
import org.apache.wicket.security.actions.Access;
import org.apache.wicket.security.components.SecureComponentHelper;
import org.apache.wicket.security.hive.authorization.Permission;
import org.apache.wicket.security.swarm.actions.SwarmAction;


/**
 * Permission for certain components. Can have actions like access, render or enable.
 * 
 * @author marrink
 */
public class ComponentPermission extends ActionPermission
{

	private static final long serialVersionUID = 8950870313751454034L;

	/**
	 * Creates a new ComponentPermission with the default access action.
	 * 
	 * @param component
	 */
	public ComponentPermission(Component component)
	{
		super(SecureComponentHelper.alias(component), getAction(Access.class));
	}
	public ComponentPermission(Component component, int actions)
	{
		super(SecureComponentHelper.alias(component), getAction(actions));
	}
	/**
	 * Creates a new ComponentPermission with the specified actions.
	 * 
	 * @param component
	 * @param action
	 */
	public ComponentPermission(Component component, SwarmAction action)
	{
		super(SecureComponentHelper.alias(component), action);
	}

	/**
	 * Creates a new ComponentPermission with the specified actions.
	 * 
	 * @param componentPath
	 * @param actions
	 */
	public ComponentPermission(String componentPath, String actions)
	{
		super(componentPath, getAction(actions));
	}
	/**
	 * Creates a new ComponentPermission with the specified actions.
	 * @param securityString
	 * @param actions
	 */
	public ComponentPermission(String securityString, SwarmAction actions)
	{
		super(securityString, actions);
	}

	/**
	 * @see Permission#implies(Permission)
	 */
	public boolean implies(Permission permission)
	{
		return (permission instanceof ComponentPermission) && super.implies(permission);
	}
}
