import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterface extends JFrame implements ActionListener {
	
	JLabel LB_email;
	JTextField TF_email;
	JLabel LB_password;
	JPasswordField PF_password;
	JButton BT_login;
	JLabel LB_incorrect_password;
	GridBagConstraints gbc;
	
	public UserInterface() {
		
		super("Task Manager");
		ShowLoginScreen();
	}
	
	public void ShowLoginScreen(){
		
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		GridBagLayout grid = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(grid);
		
		LB_email = new JLabel("Ingresar Email: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_email,gbc);
		
		TF_email = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(TF_email,gbc);
		
		LB_password = new JLabel("Ingresar Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_password,gbc);
		
		PF_password = new JPasswordField(30);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(PF_password,gbc);

		BT_login = new JButton("Iniciar Sesion");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_login,gbc);
		
		BT_login.addActionListener(this);
		
		LB_incorrect_password = new JLabel("*Usuario y/o contraseña inválidos, intente de nuevo.");
		LB_incorrect_password.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==BT_login){
			if(Main.searcher.ValidateUser(TF_email.getText(), PF_password.getPassword())){
				//cambiar de vista
			}
			else{
				add(LB_incorrect_password,gbc);
				setVisible(true);
			}
		}
	}

}
