import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.layout.BackgroundFill;

public class Database {
	static private Database database = new Database();
	private Database() {
		
	}
	static public Database getInstance() {
		return database;
	}
	
	public int login(String username,String password) {
		final int EXCEPTION = -1;
		final int LOGIN_SUCCEED = 1;
		final int NO_USER = 2;
		final int WRONG_PASSWORD = 3;
		
		File userFile = new File("user.data");
		if(!userFile.exists()) {
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(userFile));
			String user = br.readLine();
			while(user != null) {
				String[] contents = user.split("\\t");
				if(contents[0].equals(username)) {
					if(contents[1].equals(password)) {
						for(int i = 2;i<contents.length;i++) {
							
						}
						return LOGIN_SUCCEED;
					}
					else {
						return WRONG_PASSWORD;
					}
				}
				user = br.readLine();
			}
			return NO_USER;
		} catch (IOException e) {
			e.printStackTrace();
			return EXCEPTION;
		}
	}
	
	public int register(String username,String password) {
		final int EXCEPTION = -1;
	
		final int REGISTER_SUCCEED = 1;
		final int USERNAME_OCCUPIED = 2;
		
		File userFile = new File("user.data");
		if(!userFile.exists()) {
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(userFile));
			String user = br.readLine();
			while(user != null) {
				String[] contetns = user.split("\t");
				if(contetns[0].equals(username)) {
					return USERNAME_OCCUPIED;
				}
				user = br.readLine();
			}
			PrintWriter pw = new PrintWriter(new FileWriter(userFile,true));
			pw.println(username + "\t" + password);
			pw.flush();
			return REGISTER_SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
			return EXCEPTION;
	}
	}
	
	public String getCourses(String username) {
		File courseFile = new File("course.data");
		if(!courseFile.exists()) {
			try {
				courseFile.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		try {
			System.out.println(courseFile.getCanonicalPath());
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}//获取标准的路径 
	    System.out.println(courseFile.getAbsolutePath());
		try {
			BufferedReader br = new BufferedReader(new FileReader(courseFile));
			String user = br.readLine();
			String result= "";
			while(user != null) {
				String[] contents = user.split("\t");
				if(contents[0].equals(username)) {
					result = result + contents[1] + "\t";
				}
				user = br.readLine();
			}
			if(result.isEmpty()) {
				return null;
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
	}
		return null;
	}
	
	public int bookCourse(String username,String course) {
		final int EXCEPTION = -1;
		
		final int BOOK_SUCCEED = 1;
		final int BOOK_ALREADY = 2;
		File courseFile = new File("course.data");
		if(!courseFile.exists()) {
			try {
				courseFile.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		try {
			PrintWriter pw = new PrintWriter(courseFile);
			BufferedReader br = new BufferedReader(new FileReader(courseFile));
			String targetLine = username + "\t" +  course;
			String courseLine = br.readLine();
			while(courseLine != null) {
				if(courseLine.equals(targetLine)) {
					return BOOK_ALREADY;
				}
				courseLine = br.readLine();
			}
			pw.println(targetLine);
			pw.flush();
			return BOOK_SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
			return EXCEPTION;
	}
	}
}
