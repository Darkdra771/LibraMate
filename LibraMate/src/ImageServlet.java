

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getParameter("selfLink");
		String input = request.getParameter("input");
		String choice = request.getParameter("choice");
		request.setAttribute("input", input);   
		request.setAttribute("choice", choice);
		String pageToForward = "/SearchPage.jsp";
		
		
		URL myurl = null;
		try {
			myurl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    HttpURLConnection con = null;

		try {
			con = (HttpURLConnection) myurl.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    StringBuilder content = null;

	    try (BufferedReader in = new BufferedReader(
	            new InputStreamReader(con.getInputStream()))) {

	        String line;
	        content = new StringBuilder();

	        while ((line = in.readLine()) != null) {
	            content.append(line);
	            content.append(System.lineSeparator());
	        }
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	    con.disconnect();
	    //end of HTTP GET request

	    //opening json file
	    Book book = null;
	    Gson gson = null;
	    
    		
		//create a Gson object
		gson = new Gson();
		
		//convert the json to Java object
		book = gson.fromJson(content.toString(), Book.class);
		

		//make the html string
		String images = "";
		
		String authors = "";
		if(book.getAuthors() != null) {
			for(int j = 0; j < Array.getLength(book.getAuthors()); j++) {
				if (j > 0) {authors += ", ";}
				authors = authors + (book.getAuthors())[j];
			}
		}
		else {
			authors = "Unknown";
		}
		
		String desc = "";
		if(book.getDescription() == null) {
			desc = "No description";
		}
		else {
			desc = book.getDescription();
		}
		
		String imageLink = "";
		if(book.getImageLinks() == null) {
			imageLink = book.getThumbnail();
		}
		else {
			imageLink = book.getImageLinks();
		}
		
		images = images + "<div id=\"largeImage\" style=\"display:inline-block;\">\n"
				+ "<img src=\"" + imageLink +"\""
				+ "style=\"max-height: 670px;vertical-align: text-top;\">";
		
		
		if(StoreObj.CurrentUser != null) {
			images += "<div id=\"dropdown\" class=\"dropdown\">\n"
				+ "<button onclick=\"myFunction()\" class=\"dropbtn\">Add to Library"
				+ "<i class=\"material-icons\" style=\"font-size:36px;\">arrow_drop_down</i>\n"
				+ "</button>\n<div id=\"myDropdown\" class=\"dropdown-content\">\n"
				+ "<a onclick=\"StoreRead();\">Read</a>\n"
				+ "<a onclick=\"StoreFavor();\">Favorites</a>\n"
				+ "</div>\n<strong><text id=\"msg\"></text><strong></div>";
		}
				images	+= "\n</div>\n"
				+ "<div id=\"info\" style=\"display:inline-block;\">\n"
				+ "<h1 id=\"description\" id=\"bookTitle\" >" + book.getTitle() + "</h1>\n"
				+ "<h2 id=\"description\">by " + authors + "</h2>\n"
				+ "<h3 id=\"description\" style=\"display: inline-block;\">Publisher:</h3>\n"
				+ "<h3 style=\"display:inline-block;\">" + book.getPublisher() + "</h3>\n"
				+ "<h3 id=\"description\">Description</h3>\n"
				+ "<h4 style=\"max-width: 500px;\">" + desc + "</h4>\n"
				+ "<h3 id=\"description\">Rating:</h3>\n";
		
		double rating = book.getRating();
		double leftOver = Math.floor(5.0 - rating);
		while(rating >= 0.5){
			if(rating >= 1.0) {
				images = images + "<i class=\"fa fa-star\" style=\"font-size:48px;color:yellow\"></i>\n";
				rating -= 1.0;
			}
			else if(rating >= 0.5) {
				images = images + "<i class=\"fa fa-star-half-empty\" style=\"font-size:48px;color:yellow\"></i>\n";
				rating -= 0.5;
			}
		}
		
		while(leftOver > 0) {
			images = images + "<i class=\"fa fa-star-o\" style=\"font-size:48px;color:yellow\"></i>\n";
			leftOver -= 1.0;
		}
		
		images = images + "</div>\n";
		
		request.setAttribute("images", images);
		if(StoreObj.CurrentUser != null) {
			request.setAttribute("userImage", StoreObj.CurrentUser.imageURL);
		}
		if(StoreObj.CurrentUser == null) {
			pageToForward = "/FirstSearchPage.jsp";
			//System.out.println("going to firstSearchPage from imageServlet");
		}
		
		request.setAttribute("user", "\"fa fa-book\"");
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
		dispatch.forward(request, response);
	}

}
