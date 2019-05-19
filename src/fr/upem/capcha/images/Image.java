package fr.upem.capcha.images;


public class Image {
	final private String url;
	final private String category;
	
	//Constructeur
	public Image(String url, String category) {
		super();
		this.url = url;
		this.category = category;
	}

	//Getter
	public String getCategory() {
		return category;
	}

	public String getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return "Image : url=" + url + ", category=" + category + "\n";
	}
	
}