import java.awt.Color;
import java.awt.Dimension;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;


public class AddTaskWindow{
	
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
	
	public void show(){
		frmNuevaTarea = new JFrame();
		frmNuevaTarea.setTitle("Nueva Tarea");
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
		
		LB_NuevaTarea = new JLabel("Nueva Tarea");
		LB_NuevaTarea.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmNuevaTarea.getContentPane().add(LB_NuevaTarea, "1, 2, 10, 1, center, default");
		
		JLabel LB_Descripcion = new JLabel("* Descripcion:");
		frmNuevaTarea.getContentPane().add(LB_Descripcion, "2, 6, left, default");
		
		TF_Descripcion = new JTextField();
		frmNuevaTarea.getContentPane().add(TF_Descripcion, "4, 6, 3, 1, fill, default");
		TF_Descripcion.setColumns(10);
		
		JLabel LB_Contexto = new JLabel("* Contexto:");
		frmNuevaTarea.getContentPane().add(LB_Contexto, "2, 8, left, default");
		
		CB_Contexto = new JComboBox();
		CB_Contexto.setModel(new DefaultComboBoxModel(new String[] {"En la oficina", "En el auto", "Al telefono"}));
		frmNuevaTarea.getContentPane().add(CB_Contexto, "4, 8, 3, 1, fill, default");
		
		JLabel LB_Estado = new JLabel("Estado:");
		frmNuevaTarea.getContentPane().add(LB_Estado, "2, 10, left, default");
		
		CB_Estado = new JComboBox();
		CB_Estado.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "FROZEN", "CLOSED"}));
		frmNuevaTarea.getContentPane().add(CB_Estado, "4, 10, 3, 1, fill, default");
		
		JLabel LB_Deadline = new JLabel("Deadline:");
		frmNuevaTarea.getContentPane().add(LB_Deadline, "2, 12, left, default");
		
		Date tomorrow = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(tomorrow); 
		c.add(Calendar.DATE, 1);
		tomorrow = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
		   
		TF_Deadline = new JTextField();
		TF_Deadline.setText(dateFormat.format(tomorrow));
		frmNuevaTarea.getContentPane().add(TF_Deadline, "4, 12, 3, 1, fill, default");
		TF_Deadline.setColumns(10);
		
		ArrayList<String> emails = new ArrayList<String>();
		for(ArrayList<ArrayList<String>> user : Main.searcher.getAllUsers()){
			emails.add(user.get(0).get(0));
		}
		
		JLabel LB_Responsable = new JLabel("Responsable:");
		frmNuevaTarea.getContentPane().add(LB_Responsable, "2, 14, left, default");
		
		CB_Responsable = new JComboBox(emails.toArray());
		frmNuevaTarea.getContentPane().add(CB_Responsable, "4, 14, 3, 1, fill, default");
		
		LB_Ejecutores = new JLabel("Ejecutor(es):");
		frmNuevaTarea.getContentPane().add(LB_Ejecutores, "2, 16, right, default");
		
		
		CB_Ejecutores = new JComboBox(emails.toArray());
		frmNuevaTarea.getContentPane().add(CB_Ejecutores, "4, 16, 3, 1, fill, default");
		
		BT_Agregar = new JButton("Agregar");
		BT_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedExec.contains(CB_Ejecutores.getSelectedItem().toString()) == false){
					selectedExec.add(CB_Ejecutores.getSelectedItem().toString());
					LS_Ejecutores.setListData(selectedExec.toArray());
				}
			}
		});
		frmNuevaTarea.getContentPane().add(BT_Agregar, "8, 16");
		
		LS_Ejecutores = new JList();
		frmNuevaTarea.getContentPane().add(LS_Ejecutores, "4, 18, 3, 1, fill, fill");
		
		BT_Eliminar = new JButton("Eliminar");
		BT_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedExec.remove(LS_Ejecutores.getSelectedValue().toString());
				LS_Ejecutores.setListData(selectedExec.toArray());
			}
		});
		frmNuevaTarea.getContentPane().add(BT_Eliminar, "8, 18");
		
		BT_Crear = new JButton("Crear");
		BT_Crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Task task = new Task(TF_Descripcion.getText(), CB_Contexto.getSelectedItem().toString());
				
				Random ra = new Random();
				int n = 10000000 + ra.nextInt(90000000);
				task.setTID(n);
				
				if(CB_Estado.getSelectedItem().toString().equals("") == false){
					if(CB_Estado.getSelectedItem().toString().trim().equalsIgnoreCase("active"))
						task.setState(State.ACTIVE);
					if(CB_Estado.getSelectedItem().toString().trim().equalsIgnoreCase("frozen"))
						task.setState(State.FROZEN);
					if(CB_Estado.getSelectedItem().toString().trim().equalsIgnoreCase("closed"))
						task.setState(State.CLOSED);
				}
				
				if(TF_Deadline.getText().toString().contains("Ej:") == false){
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					Date date = new Date();
					try{
						date = df.parse(TF_Deadline.getText().toString());
					}catch(ParseException e){}
					task.setDeadline(date);
				}
				
				if(CB_Responsable.getSelectedItem().toString().trim().equals("") == false)
					task.setResponsible(new User(CB_Responsable.getSelectedItem().toString(), ""));
				
				if(LS_Ejecutores.getModel().getSize()>0){
					for(int i=0; i<LS_Ejecutores.getModel().getSize(); i++){
						task.AddExecutor(new User(LS_Ejecutores.getModel().getElementAt(i).toString(), ""));
					}
				}
				
				Main.ui.addNewTaskToGUI(task);
				frmNuevaTarea.setVisible(false);
				frmNuevaTarea.dispose();
				
			}
		});
		frmNuevaTarea.getContentPane().add(BT_Crear, "4, 20, 3, 1");
		
		frmNuevaTarea.setVisible(true);
	}

}

