package net.sf.ipn.data.auto;

/**
 * Class _PrayerGroupType was generated by Cayenne. It is probably a good idea to avoid
 * changing this class manually, since it may be overwritten next time code is
 * regenerated. If you need to make any customizations, please use subclass.
 */
public class _PrayerGroupType extends org.objectstyle.cayenne.CayenneDataObject
{

	public static final String CODE_PROPERTY = "code";
	public static final String DESCRIPTION_PROPERTY = "description";

	public static final String ID_PK_COLUMN = "ID";

	public void setCode(String code)
	{
		writeProperty("code", code);
	}

	public String getCode()
	{
		return (String)readProperty("code");
	}


	public void setDescription(String description)
	{
		writeProperty("description", description);
	}

	public String getDescription()
	{
		return (String)readProperty("description");
	}


}
