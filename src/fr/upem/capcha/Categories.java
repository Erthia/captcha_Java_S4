package fr.upem.capcha;

import java.io.File;
import java.util.ArrayList;

import fr.upem.capcha.images.Category;
import fr.upem.capcha.images.Images;

public class Categories {
	private ArrayList<Category> categoryList;

	public Categories() {
		super();
		categoryList = new ArrayList<Category>();
		String path = Images.class.getResource("Images.class").getPath();
		File folder = new File(path);
		path = folder.getParent(); 
		createCategoryList(new File(path), "fr.upem.captcha.images", 0, 10);
	}
	
	private void createCategoryList(File currentFolder, String categoryName, int size, int sizeMax){
		if (size > sizeMax) {
            return;
        }
		
		File[] test = currentFolder.listFiles();
		if(test== null){
			System.out.println("Problem !");
			return;
		}
		
		for(int i=0; i< test.length; i++){
			if(test[i].isDirectory()){        		
				File subFolder = test[i];
				String str = subFolder.getName();
				String className = str.substring(0, 1).toUpperCase() + str.substring(1);
				System.out.println(className);

				size++;
				createCategoryList(subFolder, className, size, sizeMax);
			}
		}
	}

	//Getter & Setter
	public ArrayList<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	//Methods
	/*
	Category getRandomCat(ArrayList<Category> list) {
		return categoryList; 
		//Retourne une category random parmi la liste
	}*/

}
