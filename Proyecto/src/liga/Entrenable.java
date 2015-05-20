package liga;

/**
 * Interfaz entrenable que se aplica a las clases Tecnico y Futbolista
 * @author Jesús López González
 *
 */
public interface Entrenable {
	/**
	 * Metodo que entrena a un integrante de tipo Futbolista o Técnico
	 */
	void entrenar();
	
	/**
	 * Método que genera la técnica de un Futbolista o Técnico
	 */
	void generarTecnica();

}
