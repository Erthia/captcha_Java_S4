package fr.upem.capcha.images;

import java.util.ArrayList;

public interface Images{
	public ArrayList<Picture> getPhotos();
	public ArrayList<Picture> getRandomPhotos(int nbImages);
	public Picture getRandomPhoto();
	public boolean isPhotoCorrect(Category category);
}
