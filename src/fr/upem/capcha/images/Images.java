package fr.upem.capcha.images;

import java.util.ArrayList;

public interface Images{
	public ArrayList<Image> getPhotos();
	public ArrayList<Image> getRandomPhotos(int nbImages);
	public Image getRandomPhoto();
	public boolean isPhotoCorrect(Category category);
}
