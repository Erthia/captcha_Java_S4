package fr.upem.capcha;


import java.util.ArrayList;
import java.util.Collections;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Picture;

public class Controller {
	private ArrayList<Picture> imagesList;
	private final Categories categoryList;
	private Category rightCategory;

	
	public Controller(){
		super();
		setImagesList(new ArrayList<Picture>());
		categoryList = new Categories();
		rightCategory = categoryList.getRandomCat(); 
	}
	
	// Getter & Setter
	
	public Category getRightCategory() {
		return rightCategory;
	}

	public void setRightCategory(Category rightCategory) {
		this.rightCategory = rightCategory;
	}
	
	public Categories getCategoryList() {
		return categoryList;
	}
	
	public ArrayList<Picture> getImagesList() {
		return imagesList;
	}

	private Category getWrongPhotos(){
		Category wrongCat = categoryList.getRandomCat();
		while(wrongCat.getCategoryUrl().contains(this.rightCategory.getCategoryUrl())) {
			wrongCat = categoryList.getRandomCat();
		}
		return wrongCat;
	}

	public void setImagesList(ArrayList<Picture> imagesList) {
		this.imagesList = imagesList;
	}
	
	// Methods
	public ArrayList<Picture> createSelectedImageList(){
		ArrayList<Picture> right; 
		imagesList.clear();
		right = rightCategory.getRandomPhotos(4);  
		imagesList.addAll(right);  
		imagesList.addAll(getWrongPhotos().getRandomPhotos(5));
		Collections.shuffle(imagesList); 
		return imagesList;
	}
}
