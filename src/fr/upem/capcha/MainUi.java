package fr.upem.capcha;
 
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import fr.upem.capcha.Controller;
import fr.upem.capcha.images.Picture;


public class MainUi {
	
	private static ArrayList<Picture> selectedImages = new ArrayList<Picture>();
	
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Capcha"); // Création de la fenêtre principale
		
		GridLayout layout = createLayout();  // Création d'un layout de type Grille avec 4 lignes et 3 colonnes
		
		frame.setLayout(layout);  // affection du layout dans la fenêtre.
		frame.setSize(1024, 768); // définition de la taille
		frame.setResizable(false);  // On définit la fenêtre comme non redimentionnable
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'on ferme la fenêtre on quitte le programme.
		 

		JButton okButton = createOkButton(frame);

		//Link between UI and controller
		Controller controller = new Controller();
		controller.setRightCategory(0);
		ArrayList<Picture> toDisplay = controller.createSelectedImageList();
		System.out.println(toDisplay);
		System.out.println(controller.getRightCategory());
		if (toDisplay.size() != 9) throw new IllegalArgumentException("The list of pictures to display has not 9 Pictures");
		for (int i = 0; i < 9; ++i)
			frame.add(createLabelImage(toDisplay.get(i)));

		frame.add(new JTextArea("\nRight Category : \n" + controller.getRightCategory().getCategory()));
		
		
		frame.add(okButton);
		
		frame.setVisible(true);
	}
	
	
	private static GridLayout createLayout(){
		return new GridLayout(4,3);
	}
	
	private static JButton createOkButton(JFrame frame){
		return new JButton(new AbstractAction("Vérifier") { //ajouter l'action du bouton
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements
					
					@Override
					public void run() { 
						try {
							Controller.check(selectedImages);
							selectedImages.clear();
							if (Controller.isSuccess() == false) { 
								JOptionPane.showMessageDialog(frame,
									    "Vous vous êtes trompés",
									    "Résultat",
										JOptionPane.PLAIN_MESSAGE);
							}
							else {
								 int input = JOptionPane.showConfirmDialog(frame, 
										 	"Bravo ! Vous n'êtes pas un robot.",
											"Résultat",
											JOptionPane.DEFAULT_OPTION);
								 if (input == JOptionPane.OK_OPTION || input == JOptionPane.CLOSED_OPTION) {
									 frame.dispose();
								 }
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
	
	private static JLabel createLabelImage(Picture image) throws IOException{
		final URL url = MainUi.class.getResource(image.getUrl().substring("fr/upem/capcha/".length())); //Aller chercher les images !! IMPORTANT 
		
		System.out.println(" URL récupérée : " + url); 
		BufferedImage img = ImageIO.read(url); //lire l'image
		Image sImage = img.getScaledInstance(1024/3,768/4, Image.SCALE_SMOOTH); //redimentionner l'image
		
		final JLabel label = new JLabel(new ImageIcon(sImage)); // créer le composant pour ajouter l'image dans la fenêtre
		
		label.addMouseListener(new MouseListener() { //Ajouter le listener d'évenement de souris
			private boolean isSelected = false;
			
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
		
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) { //ce qui nous intéresse c'est lorsqu'on clique sur une image, il y a donc des choses à faire ici
				EventQueue.invokeLater(new Runnable() { 
					
					@Override
					public void run() {
						if(!isSelected){
							label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
							isSelected = true;
							selectedImages.add(image);
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							isSelected = false;
							selectedImages.remove(image);
						}
						
					}
				});
				
			}
		});
		
		return label;
	}
}
