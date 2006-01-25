package wicket.contrib.dojo.examples.rssreader;

import wicket.Application;
import wicket.Component;
import wicket.RequestCycle;
import wicket.Response;
import wicket.contrib.dojo.DojoAjaxHandler;
import wicket.markup.html.PackageResourceReference;
import wicket.markup.html.internal.HtmlHeaderContainer;
import wicket.response.StringResponse;
import wicket.util.resource.IResourceStream;
import wicket.util.resource.StringBufferResourceStream;

/**
 * AjaxHandler that can be used to update and rerender components on a set event. 
 * @author Marco van de Haar
 * @author Ruud Booltink
 *
 */
public abstract class DojoUpdateHandler extends DojoAjaxHandler
{

	public DojoUpdateHandler()
	{
		
	}
	
	/** 
	 * adds reference to autoupdate.js.
	 * @see wicket.AjaxHandler#renderHeadContribution(wicket.markup.html.HtmlHeaderContainer)
	 */
	protected void renderHeadContribution(HtmlHeaderContainer container)
	{
		addJsReference(container, new PackageResourceReference(Application.get(),
				DojoUpdateHandler.class, "dojoupdate.js"));
		
	}
	
	/**
	 * Method which subclasses can use to update the bound Component.
	 * 
	 * @return Component array containing Components to be updated. 
	 * NOTE: currently only supports 1 component.
	 */
	protected abstract Component[] updateComponent();

	
	/* (non-Javadoc)
	 * @see wicket.AjaxHandler#getResponse()
	 */
	protected IResourceStream getResponse() 
	{

		Component[] components = updateComponent();
		return render(components);
	}
	

	
	/**
	 * Internal render method. This handler currently only supports 1 component.<br/>
	 * The array is for future usage.
	 * 
	 * @param components The components to be rerendered.
	 * @return Rerendered component's resource stream.
	 */
	protected IResourceStream render(final Component[] components) 
	{
		
		
		StringBufferResourceStream response = new StringBufferResourceStream("text/plain");

		if (components != null)
		{
			
			Response resp = new StringResponse(); 
			RequestCycle requestCycle = RequestCycle.get();
			Response origResponse = requestCycle.getResponse();
			try
			{
				requestCycle.setResponse(resp);
				for (int i=0; i < components.length; i++)
				{
	
					
					Component component = components[i];
					boolean renderBodyOnly = component.getRenderBodyOnly();
					try
					{
						component.setRenderBodyOnly(true);

						component.doRender();
					}
					catch (Exception ex)
					{
						resp.write("UPDATE_ERROR");
						//resp.write(ex.toString());
					}
					finally
					{
						component.setRenderBodyOnly(renderBodyOnly);
					}

				}
			}
			finally
			{
				requestCycle.setResponse(origResponse);
			}
			response.append(resp.toString());
		}

		return response;
		
	}
}