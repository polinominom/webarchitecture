

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AdressServlet
 */
@WebServlet("/LoginServlet/*")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        super.init(config);
        ServletContext sc = config.getServletContext();
        List<User> users = new ArrayList<User>();
        sc.setAttribute("users",users);
        sc.setAttribute("init", true);
    }
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.sendRedirect("/Architecture/Main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String log = "Hoppa\n";
		HttpSession session = request.getSession();
		ServletContext applicationScope = request.getServletContext();
		
		session.setAttribute("loginMessage", null);
		session.setAttribute("registerMessage", null);
		
		if(!session.isNew())
		{
			session.invalidate();
			session = request.getSession();
		}
		
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
				log += register(request, applicationScope, response);
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
				response.sendRedirect("/Architecture/Record.jsp");
				
				// send redirect to other servlet
				//applicationScope.getRequestDispatcher("AdressEnter").forward(request, response);
				
			}
			else
			{
				// login is not correct username or password is not correct!
				log += "Login is Incorrect!\n";
				session.setAttribute("loginMessage", "Error: Login failed! Please try again.");
				response.sendRedirect("/Architecture/Main.jsp");
				
			}
		}
		else
		{
			// login is not correct. user is not exist.
			log += "user is not exist!\n";
			session.setAttribute("loginMessage", "Error: Login failed! Please try again.");
			response.sendRedirect("/Architecture/Main.jsp");
		}
		
		return log;
		
	}
	
	private String register(HttpServletRequest request, ServletContext applicationScope, HttpServletResponse response) throws IOException
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
		HttpSession session = request.getSession();
		int status = RegisterController.reqisterUser(users, username, password);
		if(status == 0)
		{
			// LOGIN IS CORRECT
			log += "User has been registered. Please login to proceed.\n";
			session.setAttribute("registerMessage", "User has been registered. Please login to proceed.");
			response.sendRedirect("/Architecture/Main.jsp");
			
		}
		else if(status == 1)
		{
			log += "USERNAME EXISTS! REGISTER FAILED!\n";
			session.setAttribute("registerMessage", "Username exists! Register failed! Please try again.");
			response.sendRedirect("/Architecture/Main.jsp");
		}
		else if(status == 2)
		{
			log += "Username or Password is empty! REGISTER FAILED!\n";
			session.setAttribute("registerMessage", "Username or Password is empty! Register failed! Please try again.");
			response.sendRedirect("/Architecture/Main.jsp");
		}
		else
		{
			log += "REGISTER FAILED! Dunno why!\n";
			session.setAttribute("registerMessage", "Unknown error! Register failed.");
			response.sendRedirect("/Architecture/Main.jsp");
		}	
		return log;
	}
}
