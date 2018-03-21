

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
 * Servlet implementation class SaveJson
 */
@WebServlet("/SaveJson")
public class SaveJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("SaveJson");
		
		
		LoginInformation myObj = StoreObj.myObject;
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
		
		System.out.println(path);
		
		try {
			//gson = new Gson();
			String jsonString = gson.toJson(myObj);
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
