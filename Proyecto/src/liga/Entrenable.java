package liga;

/**
 * Interfaz entrenable que se aplica a las clases Tecnico y Futbolista
 * @author Jes�s L�pez Gonz�lez
 *
 */
public interface Entrenable {
	/**
	 * Metodo que entrena a un integrante de tipo Futbolista o T�cnico
	 */
	void entrenar();
	
	/**
	 * M�todo que genera la t�cnica de un Futbolista o T�cnico
	 */
	void generarTecnica();

}
