package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import liga.Directivo;
import liga.Equipo;
import liga.Futbolista;
import liga.IntegranteEquipo;
import liga.Tecnico;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JLabel;

/**
 * Clase que nos muestra el resultado de la plantilla después de entrenar
 * @author Jesús López González
 *
 */
public class ResultadoEntrenamiento extends PadreIntegrante {

	private static final long serialVersionUID = 1L;
	private JButton btSiguiente;
	private JButton btAnterior;
	private Equipo equipo;
	private int i = 0;
	private JLabel lblTecnicaGanada = new JLabel();
	DecimalFormat formato = new DecimalFormat("0.00");
	
	/**
	 * Constructor de ResultadoEntrenamiento
	 */
	public ResultadoEntrenamiento(final Equipo equipo) {
		super();
		cbPaises.setEnabled(false);
		tfTecnica.setEditable(false);
		tfDorsal.setLocation(198, 214);
		lblDorsal.setLocation(24, 217);
		setModal(true);
		setResizable(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setText("Volver");
		btnAnadirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showOptionDialog(contentPanel, "¿Está seguro que desea eliminarlo?", "Eliminar Integrante", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, null);
				switch(opcion){
				case JOptionPane.YES_OPTION:
					if(equipo.eliminarIntegrante(equipo.get(i))){
						JOptionPane.showMessageDialog(contentPanel, "El integrante se ha eliminado con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						Gestion.setModificado(true);
						limpiarCampos();
						i=0;
						if(equipo.size()>=1){
							mostrarIntegrante(equipo.get(0));
							caparBoton();
						}
						else{
							btnAnadirFoto.setEnabled(false);
						}
					}
					break;
				}
			}
		});
		btnAnadirFoto.setForeground(Color.RED);
		btnAnadirFoto.setText("Eliminar");
		this.equipo = equipo;
		cbTipo.setEnabled(false);
		setTitle("Resultado Entrenamiento");
		cbRol.setEnabled(false);
		cbEquipos.setEnabled(false);
		lblImagen.setText("");
		tfDorsal.setEditable(false);
		tfSueldo.setEditable(false);
		cbPaises.setEditable(false);
		tfApellidos.setEditable(false);
		tfNombre.setEditable(false);
		lblTecnica.setVisible(false);
		mostrarIntegrante(equipo.get(0));
		btAnterior = new JButton("<");
		btAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(equipo.get(i-1)!=null)
					mostrarIntegrante(equipo.get(--i));
					caparBoton();
			}
		});
		btAnterior.setBounds(260, 177, 67, 35);
		contentPanel.add(btAnterior);
		
		btSiguiente = new JButton(">");
		btSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(equipo.get(i+1)!=null)
					mostrarIntegrante(equipo.get(++i));
					caparBoton();
			}
		});
		btSiguiente.setBounds(337, 177, 67, 35);
		contentPanel.add(btSiguiente);
		lblTecnicaGanada.setForeground(Color.GREEN);
		
		lblTecnicaGanada.setBounds(87, 192, 46, 14);
		contentPanel.add(lblTecnicaGanada);
		caparBoton();
		btnAnadirFoto.setVisible(false);
		okButton.setVisible(false);
	}
	
	/**
	 * Método que rellena los campos del formulario con las características del integrante a mostar
	 * @param integrante Integrante a mostar
	 */
	private void mostrarIntegrante(IntegranteEquipo integrante){
		tfNombre.setText(integrante.getNombre());
		tfApellidos.setText(integrante.getApellidos());
		cbEquipos.setSelectedItem((String)integrante.getEquipo().getNombre());
		cbEquipos.addItem(integrante.getEquipo().getNombre());
		cbEquipos.setSelectedItem(integrante.getEquipo().getNombre());
		if(integrante instanceof Futbolista){
			cbPaises.removeAllItems();
			cbPaises.addItem(integrante.getNacionalidad());
			cbPaises.setSelectedItem(integrante.getNacionalidad());
			cbRol.setSelectedIndex(0);
			cbTipo.addItem(((Futbolista) integrante).getCargo());
			cbTipo.setSelectedItem(((Futbolista) integrante).getCargo());
			lblDorsal.setVisible(true);
			tfDorsal.setText(String.valueOf(((Futbolista) integrante).getDorsal()));
			tfDorsal.setVisible(true);
			lblTecnica.setVisible(true);
			tfTecnica.setText(String.valueOf(((Futbolista) integrante).getTecnica()));
			tfTecnica.setVisible(true);
			lblTecnicaGanada.setText("+" + String.valueOf(formato.format(((Futbolista) integrante).getTecnicaGanada())));
			lblTecnicaGanada.setVisible(true);
		}
		else if(integrante instanceof Tecnico){
			cbPaises.removeAllItems();
			cbPaises.addItem(integrante.getNacionalidad());
			cbPaises.setSelectedItem(integrante.getNacionalidad());
			cbRol.setSelectedIndex(1);
			cbTipo.addItem(((Tecnico) integrante).getCargo());
			cbTipo.setSelectedItem(((Tecnico) integrante).getCargo());
			lblDorsal.setVisible(false);
			tfDorsal.setVisible(false);
			lblTecnica.setVisible(true);
			tfTecnica.setText(String.valueOf(((Tecnico) integrante).getTecnica()));
			tfTecnica.setVisible(true);
			lblTecnicaGanada.setText("+" + String.valueOf(formato.format(((Tecnico) integrante).getTecnicaGanada())));
			lblTecnicaGanada.setVisible(true);
		}
		else if(integrante instanceof Directivo){
			cbPaises.removeAllItems();
			cbPaises.addItem(integrante.getNacionalidad());
			cbPaises.setSelectedItem(integrante.getNacionalidad());
			cbRol.setSelectedIndex(2);
			cbTipo.addItem(((Directivo) integrante).getCargo());
			cbTipo.setSelectedItem(((Directivo) integrante).getCargo());
			lblDorsal.setVisible(false);
			tfDorsal.setVisible(false);
			tfTecnica.setVisible(false);
			lblTecnica.setVisible(false);
			lblTecnicaGanada.setVisible(false);
		}
		tfSueldo.setText(String.valueOf(integrante.getSueldo()));
		tfSueldo.setVisible(true);
		lblSueldo_1.setVisible(true);
		lblImagen.setIcon(integrante.getFoto());
	}
	
	/**
	 * Metodo que resetea los campos del formulario
	 */
	private void limpiarCampos() {
		tfNombre.setText("");
		tfApellidos.setText("");;
		cbEquipos.removeAll();
		cbPaises.removeAll();
		cbRol.removeAll();
		cbTipo.removeAll();
		tfSueldo.setText("");
		tfDorsal.setText("");
		lblImagen.setIcon(null);
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
		if(i+1>(equipo.size()-1))
			btSiguiente.setEnabled(false);
		else
			btSiguiente.setEnabled(true);
	}
}
