

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class WriteJson
 */
@WebServlet("/WriteJson")
public class WriteJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		String url = request.getParameter("url");
		String pageToForward = "/SignUp.jsp";
		
		//LoginInformation myObj = null;
		Gson gson = null;
		String path = getServletContext().getRealPath("File.json");
		
		//System.out.println("writeJSON1");
		
		for(int i = 0; i < StoreObj.myObject.sizeOfUsers(); i++) {
			//System.out.println("compare: " + name + " " + StoreObj.myObject.getUsers()[i].username);
			if(name.equals(StoreObj.myObject.getUsers()[i].username)) {
				PrintWriter out = response.getWriter();
		    	out.println("Username is taken.");
		    	return;
			}
		}
		
		/*
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
		*/
		
		StoreObj.myObject.addUsers(name, password, url);
		
		gson = new Gson();
		
		try {
			//gson = new Gson();
			String jsonString = gson.toJson(StoreObj.myObject);
			gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(jsonString);
			jsonString = gson.toJson(je);
			FileWriter writer = new FileWriter(path);
			writer.write(jsonString);
			writer.close();
		}
		catch(IOException ioe) {
			System.out.println("\nFile can not be saved into File.json " + ioe.getMessage());
		}
	
		
	}

}
