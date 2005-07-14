package net.sf.ipn.data.auto;

/**
 * Class _UserEmail was generated by Cayenne. It is probably a good idea to avoid changing
 * this class manually, since it may be overwritten next time code is regenerated. If you
 * need to make any customizations, please use subclass.
 */
public class _UserEmail extends org.objectstyle.cayenne.CayenneDataObject
{

	public static final String EMAIL_ADDRESS_PROPERTY = "emailAddress";
	public static final String PRIMARY_FLAG_PROPERTY = "primaryFlag";
	public static final String USER_PROPERTY = "user";

	public static final String ID_PK_COLUMN = "ID";

	public void setEmailAddress(String emailAddress)
	{
		writeProperty("emailAddress", emailAddress);
	}

	public String getEmailAddress()
	{
		return (String)readProperty("emailAddress");
	}


	public void setPrimaryFlag(Short primaryFlag)
	{
		writeProperty("primaryFlag", primaryFlag);
	}

	public Short getPrimaryFlag()
	{
		return (Short)readProperty("primaryFlag");
	}


	public void setUser(net.sf.ipn.data.User user)
	{
		setToOneTarget("user", user, true);
	}

	public net.sf.ipn.data.User getUser()
	{
		return (net.sf.ipn.data.User)readProperty("user");
	}


}
