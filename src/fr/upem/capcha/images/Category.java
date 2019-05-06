package fr.upem.capcha.images;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Category implements Images {
	private ArrayList<Image> list;
	private String categoryUrl;
	
	//Constructeur 

	//Getter & Setter
	@Override
	public ArrayList<Image> getPhotos() {
		return (ArrayList) List.copyOf(list);
	}

	public void setList(ArrayList<Image> list) {
		this.list = list;
	}
	public String getCategoryUrl() {
		return categoryUrl;
	}
	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}
	
	//Méthodes à implémenter
	@Override
	public ArrayList<Image> getRandomPhotos(int nbImage) {
		// TODO Auto-generated method stub
		return null;
	}

	// not tested
	@Override
	public Image getRandomPhoto() {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return list.get(RGenerator.nextInt(this.list.size()));
	}

	@Override
	public boolean isPhotoCorrect(Category category) {
		// TODO Auto-generated method stub
		return false;
	}
	
}