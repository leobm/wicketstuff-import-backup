/*
 * $Id: WicketExampleApplication.java 1579 2007-01-15 06:35:10Z eelco12 $
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

import javax.servlet.ServletContext;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.ISecuritySettings;
import org.apache.wicket.util.crypt.ClassCryptFactory;
import org.apache.wicket.util.crypt.NoCrypt;


/**
 * Wicket Application class.
 */
public abstract class WicketExampleApplication extends WebApplication
{
	/**
	 * Constructor.
	 */
	public WicketExampleApplication()
	{
	}

	/**
	 * Determine operations mode: deployment or development
	 */
	protected void init()
	{
		ServletContext servletContext = getServletContext();
		// WARNING: DO NOT do this on a real world application unless
		// you really want your app's passwords all passed around and
		// stored in unencrypted browser cookies (BAD IDEA!)!!!

		// The NoCrypt class is being used here because not everyone
		// has the java security classes required by Crypt installed
		// and we want them to be able to run the examples out of the
		// box.
		getSecuritySettings().setCryptFactory(
				new ClassCryptFactory(NoCrypt.class, ISecuritySettings.DEFAULT_ENCRYPTION_KEY));
	}
}
