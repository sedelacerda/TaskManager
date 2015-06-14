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

public class NotificationsWindow {
	private JFrame frmNotificaciones;
	private JLabel LB_Notificaciones;
	private JList LS_Notificaciones;
	private JComboBox CB_Notificaciones;

	
	@SuppressWarnings("unchecked")
	public void show(){
		
		frmNotificaciones = new JFrame();
		frmNotificaciones.setTitle("Notificaciones");
		frmNotificaciones.setResizable(false);
		frmNotificaciones.setBounds(100, 100, 580, 400);
		frmNotificaciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNotificaciones.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
		
		LB_Notificaciones = new JLabel("Usted tiene las siguientes notificaciones");
		LB_Notificaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmNotificaciones.getContentPane().add(LB_Notificaciones, "1, 2, 10, 1, center, default");
		frmNotificaciones.setVisible(false);
		frmNotificaciones.dispose();
		frmNotificaciones.setVisible(true);
		ArrayList<String> notificaciones = new ArrayList<String>();
		ArrayList<String> usuarios= new ArrayList<String>();
		
		for (int k=0; k<Main.searcher.Notifications.size();k++){
			usuarios.add(Main.searcher.Notifications.get(k).get(0).get(0).toString());
		}
	
		
		for(int i=0; i<Main.searcher.Notifications.size(); i++){
		
			if(usuarios.get(i).equals(Main.user.getEmail().toString())){	
				LS_Notificaciones = new JList();
				frmNotificaciones.getContentPane().add(LS_Notificaciones, "4, 18, 3, 1, fill, fill");
				for (int j = 0; j< Main.searcher.Notifications.get(i).get(2).size(); j++){
				
					
				
				
						notificaciones.add(Main.searcher.Notifications.get(i).get(2).get(j));
						CB_Notificaciones = new JComboBox(notificaciones.toArray());
						LS_Notificaciones.setListData(notificaciones.toArray());
						
					
				
				
				}
				
				Main.searcher.MarcarComoLeidaNotifications();
		
			}
			
				
		}
	
	}
}