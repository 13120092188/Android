

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String username = request.getParameter( "username");
		String password = request.getParameter("password");
		System.out.println("LOGIN GET");
		Database database = Database.getInstance();
		int loginStatus = database.login(username, password);
		System.out.println("LOGIN STATUS:"+loginStatus);
		String courses = database.getCourses(username);
		System.out.println("GOT COURSES:"+courses);
		PrintWriter pw = response.getWriter();
		pw.write("{\"loginStatus\":\""+loginStatus+"\",\"courses\":\""+courses+"\"}");
		 pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter( "username");
		String password = request.getParameter("password");
		System.out.println("LOGIN POST");
		Database database = Database.getInstance();
		int loginStatus = database.login(username, password);
		System.out.println("LOGIN STATUS:"+loginStatus);
		String courses = database.getCourses(username);
		System.out.println("GOT COURSES:"+courses);
		PrintWriter pw = response.getWriter();
		pw.write("{\"loginStatus\":\""+loginStatus+"\",\"courses\":\""+courses+"\"}");
		 pw.flush();
	}

}
