package fr.upem.capcha.images;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Objects;

import fr.upem.capcha.UrlOperations;

/**
 * Represents an images' category.
 * 
 * @author Hamadache Hédi
 * @author Corradi Emilie
 */

public abstract class Category implements Images {
	private ArrayList<Picture> picturesList = new ArrayList<Picture>();
	private String categoryUrl;
	private ArrayList<Category> children;
	protected String categoryName;
	
	/**
	 * Constructs the <code>Category</code> without any chidren or pictureList.
	 * To define these elements, this constructor must not be called directly, but through the <code>instanceFromFolder(File folder)</code> method.
	 */
	protected Category() {
		super();
		String path = Images.class.getResource("Images.class").getPath();
		File fileParent = new File(path);
		String pathParent = fileParent.getParent(); 
		String namePackage = this.getClass().getPackageName(); 
		namePackage = UrlOperations.cleanPath(namePackage);
		this.categoryUrl = pathParent + namePackage;
	}

	/**
	 * @return the pictures list of this <code>Category</code>.
	 */
	public ArrayList<Picture> getList() {
		return picturesList;
	}

	/**
	 * Add the <code>Picture</code> objects in the given list to the <code>Picture</code> list of this <code>Category</code>.
	 * 
	 * @param pictures the <code>ArrayList</code> of the pictures to be added.
	 */
	public void addPictures(ArrayList<Picture> pictures){
		picturesList.addAll(pictures);
	}
	
	/**
	 * @return the <code>String<code> of the absolute path of the folder's category (where its images are).
	 */
	public String getCategoryUrl() {
		return categoryUrl;
	}
	
	public String getCategory() {
		String category = this.getClass().getSimpleName().toLowerCase();
		return category;
	}

	public ArrayList<Category> getChildren(){
		return this.children;
	}

	public void setChildren(ArrayList<Category> children){
		this.children = children; 
	}

	//Interface's Method
	@Override
	public ArrayList<Picture> getPhotos() {
		return picturesList;
	}

	@Override
	public ArrayList<Picture> getRandomPhotos(int nbImages){
		if (nbImages > picturesList.size()) { 
			 throw new IllegalArgumentException("Not enough images in " + getCategory() + " (" + picturesList.size() + ")");
		}
		ArrayList<Picture> result = new ArrayList<>();
		ArrayList<Picture> tmp = picturesList;

		for(int cpt=0; cpt<nbImages; cpt ++){
			Picture randomPhoto = getRandomPhoto(tmp);
			result.add(randomPhoto);
			tmp.remove(randomPhoto);
		}
		return result;
	}

	@Override
	public Picture getRandomPhoto() {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return picturesList.get(RGenerator.nextInt(this.picturesList.size()));
	}
	
	public Picture getRandomPhoto(ArrayList<Picture> tmpList) {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return tmpList.get(RGenerator.nextInt(tmpList.size()));
	}

	/**
	 * @return the beautiful category name, to be used in the displayed questions
	 */
	public final String getCategoryName(){
		return this.categoryName;
	}

	@Override
	public boolean isPhotoCorrect(Category category) {
		if (getCategoryUrl().contains(category.getCategory())) {
			return true;
		}
		else {
			return false;
		}
	}

	// does not verify is the file is a folder
	// return null if problems when instancing the Category.
	static public Category instanceFromFolder(File folder){
		String str = folder.getName();
		String className = str.substring(0, 1).toUpperCase() + str.substring(1);
		String path = folder.getPath(); 
		path = cleanPath(path);
		String classPath = path + "." + className;
		try{
			Class<?> classe = Class.forName(classPath);
			return (Category) classe.newInstance();
		}
			catch (ClassNotFoundException e){
				System.out.println("La classe n'existe pas");
				throw new IllegalArgumentException();
			}
			catch (InstantiationException e){
				System.out.println(" La classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramètre");
				throw new IllegalArgumentException();
			}
			catch (IllegalAccessException e){
				System.out.println("La classe n'est pas accessible");
				throw new IllegalArgumentException();
			}
	}

	
	@Override
	public String toString() {
		return "Category : url=" + getCategoryUrl() + ", category=" + getCategory() + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		return obj.getClass().equals(this.getClass());
	}

	@Override
	public int hashCode() {
		return Objects.hash(picturesList, categoryUrl, children);
	}

	private static String cleanPath(String namePackage) {
		namePackage = namePackage.replace(File.separator, ".");
		namePackage = namePackage.substring(namePackage.lastIndexOf("fr."), namePackage.length()); // filtre jusqu'a /images
		return namePackage;
	}
}