

import java.io.IOException;
import java.lang.reflect.Array;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageToForward = "/UserPage.jsp";
		String Following = "";
		String Followers = "";
		String images = "";
		String read = "";
		
		data myUser = StoreObj.CurrentUser;
		
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
		String[] links = str.getFavorURL();
		Library lib = StoreObj.CurrentUser.getLibrary();
		
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
		
		/*get read book*/
		links = str.getReadURL();
		
		read += "<table style=\"40%;\">\n<tr>\n";
		
		for(int i = 0; i < str.readSize; i++) {
			String[] authors = str.authors.get(i);
			String author = "";
			for(int j = 0; j < Array.getLength(authors); j++) {
				 author += authors[j] + ", ";
			}
			
			read += "<th>\n"
					+ "<img src=\"" + links[i] + "\">\n"
					+ "<h3 style=\"max-width: 150px;\">" + lib.getRead()[i] + "</h3>\n"
					+ "<h4 style=\"margin-top:-10px;\">by " + author + "</h4>\n"
					+ "</th>\n";
			
		}
		
		read += "</tr>\n</table>\n";
		
		
		
		request.setAttribute("read", read);
		request.setAttribute("imageURL", myUser.imageURL);
		request.setAttribute("username", myUser.username);
		request.setAttribute("Following", Following);
		request.setAttribute("Followers", Followers);
		request.setAttribute("images", images);
		request.setAttribute("userImage", myUser.imageURL);
		
		
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
		dispatch.forward(request, response);
		
	}

}
