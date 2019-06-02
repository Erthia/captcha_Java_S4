package fr.upem.capcha.images;

import java.util.ArrayList;

/**
 * Interface to manipulate images.
 * 
 * @author Hamadache Hédi
 * @author Corradi Emilie
 */

public interface Images{
	/**
	 * @return its Pictures.
	 */
	public ArrayList<Picture> getPhotos();

	/**
	 * @param nbImages the number of random photos wanted.
	 * @return the given number of its Pictures.
	 */
	public ArrayList<Picture> getRandomPhotos(int nbImages);

	/**
	 * @return a random Picture among its Pictures.
	 */
	public Picture getRandomPhoto();

	/**
	 * @param category the correct category
	 * @return true if the structure implementing this interface is correct regarding to the given Category.
	 */
	public boolean isPhotoCorrect(Category category);
}
