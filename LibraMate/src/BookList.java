import java.lang.reflect.Array;


public class BookList {
	
	private Volume[] items;
	
	int getSize() {
		if(items != null) {
			return Array.getLength(items);
		}else {
			return 0;
		}
	}
	
	String getImageLink(int i) {
		return items[i].getImageLink();
	}
	
	String getPublisher(int i) {
		return items[i].getPublisher();
	}
	
	String getTitle(int i) {
		return items[i].getTitle();
	}
	
	String[] getAuthors(int i) {
		return items[i].getAuthors();
	}

	String getSelfLink(int i) {
		return items[i].getSelfLink();
	}

}

class Volume{
	
	private String id;
	private String selfLink;
	private Info volumeInfo;
	
	
	
	String getImageLink() {
		return volumeInfo.getImageLink();
	}
	
	String getPublisher() {
		return volumeInfo.getPublisher();
	}

	String getTitle() {
		return volumeInfo.getTitle();
	}
	
	String[] getAuthors() {
		return volumeInfo.getAuthors();
	}

	String getSelfLink() {
		return selfLink;
	}
	
}

class Info{
	
	private String title;
	private String subtitle;
	private String[] authors;
	private String publisher;
	private String publishedDate;
	private String description;
	private double averageRating;
	private Image imageLinks;
	
	
	String getImageLink() {
		if(imageLinks != null) {
			return imageLinks.getImageLink();
		}else {
			return "NO IMAGE FOUND";
		}
	}
	
	String getPublisher() {
		return publisher;
	}
	
	String getTitle() {
		return title;
	}
	
	String[] getAuthors() {
		return authors;
	}

	
}

class Image{
	private String smallThumbnail;
	private String thumbnail;
	
	
	String getImageLink() {
		if(thumbnail != null) {
			return thumbnail;
		}
		else if(smallThumbnail != null) {
			return smallThumbnail;
		}
		else {
			return "NO IMAGE FOUND";
		}
	}
	
}
