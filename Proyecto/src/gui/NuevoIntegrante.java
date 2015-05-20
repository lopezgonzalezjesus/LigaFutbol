package gui;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import liga.Cargo;
import liga.Directivo;
import liga.Equipo;
import liga.Futbolista;
import liga.Tecnico;

import javax.swing.DefaultComboBoxModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import excepciones.DorsalExisteException;
import excepciones.DorsalNullException;
import excepciones.EntrenadorYaExistenteException;
import excepciones.EquipoNullException;
import excepciones.FotoNullException;
import excepciones.IntegranteYaExistenteException;
import excepciones.NacionalidadNullException;
import excepciones.NombreInvalidoException;
import excepciones.NumeroMaximoJugadoresException;
import excepciones.PresidenteYaDefinidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Clase que nos permite añadir un nuevo integrante a un equipo
 * @author Jesús López González
 *
 */
public class NuevoIntegrante extends PadreIntegrante {

	private static final long serialVersionUID = 1L;
	private Equipo equipo;
	File imagen;

	/**
	 * Constructor de NuevoIntegrante
	 */
	public NuevoIntegrante() {
		super();
		lblSueldo_1.setLocation(24, 167);
		setModal(true);
		setResizable(false);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equipo=seleccionarEquipo(cbEquipos.getSelectedItem().toString());
				if(cbRol.getSelectedItem()=="Jugador"){
						try {
							equipo.anadirFutbolista(tfNombre.getText(), tfApellidos.getText(), (String)cbPaises.getSelectedItem(), equipo, (ImageIcon)(lblImagen.getIcon()), tfDorsal.getText(), (Cargo)cbTipo.getSelectedItem());
							JOptionPane.showMessageDialog(contentPanel, "Futbolista añadido con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							Gestion.setModificado(true);
							limpiarCampos();
						} catch (NombreInvalidoException | EquipoNullException | NumberFormatException | NacionalidadNullException | FotoNullException | DorsalNullException | NumeroMaximoJugadoresException | DorsalExisteException | IntegranteYaExistenteException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(cbRol.getSelectedItem()=="Técnico"){
						try {
							equipo.anadirTecnico(tfNombre.getText(), tfApellidos.getText(), (String)cbPaises.getSelectedItem(), equipo, (ImageIcon)(lblImagen.getIcon()), (Cargo)cbTipo.getSelectedItem());
							JOptionPane.showMessageDialog(contentPanel, "Técnico añadido con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							Gestion.setModificado(true);
							limpiarCampos();
						} catch (NombreInvalidoException | EquipoNullException | NacionalidadNullException | FotoNullException | EntrenadorYaExistenteException | IntegranteYaExistenteException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(cbRol.getSelectedItem()=="Directivo"){
							try {
								equipo.anadirDirectivo(tfNombre.getText(), tfApellidos.getText(), (String)cbPaises.getSelectedItem(), equipo, (ImageIcon)(lblImagen.getIcon()), (Cargo)cbTipo.getSelectedItem());
								JOptionPane.showMessageDialog(contentPanel, "Directivo añadido con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
								Gestion.setModificado(true);
								limpiarCampos();
							} catch (NombreInvalidoException | EquipoNullException
									| FotoNullException | NacionalidadNullException | PresidenteYaDefinidoException | IntegranteYaExistenteException e1) {
								JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
					}
			}
		});
		lblTipo.setBounds(24, 142, 86, 14);
		cbRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extraerTipo();
			}
		});
		setTitle("A\u00F1adir Integrante");
		btnAnadirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser elegirImagen = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
				elegirImagen.setFileFilter(filtro);
				if(cbRol.getSelectedItem()=="Jugador"){
					elegirImagen.setCurrentDirectory(new File("src\\imagenes\\jugadores\\"));
				}
				if(cbRol.getSelectedItem()=="Técnico"){
					elegirImagen.setCurrentDirectory(new File("src\\imagenes\\entrenadores\\"));
				}
				if(cbRol.getSelectedItem()=="Directivo"){
					elegirImagen.setCurrentDirectory(new File("src\\imagenes\\presidentes\\"));
				}
				if(JFileChooser.APPROVE_OPTION == elegirImagen.showOpenDialog(elegirImagen))
					lblImagen.setIcon((Gestion.abrirImagen(elegirImagen.getSelectedFile())));
			}
		});
		okButton.setText("A\u00F1adir");
		cancelButton.setText("Volver");
		cbEquipos.setModel(new DefaultComboBoxModel(Gestion.liga.extraerEquipos()));
		cbEquipos.setSelectedIndex(0);
		cbTipo.setVisible(false);
		lblTipo.setVisible(false);
		tfSueldo.setVisible(false);
		lblSueldo_1.setVisible(false);
		lblDorsal.setVisible(false);
		tfDorsal.setVisible(false);
		lblImagen.setText("");
		lblTecnica.setVisible(false);
		tfTecnica.setVisible(false);
		cbPaises.setModel(new DefaultComboBoxModel(extraerPaises()));
		extraerTipo();
	}
	
	/**
	 * Método que nos permite mostrar un JComboBox con los diferentes puestos de un integrante dependiendo
	 * del tipo de integrante
	 */
	private void extraerTipo(){
		if(cbRol.getSelectedIndex()==0){
			cbTipo.removeAllItems();
			for (Cargo cargo : Cargo.values()) {
				if(cargo.getClase()==Futbolista.class)
					cbTipo.addItem(cargo);
			}
			lblTipo.setText("Posición");
			lblTipo.setVisible(true);
			cbTipo.setVisible(true);
			lblDorsal.setVisible(true);
			tfDorsal.setVisible(true);
		}
		else if(cbRol.getSelectedIndex()==1){
			cbTipo.removeAllItems();
			for (Cargo cargo : Cargo.values()) {
				if(cargo.getClase()==Tecnico.class)
					cbTipo.addItem(cargo);
			}
			lblTipo.setText("Titulación");
			lblTipo.setVisible(true);
			cbTipo.setVisible(true);
			lblDorsal.setVisible(false);
			tfDorsal.setVisible(false);
		}
		else if(cbRol.getSelectedIndex()==2){
			cbTipo.removeAllItems();
				for (Cargo cargo : Cargo.values()) {
					if(cargo.getClase()==Directivo.class)
						cbTipo.addItem(cargo);
			}
			lblTipo.setText("Cargo");
			lblTipo.setVisible(true);
			cbTipo.setVisible(true);
			lblDorsal.setVisible(false);
			tfDorsal.setVisible(false);
		}
			
	}
	
	/**
	 * Método que añade a un jugador a un equipo seleccionado
	 * @param nombre Nombre el equipo
	 * @return Devuelve el equipo seleccionado o null si no existe
	 */
	private Equipo seleccionarEquipo(String nombre){
		for (int i = 0; i < Gestion.liga.size(); i++) {
			if(nombre==Gestion.liga.get(i).getNombre())
				return Gestion.liga.get(i);
		}
		return null;
	}

	/**
	 * Metodo que resetea los campos del formulario
	 */
	private void limpiarCampos() {
		tfNombre.setText("");
		tfApellidos.setText("");;
		cbEquipos.removeAll();
		cbRol.removeAll();
		cbTipo.removeAll();
		tfSueldo.setText("");
		tfDorsal.setText("");
		lblImagen.setIcon(null);
	}
	
	private String[] extraerPaises() {
		Locale[] paises = Locale.getAvailableLocales();
		ArrayList<String> paisesTexto = new ArrayList<String>();
		int j =0;
		for (int i = 0; i < paises.length; i++) {
			if(!(paises[i].getDisplayCountry().equals(""))){
				paisesTexto.add(paises[i].getDisplayCountry());
				j++;
			}			
		}
		
		String[] array = new String[paisesTexto.size()];
		for (int i = 0; i < paisesTexto.size(); i++) {
			array[i]=paisesTexto.get(i);
		}
		Arrays.sort(array);
		return array;
	}
	
}
