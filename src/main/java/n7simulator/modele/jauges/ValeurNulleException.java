package n7simulator.modele.jauges;

/**
 * Exception étant levée si une jauge atteint une valeur nulle
 */
public class ValeurNulleException extends RuntimeException {
	
	//La jauge ayant déclenché l'exception
	private Jauge jaugeDeclenchement;
	
	/**
	 * Obtenir une exception
	 * @param jaugeException : la jauge ayant déclenché l'exception
	 */
	public ValeurNulleException(Jauge jaugeException) {
		this.jaugeDeclenchement = jaugeException;
	}
	
	/**
	 * Obtenir la jauge ayant déclenché l'exception
	 * @return : la jauge ayant déclenché l'exception
	 */
	public Jauge getJaugeDeclenchement() {
		return this.jaugeDeclenchement;
	}

}
