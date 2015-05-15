package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import liga.Equipo;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class PadreIntegrante extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the dialog.
	 */
	protected final JPanel contentPanel = new JPanel();
	protected JTextField tfNombre;
	protected JTextField tfApellidos;
	protected JTextField tfSueldo;
	protected JLabel lblImagen;
	protected JLabel lblSueldo_1;
	protected JComboBox cbEquipos;
	protected JButton btnAnadirFoto;
	protected JPanel buttonPane;
	protected JButton okButton;
	protected JButton cancelButton;
	protected JLabel lblTipo;
	protected JComboBox cbTipo;
	protected JComboBox cbRol;
	protected JLabel lblRol;
	protected JLabel lblNombre;
	protected JLabel lblApellidos;
	protected JLabel lblEquipo;
	protected JLabel lblNacionalidad;
	protected JLabel lblDorsal;
	protected JTextField tfDorsal;
	protected JTextField tfTecnica;
	protected JLabel lblTecnica;
	protected JComboBox cbPaises;

	/**
	 * Create the dialog.
	 */
	public PadreIntegrante() {
		setBounds(100, 100, 450, 328);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 14, 88, 14);
		contentPanel.add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(24, 39, 88, 14);
		contentPanel.add(lblApellidos);
		
		lblEquipo = new JLabel("Equipo");
		lblEquipo.setBounds(24, 64, 88, 14);
		contentPanel.add(lblEquipo);
		
		lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(24, 89, 88, 14);
		contentPanel.add(lblNacionalidad);
		
		lblSueldo_1 = new JLabel("Sueldo");
		lblSueldo_1.setBounds(24, 167, 88, 14);
		contentPanel.add(lblSueldo_1);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(139, 11, 105, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellidos = new JTextField();
		tfApellidos.setBounds(139, 36, 105, 20);
		contentPanel.add(tfApellidos);
		tfApellidos.setColumns(10);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(139, 164, 105, 20);
		contentPanel.add(tfSueldo);
		tfSueldo.setColumns(10);
		
		lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(265, 11, 135, 130);
		contentPanel.add(lblImagen);
		
		cbEquipos = new JComboBox<Equipo>();
		cbEquipos.setBounds(139, 61, 105, 20);
		contentPanel.add(cbEquipos);
		
		btnAnadirFoto = new JButton("A\u00F1adir Foto");
		btnAnadirFoto.setBounds(265, 152, 135, 23);
		contentPanel.add(btnAnadirFoto);
		
		lblRol = new JLabel("Rol");
		lblRol.setBounds(24, 117, 46, 14);
		contentPanel.add(lblRol);
		
		cbRol = new JComboBox();
		cbRol.setModel(new DefaultComboBoxModel(new String[] {"Jugador", "T\u00E9cnico", "Directivo"}));
		cbRol.setSelectedIndex(0);
		cbRol.setBounds(139, 114, 105, 20);
		contentPanel.add(cbRol);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(24, 142, 46, 14);
		contentPanel.add(lblTipo);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(139, 139, 105, 20);
		contentPanel.add(cbTipo);
		
		lblDorsal = new JLabel("Dorsal");
		lblDorsal.setBounds(24, 215, 46, 14);
		contentPanel.add(lblDorsal);
		
		tfDorsal = new JTextField();
		tfDorsal.setBounds(198, 212, 46, 20);
		contentPanel.add(tfDorsal);
		tfDorsal.setColumns(10);
		
		tfTecnica = new JTextField();
		tfTecnica.setBounds(158, 189, 86, 20);
		contentPanel.add(tfTecnica);
		tfTecnica.setColumns(10);
		
		lblTecnica = new JLabel("T\u00E9cnica");
		lblTecnica.setBounds(24, 192, 46, 14);
		contentPanel.add(lblTecnica);
		
		cbPaises = new JComboBox();
		cbPaises.setBounds(139, 86, 105, 20);
		contentPanel.add(cbPaises);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
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
}