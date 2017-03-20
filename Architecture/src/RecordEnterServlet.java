import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class AdressEnterServlet
 */
@WebServlet("/RecordEnterServlet/*")
public class RecordEnterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordEnterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.sendRedirect("/Architecture/Main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// reset the sessions editing record
		request.getSession().setAttribute("editingRecord", null);
				
		//control logout first
		if(request.getParameter("logout") != null)
		{
			//logout
			request.getSession().invalidate();
			response.sendRedirect("/Architecture/Main.jsp");
			return;
		}
		
		// get the path info
		String pathInfo = request.getPathInfo();
		if(pathInfo.equals("/new"))
		{
			// open the record page
			String c = request.getParameter("new");
			if(c.equals("New"))
			{
				response.sendRedirect("/Architecture/Record.jsp");
			}			
		}
		else if(pathInfo.equals("/record"))
		{
			// add the record
			addRecord(request, response);
		}
		else if(pathInfo.equals("/sessionEdit"))
		{
			// edit the record that in the user's session scope
			String edit = request.getParameter("edit");
			String delete = request.getParameter("delete");
			
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("user");
			if(u != null)
			{
				List<Record> sessionRecords = u.getRecords();
				if(edit != null)
				{
					//TODO: send edit request with id for the list
					int id = Integer.parseInt(edit);
					
					// add the record that user is going to editing to the session
					// record, type
					Record record = sessionRecords.get(id);
 					session.setAttribute("editingRecord", new EditableRecord(record, "Session", id));
 					response.sendRedirect("/Architecture/EditRecord.jsp");
 					return;
					
				}
				
				if(delete != null)
				{
					int id = Integer.parseInt(delete);
					sessionRecords.remove(id);
					response.sendRedirect("/Architecture/ListRecords.jsp");
					return;
				}				
			}
			
		}
		else if(pathInfo.equals("/applicationEdit"))
		{
			// edit the record that in the application scope
			String edit = request.getParameter("edit");
			String delete = request.getParameter("delete");
			
			ServletContext applicationScope = request.getServletContext();
			List<Record> records = (List<Record>)applicationScope.getAttribute("records");
			if(records != null)
			{
				
				if(edit != null)
				{
					
					int id = Integer.parseInt(edit);
					// add the record that user is going to editing to the session
					// record, type
					Record record = records.get(id);
 					request.getSession().setAttribute("editingRecord", new EditableRecord(record, "Application", id));
 					response.sendRedirect("/Architecture/EditRecord.jsp");
					
				}
				
				if(delete != null)
				{
					int id = Integer.parseInt(delete);
					records.remove(id);
					applicationScope.setAttribute("records", records);
					response.sendRedirect("/Architecture/ListRecords.jsp");
					return;
				}				
			}
		}
		
	}
	
	// Tries to add record
	private void addRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String command = request.getParameter("record");
		if(command == null)
		{
			response.sendRedirect("/Architecture/Record.jsp");
		}
			
		if(command.equals("List"))
		{
			response.sendRedirect("/Architecture/ListRecords.jsp");
		}
		else if(command.equals("Record"))
		{
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			Record record = new Record();
			record.setName(request.getParameter("name"));
			record.setSurname(request.getParameter("surname"));
			record.setAddress(request.getParameter("address"));
			record.setTelephone(request.getParameter("telephone"));
			record.setAge(Integer.parseInt(request.getParameter("age")));
			record.setSessionType(request.getParameter("sessionType"));
			
			if(record.getSessionType().equals("Application"))
			{
				ServletContext applicationScope = request.getServletContext();
				List<Record> records = (List<Record>) applicationScope.getAttribute("records");
				if(records == null)
				{
					records = new ArrayList<Record>();
				}
				
				records.add(record);
				applicationScope.setAttribute("records", records);
				
			}
			else
			{
				user.addRecord(record);
			}
			
			response.sendRedirect("/Architecture/Record.jsp");
		}				
	}


}
