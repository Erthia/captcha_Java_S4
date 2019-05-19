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
		createCategoryList(new File(path), 0, 10);
	}
	
	private void createCategoryList(File currentFolder, int size, int sizeMax){
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

				String path = subFolder.getPath(); 
				path = cleanPath(path); 
				System.out.println(path);
				
				String classPath = path + "." + className;
				System.out.println(classPath);
				try{
					Class<?> classe = Class.forName(classPath);
					Category instance = (Category) classe.newInstance();
					categoryList.add(instance);
				}
			    catch (ClassNotFoundException e){
			      System.out.println("La classe n'existe pas");
			    }
			    catch (InstantiationException e){
			      System.out.println(" La classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramètre");
			    }
			    catch (IllegalAccessException e){
				  System.out.println("La classe n'est pas accessible");
			    }

				size++;
				createCategoryList(subFolder, size, sizeMax);
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
	
	private String cleanPath(String namePackage) {
		namePackage = namePackage.replace("/","."); 
		namePackage = namePackage.substring(namePackage.lastIndexOf("fr."), namePackage.length()); // filtre jusqu'a /images
		return namePackage;
	}


}
