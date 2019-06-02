package fr.upem.capcha.images;

import java.util.Objects;

/**
 * Represents an image.
 * 
 * @author Hamadache Hédi
 * @author Corradi Emilie
 */

public final class Picture {
	final private String url;
	final private String category;
	
	/**
	 * Constructs the <code>Picture</code>.
	 * @param url initialize the url of the <code>Picture</code>.
	 * @param category initialize the category of the <code>Picture</code>.
	 */
	//Constructeur
	public Picture(String url, String category) {
		this.url = url;
		this.category = category;
	}

	//Getter
	/**
	 * @return a string category of the <code>Picture</code>.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * @return an object <code>Category</code> of the <code>Picture</code>.
	 */
	public Category getCategoryClass() throws Exception {
		String classPackage = this.url.substring(this.url.lastIndexOf("fr/upem"), this.url.length()).replace("/", "."); 
		classPackage = classPackage.substring(0, classPackage.lastIndexOf(".")); 
		classPackage = classPackage.substring(0, classPackage.lastIndexOf(".")); 
		Class<?> clazz = Class.forName(classPackage + "." + this.category.substring(0, 1).toUpperCase() + this.category.substring(1)); 
		return (Category) clazz.newInstance();
	}
	
	/**
	 * @return the url of this <code>Picture</code>.
	 */
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