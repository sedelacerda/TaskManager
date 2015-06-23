import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class AddProjectWindow{
	
	private JFrame frmNuevaTarea;
	private JTextField TF_Descripcion;
	private JTextField TF_Deadline;
	private JComboBox CB_Estado;
	private JComboBox CB_Responsable;
	private JComboBox CB_Contexto;
	private JLabel LB_Ejecutores;
	private JButton BT_Crear;
	private JLabel LB_NuevaTarea;
	private JComboBox CB_Ejecutores;
	private JButton BT_Agregar;
	private JList LS_Ejecutores;
	private JButton BT_Eliminar;
	private ArrayList<String> selectedExec = new ArrayList<String>();
	private Context context = new Context();
	State st;
	
	public void show(){
		frmNuevaTarea = new JFrame();
		frmNuevaTarea.setTitle("Nuevo Projecto");
		frmNuevaTarea.setResizable(false);
		frmNuevaTarea.setBounds(100, 100, 580, 400);
		frmNuevaTarea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNuevaTarea.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(187dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(1dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(3dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		LB_NuevaTarea = new JLabel("Nuevo Projecto");
		LB_NuevaTarea.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmNuevaTarea.getContentPane().add(LB_NuevaTarea, "1, 2, 10, 1, center, default");
		
		JLabel LB_Descripcion = new JLabel("* Descripcion:");
		frmNuevaTarea.getContentPane().add(LB_Descripcion, "2, 6, left, default");
		
		TF_Descripcion = new JTextField();
		frmNuevaTarea.getContentPane().add(TF_Descripcion, "4, 6, 3, 1, fill, default");
		TF_Descripcion.setColumns(10);
		
		JLabel LB_Estado = new JLabel("Estado:");
		frmNuevaTarea.getContentPane().add(LB_Estado, "2, 10, left, default");
		
		CB_Estado = new JComboBox();
		CB_Estado.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "FROZEN", "CLOSED"}));
		frmNuevaTarea.getContentPane().add(CB_Estado, "4, 10, 3, 1, fill, default");
				
		BT_Crear = new JButton("Crear");
		BT_Crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Random ra = new Random();
				int n = 0000 + ra.nextInt(9999);
				if(CB_Estado.getSelectedItem().toString().equals("") == false){
					if(CB_Estado.getSelectedItem().toString().trim().equalsIgnoreCase("active"))
						st = State.ACTIVE;
					if(CB_Estado.getSelectedItem().toString().trim().equalsIgnoreCase("frozen"))
						st = State.FROZEN;
					if(CB_Estado.getSelectedItem().toString().trim().equalsIgnoreCase("closed"))
						st = State.CLOSED;
					String mensaje = "El usuario "+Main.user.getEmail()+" lo ha incluido en el proyecto "+TF_Descripcion.getSelectedText();
					ArrayList<String> usuarios = new ArrayList<>();
					usuarios.add(Main.user.getEmail());
					Main.searcher.addNewNotification(mensaje,selectedExec);
				}
				Project project = new Project(n, TF_Descripcion.getText(),st);
				
				Main.ui.addNewProjectToGUI(project);
				frmNuevaTarea.setVisible(false);
				frmNuevaTarea.dispose();
				
			}
		});
		frmNuevaTarea.getContentPane().add(BT_Crear, "4, 20, 3, 1");
		
		frmNuevaTarea.setVisible(true);
	
	}

}
