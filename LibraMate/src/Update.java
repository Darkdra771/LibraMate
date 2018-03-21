

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String line = request.getParameter("user");
		
		PrintWriter out = response.getWriter();
		//System.out.println("Update.java");
		//check if user is log in
		if(line.equals("active")) {
			
			if(StoreObj.CurrentUser != null) {
				out.println("yes");
			}
			else {
				out.println("no");
			}
			
			return;
			
		}
		//update read
		else if(line.equals("read")) {
			String title = request.getParameter("title");
			//System.out.println("read: " + title);
			
			if(StoreObj.CurrentUser.library.getRead() == null) {	
				return;
			}
			
			String[] checkLib = StoreObj.CurrentUser.library.getRead();
			
			for(int i = 0; i < Array.getLength(checkLib); i++) {
				String reading = checkLib[i].split(":")[0];
				if(reading.equals(title)) {
					out.println("Already added");
					return;
				}
			}
			
			int length = Array.getLength(checkLib);
			StoreObj.CurrentUser.library.read = new String[length + 1];
			for(int i = 0; i < length; i++) {
				StoreObj.CurrentUser.library.read[i] = checkLib[i];
			}
			
			StoreObj.CurrentUser.library.read[length] = title;
			out.println("Added to Read");
			String pathToFile = getServletContext().getRealPath("File.json");
			StoreObj.saveStateToJson(pathToFile);
			return;
				
		}
		//update favorite
		else if(line.equals("favor")) {
			String title = request.getParameter("title");
			System.out.println("favor: " + title);
			String[] checkLib = StoreObj.CurrentUser.library.getFavorite();
			
			for(int i = 0; i < Array.getLength(checkLib); i++) {
				String favorite = checkLib[i].split(":")[0];
				if(favorite.equals(title)) {
					out.println("Already Favorite");
					return;
				}
			}
			
			int length = Array.getLength(checkLib);
			StoreObj.CurrentUser.library.favorite = new String[length + 1];
			for(int i = 0; i < length; i++) {
				StoreObj.CurrentUser.library.favorite[i] = checkLib[i];
			}
			
			StoreObj.CurrentUser.library.favorite[length] = title;
			out.println("Added to Favorite");
			String pathToFile = getServletContext().getRealPath("File.json");
			StoreObj.saveStateToJson(pathToFile);
			return;
			
		}
		
		
	}

}
