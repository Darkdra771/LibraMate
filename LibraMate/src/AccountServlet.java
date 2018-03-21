

import java.io.IOException;
import java.lang.reflect.Array;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String get = request.getParameter("index");
		int index = Integer.parseInt(get);
		String pageToForward = "/ProfilePage.jsp";
		String Following = "";
		String Followers = "";
		String images = "";
		
		LoginInformation myObj = StoreObj.myObject;
		data myUser = myObj.getUsers()[index];
		
		int lengthOfFollowing = myUser.getSizeOfFollowing();
		int lengthOfFollowers = myUser.getSizeOfFollowers();
		
		for(int i = 0 ; i < lengthOfFollowing; i++) {
			Following += "<h3>@" + myUser.following()[i] + "</h3>\n";
		}
		
		for(int i = 0; i < lengthOfFollowers; i++) {
			Followers += "<h3>@" + myUser.followers()[i] + "</h3>\n";	
		}
		
		/*get image book*/
		StoreObj str = new StoreObj();
		String[] links = str.getUserFavorURL(index);
		Library lib = StoreObj.myObject.getUsers()[index].getLibrary();
		
		images += "<table style=\"40%;\">\n<tr>\n";
	
		
		for(int i = 0; i < str.favorSize; i++) {
			String[] authors = str.authors.get(i);
			String author = "";
			for(int j = 0; j < Array.getLength(authors); j++) {
				 author += authors[j] + ", ";
			}
			
			images += "<th>\n"
					+ "<img src=\"" + links[i] + "\">\n"
					+ "<h3 style=\"max-width: 150px;\">" + lib.getFavorite()[i] + "</h3>\n"
					+ "<h4 style=\"margin-top:-10px;\">by " + author + "</h4>\n"
					+ "</th>\n";
			
		}
		
		images += "</tr>\n</table>\n";
	
		
		request.setAttribute("imageURL", myUser.imageURL);
		request.setAttribute("username", myObj.getUsers()[index].username);
		request.setAttribute("Following", Following);
		request.setAttribute("Followers", Followers);
		request.setAttribute("images", images);
		data currentUser = StoreObj.CurrentUser;
		request.setAttribute("userImage", currentUser.imageURL);
		
		
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
		dispatch.forward(request, response);
	}

}
