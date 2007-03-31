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
package wicket.contrib.dojo.skin;

import wicket.contrib.dojo.WicketTestCase;
import wicket.contrib.dojo.skin.manager.SkinManager;


/**
 * Test {@link SkinManager} 
 */
public class SkinnTest extends WicketTestCase
{
	/**
	 * Construct.
	 * 
	 * @param name
	 */
	public SkinnTest(String name)
	{
		super(name);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void testskinnedPage() throws Exception
	{
		executeTest(SkinnedPage.class, "SkinnedPage_ExpectedResult.html");
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void testnoSkinnedPage() throws Exception
	{
		executeTest(NoSkinnedPage.class, "NoSkinnedPage_ExpectedResult.html");
	}
}
