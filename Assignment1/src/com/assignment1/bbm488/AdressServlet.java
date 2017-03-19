package com.assignment1.bbm488;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.faces.facelets.util.Path;

/**
 * Servlet implementation class AdressServlet
 */
@WebServlet("/AdressServlet/*")
public class AdressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdressServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String log = "Hoppa  " +request.getPathInfo()+"\n";
		HttpSession session = request.getSession();
		ServletContext applicationScope = request.getServletContext();
		
		String sign = request.getParameter("sign");
		boolean isSign = (sign != null);
		
		// check the user already logged in
		if(isSign)
		{
		
			if(sign.equals("Login"))
			{
				log += login(request, applicationScope, session, response);
			}
			else if(sign.equals("Register"))
			{
				log += register(request, applicationScope);
			}
			else
			{
				// wrong action has been submited which is IMPOSSIBLE, however if its occurs handle it hear
				log += "Wrong Action: "+sign;
			}
			
			log += DebugController.outAllParamaters(request);
			
			//response.getWriter().append(log);
		}
	}
	
	private String login(HttpServletRequest request, ServletContext applicationScope, HttpSession session, HttpServletResponse response) throws ServletException, IOException
	{
		String log = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<User> users = (List<User>) applicationScope.getAttribute("users");
		boolean userExist = UserListController.isUserExists(users, username);
		if(userExist)
		{
			if(LoginController.isLoginCorrect(users, username, password))
			{
				// LOGIN IS CORRECT, set the attributes
				session.setAttribute("userLogged", true);
				session.setAttribute("user", UserListController.getUserbyUsername(users, username));
				
				// TODO: redirect to another servlet?
				log += "Login is Correct!\n";
				response.sendRedirect("/Assignment1/Record.jsp");
				// send redirect to other servlet
				//applicationScope.getRequestDispatcher("AdressEnter").forward(request, response);
				
			}
			else
			{
				// login is not correct username or password is not correct!
				log += "Login is Incorrect!\n";
				
			}
		}
		else
		{
			// login is not correct. user is not exist.
			log += "user is not exist!\n";
		}
		
		return log;
		
	}
	
	private String register(HttpServletRequest request, ServletContext applicationScope)
	{
		String log = "";
		//TODO: register the user if there aren't any user that using same username
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<User> users = (List<User>) applicationScope.getAttribute("users");
		if(users == null)
		{
			//log += "list has been initialized!\n";
			users = (List<User>) new ArrayList<User>();
			applicationScope.setAttribute("users",users);
		}
		
		int status = RegisterController.reqisterUser(users, username, password);
		if(status == 0)
		{
			// LOGIN IS CORRECT
			log += "REGISTER IS CORRECT\n";
			
		}
		else if(status == 1)
		{
			log += "USERNAME EXISTS! REGISTER FAILED!\n";
		}
		else if(status == 2)
		{
			log += "Username or Password is empty! REGISTER FAILED!\n";
		}
		else
		{
			log += "REGISTER FAILED! Dunno why!\n";
		}	
		return log;
	}
}
