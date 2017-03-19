package com.demo.guess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/play/*")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Random random = new Random();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
    }
    
    
    @Override
    public void init() throws ServletException {
       	super.init();
       	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nick = (String) session.getAttribute("nick");
		Integer randomNo = (Integer)session.getAttribute("randomNo");
		List<Guess> guesses = (List<Guess>) session.getAttribute("guesses");
		request.setAttribute("won", false);
		
		if(nick != null && guesses != null && guesses.size()>0) {
			Guess lastGuess = guesses.get(guesses.size()-1);
			if(lastGuess.getValue() == randomNo.intValue()) {
				request.setAttribute("won", true);
			}
		}

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("guess.jsp");
		dispatcher.forward(request, response);
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pinf = request.getPathInfo(); 
		HttpSession session = request.getSession();
		
		
		String nick = (String) session.getAttribute("nick");
		
		if("/new".equals(pinf)) {
			createNewGame(session, nick);
			response.sendRedirect("../play");
		} else {
			if(nick==null) {
				nick=request.getParameter("name");
				createNewGame(session, nick);
			} 
			
			Integer randomNo = (Integer)session.getAttribute("randomNo");
			List<Guess> guesses = (List<Guess>) session.getAttribute("guesses");
			int guess = Integer.valueOf(request.getParameter("guess"));
					
			
			Guess newGuess;
			if(guesses.size()>0) {
				Guess lastGuess = guesses.get(guesses.size()-1);
							
				int compare = Math.abs(lastGuess.getValue() - randomNo) - Math.abs(guess - randomNo);
				String guessString = null;
						
				if(guess == randomNo) {
					guessString = "WON!!!";
				}
				else if(compare==0) {
					guessString = "The Same";
				} else if(compare<0) {
					guessString = "Colder";
				} else {
					guessString = "Hotter";
				}
				newGuess = new Guess(new Date(), guessString, guess, nick);
				
			} else {
				newGuess = new Guess(new Date(), null, guess, nick);
			}
				
			guesses.add(newGuess);
			response.sendRedirect("play");
		}
		
				
		
	}


	private void createNewGame(HttpSession session, String nick) {
		session.setAttribute("nick", nick);
		
		
		
		List<Guess> guesses = new ArrayList<Guess>();
		session.setAttribute("guesses", guesses);
		session.setAttribute("randomNo", random.nextInt(100));
	}

}
