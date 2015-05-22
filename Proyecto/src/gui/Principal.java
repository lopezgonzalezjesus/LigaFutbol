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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JSeparator;

import java.io.*;

/**
 * Interfaz principal del programa, de la que partir&aacute;n las demas
 * @author Jes&uacute;s L&oacute;pez Gonz&aacute;lez
 * @version 1.0
 *
 */
public class Principal {

	private JFrame frmLigaDeFtbol;
	protected NuevoEquipo nuevoEquipo;
	protected MostrarEquipos mostrarEquipos;
	protected NuevoIntegrante nuevoIntegrante;
	protected BuscarIntegranteTipo buscarIntegranteTipo;
	protected MostrarIntegrantePorNombre mostrarPorNombre;
	protected BuscarEquipoNombre buscarEquipoNombre;
	protected AcercaDe acercaDe;
	protected Ayuda ayuda;

	/**
	 * Main de la aplicaci&oacute;n.
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
	 * Constructor de clase Principal
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Inicializando los contenidos del frame Principal
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
				mostrarEquipos = new MostrarEquipos();
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
		mnArchivo.setMnemonic('a');
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevaLiga = new JMenuItem("Nueva Liga");
		mntmNuevaLiga.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNuevaLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaLiga();
			}
		});
		mnArchivo.add(mntmNuevaLiga);
		
		JMenuItem mntmCargarLiga = new JMenuItem("Cargar Liga");
		mntmCargarLiga.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmCargarLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mnArchivo.add(mntmCargarLiga);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como...");
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		mnArchivo.add(mntmSalir);
		
		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('b');
		menuBar.add(mnBuscar);
		
		JMenu mnEquipo = new JMenu("Equipo");
		mnBuscar.add(mnEquipo);
		
		JMenuItem mntmPorNombre = new JMenuItem("Por Nombre...");
		mntmPorNombre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mntmPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarEquipoNombre = new BuscarEquipoNombre();
				buscarEquipoNombre.setVisible(true);
			}
		});
		mnEquipo.add(mntmPorNombre);
		
		JMenu mnIntegrante = new JMenu("Integrante");
		mnBuscar.add(mnIntegrante);
		
		JMenuItem mntmPorNombre_1 = new JMenuItem("Por Nombre...");
		mntmPorNombre_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnIntegrante.add(mntmPorNombre_1);
		mntmPorNombre_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPorNombre = new MostrarIntegrantePorNombre();
				mostrarPorNombre.setVisible(true);
			}
		});
		
		JMenuItem mntmPorTipo = new JMenuItem("Por Tipo...");
		mntmPorTipo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		mntmPorTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarIntegranteTipo = new BuscarIntegranteTipo();
				buscarIntegranteTipo.setVisible(true);
			}
		});
		mnIntegrante.add(mntmPorTipo);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('y');
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda = new Ayuda();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);
		btAnadirMiembro.setBounds(30, 144, 134, 23);
		frmLigaDeFtbol.getContentPane().add(btAnadirMiembro);
		
		JLabel lbFondo = new JLabel("Fondo");
		lbFondo.setIcon(new ImageIcon("src\\imagenes\\fondo.jpg"));
		lbFondo.setBounds(0, 0, 445, 271);
		frmLigaDeFtbol.getContentPane().add(lbFondo);
		
		
		//Configuramos el cierre de la ventana con X
		frmLigaDeFtbol.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (Gestion.isModificado()){
                    int confirm = JOptionPane.showOptionDialog(null, "¿Desea Guardar el trabajo actual antes de salir?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    switch(confirm){
                    case JOptionPane.OK_OPTION:
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
        };
        frmLigaDeFtbol.addWindowListener(exitListener);
		
		
	}
	
	/**
	 * M&eacute;todo que nos permite cargar un fichero a nuestra aplicación
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
	 * M&eacute;todo que complementa a abrir() que se encarga de abrir un fichero.
	 */
	private void abrirFichero() {
		JFileChooser abrir = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("obj","obj");
		abrir.setFileFilter(filtro);
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
	 * M&eacute;todo para guardar nuestro progreso
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
	 * M&eacute;todo que nos permite Guardar como... nuestro progreso
	 */
	private void guardarComo() {
		JFileChooser guardar = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("obj","obj");
		guardar.setFileFilter(filtro);
		if(guardar.showSaveDialog(guardar) == JFileChooser.APPROVE_OPTION){
			Gestion.setFichero(Gestion.extensionValida(guardar.getSelectedFile()));
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
	 * M&eacute;todo que nos permite iniciar una nueva liga desde cero.
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
	
	/**
	 * M&eacute;todo que nos permite salir de la apliaci&oacute;n
	 */
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
