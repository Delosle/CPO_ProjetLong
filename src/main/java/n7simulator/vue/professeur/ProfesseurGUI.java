package n7simulator.vue.professeur;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.professeur.Professeur;

/**
 * Classe représentant l'affichage d'un professeur
 */
public class ProfesseurGUI extends JLabel implements Observer {

	// Le professeur à afficher
	Professeur professeur;

	/**
	 * Création d'une vue professeur
	 * 
	 * @param professeur  : le professeur à afficher
	 * @param estEmbauche : est-ce que le professeur est embauche ?
	 */
	public ProfesseurGUI(Professeur professeur, boolean estEmbauche) {
		this.professeur = professeur;
		this.setText(this.getTextProfesseur(estEmbauche));
		this.setBorder(new EmptyBorder(15, 10, 10, 10));
	}

	/**
	 * Obtenir la jauge de la valeur de l'apport pédagogique
	 * 
	 * @param pedagogie : la valeur de l'apport pédagogique
	 * @return : la chaine de caractere correspondant à l'HTML permettant de creer
	 *         la jauge
	 */
	private String creerJaugePedagogie(int pedagogie) {
		StringBuilder gaugeBuilder = new StringBuilder();
		gaugeBuilder.append(
				"<div style='display: inline-block; width: 100px; height: 10px; background-color: #e0e0e0; border: 1px solid #000;'>");
		gaugeBuilder.append("<div style='width: ").append(pedagogie * 10)
				.append("px; height: 10px; background-color: #76c7c0;'></div>");
		gaugeBuilder.append("</div>");
		return gaugeBuilder.toString();
	}

	/**
	 * Obtenir le texte HTML permettant d'afficher les informations concernant le
	 * professeur
	 * 
	 * @param estEmbauche : est-ce que le professeur est embauché ?
	 * @return : le texte HTML
	 */
	private String getTextProfesseur(boolean estEmbauche) {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html><p style='width: 600px; text-align: justify'>").append("<span style='color: #115c2c;'> Nom : </span>")
				.append(professeur.getPrenom()).append(" ").append(professeur.getNom()).append("<br>")
				.append("<span style='color: #115c2c;'>Salaire minimum : </span>").append(professeur.getSalaireMin()).append(" €/heure").append("<br>")
				.append("<span style='color: #115c2c;'>Matière enseignée : </span>").append(professeur.getMatiere().getNom()).append("<br><br>")
				.append("<span style='color: #115c2c;'>Description : </span>").append(professeur.getDescription()).append("<br><br>")
				.append("<span style='color: #115c2c;'>Apport pédagogique de la matière : </span>")
				.append(creerJaugePedagogie(professeur.getMatiere().getPedagogie())).append("");

		// affichage des informations du contrat (salaire, nb heures) si le professeur
		// est embauché
		if (estEmbauche) {
			htmlBuilder.append("<br><span style='color: #d94d07;'>Salaire actuel : </span>").append(professeur.getSalaireActuel()).append("€/heure<br>")
					.append("<span style='color: #d94d07;'>Nombre d'heures travaillées par jour : </span>").append(professeur.getNbHeuresTravaillees())
					.append("h");
		}
		htmlBuilder.append("</p></html>");

		return htmlBuilder.toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setText(this.getTextProfesseur(true));
	}

}
