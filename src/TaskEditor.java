import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JSeparator;
import javax.swing.text.DefaultCaret;


public class TaskEditor {
	
	private JFrame frmEdicionDeTarea;
	private JTextField TF_Descripcion;
	private JTextField TF_Deadline;
	private JTextField TF_DescripcionDown;
	private JTextField textField_2;
	private JTextField textField_3;
	private ArrayList<String> selectedExec = new ArrayList<String>();
	private JList LS_Ejecutores;
	private Context context = new Context();
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void show() {
		frmEdicionDeTarea = new JFrame();
		frmEdicionDeTarea.setTitle("Edicion de tarea");
		frmEdicionDeTarea.setBounds(100, 100, 450, 460);
		frmEdicionDeTarea.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		
		//#################### TAB 1 ###############################
		frmEdicionDeTarea.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JList LS_Ejecutores = new JList();
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Agregar Tarea", null, panel, null);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel LB_Descripcion = new JLabel("Descripcion: ");
		panel.add(LB_Descripcion, "2, 2, left, default");
		
		TF_Descripcion = new JTextField();
		panel.add(TF_Descripcion, "4, 2, fill, default");
		TF_Descripcion.setColumns(10);
		
		JLabel LB_Contexto = new JLabel("Contexto: ");
		panel.add(LB_Contexto, "2, 4, left, default");
		
		JComboBox CB_Contexto = new JComboBox();
		CB_Contexto.setModel(new DefaultComboBoxModel(context.getContextList()));
		final String newContextAdd = "Agregar nuevo Contexto";
		CB_Contexto.addItem( newContextAdd );
		CB_Contexto.addItemListener( new ItemListener() {
          @Override
          public void itemStateChanged( ItemEvent e ) {
            if ( e.getStateChange() == ItemEvent.SELECTED && newContextAdd.equals( e.getItem() ) ){
            	JFrame frame = new JFrame("Nuevo Contexto");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try 
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e1) {
                   e1.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JTextArea textArea = new JTextArea(15, 50);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));                
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JTextField input = new JTextField(20);
                JButton buttonEnter = new JButton("Enter");
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                inputpanel.add(input);
                inputpanel.add(buttonEnter);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                input.requestFocus();
                buttonEnter.addActionListener(new ActionListener() {                	 
                    public void actionPerformed(ActionEvent e)
                    {               
                    	context.addNewContext(input.getText());
                    	frame.dispose();
                    	frmEdicionDeTarea.dispose();
                    	show();
                    }
                });
            }
          }
        } );
		panel.add(CB_Contexto, "4, 4, fill, default");
		
		JLabel LB_Estado = new JLabel("Estado: ");
		panel.add(LB_Estado, "2, 6, left, default");
		
		JComboBox CB_Estado = new JComboBox();
		CB_Estado.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "FROZEN", "CLOSED"}));
		panel.add(CB_Estado, "4, 6, fill, default");
		
		JLabel LB_Deadline = new JLabel("Deadline: ");
		panel.add(LB_Deadline, "2, 8, left, default");
		
		Date tomorrow = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(tomorrow); 
		c.add(Calendar.DATE, 1);
		tomorrow = c.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
		
		//Revisar esto!!!
		TF_Deadline = new JTextField();
		TF_Deadline.setText(dateFormat.format(tomorrow));
		panel.add(TF_Deadline, "4, 8, fill, default");
		TF_Deadline.setColumns(10);
		
		ArrayList<String> emails = new ArrayList<String>();
		for(ArrayList<ArrayList<String>> user : Main.searcher.getAllUsers()){
			emails.add(user.get(0).get(0));
		}
		
		JLabel LB_Responsable = new JLabel("Responsable: ");
		panel.add(LB_Responsable, "2, 10, left, default");
		
		JComboBox CB_Responsable = new JComboBox(emails.toArray());
		panel.add(CB_Responsable, "4, 10, fill, default");
		
		JLabel LB_Ejecutores = new JLabel("Ejecutor(es):");
		panel.add(LB_Ejecutores, "2, 12, left, default");
		
		JComboBox CB_Ejecutores = new JComboBox(emails.toArray());
		panel.add(CB_Ejecutores, "4, 12, fill, default");
		
		JButton BT_Agregar = new JButton("Agregar");
		BT_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedExec.contains(CB_Ejecutores.getSelectedItem().toString()) == false){
					selectedExec.add(CB_Ejecutores.getSelectedItem().toString());
					LS_Ejecutores.setListData(selectedExec.toArray());
					
				}
			}
		});
		panel.add(BT_Agregar, "6, 12");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "4, 14, fill, fill");
		
		scrollPane.setViewportView(LS_Ejecutores);
		
		JButton BT_Eliminar = new JButton("Eliminar");
		BT_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedExec.remove(LS_Ejecutores.getSelectedValue().toString());
				LS_Ejecutores.setListData(selectedExec.toArray());
			}
		});
		panel.add(BT_Eliminar, "6, 14");
		
		JButton BT_Aceptar = new JButton("Aceptar");
		BT_Aceptar.addActionListener(new ActionListener() {
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
					String mensaje = "El usuario "+Main.user.getEmail()+" lo ha agregado en la tarea: "+TF_Descripcion.getText();
					Main.searcher.addNewNotification(mensaje,selectedExec);
				}
				
				Main.ui.addNewTaskToGUI(task);
				panel.setVisible(false);
				frmEdicionDeTarea.setVisible(false);
				frmEdicionDeTarea.dispose();
				
			}
		});
		panel.add(BT_Aceptar, "2, 16, 5, 1");
		frmEdicionDeTarea.setVisible(true);
		
		
		//############################### Fin tab 1 #########################################
		
		//###############################   tab 2   #########################################
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Eliminar tarea", null, panel_1, null);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n: ");
		panel_1.add(lblDescripcin, "2, 4");
		
		ArrayList<String> tasksDesc = new ArrayList<String>();
		for(ArrayList<ArrayList<String>> task : Main.searcher.getAllTasks()){
			tasksDesc.add(task.get(1).get(0));
		}
		
		JComboBox comboBox_4 = new JComboBox(tasksDesc.toArray());
		panel_1.add(comboBox_4, "4, 4, 5, 1, fill, default");
		
		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ArrayList<String>> tas = Main.searcher.getAllTasks().get(comboBox_4.getSelectedIndex());
				Main.searcher.removeTask(tas);
				
				String emailaux = Main.user.getEmail();
				String passaux = Main.user.getPassword();
				
				Main.user.LogOut();					
				Main.LogInUser(emailaux,passaux);
				
				Main.ui.ShowTasksByProjectScreen(Main.user.getProjects());
				String mensaje = "El usuario "+Main.user.getEmail()+"  ha eliminado la tarea: "+comboBox_4.getSelectedItem().toString();
				ArrayList<String> usuarios = new ArrayList<>();
				usuarios.add(Main.user.getEmail());
				Main.searcher.addNewNotification(mensaje,usuarios);
				frmEdicionDeTarea.setVisible(false);
				frmEdicionDeTarea.dispose();				
			}
		});
		panel_1.add(btnEliminar_1, "2, 8, 7, 1");
		
		//############################ Fin tab 2 ##################################
		
		//############################   tab 3   ##################################
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Modificar Tarea", null, panel_2, null);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel LB_DescripcionMain = new JLabel("Descripcion: ");
		JSeparator separator = new JSeparator();
		JLabel LB_DescripcionDown = new JLabel("Descripcion:");
		TF_DescripcionDown = new JTextField();
		JLabel lblNewLabel = new JLabel("Contexto: ");
		JComboBox CB_ContextoUp = new JComboBox();
		CB_ContextoUp.setModel(new DefaultComboBoxModel(new String[] {"En la oficina", "En el auto", "Al telefono"}));
		JComboBox CB_EstadoUp = new JComboBox();
		CB_EstadoUp.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "FROZEN", "CLOSED"}));
		JLabel lblDeadline_1 = new JLabel("Deadline: ");
		textField_3 = new JTextField();
		JLabel lblResponsable_1 = new JLabel("Responsable:");
		JComboBox CB_ResponsableUp = new JComboBox(emails.toArray());
		JLabel lblEjecutores = new JLabel("Ejecutor(es):");
		JComboBox CB_EjecutorUp = new JComboBox(emails.toArray());
		
		JLabel lblEstado = new JLabel("Estado: ");
		panel_2.add(lblEstado, "2, 10, left, default");
		
		panel_2.add(LB_DescripcionMain, "2, 2, left, default");
		
		JComboBox CB_DescripcionUp = new JComboBox(tasksDesc.toArray());
		CB_DescripcionUp.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        ArrayList<ArrayList<String>> currentTask = Main.searcher.getAllTasks().get(CB_DescripcionUp.getSelectedIndex());
		        TF_DescripcionDown.setText(currentTask.get(1).get(0));
		        textField_3.setText(currentTask.get(4).get(0));
		    }
		});
		
		panel_2.add(CB_DescripcionUp, "4, 2, fill, default");
		
		panel_2.add(separator, "2, 4, 5, 1");
		
		panel_2.add(LB_DescripcionDown, "2, 6, left, default");
		
		panel_2.add(TF_DescripcionDown, "4, 6, fill, default");
		TF_DescripcionDown.setColumns(10);
		
		panel_2.add(lblNewLabel, "2, 8, left, default");
		
		panel_2.add(CB_ContextoUp, "4, 8, fill, default");
		
		panel_2.add(CB_EstadoUp, "4, 10, fill, default");
		
		panel_2.add(lblDeadline_1, "2, 12, left, default");
		
		panel_2.add(textField_3, "4, 12, fill, default");
		textField_3.setColumns(10);
		
		panel_2.add(lblResponsable_1, "2, 14, left, default");
		
		panel_2.add(CB_ResponsableUp, "4, 14, fill, default");
		
		panel_2.add(lblEjecutores, "2, 16, left, default");
		
		panel_2.add(CB_EjecutorUp, "4, 16, fill, default");
		
		JButton btnAgregar_1 = new JButton("Agregar");
		panel_2.add(btnAgregar_1, "6, 16");
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "El usuario "+Main.user.getEmail()+" ha modificado la tarea ";
				ArrayList<String> usuarios = new ArrayList<>();
				usuarios.add(Main.user.getEmail());
				Main.searcher.addNewNotification(mensaje,usuarios);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, "4, 18, fill, fill");
		
		JList LS_EjecutoresUp = new JList();
		scrollPane_1.setViewportView(LS_EjecutoresUp);
		
		JButton btnEliminar_2 = new JButton("Eliminar");
		panel_2.add(btnEliminar_2, "6, 18");
		panel_2.add(btnNewButton, "2, 20, 5, 1");
		
		
		
		//##########################################################
	
		
		}

}