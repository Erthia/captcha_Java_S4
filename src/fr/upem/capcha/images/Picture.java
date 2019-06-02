package fr.upem.capcha.images;

import java.util.Objects;


public final class Picture {
	final private String url;
	final private String category;
	
	//Constructeur
	public Picture(String url, String category) {
		this.url = url;
		this.category = category;
	}

	//Getter
	public String getCategory() {
		return category;
	}
	
	public Category getCategoryClass() throws Exception {
		String classPackage = this.url.substring(this.url.lastIndexOf("fr/upem"), this.url.length()).replace("/", "."); 
		classPackage = classPackage.substring(0, classPackage.lastIndexOf(".")); 
		classPackage = classPackage.substring(0, classPackage.lastIndexOf(".")); 
		Class<?> clazz = Class.forName(classPackage + "." + this.category.substring(0, 1).toUpperCase() + this.category.substring(1)); 
		return (Category) clazz.newInstance();
	}
	
	public String getUrl() {
		return url;
	}
	
	@Override
	public String toString() {
		return "Image : url=" + url + ", category=" + category + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Picture)) return false;
		Picture picture = (Picture) obj;
		return this.url.equals(picture.url) && this.category.equals(picture.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.url, this.category);
	}
	
}