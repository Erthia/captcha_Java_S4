package fr.upem.capcha;

import fr.upem.capcha.images.vehicules.Vehicules;

import java.util.ArrayList;
import java.util.Collections;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Picture;
import fr.upem.capcha.images.panneaux.Panneaux;

public class Controller {
	private ArrayList<Picture> imagesList;
	private final Categories categoryList;
	private Category rightCategory;
	private Category wrongCategory; // A supprimer une fois qu'on a la fonction qui filtre

	
	public Controller(){
		super();
		setImagesList(new ArrayList<Picture>());
		categoryList = new Categories();
		rightCategory = categoryList.getRandomCat(); // pour test, à faire une fonction getRandomCategory
		wrongCategory = new Panneaux(); 
	}
	
	// Getter & Setter
	
	public Category getRightCategory() {
		return rightCategory;
	}

	public void setRightCategory(Category rightCategory) {
		this.rightCategory = rightCategory;
	}

	public Category getWrongCategory() {
		return wrongCategory;
	}

	public void setWrongCategory(Category wrongCategory) {
		this.wrongCategory = wrongCategory;
	}
	
	public Categories getCategoryList() {
		return categoryList;
	}
	
	public ArrayList<Picture> getImagesList() {
		return imagesList;
	}

	public void setImagesList(ArrayList<Picture> imagesList) {
		this.imagesList = imagesList;
	}
	
	// Methods
	public ArrayList<Picture> createSelectedImageList(){
		ArrayList<Picture> right; 
		ArrayList<Picture> wrong; 
		imagesList.clear();
		right = rightCategory.getRandomPhotos(4);  
		imagesList.addAll(right); 
		wrong = wrongCategory.getRandomPhotos(5); 
		imagesList.addAll(wrong); 
		Collections.shuffle(imagesList); 
		return imagesList;
	}
}
