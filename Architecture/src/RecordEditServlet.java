

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

/**
 * Servlet implementation class RecordEditServlet
 */
@WebServlet("/RecordEditServlet")
public class RecordEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/Architecture/Main.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// check the logout at the beginning!
		HttpSession session = request.getSession();
		if(request.getParameter("logout") != null)
		{
			session.invalidate();
			response.sendRedirect("/Architecture/Main.jsp");
			return;
		}
		
		
		ServletContext applicationScope = request.getServletContext();
		
		String recordCommand = request.getParameter("record");
		if(recordCommand != null)
		{
			if(recordCommand.equals("Record"))
			{
				EditableRecord editableRecord = (EditableRecord) session.getAttribute("editingRecord");
				if(editableRecord != null)
				{
					//TODO: check the variables
					String type = editableRecord.getScopeType();
					Record record = editableRecord.getRecord();
					
					record.setName(request.getParameter("name"));
					record.setSurname(request.getParameter("surname"));
					record.setAddress(request.getParameter("address"));
					record.setTelephone(request.getParameter("telephone"));
					record.setAge(Integer.parseInt(request.getParameter("age")));
					record.setSessionType(request.getParameter("sessionType"));
					
					
					List<Record> records = (List<Record>) applicationScope.getAttribute("records");
					User user = (User)session.getAttribute("user");
					
					int recordIndex = editableRecord.getId();
					
					String newSessionType = record.getSessionType();
					if(newSessionType.equals("Application"))
					{
						
						if(type.equals("Application"))
						{
							// do nothing it is the same type
						}
						else if(type.equals("Session"))
						{
							//add the edited record to the applicationScope's list
							records.add(record);
							
							//remove from user's list
							user.getRecords().remove(recordIndex);
						}
						
						//update the application Scope's record list
						applicationScope.setAttribute("records", records);		
					}
					else if(newSessionType.equals("Session"))
					{
						if(type.equals("Application"))
						{	
							// remove the old position of record from application scope
							records.remove(recordIndex);
							applicationScope.setAttribute("records", records);
							
							//add the edited record to the user's list
							user.addRecord(record);
							
						}
						else if(type.equals("Session"))
						{
							//do nothing it is the same type
							
							
							//remove from user's list
							User u = (User)session.getAttribute("user");
							int id = editableRecord.getId();
							u.getRecords().remove(id);
						}						
						
					}
					response.sendRedirect("/Architecture/ListRecords.jsp");
					return;
				}
				
			}
			else if(recordCommand.equals("List"))
			{
				response.sendRedirect("/Architecture/ListRecords.jsp");
			}

		}
		
	}

}
