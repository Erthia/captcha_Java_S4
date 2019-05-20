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

	private ArrayList<Picture> getWrongPhotos(int nb){
		ArrayList<Picture> result = new ArrayList<>(nb);
		for(int i=0; i<nb; i++){
			Category wrongCat = categoryList.getRandomCat();
			while(wrongCat.getCategoryUrl().contains(this.rightCategory.getCategoryUrl()))
				wrongCat = categoryList.getRandomCat();
			result.add(wrongCat.getRandomPhoto());
		}
		return result;
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
		imagesList.addAll(getWrongPhotos(5));
		Collections.shuffle(imagesList); 
		return imagesList;
	}
}
