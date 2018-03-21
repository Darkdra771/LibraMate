import java.lang.reflect.Array;

public class LoginInformation {

	private data[] users;
	
	public void printall() {
		users[0].printall();
	}
	
	public data getData(String name) {
		for(int i = 0; i < sizeOfUsers(); i++) {
			if(name.equals(users[i].username)) {
				return users[i];
			}
		}
		return null;
		
	}
	
	public data[] getUsers() {
		return users;
	}
	
	public int sizeOfUsers() {
		return Array.getLength(users);
	}
	
	public void addUsers(String userName, String password, String Url) {
		
		int length = sizeOfUsers();
		data[] temp = users;
		users = new data[length + 1];
		for( int i = 0; i < length; i++) {
			users[i] = temp[i];
		}
		
		data newUser = new data(userName, password, Url);
		users[length] = newUser;
		
		
	}
	
}

class data{
	
	public String username;
	public String password;
	public String imageURL;
	public String[] followers;
	public String[] following;
	public Library library;
	
	public int getSizeOfFollowers() {
		return Array.getLength(followers);
	}
	public int getSizeOfFollowing() {
		return Array.getLength(following);
	}
	
	public void printall() {
		System.out.println(username);
		System.out.println(password);
		System.out.println(imageURL);
		for(int i = 0; i < Array.getLength(followers); i++) {
		System.out.println(followers[i]);}
		for(int i = 0; i < Array.getLength(following); i++) {
			System.out.println(following[i]);}
	}
	
	public data(String userName, String password, String Url) {
		username = userName;
		this.password = password;
		this.imageURL = Url;
	}
	
	public String getName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUrl() {
		return imageURL;
	}
	public String[] followers(){
		return followers;
	}
	public String[] following() {
		return following;
	}
	public Library getLibrary() {
		return library;
	}
	
}

class Library{
	
	public String[] read;
	public String[] favorite;
	
	public int sizeOfRead() {
		return Array.getLength(read);
	}
	
	public int sizeOfFavorite() {
		return Array.getLength(favorite);
	}
	
	public String[] getRead() {
		return read;
	}
	
	public String[] getFavorite() {
		return favorite;
	}
	
}