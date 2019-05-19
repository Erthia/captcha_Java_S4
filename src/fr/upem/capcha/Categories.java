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
		createCategoryList(new File(path), "fr.upem.captcha.images", 0, 5);
	}
	
	private void createCategoryList(File currentFolder, String classFolder, int size, int sizeMax){
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
				String className = currentFolder.getName();
				
				//Instancier les category ???
				
				//categoryList.add();
				
				size++;
				File subFolder = test[i];
				createCategoryList(subFolder, className + "." + subFolder.getName(), size, sizeMax);
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
