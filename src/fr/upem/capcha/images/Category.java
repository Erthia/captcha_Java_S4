package fr.upem.capcha.images;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class Category implements Images {
	private ArrayList<Image> list;
	private String categoryUrl;
	// Maybe add a list of the children catogories, to manage the captcha difficulty
	
	//Constructeur 
	public Category() {
		super();
		list = new ArrayList<Image>();
		String path = Images.class.getResource("Images.class").getPath();
		File fileParent = new File(path);
		String pathParent = fileParent.getParent(); 
		String namePackage = this.getClass().getPackageName(); 
		namePackage = cleanPath(namePackage); 
		this.categoryUrl = pathParent + namePackage;
		createList();
	}
	
	private String cleanPath(String namePackage) {
		Objects.requireNonNull(namePackage, "Le nom du package ne doit pas être nul");
		namePackage = namePackage.replace(".", "/"); 
		namePackage = namePackage.substring(namePackage.lastIndexOf("images/"), namePackage.length()); // filtre jusqu'a /images
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
		String category = this.getClass().getSimpleName().toLowerCase();
		return category;
	}

	//Interface's Method
	@Override
	public ArrayList<Image> getPhotos() {
		return list;
	}

	@Override
	public ArrayList<Image> getRandomPhotos(int nbImages){
		if (nbImages > list.size()) { 
			 throw new IllegalArgumentException("nbImages trop grand");
		}
		ArrayList<Image> result = new ArrayList<>();
		ArrayList<Image> tmp = list;

		for(int cpt=0; cpt<nbImages; cpt ++){
			Image randomPhoto = getRandomPhoto(tmp);
			result.add(randomPhoto);
			tmp.remove(randomPhoto);
		}
		return result;
	}

	@Override
	public Image getRandomPhoto() {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return list.get(RGenerator.nextInt(this.list.size()));
	}
	
	public Image getRandomPhoto(ArrayList<Image> tmpList) {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return tmpList.get(RGenerator.nextInt(tmpList.size()));
	}

	@Override
	public boolean isPhotoCorrect(Category category) {
		if (getCategoryUrl().contains(category.getCategory())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Methods
	private void createList(){
        createList(new File(this.getCategoryUrl()), "", 0, 20);
	}
	
	private void createList(File currentFolder, String subCategory, int size, int sizeMax){
		File[] test = currentFolder.listFiles();
		if (size > sizeMax) {
            return;
        }
		if(test== null){
			System.out.println("Problem !");
			return;
		}
		
		for(int i=0; i< test.length; i++){
			if(test[i].isDirectory()){
				// Modify here if we want to represent the file arborescence with a tree
				// Idea : create the sub-category here, and call its getPhotos , then add the photos in the list of the current Category
				subCategory = currentFolder.getName();
				size++;
				File subFolder = test[i];
				createList(subFolder, subCategory, size, sizeMax);
			}
			else if(test[i].getName().contains(".jpg")){
				list.add(new Image(test[i].getPath(), currentFolder.getName()));
			}
		}
	}
}