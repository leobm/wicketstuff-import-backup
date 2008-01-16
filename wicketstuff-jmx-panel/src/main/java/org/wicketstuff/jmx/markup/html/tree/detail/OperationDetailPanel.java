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
package org.wicketstuff.jmx.markup.html.tree.detail;

import javax.management.MBeanOperationInfo;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;
import org.wicketstuff.jmx.markup.html.tree.DetailPanel;
import org.wicketstuff.jmx.util.JmxMBeanWrapper;

public class OperationDetailPanel extends DetailPanel
{
	private static final long serialVersionUID = 1L;

	public OperationDetailPanel(String id, JmxMBeanWrapper bean, final MBeanOperationInfo operation)
	{
		super(id);
		add(new OperationPanel("operation", bean, operation).add(new AttributeModifier("class",
				true, new Model("operation-last"))));
	}

}