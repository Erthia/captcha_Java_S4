package fr.upem.capcha.images;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import fr.upem.capcha.UrlOperations;

public abstract class Category implements Images {
	private ArrayList<Picture> list;
	private String categoryUrl;
	public final int level; // from 0
	private boolean lastLevel = true;
	// Maybe add a list of the children catogories, to manage the captcha difficulty
	
	//Constructeur 
	public Category() {
		super();
		list = new ArrayList<Picture>();
		String path = Images.class.getResource("Images.class").getPath();
		File fileParent = new File(path);
		String pathParent = fileParent.getParent(); 
		String namePackage = this.getClass().getPackageName(); 
		namePackage = UrlOperations.cleanPath(namePackage);
		this.categoryUrl = pathParent + namePackage;
		String relativeUrl = UrlOperations.AbsoluteToRelative(pathParent + namePackage);
		this.level = UrlOperations.countSeparators(relativeUrl, File.separatorChar) - 4;
		createList();
	}

	//Getter & Setter
	public ArrayList<Picture> getList() {
		return list;
	}
	
	public void setList(ArrayList<Picture> list) {
		this.list = list;
	}
	
	public String getCategoryUrl() {
		return categoryUrl;
	}
	
	public String getCategory() {
		String category = this.getClass().getSimpleName().toLowerCase();
		return category;
	}

	public final boolean getLastLevel(){
		return lastLevel;
	}

	//Interface's Method
	@Override
	public ArrayList<Picture> getPhotos() {
		return list;
	}

	@Override
	public ArrayList<Picture> getRandomPhotos(int nbImages){
		if (nbImages > list.size()) { 
			 throw new IllegalArgumentException("Not enough images in " + getCategory() + " (" + list.size() + ")");
		}
		ArrayList<Picture> result = new ArrayList<>();
		ArrayList<Picture> tmp = list;

		for(int cpt=0; cpt<nbImages; cpt ++){
			Picture randomPhoto = getRandomPhoto(tmp);
			result.add(randomPhoto);
			tmp.remove(randomPhoto);
		}
		return result;
	}

	@Override
	public Picture getRandomPhoto() {
		long seed = System.currentTimeMillis();
		Random RGenerator = new Random(seed);
		return list.get(RGenerator.nextInt(this.list.size()));
	}
	
	public Picture getRandomPhoto(ArrayList<Picture> tmpList) {
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
				lastLevel = false;
				subCategory = currentFolder.getName();
				size++;
				File subFolder = test[i];
				createList(subFolder, subCategory, size, sizeMax);
			}
			else if(test[i].getName().contains(".jpg") || test[i].getName().contains(".JPG") || test[i].getName().contains(".jpeg")){
				String relUrl = UrlOperations.AbsoluteToRelative(test[i].getAbsolutePath());
				list.add(new Picture(relUrl, currentFolder.getName()));
			}
		}
	}
	
	@Override
	public String toString() {
		return "Category : url=" + getCategoryUrl() + ", category=" + getCategory() + "\n" + ", level=" + this.level +", last level ? " + this.lastLevel;
	}
}