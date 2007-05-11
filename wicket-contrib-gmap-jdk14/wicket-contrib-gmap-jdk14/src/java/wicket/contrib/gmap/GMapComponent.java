package wicket.contrib.gmap;

import java.util.Iterator;

/**
 * @author Iulian-Corneliu Costan
 */
class GMapComponent extends JavaScriptComponent
{

	private GMap gmap;

	public GMapComponent(GMap gmap)
	{
		super(ID);
		this.gmap = gmap;
	}

	public String onJavaScriptComponentTagBody()
	{
		StringBuffer buffer = new StringBuffer("\n//<![CDATA[\n").append("function initGMap() {\n")
				.append("if (GBrowserIsCompatible()) {\n").append("\n" + gmapDefinition()).append(
						"\n" + overlayDefinitions()).append("}\n").append("}\n").append("//]]>\n");
		return buffer.toString();
	}

	private String overlayDefinitions()
	{
		StringBuffer buffer = new StringBuffer("map.clearOverlays();\n");
		Iterator iterator = gmap.getOverlays().iterator();
		while (iterator.hasNext())
		{
			Overlay overlay = (Overlay)iterator.next();
			buffer.append("map.addOverlay(" + overlay.getFactoryMethod() + "());\n");
		}
		return buffer.toString();
	}

	private String gmapDefinition()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("var map = map ? map : new GMap2(document.getElementById(\"map\"));\n");
		if (gmap.isLargeMapControl())
		{
			buffer.append("map.addControl(new GLargeMapControl());\n");
		}
		if (gmap.isTypeControl())
		{
			buffer.append("map.addControl(new GMapTypeControl());\n");
		}
		if (gmap.isScaleControl())
		{
			buffer.append("map.addControl(new GScaleControl());\n");


		}
		if (gmap.isOverviewMapControl())
		{
			buffer.append("map.addControl(new GOverviewMapControl());\n");
		}
		if (gmap.isSmallMapControl())
		{
			buffer.append("map.addControl(new GSmallMapControl());\n");
		}
		if (gmap.isSmallZoomControl())
		{
			buffer.append("map.addControl(new GSmallZoomControl());\n");
		}

		buffer.append("map.setCenter(").append(gmap.getCenter().toString()).append(", ").append(
				gmap.getZoomLevel()).append(");\n");
		// Below registers when the user stoped moving the map, need to update
		// some values
		buffer.append("GEvent.addListener(map, \"moveend\", function () {\n"
				+ "var center = map.getCenter();\n"
				+ "document.getElementById(\"latitude\").value=center.lat();\n"
				+ "document.getElementById(\"longtitude\").value=center.lng();\n"
				+ "document.getElementById(\"zoomLevel\").value=map.getZoom();\n"
				+ "document.getElementById(\"gmap_ajaxGMapUpdatingFormSubmit\").onclick();\n" +

				"});\n");
		
		// is gmap is in insert model this must be added for the notifier form to be submitted on click
		if(gmap.isInsertMode())
		{
			buffer.append("GEvent.addListener(map, \"click\", function (marker, point) {\n"
					+ "if(marker){}\n"
					+ "else{\n"
					+ "document.getElementById(\"clickNotifierLatitude\").value=point.lat();\n"
					+ "document.getElementById(\"clickNotifierLongtitude\").value=point.lng();\n"
					+ "document.getElementById(\"gmap_ajaxGMapClickNotifierFormSubmit\").onclick();\n" +
			"}});\n");
		}


		return buffer.toString();
	}

	public static final String ID = "gmapComponent";
}
