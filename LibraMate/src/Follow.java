

import java.io.IOException;
import java.lang.reflect.Array;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Follow
 */
@WebServlet("/Follow")
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuilder username = new StringBuilder(request.getParameter("follow"));
		username.deleteCharAt(0);
		String name = username.toString();
		System.out.println("follow.java: " + username);
		
		data currentUser = StoreObj.CurrentUser;
		String[] temp = currentUser.following();
		
		currentUser.following = new String[Array.getLength(temp) + 1];
		for(int i = 0; i < Array.getLength(temp); i++) {
			currentUser.following[i] = temp[i];
		}
		
		currentUser.following[Array.getLength(temp)] = name;
		
		StoreObj.save();
		String pathToFile = getServletContext().getRealPath("File.json");
		StoreObj.saveStateToJson(pathToFile);
		
		
	}

}
