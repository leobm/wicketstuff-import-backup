/*
 * Created by IntelliJ IDEA.
 * User: kinabalu
 * Date: Jan 31, 2010
 * Time: 8:16:26 PM
 */
package org.wicketstuff.html5;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.MountedMapper;
import org.wicketstuff.html5.geolocation.GeolocationDemo;
import org.wicketstuff.html5.markup.html.form.Html5InputsDemo;
import org.wicketstuff.html5.media.audio.AudioDemo;
import org.wicketstuff.html5.media.video.VideoDemo;

/**
 *
 * @author Andrew Lombardi
 */
public class WicketApplication extends WebApplication {

    public WicketApplication() {}

    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    protected void init() {
        super.init();

        getRootRequestMapperAsCompound()
        	.add(new MountedMapper("/audio", AudioDemo.class))
        	.add(new MountedMapper("/video", VideoDemo.class))
        	.add(new MountedMapper("/geolocation", GeolocationDemo.class))
        	.add(new MountedMapper("/form/inputs", Html5InputsDemo.class));
    }

	/* (non-Javadoc)
	 * @see org.apache.wicket.protocol.http.WebApplication#getConfigurationType()
	 */
	@Override
	public String getConfigurationType() {
		return Application.DEVELOPMENT;
	}
    
}