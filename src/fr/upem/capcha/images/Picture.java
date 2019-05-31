package fr.upem.capcha.images;


public class Picture {
	final private String url;
	final private String category;
	
	//Constructeur
	public Picture(String url, String category) {
		super();
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
	
}