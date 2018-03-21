

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LoadJson
 */
@WebServlet("/LoadJson")
public class LoadJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pageToForward = "/SearchPage.jsp";
		String line = request.getParameter("forward");
		StoreObj.CurrentUser = null;
		
		//System.out.println("Load.json");
		LoginInformation myObj = null;
		Gson gson = null;
		String path = getServletContext().getRealPath("File.json");
		
		
		try {
			gson = new Gson();
			myObj = gson.fromJson(new FileReader(path), LoginInformation.class);
		}
		catch(FileNotFoundException ioe) {
			System.out.println("File not found" + ioe.getMessage());
		}
		catch(IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}
		
		//System.out.println(myObj.getUsers()[0].getUrl());
		
		StoreObj.myObject = myObj;
		
		//for(int i = 0;i < StoreObj.myObject.sizeOfUsers();i++) {
		//	System.out.println(StoreObj.myObject.getUsers()[i].username);
		//}
		
		if(line == null) {}
		else if(line.equals("forward")) {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
			dispatch.forward(request, response);
		}
		
		
	}

}
