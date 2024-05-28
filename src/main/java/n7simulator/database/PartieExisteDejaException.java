package n7simulator.database;

public class PartieExisteDejaException extends Exception{

    /**
     * Constructeur de l'exception vérifier si la partie existe déjà
     * @param message le message d'erreur
     */
    public PartieExisteDejaException(String message) {
        super(message);
    }
}
