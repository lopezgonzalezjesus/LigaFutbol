package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import liga.Cargo;
import liga.Directivo;
import liga.Futbolista;
import liga.IntegranteEquipo;
import liga.Tecnico;

/**
 * Clase que nos permite buscar un integrante de un equipo según su Tipo (Jugador, Técnico o Directivo), y su puesto
 * (Defensa, Portero, Entrenador, Presidente, etc).
 * @author Jesús López González
 *
 */
public class BuscarIntegranteTipo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MostrarIntegrantePorTipo mostrarPorTipo;


	/**
	 * Constructor de clase
	 */
	public BuscarIntegranteTipo() {
		setResizable(false);
		setModal(true);
		setTitle("Buscar Integrante por Tipo");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(81, 45, 46, 14);
		contentPanel.add(lblTipo);
		
		final JLabel lblPuesto = new JLabel("New label");
		lblPuesto.setBounds(81, 72, 76, 14);
		contentPanel.add(lblPuesto);
		lblPuesto.setVisible(false);
		
		final JComboBox cbPuesto = new JComboBox();
		cbPuesto.setBounds(182, 69, 107, 20);
		contentPanel.add(cbPuesto);
		cbPuesto.setVisible(false);
		
		final JComboBox cbTipo = new JComboBox();
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(cbTipo.getSelectedIndex()){
				case 0:
					cbPuesto.removeAllItems();
					lblPuesto.setText("Demarcación");
					lblPuesto.setVisible(true);
					cbPuesto.addItem(Cargo.PORTERO);
					cbPuesto.addItem(Cargo.DEFENSA);
					cbPuesto.addItem(Cargo.CENTROCAMPISTA);
					cbPuesto.addItem(Cargo.DELANTERO);
					cbPuesto.setVisible(true);
					break;
				case 1:
					cbPuesto.removeAllItems();
					lblPuesto.setText("Titulación");
					lblPuesto.setVisible(true);
					cbPuesto.addItem(Cargo.ENTRENADOR);
					cbPuesto.addItem(Cargo.MASAJISTA);
					cbPuesto.setVisible(true);
					break;
				case 2:
					cbPuesto.removeAllItems();
					lblPuesto.setText("Cargo");
					lblPuesto.setVisible(true);
					cbPuesto.addItem(Cargo.DIRECTIVO);
					cbPuesto.addItem(Cargo.PRESIDENTE);
					break;
				}
			}
		});
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Jugador", "T\u00E9cnico", "Directivo"}));
		cbTipo.setSelectedIndex(0);
		cbTipo.setBounds(182, 41, 107, 20);
		contentPanel.add(cbTipo);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Buscar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ArrayList<IntegranteEquipo> integrantes = generarArrayListTipo(cbPuesto.getSelectedItem());
						if(integrantes.size()==0)
							JOptionPane.showMessageDialog(null, "No hay integrantes de ese tipo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						else{
							mostrarPorTipo = new MostrarIntegrantePorTipo(integrantes);
							mostrarPorTipo.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Método que genera un ArrayList de todos los integrantes de un mismo Tipo.
	 * @param puesto Tipo o puesto por el que se buscarán a los integrantes de un equipo.
	 * @return Devuelve un ArrayList con los integrantes que pasan el filtro.
	 */
	private ArrayList<IntegranteEquipo> generarArrayListTipo(Object puesto) {
		ArrayList<IntegranteEquipo> arrayList = new ArrayList<IntegranteEquipo>();
		
		for (int i = 0; i < Gestion.liga.size(); i++) {
			for (int j = 0; j < Gestion.liga.get(i).size(); j++) {
				if (Gestion.liga.get(i).get(j).getCargo()==puesto)
					arrayList.add(Gestion.liga.get(i).get(j));
			}
		}
		return arrayList;

	}
}
