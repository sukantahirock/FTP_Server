package network;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class FtpServer extends JFrame {
	static InputStream in;
	static OutputStream out;
	static ServerSocket serverSocket;
	static JLabel lblNewLabel_5;

	static JLabel lblNewLabel_6;
	static JLabel lblNewLabel_7;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FtpServer frame = new FtpServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		serverSocket=new ServerSocket(1000);
		Socket socket=serverSocket.accept();
		lblNewLabel_6.setText("new client");
		lblNewLabel_6.setForeground(Color.ORANGE);
		in=socket.getInputStream();
		out=new FileOutputStream("test.txt");
		byte[]b=new byte[16*1024];
		int count;
		while((count=in.read(b))>0)		
		{
			out.write(b, 0, count);
			lblNewLabel_7.setText("new File recieved");
			lblNewLabel_7.setForeground(Color.GREEN);
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public FtpServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("G:\\eclipse workspace\\Network\\src\\network\\FtpClient.png"));
		lblNewLabel.setBounds(362, 26, 274, 276);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Status:");
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 56, 80, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Connection:");
		lblNewLabel_3.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 137, 109, 31);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Recieve Files:");
		lblNewLabel_4.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 243, 130, 31);
		contentPane.add(lblNewLabel_4);
		
		 lblNewLabel_5 = new JLabel("running");
		lblNewLabel_5.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblNewLabel_5.setBounds(160, 56, 207, 31);
		contentPane.add(lblNewLabel_5);
		
		 lblNewLabel_6 = new JLabel("no client");
		lblNewLabel_6.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblNewLabel_6.setBounds(160, 137, 207, 31);
		contentPane.add(lblNewLabel_6);
		
		 lblNewLabel_7 = new JLabel("no file recieved");
		lblNewLabel_7.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblNewLabel_7.setBounds(160, 243, 207, 31);
		contentPane.add(lblNewLabel_7);
	}
}
