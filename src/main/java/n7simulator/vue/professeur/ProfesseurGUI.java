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
		htmlBuilder.append("<html><p style='text-align: justify;text-align: left;'>").append("Nom : ")
				.append(professeur.getPrenom()).append(" ").append(professeur.getNom()).append("<br>")
				.append("Matière enseignée : ").append(professeur.getMatiere().getNom()).append("<br>")
				.append("Apport pédagogique de la matière : ")
				.append(creerJaugePedagogie(professeur.getMatiere().getPedagogie())).append("<br>")
				.append("Salaire minimum : ").append(professeur.getSalaireMin()).append(" €/heure");

		// affichage des informations du contrat (salaire, nb heures) si le professeur
		// est embauché
		if (estEmbauche) {
			htmlBuilder.append("<br>Salaire actuel : ").append(professeur.getSalaireActuel()).append("€/heure<br>")
					.append("Nombre d'heures travaillées par jour : ").append(professeur.getNbHeuresTravaillees())
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
