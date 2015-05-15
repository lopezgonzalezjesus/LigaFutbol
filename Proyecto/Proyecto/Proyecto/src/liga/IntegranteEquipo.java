package liga;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

public abstract class IntegranteEquipo implements Retributable, Serializable{

	private static final long serialVersionUID = 1L;
		private static final Pattern PATRON_NOMBRE = Pattern.compile("^[A-Z¡…Õ⁄”]([a-z·ÈÌÛ˙ÒÁ]){1,}$");
		private static Matcher matcher;
		protected static int ultimoId;
		protected String nombre;
		protected String apellidos;
		protected float sueldo;
		protected String nacionalidad;
		protected Equipo equipo;
		protected ImageIcon foto;
		protected int id;
		
		/**
		 * Constructor por id
		 * @param id
		 */
		public IntegranteEquipo(int id){
			setId(id);
		}
		
		/**
		 * Constructor de Persona
		 * @param nombre
		 * @param apellidos
		 * @param nacimiento
		 * @param sueldo
		 * @param nacionalidad
		 * @param equipo
		 * @param id
		 */
		public IntegranteEquipo(String nombre, String apellidos, String nacionalidad, Equipo equipo, ImageIcon foto) {
			setNombre(nombre);
			setApellidos(apellidos);
			setNacionalidad(nacionalidad);
			setEquipo(equipo);
			setId(ultimoId++);
			setFoto(foto);
		}

		public String getNombre() {
			return nombre;
		}


		private void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getApellidos() {
			return apellidos;
		}


		private void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public float getSueldo() {
			return sueldo;
		}


		protected void setSueldo(float sueldo) {
			this.sueldo = sueldo;
		}


		public String getNacionalidad() {
			return nacionalidad;
		}


		private void setNacionalidad(String nacionalidad) {
			this.nacionalidad = nacionalidad;
		}


		public Equipo getEquipo() {
			return equipo;
		}


		private void setEquipo(Equipo equipo) {
			this.equipo = equipo;
		}


		private void setId(int id) {
			this.id = id;
		}
		
		public ImageIcon getFoto() {
			return foto;
		}

		public void setFoto(ImageIcon foto) {
			this.foto = foto;
		}
		
		public static boolean validarNombre(String nombre){
			matcher = PATRON_NOMBRE.matcher(nombre);
			return matcher.matches();
			
		}
		
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((apellidos == null) ? 0 : apellidos.hashCode());
			result = prime * result
					+ ((equipo == null) ? 0 : equipo.hashCode());
			result = prime * result + ((foto == null) ? 0 : foto.hashCode());
			result = prime * result + id;
			result = prime * result
					+ ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
			result = prime * result
					+ ((nombre == null) ? 0 : nombre.hashCode());
			result = prime * result + Float.floatToIntBits(sueldo);
			return result;
		}

		@Override
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
			if (equipo == null) {
				if (other.equipo != null)
					return false;
			} else if (!equipo.equals(other.equipo))
				return false;
			if (foto == null) {
				if (other.foto != null)
					return false;
			} else if (!foto.equals(other.foto))
				return false;
			if (id != other.id)
				return false;
			if (nacionalidad == null) {
				if (other.nacionalidad != null)
					return false;
			} else if (!nacionalidad.equals(other.nacionalidad))
				return false;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			if (Float.floatToIntBits(sueldo) != Float
					.floatToIntBits(other.sueldo))
				return false;
			return true;
		}

		
		
		@Override
		public String toString() {
			return "IntegranteEquipo [nombre=" + nombre + ", apellidos="
					+ apellidos + ", sueldo=" + sueldo + ", nacionalidad="
					+ nacionalidad + ", equipo=" + equipo + ", foto=" + foto
					+ ", id=" + id + "]";
		}

		public void entrenar() {
		}
		
		public void generarTecnica(){
		}
}
