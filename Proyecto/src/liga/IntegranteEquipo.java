package liga;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

/**
 * Clase abstracta IntegranteEquipo de la que heredarán Futbolista, Tecnico y Directivo. A esta clase se le
 * aplica la interfaz Retributable con el fin de que en cada uno de sus hijos se deban implementar los métodos de 
 * dicha interfaz.
 * @author Jesús López González
 *
 */
public abstract class IntegranteEquipo implements Retributable, Serializable{

		private static final long serialVersionUID = 1L;
		/**
		 * Patrón que permite introducir un nombre simple que comience por Mayúscula y contenga tilde o ñ
		 */
		private static final Pattern PATRON_NOMBRE = Pattern.compile("^([A-ZÁÉÍÓÚ][a-záéíóúç]*)+((\\s)[A-ZÁÉÍÓÚ][a-záéíóúç]*)*$");
		private static Matcher matcher;
		protected String nombre;
		protected String apellidos;
		protected float sueldo;
		protected String nacionalidad;
		protected Equipo equipo;
		protected ImageIcon foto;
		protected Cargo cargo;
		
		/**
		 * Constructor por nombre y apellidos
		 * @param id
		 */
		public IntegranteEquipo(String nombre, String apellidos){
			setNombre(nombre);
			setApellidos(apellidos);
		}
		
		/**
		 * Constructor de IntegranteEquipo
		 * @param nombre
		 * @param apellidos
		 * @param nacimiento
		 * @param sueldo
		 * @param nacionalidad
		 * @param equipo
		 */
		public IntegranteEquipo(String nombre, String apellidos, String nacionalidad, Equipo equipo, ImageIcon foto, Cargo cargo) {
			setNombre(nombre);
			setApellidos(apellidos);
			setNacionalidad(nacionalidad);
			setEquipo(equipo);
			setFoto(foto);
			setCargo(cargo);
		}

		/**
		 * Getter de cargo
		 * @return
		 */
		public Cargo getCargo() {
			return cargo;
		}

		/**
		 * Setter de Cargo
		 * @param cargo
		 */
		protected void setCargo(Cargo cargo) {
			this.cargo = cargo;
		}

		/**
		 * Getter de nombre
		 * @return
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * Setter de nombre
		 * @param nombre
		 */
		private void setNombre(String nombre) {
			this.nombre = nombre;
		}

		/**
		 * Getter de Apellidos
		 * @return
		 */
		public String getApellidos() {
			return apellidos;
		}

		/**
		 * Setter de Apellidos
		 * @param apellidos
		 */
		private void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}
		
		/**
		 * Getter de sueldo
		 * @return
		 */
		public float getSueldo() {
			return sueldo;
		}

		/**
		 * Setter de sueldo
		 * @param sueldo
		 */
		protected void setSueldo(float sueldo) {
			this.sueldo = sueldo;
		}

		/**
		 * Getter de nacionalidad
		 * @return
		 */
		public String getNacionalidad() {
			return nacionalidad;
		}

		/**
		 * Setter de nacionalidad
		 * @param nacionalidad
		 */
		private void setNacionalidad(String nacionalidad) {
			this.nacionalidad = nacionalidad;
		}

		/**
		 * Getter de Equipo
		 * @return
		 */
		public Equipo getEquipo() {
			return equipo;
		}

		/**
		 * Setter de Equipo
		 * @param equipo
		 */
		private void setEquipo(Equipo equipo) {
			this.equipo = equipo;
		}
		
		/**
		 * Getter de foto
		 * @return
		 */
		public ImageIcon getFoto() {
			return foto;
		}

		/**
		 * Setter de foto
		 * @param foto
		 */
		public void setFoto(ImageIcon foto) {
			this.foto = foto;
		}
		
		/**
		 * Método que nos permite validar el nombre de un jugador
		 * @param nombre Nombre del jugador
		 * @return True en caso de que sea válido o False en caso contrario
		 */
		public static boolean validarNombre(String nombre){
			matcher = PATRON_NOMBRE.matcher(nombre);
			return matcher.matches();
			
		}

		
		@Override
		/**
		 * Metodo hashCode de IntegranteEquipo
		 */
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((apellidos == null) ? 0 : apellidos.hashCode());
			result = prime * result
					+ ((nombre == null) ? 0 : nombre.hashCode());
			return result;
		}

		@Override
		/**
		 * Método equals de IntegranteEquipo que tendrá en cuenta el nombre y los apellidos
		 */
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IntegranteEquipo other = (IntegranteEquipo) obj;
			if (apellidos == null) {
				if (other.apellidos != null)
					return false;
			} else if (!apellidos.equals(other.apellidos))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}
		
		public void generarSueldo(){
			
		}
}
