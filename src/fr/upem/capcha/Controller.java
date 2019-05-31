package fr.upem.capcha;


import java.util.ArrayList;
import java.util.Collections;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Picture;

public class Controller {
	private ArrayList<Picture> imagesList;
	private final Categories categoryList;
	private static Category rightCategory;
	public static boolean success;
	
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
		Controller.rightCategory = rightCategory;
	}
	
	public Categories getCategoryList() {
		return categoryList;
	}
	
	public ArrayList<Picture> getImagesList() {
		return imagesList;
	}

	private Category getWrongPhotos(){
		Category wrongCat = categoryList.getRandomCat();
		while(wrongCat.getCategoryUrl().contains(Controller.rightCategory.getCategoryUrl())) {
			wrongCat = categoryList.getRandomCat();
		}
		return wrongCat;
	}

	public void setImagesList(ArrayList<Picture> imagesList) {
		this.imagesList = imagesList;
	}

	public static boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		Controller.success = success;
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
	
	public static boolean verify(ArrayList<Picture> list) throws Exception {
		int count = 0;
		for (int i = 0; i < list.size(); ++i) {
			if (!list.get(i).getCategoryClass().isPhotoCorrect(rightCategory))
				return false;
			++count;
		}
		if (count >= 4)
			return true;
		else
			return false;
	}
	
	public static void check(ArrayList<Picture> list) throws Exception {
		success = verify(list);
	}
}
