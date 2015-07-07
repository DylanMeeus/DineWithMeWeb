package net.itca.dwmweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itca.dwm.core.DineWithMeFacade;
import net.itca.dwm.core.User;
import net.itca.dwmweb.core.DWMWebFacade;

/**
 * Servlet implementation class DWMServlet
 */
@WebServlet("/Servlet")
public class DWMServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private DWMWebFacade facade = new DWMWebFacade();

	public DWMServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in get: " + request.getParameter("action"));
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{

		boolean toForward = true;
		System.out.println("in post!");
		String action = request.getParameter("action");
		String destination = "";
		switch (action)
		{
		case "login":
			destination = login(request, response);
			break;
		case "logout":
			destination = logout(request, response);
			break;
		case "navigateFriends":
			destination = navigateFriends(request, response);
			break;
		case "friendsAsync":
			getFriendsAsync(request, response);
			toForward = false;
			return;
		case "openChat":
			destination = openChat(request, response);
			break;
		case "addFriend":
			destination = addFriend(request, response);
			break;
		}

		if (toForward)
		{
			RequestDispatcher view = request.getRequestDispatcher(destination);
			view.forward(request, response);
		}
	}

	private String logout(HttpServletRequest request, HttpServletResponse response)
	{
		facade.setCurrentUser(null);
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "index.html";
	}

	private String addFriend(HttpServletRequest request, HttpServletResponse response)
	{
		String friendUsername = request.getParameter("username").split(" ")[0];
		System.out.println("adding: " + friendUsername);
		try
		{
			facade.addFriend(friendUsername);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return navigateFriends(request, response);
	}

	private String openChat(HttpServletRequest request, HttpServletResponse response)
	{
		String friendString = (request.getParameter("friend"));
		String name = friendString.split(" ")[1];
		String lastname = friendString.split(" ")[2];
		String username = friendString.split(" ")[0];
		request.setAttribute("lastname", lastname);
		request.setAttribute("firstname", name);
		request.setAttribute("username", username);
		return "chat.jsp";
	}

	/**
	 * Push this as XML data!
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String navigateFriends(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			// request.setAttribute("friends", facade.getFriendsByUserID());
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return "friends.jsp";
	}

	private void getFriendsAsync(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("Getting data async");
		String responseXML = "";
		try
		{
			response.setContentType("text/xml");
			responseXML = friendToXML();
			response.getWriter().write(responseXML);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	private String friendToXML()
	{
		StringBuffer xmlDoc = new StringBuffer();
		xmlDoc.append("<friends>\n");
		try
		{
			for (String friend : facade.getFriendsByUserID())
			{
				xmlDoc.append("<friend>\n");
				xmlDoc.append(friend);
				xmlDoc.append("</friend>\n");
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		xmlDoc.append("</friends>");

		return xmlDoc.toString();
	}

	private String login(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// System.out.println("username: " + username + "password: " +
		// password);
		String destination = "";
		try
		{
			// the facade should encrypt it within the login call.
			if (facade.login(username, facade.encrypt(password)))
			{
				User user = new User(username, facade.getUserID(username));
				facade.setCurrentUser(user);
				HttpSession session = request.getSession();
				session.setAttribute("user", facade.getCurrentUser());
				destination = "DWMHome.jsp";
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			destination = "index.html";
		}
		System.out.println("Destination:" + destination);
		return destination;
	}

}
