import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
 * Servlet implementation class Input
 */
@WebServlet("/Input")
public class Input extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//System.out.println("Input.java");
		String input = request.getParameter("input");
		String choice = request.getParameter("choice");
		String pageToForward = "/Home.jsp";
		request.setAttribute("user", "\"fa fa-search\"");
		
		//this goes to user SearchPage
		if(input == null && choice == null) {
			request.setAttribute("choice", "Title");
			request.setAttribute("user", "\"fa fa-book\"");
			pageToForward = "/SearchPage.jsp?user=true";
			String username = request.getParameter("userName");

			
			//get picture
			StoreObj myObject = new StoreObj();
			String url = myObject.getImageUrl(username);
			request.setAttribute("userImage", url);
			
			
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
			
			StoreObj.myObject = myObj;
			data currentUser = myObj.getData(username);
			StoreObj.CurrentUser = currentUser;
			
			//myObj.printall();
			
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
			dispatch.forward(request, response);
			return;
		}
		
		
		if(input.length() == 0) { //no input
			request.setAttribute("SearchError", "Please enter a search");
		}
		else if(input != null && input.length() > 0) { //input
			request.setAttribute("input", input);
			request.setAttribute("choice", choice);


			//make the link
			String urlToRead = "https://www.googleapis.com/books/v1/volumes?q=";
			
			if(choice.equals("Title")) {
				urlToRead += "intitle:";
			}
			else if (choice.equals("ISBN") ) {
				urlToRead += "isbn:";
			}
			else if (choice.equals("Author")) {
				urlToRead += "inauthor:";
			}
			else if(choice.equals("Genre")) {
				urlToRead += "subject:";
			}

			urlToRead +=  URLEncoder.encode(input, "UTF-8");
			urlToRead += "&maxResults=12";
			urlToRead += "&orderby=Popularity";

			//System.out.println("url: " + urlToRead);

			URL myurl = null;
			try {
				myurl = new URL(urlToRead);
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

		    //System.out.println(content.toString());

		    con.disconnect();
		    //end of HTTP GET request

		    //opening json file
		    BookList book = null;
		    Gson gson = null;


    		//create a Gson object
    		gson = new Gson();

    		//convert the json to Java object
    		book = gson.fromJson(content.toString(), BookList.class);

    		//make the html string
    		String images = "<form method=\"POST\" action=\"ImageServlet\">\n";

    		images += "<input type=\"hidden\" name=\"input\" value=\"" + input + "\">\n";
    		images += "<input type=\"hidden\" name=\"choice\" value=\"" + choice + "\">\n";

    		for( int i = 0; i < book.getSize(); i++) {
    			//System.out.println("this is i: " + i);
    			if(i == 12) {break;}
    			String authors = "";
    			if(book.getAuthors(i) != null) {
    				for(int j = 0; j < Array.getLength((book.getAuthors(i))); j++) {
    					if (j > 0) {authors += ", ";}
    					authors = authors + (book.getAuthors(i))[j];
    				}
    			}
    			else {
    				authors = "Unknown";
    			}
    			images = images + "<div id=\"container\" style=\"vertical-align:top;\" >\n"
    					+ "<button type=\"submit\" style=\"outline:none;background-color:transparent;border:none;\" name=\"selfLink\" value=\""
    					+ book.getSelfLink(i) + "\">"
    					+ "<img src=\"" + book.getImageLink(i) + "\">\n"
    					+ "<h3 style=\"max-width: 150px;\">" + book.getTitle(i) + "</h3>\n"
    					+ "<h4 style=\"max-width: 150px; padding-top: -5px; margin-top: -10px; font-size:13px;\">by "
    					+ authors + "</h4>\n"
    					+"</button>\n"
    					+ "</div>\n";
    		}

    		images = images + "</form>\n";

	    	pageToForward = "/FirstSearchPage.jsp";

	    	if(StoreObj.CurrentUser != null) {
	    		PrintWriter out = response.getWriter();
	    		out.println(images);
	    		return;
	    	}
	    	request.setAttribute("images", images);
		}
		
		//CHANGE THIS
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(pageToForward);
		dispatch.forward(request, response);

	}

}
