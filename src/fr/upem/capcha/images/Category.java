package fr.upem.capcha.images;

import java.util.List;

public abstract class Category implements Images {
	private List<Image> list;
	private String categoryUrl;
	
	//Constructeur 

	//Getter & Setter
	@Override
	public List<Image> getPhotos() {
		// TODO Auto-generated method stub
		return list;
	}

	public void setList(List<Image> list) {
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
	public List<Image> getRandomPhotos(int nbPhotos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getRandomPhoto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPhotoCorrect(Category category) {
		// TODO Auto-generated method stub
		return false;
	}
	
}