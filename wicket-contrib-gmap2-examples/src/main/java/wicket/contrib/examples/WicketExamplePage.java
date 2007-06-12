/*
 * $Id: WicketExamplePage.java 1579 2007-01-15 06:35:10Z eelco12 $
 * $Revision: 1579 $
 * $Date: 2007-01-15 07:35:10 +0100 (Mo, 15 Jan 2007) $
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wicket.contrib.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;


/**
 * Base class for all example pages.
 */
public class WicketExamplePage<T> extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public WicketExamplePage() {
		this(null);
	}

	/**
	 * Construct.
	 * 
	 * @param model
	 */
	public WicketExamplePage(IModel model) {
		super(model);
		final String packageName = getClass().getPackage().getName();
		add(new WicketExampleHeader("mainNavigation", Strings.afterLast(
				packageName, '.')));
		explain();
	}

	/**
	 * Override base method to provide an explanation
	 */
	protected void explain() {
	}
}
