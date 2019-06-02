package fr.upem.capcha;


import java.util.ArrayList;
import java.util.Collections;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Picture;

/**
 * Stores and manipulates the pictures to display.
 * 
 * @author Corradi Emilie
 * @author Hamadache Hédi
 */
public class Controller {
	private ArrayList<Picture> imagesList;
	private final Categories categoryList;
	private static Category rightCategory;
	
	/**
	 * Constructor
	 * 
	 * @param categoriesList The <code>Categories</code> object storing the different Categories.
	 */
	public Controller(Categories categoriesList){
		super();
		this.imagesList = new ArrayList<Picture>();
		this.categoryList = categoriesList;
	}
	
	/**
	 * @return the Category the user has to select.
	 */
	public final Category getRightCategory() {
		return rightCategory;
	}

	/**
	 * Choose the right Category (random),
	 * among the 0-level if there was not right Category previously,
	 * or among the children of the previous right Category (level +1)
	 * <p>
	 * Set the rightCategory to null if the previous has no children (no difficulty higher).
	 */
	public final void setRightCategory() {
		if(rightCategory == null) rightCategory = categoryList.getRandomCat();
		else{
			ArrayList<Category> categoriesSup = rightCategory.getChildren();
			if(categoriesSup.isEmpty() == true) rightCategory=null;
			else rightCategory = Categories.getRandomCat(categoriesSup);
		}
	}

	/**
	 * @return a random Category, different from the right Category. The given Category is the most high-leveled in its branch.
	 */
	private final Category getWrongCat(){
		Category wrongCat = categoryList.getRandomCat();
		while(wrongCat.isPhotoCorrect(rightCategory) || rightCategory.isPhotoCorrect(wrongCat)) {
			wrongCat = categoryList.getRandomCat();
			while(!wrongCat.getChildren().isEmpty()){
				wrongCat = Categories.getRandomCat(wrongCat.getChildren());
			}
		}
		return wrongCat;
	}
	
	/**
	 * Create the list of Picture to display.
	 * 
	 * @return the list of Picture to display.
	 */
	public ArrayList<Picture> createSelectedImageList(){
		int nbRightImages = 4;
		int nbWrongImages = 5;
		ArrayList<Picture> right = new ArrayList<Picture>(4);
		ArrayList<Picture> wrong = new ArrayList<Picture>(5);

		imagesList.clear();
		
		right = rightCategory.getRandomPhotos(nbRightImages);
		imagesList.addAll(right);

		for(int cpt=0; cpt<nbWrongImages; cpt ++){
			Category wrongCat = getWrongCat();
			Picture randomPhoto;
			do {randomPhoto = wrongCat.getRandomPhoto();}
			while(wrong.contains(randomPhoto));
			wrong.add(randomPhoto);
		}
		imagesList.addAll(wrong);
		Collections.shuffle(imagesList);

		return imagesList;
	}
	
	/**
	 * Verify if the given ArrayList of Picture, corresponding to the selected images, is correct (all the displayed right images have been selected).
	 * 
	 * @param list the list to check
	 * @return true if the ArrayList contains all the displayed right images
	 * @throws Exception 
	 */
	public static boolean verify(ArrayList<Picture> list, int nbRightImages) throws Exception {
		int count = 0;
		for (int i = 0; i < list.size(); ++i) {
			if (!list.get(i).getCategoryClass().isPhotoCorrect(rightCategory))
				return false;
			++count;
		}
		if (count >= nbRightImages)
			return true;
		else
			return false;
	}

}
