/*
 * $Id: DataPermission.java,v 1.2 2006/06/19 14:03:43 Marrink Exp $
 * $Revision: 1.2 $
 * $Date: 2006/06/19 14:03:43 $
 *
 * ====================================================================
 * Copyright (c) 2005, Topicus B.V.
 * All rights reserved.
 */
package org.apache.wicket.security.hive.authorization.permissions;


import org.apache.wicket.Component;
import org.apache.wicket.security.hive.authorization.Permission;
import org.apache.wicket.security.swarm.actions.SwarmAction;
import org.apache.wicket.security.swarm.models.SwarmModel;


/**
 * A permission for data or plain old pojo's.
 * Can have actions like access, read or write.
 * @author marrink
 *
 */
public class DataPermission extends ActionPermission
{
	private static final long serialVersionUID = 5192668688933417376L;
	/**
	 * Creats a new DataPermission for a components model.
	 * The model will specify the name for this permission.
	 * Currently we dont check if the component really has the model you specified here.
	 * @param component component containing the model
	 * @param model the model of the component
	 * @param actions a logical and of all the allowed / required actions
	 */
	public DataPermission(Component component,SwarmModel model, SwarmAction actions)
	{
		super(model.getSecurityId(component),actions);
	}

	/**
	 * Creates a new DataPermission with the specified name and actions.
	 * @param name
	 * @param actions
	 */
	public DataPermission(String name, String actions)
	{
		super(name, getAction(actions));
	}

	/**
	 * @see Permission#implies(Permission)
	 */
	public boolean implies(Permission permission)
	{
		return (permission instanceof DataPermission) && super.implies(permission);
	}

	/**
	 * @see wicket.extensions.security.jaas.ActionPermission#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if(obj instanceof DataPermission)
			return super.equals(obj);
		return false;
	}

	/**
	 * @see wicket.extensions.security.jaas.ActionPermission#hashCode()
	 */
	public int hashCode()
	{
		// super implementation already gives out distinct hashcodes per subclass
		return super.hashCode();
	}
}
