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
		namePackage = UrlOperations.packageToPath(namePackage);
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
	 * @return the String of the absolute path of the folder's category (where its images are).
	 */
	public String getCategoryUrl() {
		return categoryUrl;
	}
	
	/**
	 * @return the String name of the class, in lower case.
	 */
	public String getCategory() {
		String category = this.getClass().getSimpleName().toLowerCase();
		return category;
	}

	/**
	 * @return the <code>ArrayList</code> of its children Categories.
	 */
	public ArrayList<Category> getChildren(){
		return this.children;
	}

	/**
	 * @param tmpList the list in which we take the random Pictures.
	 * @return a list of random Pictures choosen into the given ArrayList.
	 */
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

	/**
	 * Set its children Categories.
	 * @param children children
	 */
	public void setChildren(ArrayList<Category> children){
		this.children = children; 
	}

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

	@Override
	public boolean isPhotoCorrect(Category category) {
		if (getCategoryUrl().contains(category.getCategory())) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @param folder Assume that it is a folder
	 * @return the Category corresponding to the given folder. returns <code>null</code> if a problem occurs when instancing the Category.
	 * @throws IllegalArgumentException Exception
	 */
	static public Category instanceFromFolder(File folder){
		String classPath = UrlOperations.folderToPackageName(folder);
		try{
			Class<?> classe = Class.forName(classPath);
			return (Category) classe.newInstance();
		}
			catch (ClassNotFoundException e){
				throw new IllegalArgumentException("La classe n'existe pas");
			}
			catch (InstantiationException e){
				throw new IllegalArgumentException(" La classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramètre");
			}
			catch (IllegalAccessException e){
				throw new IllegalArgumentException("La classe n'est pas accessible");
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
}