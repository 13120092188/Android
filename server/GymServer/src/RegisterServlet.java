import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
    		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter( "username");
			String password = request.getParameter("password");
			System.out.println("REGISTER GET");
			Database database = Database.getInstance();
			int registerStatus = database.register(username, password);
			System.out.println("REGISTER STATUS:"+registerStatus);
			PrintWriter pw = response.getWriter();
			pw.write("{\"registerStatus\":\""+registerStatus+"\"}");
			 pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter( "username");
			String password = request.getParameter("password");
			System.out.println("REGISTER POST");
			Database database = Database.getInstance();
			int registerStatus = database.register(username, password);
			System.out.println("REGISTER STATUS:"+registerStatus);
			PrintWriter pw = response.getWriter();
			pw.write("{\"registerStatus\":\""+registerStatus+"\"}");
			 pw.flush();
	}
	
	
}
