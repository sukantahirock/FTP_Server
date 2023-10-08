package network;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FtpClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    static Socket socket;
    String str;
    static InputStream in;
	static OutputStream out;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FtpClient frame = new FtpClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		socket=new Socket("127.0.0.1",1000);
	}

	/**
	 * Create the frame.
	 */
	public FtpClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("G:\\eclipse workspace\\Network\\src\\network\\FtpClient.jfif"));
		lblNewLabel.setBounds(185, 50, 297, 213);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select FIle");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 10, 114, 23);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(134, 10, 289, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser=new JFileChooser();
				filechooser.showOpenDialog(btnNewButton);
				str=filechooser.getSelectedFile().getAbsolutePath();
				textField.setText(str);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(453, 11, 127, 42);
		contentPane.add(btnNewButton);
		
		JButton btnPaste = new JButton("Sent");
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				File file=new File(str);
				byte[]b=new byte[16*1024];
				try {
					in=new FileInputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					out=socket.getOutputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					int count;
					while((count=in.read(b))>0)		
					{
						out.write(b, 0, count);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPaste.setForeground(new Color(0, 0, 0));
		btnPaste.setBackground(new Color(240, 240, 240));
		btnPaste.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPaste.setBounds(468, 200, 127, 31);
		contentPane.add(btnPaste);
	}
}
