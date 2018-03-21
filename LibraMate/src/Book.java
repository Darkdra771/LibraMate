
public class Book {
	
	private volumeInfo volumeInfo;
	
	void print() {
		volumeInfo.print();
	}
	
	String getTitle() {
		return volumeInfo.getTitle();
	}
	
	String[] getAuthors() {
		return volumeInfo.getAuthors();
	}
	
	String getPublisher() {
		return volumeInfo.getPublisher();
	}
	
	String getDescription() {
		return volumeInfo.getDescription();
	}
	
	double getRating() {
		return volumeInfo.getRating();
	}
	
	String getImageLinks() {
		return volumeInfo.getImageLinks();
	}
	
	String getThumbnail() {
		return volumeInfo.getThumbnail();
	}
}

class volumeInfo{
	
	private String title;
	private String[] authors;
	private String publisher;
	private String description;
	private double averageRating;
	private ImageLinks imageLinks;
	
	void print() {
		System.out.println("title: " + title);
		System.out.println("auhtor: " + authors[0]);
		System.out.println("publisher: " + publisher);
		System.out.println("description: " + description);
		System.out.println("rateing: " + averageRating);
		imageLinks.print();
	}
	
	String getTitle() {
		return title;
	}
	
	String[] getAuthors() {
		return authors;
	}
	
	String getPublisher() {
		return publisher;
	}
	
	String getDescription() {
		return description;
	}
	
	double getRating() {
		return averageRating;
	}
	
	String getImageLinks() {
		if(imageLinks != null) {
			return imageLinks.getImageLink();
		}
		else {
			return "NO IMAGE FOUND";
		}
	}
	
	String getThumbnail() {
		return imageLinks.getThumbnail();
	}
}

class ImageLinks{
	
	private String medium;
	private String thumbnail;
	
	void print() {
		System.out.println("link: " + medium);
	}
	
	String getImageLink() {
		return medium;
	}
	
	String getThumbnail() {
		return thumbnail;
	}
	
	
}