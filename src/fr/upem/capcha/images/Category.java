package fr.upem.capcha.images;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Category implements Images {
	private ArrayList<Image> list;
	String categoryUrl;
	
	//Constructeur 
	public Category() {
		super();
		list = new ArrayList<Image>();
		String path = Images.class.getResource("Images.class").getPath();
		File dir = new File(path);
		this.categoryUrl = dir.getParent() + this.getClass().getPackageName().replace(".", "/") + ".java";
	}// Quelque chose du genre... je sais pas trop faut testé.
	
	//Getter & Setter	
	
	// not tested
	@Override
	public ArrayList<Image> getPhotos() {
		return list;
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
	
	// not tested
	@Override
	public ArrayList<Image> getRandomPhotos(int nbImages){
		ArrayList<Image> result = new ArrayList<>();
		for(int cpt=0; cpt<nbImages; cpt ++){
			Image randomPhoto = getRandomPhoto();
			if(!this.list.contains(randomPhoto))
				result.add(randomPhoto);
		}
		return result;
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