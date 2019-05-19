package fr.upem.capcha;

import fr.upem.capcha.images.vehicules.Vehicules;

import java.util.ArrayList;
import java.util.Collections;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Image;
import fr.upem.capcha.images.panneaux.Panneaux;

public class Controller {
	private ArrayList<Image> imagesList;
	private ArrayList<Category> categoryList;
	private Category rightCategory;
	private Category wrongCategory; // A supprimer une fois qu'on a la fonction qui filtre

	
	public Controller(){
		super();
		setImagesList(new ArrayList<Image>());
		setCategoryList(new ArrayList<Category>());
		rightCategory = new Vehicules(); // pour test, à faire une fonction getRandomCategory
		wrongCategory = new Panneaux(); // Faire une fonction qui recupère aléatoirement 5 photos qui ne sont pas rightCategory
	}
	
	// Getter & Setter
	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<Image> getImagesList() {
		return imagesList;
	}

	public void setImagesList(ArrayList<Image> imagesList) {
		this.imagesList = imagesList;
	}
	
	// Methods
	public ArrayList<Image> createSelectedImageList(){
		ArrayList<Image> right; 
		ArrayList<Image> wrong; 
		imagesList.clear();
		right = rightCategory.getRandomPhotos(4);  // J'ai l'impression que getRandomPhotos est encore faux... a retest
		imagesList.addAll(right); 
		wrong = wrongCategory.getRandomPhotos(5); // fonction qui recupère 5 autres photos hors celle de rightCategory
		imagesList.addAll(wrong); 
		Collections.shuffle(imagesList);  // Met dans le desordre
		return imagesList;
	}

}
