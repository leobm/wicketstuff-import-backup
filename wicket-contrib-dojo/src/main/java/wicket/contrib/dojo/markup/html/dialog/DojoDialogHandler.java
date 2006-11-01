package wicket.contrib.dojo.markup.html.dialog;

import wicket.ajax.AjaxRequestTarget;
import wicket.contrib.dojo.DojoAjaxHandler;
import wicket.markup.html.IHeaderResponse;

/**
 * A Dojo Dialog Box Handler
 * @author <a href="http://www.demay-fr.net/blog">Vincent Demay</a>
 *
 */
public class DojoDialogHandler extends DojoAjaxHandler
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
		require += "	dojo.require(\"dojo.widget.Dialog\")\n";
		require += "\n";
		require += "function getDialog(id){\n";
		require += "	var dlg = dojo.widget.byId(id);\n";
		require += "	return dlg;\n";
		require += "}\n";
		require += "</script>\n";

		response.renderString(require);
	}

}
