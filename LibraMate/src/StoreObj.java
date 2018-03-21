import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class StoreObj {

	public static LoginInformation myObject = null;
	public static data CurrentUser = null;
	public int favorSize;
	public int readSize;
	public List<String[]> authors = null;
	
	public StoreObj() {
		
	}
	
	public static void saveStateToJson(String pathToFile) {
		StoreObj.save();
		String path = pathToFile;
		
		//to write to file
		//String pathToFile = getServletContext().getRealPath("File.json");
		//StoreObj.saveStateToJson(pathToFile);
		
		Gson gson = new Gson();
		
		try {
			//gson = new Gson();
			String jsonString = gson.toJson(myObject);
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
	
	public String getImageUrl(String username) {
		int length = myObject.sizeOfUsers();
		
		for(int i = 0; i < length; i++){
			if(username.equals(myObject.getUsers()[i].username)){
					return myObject.getUsers()[i].imageURL;
			}
		}
		return "";
	}
	
	public static void save() {
		
		if(CurrentUser == null) {return;}
		
		//write to the static loginInformation
		for(int i = 0; i < Array.getLength(myObject.getUsers()); i++) {
			if(CurrentUser.username.equals(myObject.getUsers()[i].username)) {
				myObject.getUsers()[i] = CurrentUser;
			}
		}
		
		
	}
	
	public String[] getUserFavorURL(int index) throws IOException{
		Library lib = myObject.getUsers()[index].getLibrary();
		String[] myBooksURL = new String[lib.sizeOfFavorite()];
		authors = new ArrayList<String[]>();
		
		
		for(int i = 0; i < lib.sizeOfFavorite(); i++) {
			String bookURL = lib.getFavorite()[i];
			
			String urlToRead = "https://www.googleapis.com/books/v1/volumes?q=";
			
			urlToRead += "intitle:";

			urlToRead +=  URLEncoder.encode(bookURL, "UTF-8");


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

		    con.disconnect();
		    //end of HTTP GET request

		    //opening json file
		    BookList book = null;
		    Gson gson = null;


    		//create a Gson object
    		gson = new Gson();

    		//convert the json to Java object
    		book = gson.fromJson(content.toString(), BookList.class);

    		//I have the book now
    		myBooksURL[i] = book.getImageLink(0);
			
    		if (book.getAuthors(0) == null) {continue;}
    		else {
    			this.authors.add(book.getAuthors(0));
    		}
    		
		}
		
		this.favorSize = Array.getLength(myBooksURL);
		
		return myBooksURL;
		
	}
	
	public String[] getReadURL() throws IOException {
		
		Library lib = CurrentUser.getLibrary();
		String[] myBooksURL = new String[lib.sizeOfRead()];
		authors = new ArrayList<String[]>();
		
		for(int i = 0; i < lib.sizeOfRead(); i++) {
			String bookURL = lib.getRead()[i];
			
			String urlToRead = "https://www.googleapis.com/books/v1/volumes?q=";
			
			urlToRead += "intitle:";

			urlToRead +=  URLEncoder.encode(bookURL, "UTF-8");


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

		    con.disconnect();
		    //end of HTTP GET request

		    //opening json file
		    BookList book = null;
		    Gson gson = null;


    		//create a Gson object
    		gson = new Gson();

    		//convert the json to Java object
    		book = gson.fromJson(content.toString(), BookList.class);

    		//I have the book now
    		myBooksURL[i] = book.getImageLink(0);
			
    		if (book.getAuthors(0) == null) {continue;}
    		else {
    			this.authors.add(book.getAuthors(0));
    		}
    		
		}
		
		this.readSize = Array.getLength(myBooksURL);
		
		return myBooksURL;
		
		
	}

	
	public String[] getFavorURL() throws IOException {
		
		Library lib = CurrentUser.getLibrary();
		String[] myBooksURL = new String[lib.sizeOfFavorite()];
		authors = new ArrayList<String[]>();
		
		for(int i = 0; i < lib.sizeOfFavorite(); i++) {
			String bookURL = lib.getFavorite()[i];
			
			String urlToRead = "https://www.googleapis.com/books/v1/volumes?q=";
			
			urlToRead += "intitle:";

			urlToRead +=  URLEncoder.encode(bookURL, "UTF-8");


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

		    con.disconnect();
		    //end of HTTP GET request

		    //opening json file
		    BookList book = null;
		    Gson gson = null;


    		//create a Gson object
    		gson = new Gson();

    		//convert the json to Java object
    		book = gson.fromJson(content.toString(), BookList.class);

    		//I have the book now
    		myBooksURL[i] = book.getImageLink(0);
			
    		if (book.getAuthors(0) == null) {continue;}
    		else {
    			this.authors.add(book.getAuthors(0));
    		}
    		
		}
		
		this.favorSize = Array.getLength(myBooksURL);
		
		return myBooksURL;
		
	}
	

	
}
