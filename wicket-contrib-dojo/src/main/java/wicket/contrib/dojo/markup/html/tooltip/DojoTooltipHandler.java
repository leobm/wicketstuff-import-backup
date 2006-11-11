package wicket.contrib.dojo.markup.html.tooltip;

import wicket.ajax.AjaxRequestTarget;
import wicket.contrib.dojo.AbstractDefaultDojoBehavior;
import wicket.markup.html.IHeaderResponse;

/**
 * A Dojo Dialog Box Handler
 * @author <a href="http://www.demay-fr.net/blog">Vincent Demay</a>
 *
 */
public class DojoTooltipHandler extends AbstractDefaultDojoBehavior
{
	@Override
	protected void respond(AjaxRequestTarget target)
	{
		//DO NOTHING
	}
	
	/* (non-Javadoc)
	 * @see wicket.contrib.dojo.DojoAjaxHandler#renderHead(wicket.markup.html.IHeaderResponse)
	 */
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);
		String require = "";
		require += "<script language=\"JavaScript\" type=\"text/javascript\">\n";
		require += "	dojo.require(\"dojo.widget.Tooltip\")\n";
		require += "\n";
		require += "</script>\n";

		response.renderString(require);
	}

	@Override
	protected void onBind()
	{
		super.onBind();
		((DojoTooltip)getComponent()).getTooltipedComponent().setOutputMarkupId(true);
	}

}
