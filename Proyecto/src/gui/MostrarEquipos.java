package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import excepciones.EquipoNoExisteException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import liga.Equipo;
import liga.Liga;

import java.awt.Color;

/**
 * Clase que nos muestra los equipos de la Liga
 * @author Jesús López González
 *
 */
public class MostrarEquipos extends PadreEquipo {

	private static final long serialVersionUID = 1L;
	MostrarIntegrante mostrarIntegrantes;
	ResultadoEntrenamiento resultadoEntrenamiento;
	private JButton btAnterior;
	private JButton btSiguiente;
	private int i=0;


	/**
	 * Constructor de MostrarEquipos
	 */
	public MostrarEquipos() {
		super();
		btAnadirEscudo.setSize(139, 23);
		setModal(true);
		setResizable(false);
		cancelButton.setText("Volver");
		tfFundacion.setBounds(90, 92, 116, 20);
		tfEstadio.setBounds(90, 61, 116, 20);
		tfNombre.setBounds(90, 30, 116, 20);
		lblImagen.setBounds(250, 11, 162, 138);
		mostrarEquipo(Gestion.liga.get(0));
		btAnadirEscudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Gestion.liga.size()>0){
					if (Gestion.liga.get(i).size() == 0) {
						JOptionPane.showMessageDialog(contentPanel,
								"La plantilla no tiene integrantes aún",
								"Aviso", JOptionPane.INFORMATION_MESSAGE);
					} else {
						mostrarIntegrantes = new MostrarIntegrante(Gestion.liga.get(i));
						mostrarIntegrantes.setVisible(true);
					}
				}
				else
					JOptionPane.showMessageDialog(contentPanel, "No hay equipos en la liga", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelDatos.setBounds(21, 11, 230, 124);
		btAnadirEscudo.setLocation(21, 170);
		tfFundacion.setEditable(false);
		tfEstadio.setEditable(false);
		tfNombre.setEditable(false);
		setTitle("Equipos");
		btAnadirEscudo.setText("Ver plantilla");
		
		btAnterior = new JButton("<");
		btAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gestion.liga.get(i-1)!=null)
					mostrarEquipo(Gestion.liga.get(--i));
					caparBoton();
			}
		});
		btAnterior.setBounds(237, 180, 89, 37);
		contentPanel.add(btAnterior);
		
		btSiguiente = new JButton(">");
		btSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gestion.liga.get(i+1)!=null)
					mostrarEquipo(Gestion.liga.get(++i));
					caparBoton();
			}
		});
		btSiguiente.setBounds(335, 180, 89, 37);
		contentPanel.add(btSiguiente);
		
		JButton btnEliminarEquipo = new JButton("Eliminar Equipo");
		btnEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gestion.liga.size()==0){
					JOptionPane.showMessageDialog(contentPanel, "No hay equipos en la liga", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				else
					eliminarEquipo();
					Gestion.setModificado(true);
			}
		});
		btnEliminarEquipo.setForeground(Color.RED);
		btnEliminarEquipo.setBounds(21, 194, 139, 23);
		contentPanel.add(btnEliminarEquipo);
		
		JButton btEntrenar = new JButton("Entrenar");
		btEntrenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gestion.liga.size()==0 || Gestion.liga.get(i).size()==0){
					JOptionPane.showMessageDialog(contentPanel, "No hay jugadores en la plantilla", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					Gestion.liga.get(i).entrenar();
					resultadoEntrenamiento = new ResultadoEntrenamiento(Gestion.liga.get(i));
					resultadoEntrenamiento.setVisible(true);
				}
			}
		});
		btEntrenar.setBounds(21, 146, 139, 23);
		contentPanel.add(btEntrenar);
		okButton.setVisible(false);
		caparBoton();
	}
	
	/**
	 * Método que rellena los campos de texto de nuestra interfaz con los datos de un equipo
	 * @param equipo Equipo a mostrar
	 */
	private void mostrarEquipo(Equipo equipo){
		tfNombre.setText(equipo.getNombre());
		tfEstadio.setText(equipo.getEstadio());
		tfFundacion.setText(String.valueOf(new SimpleDateFormat("dd-MM-yyyy").format(equipo.getFundacion().getTime())));
		lblImagen.setIcon(equipo.getEscudo());
	}
	
	/**
	 * Método que habilita o deshabilita los botones de < y > según el número de equipos
	 * de la liga y la posición en la que nos encontremos
	 */
	private void caparBoton(){
		if(i-1<0)
			btAnterior.setEnabled(false);
		else
			btAnterior.setEnabled(true);
		if(i+1>(Gestion.liga.size()-1))
			btSiguiente.setEnabled(false);
		else
			btSiguiente.setEnabled(true);
	}
	
	/**
	 * Método que elimina un equipo de la liga preguntando previamente al usuario
	 */
	private void eliminarEquipo(){
		try {
			int opcion = JOptionPane.showOptionDialog(contentPanel, "¿Está seguro que desea eliminarlo? Se perderán sus componentes", "Eliminar Coche", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, null);
			switch(opcion){
			case JOptionPane.YES_OPTION:
				if(Gestion.liga.eliminarEquipo(tfNombre.getText())){
					JOptionPane.showMessageDialog(contentPanel, "El Equipo se ha eliminado con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					limpiarCampos();
					i=0;
					if(Gestion.liga.size()>=1){
						mostrarEquipo(Gestion.liga.get(0));
						caparBoton();
					}
				}
				break;
			}
			
		} catch (EquipoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que resetea los campos del formulario
	 */
	private void limpiarCampos() {
		tfNombre.setText("");
		tfEstadio.setText("");;
		tfFundacion.setText("");
		lblImagen.setIcon(null);
	}
}
