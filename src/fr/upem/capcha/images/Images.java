package fr.upem.capcha.images;

import java.util.List;

public interface Images{
	public List<Image> getPhotos();
	public List<Image> getRandomPhotos();
	public Image getRandomPhoto();
	public boolean isPhotoCorrect(Category category);
}
