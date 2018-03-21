

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.StringBuilder;
import java.lang.Math;

/**
 * Servlet implementation class Accounts
 */
@WebServlet("/Accounts")
public class Accounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String input = request.getParameter("input");
		LoginInformation myObj = StoreObj.myObject;
		//String pageToForward = "SearchPage.jsp";
		Boolean check = true;
		

		String images = "";
		
		images += "<form name=\"account_form\" action=\"AccountServlet\">\n";
		
		for(int i = 0; i < myObj.sizeOfUsers(); i++) {
			String name = myObj.getUsers()[i].getName();
			if(compare(input, name)) {
				check = false;
				images += "<div id=\"container\" style=\"vertical-align: top;\">\n"
						+ "<button type=\"submit\" name=\"index\" style=\"outline:none;background-color:transparent;border:none;\""
						+ "value=\"" + i + "\">\n"
						+ "<img id=\"image\" style=\"width:100px;height:100px;\""
						+ "src=\"" + myObj.getUsers()[i].getUrl() + "\">\n"
						+ "<h3 style=\"max-width:150px;margin-left:40px;\">"
						+ "@" + myObj.getUsers()[i].getName() + "</h3>\n"
						+ "</button>\n"
						+ "</div>\n";
			}
		}
		
		images += "</form>\n";
		
		request.setAttribute("user", "\"fa fa-group\"");
		
		if(check) {
			images = "";
			images += "<text id=\"text\" style=\"margin-top:300px;margin-left: 450px;\">NO USER FOUND!</text>\n";
		}
		
    	PrintWriter out = response.getWriter();
    	out.println(images);
		
	}
	
	public boolean compare(String input, String name) {
		
		
		//System.out.println("comparing: " + input + " " + name);
		
		int length = input.length();
		
		if(name.length() < length) {
			return false;
		}
		
		for( int i = 0; i < length; i++) {
			if(input.charAt(i) != name.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}

}
