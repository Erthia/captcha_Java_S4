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
	
	public Controller(Categories categoriesList){
		super();
		setImagesList(new ArrayList<Picture>());
		this.categoryList = categoriesList;
	}
	
	// Getter & Setter
	
	public Category getRightCategory() {
		return rightCategory;
	}

	// does not verify if the value of level is too high
	// set rightCategory to null if the previous level was the last
	public final void setRightCategory() {
		if(rightCategory == null) rightCategory = categoryList.getRandomCat();
		else{
			ArrayList<Category> categoriesSup = rightCategory.getChildren();
			if(categoriesSup.isEmpty() == true) rightCategory=null;
			else rightCategory = Categories.getRandomCat(categoriesSup);
		}
	}
	
	public Categories getCategoryList() {
		return categoryList;
	}
	
	public ArrayList<Picture> getImagesList() {
		return imagesList;
	}

	private Category getWrongCat(){
		Category wrongCat = categoryList.getRandomCat();
		while(wrongCat.isPhotoCorrect(rightCategory) || rightCategory.getCategoryUrl().contains(wrongCat.getCategoryUrl())) {
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
		int nbRightImages = 4;
		int nbWrongImages = 5;
		ArrayList<Picture> right = new ArrayList<Picture>(4);
		ArrayList<Picture> wrong = new ArrayList<Picture>(5);

		imagesList.clear();
		
		right = rightCategory.getRandomPhotos(nbRightImages);
		imagesList.addAll(right);

		for(int cpt=0; cpt<nbWrongImages; cpt ++){
			Category wrongCat = getWrongCat();
			System.out.println("Wrong Category --------- :\n" + wrongCat);
			Picture randomPhoto;
			do {randomPhoto = wrongCat.getRandomPhoto();}
			while(wrong.contains(randomPhoto));
			wrong.add(randomPhoto);
		}
		imagesList.addAll(wrong);
		Collections.shuffle(imagesList);

		//debug
		System.out.println("Wrong pictures :\n" + wrong);
		System.out.println("Right pictures :\n" + right);
		//end debug
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
