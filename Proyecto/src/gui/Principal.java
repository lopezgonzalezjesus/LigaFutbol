package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import liga.Liga;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Principal {

	private JFrame frmLigaDeFtbol;
	protected NuevoEquipo nuevoEquipo;
	protected MostrarEquipos mostrarEquipos;
	protected NuevoIntegrante nuevoIntegrante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmLigaDeFtbol.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLigaDeFtbol = new JFrame();
		frmLigaDeFtbol.setResizable(false);
		frmLigaDeFtbol.setTitle("Liga de F\u00FAtbol");
		frmLigaDeFtbol.setBounds(100, 100, 445, 321);
		frmLigaDeFtbol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLigaDeFtbol.getContentPane().setLayout(null);
		
		JButton btEquipos = new JButton("Equipos");
		btEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gestion.liga.size()==0)
					JOptionPane.showMessageDialog(null, "No hay equipos que mostrar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				else{
				mostrarEquipos = new MostrarEquipos(Gestion.liga);
				mostrarEquipos.setVisible(true);
				}
			}
		});
		btEquipos.setBounds(30, 76, 134, 23);
		frmLigaDeFtbol.getContentPane().add(btEquipos);
		
		JButton btAnadir = new JButton("A\u00F1adir Equipo");
		btAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoEquipo = new NuevoEquipo(Gestion.liga);
				nuevoEquipo.setVisible(true);
			}
		});
		btAnadir.setBounds(30, 110, 134, 23);
		frmLigaDeFtbol.getContentPane().add(btAnadir);
		
		JButton btAnadirMiembro = new JButton("A\u00F1adir Integrante");
		btAnadirMiembro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gestion.liga.size()==0){
					JOptionPane.showMessageDialog(null, "Se debe tener al menos 1 equipo para añadir integrantes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
				nuevoIntegrante = new NuevoIntegrante();
				nuevoIntegrante.setVisible(true);
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmLigaDeFtbol.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevaLiga = new JMenuItem("Nueva Liga");
		mntmNuevaLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaLiga();
			}
		});
		mnArchivo.add(mntmNuevaLiga);
		
		JMenuItem mntmCargarLiga = new JMenuItem("Cargar Liga");
		mntmCargarLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mnArchivo.add(mntmCargarLiga);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);
		btAnadirMiembro.setBounds(30, 144, 134, 23);
		frmLigaDeFtbol.getContentPane().add(btAnadirMiembro);
		
		JLabel lbFondo = new JLabel("Fondo");
		lbFondo.setIcon(new ImageIcon("src\\imagenes\\fondo.jpg"));
		lbFondo.setBounds(0, 0, 445, 271);
		frmLigaDeFtbol.getContentPane().add(lbFondo);
		
		
		
	
		
		
	}
	
	/**
	 * 
	 */
	private void abrir() {
		if(Gestion.isModificado()){
			int opcion = JOptionPane.showOptionDialog(null, "¿Deseas guardar los cambios?", "Cargar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, null);
			switch(opcion){
			case JOptionPane.OK_OPTION:
				guardar();
				abrirFichero();
				break;
			case JOptionPane.NO_OPTION:
				abrirFichero();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else{
			abrirFichero();
		}
		
	}

	/**
	 * 
	 */
	private void abrirFichero() {
		JFileChooser abrir = new JFileChooser();
		if(abrir.showOpenDialog(abrir) == JFileChooser.APPROVE_OPTION){
			try {
				Gestion.liga = (Liga) Gestion.abrir(abrir.getSelectedFile());
				Gestion.setAbierto(true);
				Gestion.setFichero(abrir.getSelectedFile());
				Gestion.setModificado(false);
				frmLigaDeFtbol.setTitle(Gestion.getFichero().getName());
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido abrir el fichero", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Metodo para guardar
	 */
	private void guardar() {
		if(Gestion.isModificado() && Gestion.getFichero()!=null){
			try {
				Gestion.escribir(Gestion.getFichero());
				Gestion.setModificado(false);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(Gestion.isModificado() && Gestion.getFichero()==null){
			guardarComo();
		}
		else if(frmLigaDeFtbol.getTitle()=="Liga de Fútbol" && !Gestion.isModificado())
			guardarComo();
	}
	
	
	/**
	 * 
	 */
	private void guardarComo() {
		JFileChooser guardar = new JFileChooser();
		if(guardar.showSaveDialog(guardar) == JFileChooser.APPROVE_OPTION){
			Gestion.setFichero(guardar.getSelectedFile());
			try {
				if(!Gestion.existe(Gestion.getFichero())){
					Gestion.escribir(Gestion.getFichero());
					Gestion.setModificado(false);
					frmLigaDeFtbol.setTitle(Gestion.getFichero().getName());
				}
				else{
					switch(JOptionPane.showConfirmDialog(null, "El archivo existe. Desea sobreescribirlo?", "Aviso", JOptionPane.WARNING_MESSAGE)){
					case JOptionPane.OK_OPTION:
						Gestion.escribir(Gestion.getFichero());
						Gestion.setModificado(false);
								frmLigaDeFtbol.setTitle(Gestion.getFichero().getName());
						break;
					}
				}						
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * 
	 */
	private void nuevaLiga() {
		if(Gestion.isModificado()){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea guardar los cambios?", "Nueva Liga", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, null);
			switch(opcion){
			case JOptionPane.YES_OPTION:
				guardar();
				break;
			case JOptionPane.NO_OPTION:
				Gestion.reset();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else
			Gestion.reset();
		frmLigaDeFtbol.setTitle("Liga de Fútbol");
	}
	
	private void salir(){
		if(Gestion.isModificado()){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea guardar los cambios?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, null);
			switch(opcion){
			case JOptionPane.YES_OPTION:
				guardar();
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else
			System.exit(0);
	}
}
