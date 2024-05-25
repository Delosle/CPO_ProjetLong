package n7simulator.vue.professeur;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import n7simulator.modele.professeur.Professeur;

public class ProfesseurGUI extends JLabel {
	
	Professeur professeur;
	
	public ProfesseurGUI(Professeur professeur, boolean estEmbauche) {
		this.professeur = professeur;
		this.setText(this.getTextProfesseur(estEmbauche));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	private String creerJaugePedagogie(int pedagogie) {
		 StringBuilder gaugeBuilder = new StringBuilder();
	        gaugeBuilder.append("<div style='display: inline-block; width: 100px; height: 10px; background-color: #e0e0e0; border: 1px solid #000;'>");
	        gaugeBuilder.append("<div style='width: ").append(pedagogie * 10).append("px; height: 10px; background-color: #76c7c0;'></div>");
	        gaugeBuilder.append("</div>");
	        return gaugeBuilder.toString();
    }
	
	private String getTextProfesseur(boolean estEmbauche) {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html><p style='text-align: justify;text-align: left;'>")
            .append("Nom : ").append(professeur.getPrenom()).append(" ").append(professeur.getNom()).append("<br>")
            .append("Matière enseignée : ").append(professeur.getMatiere().getNom()).append("<br>")
            .append("Apport pédagogique de la matière : ").append(creerJaugePedagogie(professeur.getMatiere().getPedagogie())).append("<br>")
            .append("Salaire minimum : ").append(professeur.getSalaireMin()).append(" €/heure");
		
		if(estEmbauche) {
			htmlBuilder.append("<br>Salaire actuel : ").append(professeur.getSalaireActuel()).append("€/heure<br>")
						.append("Nombre d'heures travaillées par jour : ").append(professeur.getNbHeuresTravaillees()).append("h");
		}
        htmlBuilder.append("</p></html>");
		
		return htmlBuilder.toString();
	}

}
