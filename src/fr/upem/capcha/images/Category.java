package fr.upem.capcha.images;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Category implements Images {
	private ArrayList<Image> list;
	String categoryUrl;
	
	//Constructeur 
	public Category() {
		super();
		list = new ArrayList<Image>();
		String path = Images.class.getResource("Images.class").getPath();
		File fileParent = new File(path);
		String pathParent = fileParent.getParent(); // /Users/Hedi/Documents/IMAC/IMAC%202/Semestre%204/Java/captcha_Java_S4/bin/fr/upem/capcha/images
		String namePackage = this.getClass().getPackageName(); // fr.upem.capcha.images.vehicules.voitures
		namePackage = cleanPath(namePackage); // on modifie le string pour n'afficher que /vehicules/voitures
		this.categoryUrl = pathParent + namePackage;
	}
	
	private String cleanPath(String namePackage) {
		Objects.requireNonNull(namePackage, "Le nom du package ne doit pas être nul");
		namePackage = namePackage.replace(".", "/"); // fr/upem/capcha/images/vehicules/voitures
		namePackage =  namePackage.substring(namePackage.lastIndexOf("images/"), namePackage.length()); // filtre jusqu'a /images
		namePackage = namePackage.replace("images/", "/"); // remplace images/ par /
		return namePackage;
	}

	//Getter & Setter
	public ArrayList<Image> getList() {
		return list;
	}
	
	public void setList(ArrayList<Image> list) {
		this.list = list;
	}
	
	public String getCategoryUrl() {
		return categoryUrl;
	}
	
	public String getCategory() {
		String category = this.getClass().getSimpleName();
		return category;
	}

	//Interface's Method
	@Override
	public ArrayList<Image> getPhotos() {
		return list;
	}

	@Override
	public ArrayList<Image> getRandomPhotos(int nbImages){
		if (nbImages >= list.size()) return list;

		ArrayList<Image> result = new ArrayList<>();
		for(int cpt=0; cpt<nbImages; cpt ++){
			Image randomPhoto = getRandomPhoto();
			while(this.list.contains(randomPhoto))
				randomPhoto = getRandomPhoto();
			result.add(randomPhoto);
		}
		return result;
	}

	@Override
	public Image getRandomPhoto() {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return list.get(RGenerator.nextInt(this.list.size()));
	}

	@Override
	public boolean isPhotoCorrect(Category category) {
		if (this.categoryUrl.contains(category.getCategory())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}