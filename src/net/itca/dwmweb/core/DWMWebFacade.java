package net.itca.dwmweb.core;

import java.util.ArrayList;

import net.itca.dwm.core.DineWithMeFacade;
import net.itca.dwm.core.User;

public class DWMWebFacade extends DineWithMeFacade
{

	// We don't work with a user in the same way as in the android application.
	// Override all methods to allow for users to change dynamically?
	public DWMWebFacade()
	{
		super();
	}
	
	public ArrayList<String> getFriendsByUserID(User user)
	{
		return null;
	}
}
