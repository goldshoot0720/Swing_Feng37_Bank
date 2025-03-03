package feng37;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class feng2025 {

	private JFrame frame;
	private JTextField textField1;
	private JTextPane textPane1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					feng2025 window = new feng2025();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public feng2025() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private int[] bank_savings = {0,0,0,0,0,0,0};

	private int Calc_Sum_Saving()
	{
	    int sum_saving = 0;
	    for (int i = 0; i < 7; i++)
	    {
	        sum_saving += bank_savings[i];
	    }
	    return sum_saving;
	}
	
	public static String dirStr = System.getenv("USERPROFILE") + "/AppData/Local/Swing_Feng37_Bank";
	public static String fileStr = dirStr +"/bank_savings.txt";
	
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// JOptionPane.showInputDialog(null,"feng37","feng37",JOptionPane.INFORMATION_MESSAGE);
				frame.setTitle("Swing_Feng37_Bank:"+System.getProperty("os.name")+":"+System.getProperty("os.arch")+":"+System.getProperty("os.version")+":"+System.getProperty("java.version"));
				String line;
				try {
			    	BufferedReader bufferReader = new BufferedReader(new FileReader(fileStr));
			    	int i = 0;
			    	while ((line = bufferReader.readLine()) != null) {
			    		bank_savings[i] = Integer.valueOf(line);
			    		i++;
					}
				}
				catch (IOException e2) {
					e2.printStackTrace();
				}
				textField1.setText(String.valueOf(bank_savings[0]));
				textPane1.setText(String.valueOf(Calc_Sum_Saving()));
			}
		});
		frame.setTitle("Swing_Feng37_BanK");
		frame.setBounds(100, 100, 482, 248);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField1 = new JTextField(10);
		textField1.setToolTipText("請輸入整數數字");
		textField1.setBounds(198, 61, 259, 41);
		frame.getContentPane().add(textField1);
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = comboBox1.getSelectedIndex();
				textField1.setText(String.valueOf(bank_savings[index]));
			}
		});
		comboBox1.setSelectedIndex(-1);
		comboBox1.setToolTipText("請選擇金融機構");
		comboBox1.setBounds(198, 10, 258, 41);
		comboBox1.addItem("(013)國泰世華商業銀行");
		comboBox1.addItem("(017)兆豐國際商業銀行");
		comboBox1.addItem("(048)王道商業銀行");
		comboBox1.addItem("(103)臺灣新光商業銀行");
		comboBox1.addItem("(700)中華郵政股份有限公司");
		comboBox1.addItem("(812)台新國際商業銀行");
		comboBox1.addItem("(822)中國信託商業銀行");
		frame.getContentPane().add(comboBox1);
				
		textPane1 = new JTextPane();
		textPane1.setToolTipText("累積存款");
		textPane1.setEditable(false);
		textPane1.setText("尚未開始計算");
		textPane1.setBounds(199, 109, 258, 41);
		frame.getContentPane().add(textPane1);
		
		JButton button1 = new JButton("修改");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox1.getSelectedIndex();
				bank_savings[index] = Integer.valueOf(textField1.getText());
				int sum_saving = Calc_Sum_Saving();
				textPane1.setText(String.valueOf(sum_saving));
			}
		});
		button1.setToolTipText("存檔之前記得先修改");
		button1.setBounds(198, 160, 115, 41);
		frame.getContentPane().add(button1);
		
		JButton button2 = new JButton("存檔");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File theDir = new File(dirStr);
				if (!theDir.exists()){
				    theDir.mkdirs();
				}
				File theFile = new File(fileStr);	
				try {
					theFile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			    try {
			    	FileWriter myWriter = new FileWriter(fileStr);
					for (int i = 0; i < 7; i++){
						myWriter.write(bank_savings[i]+"\n");
					}
					myWriter.close();
				}
				catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		button2.setToolTipText("關閉程式之前不要忘記存檔");
		button2.setBounds(341, 160, 115, 41);
		frame.getContentPane().add(button2);
		
		JTextPane textPane2 = new JTextPane();
		textPane2.setText("金融機構：");
		textPane2.setBounds(10, 10, 126, 41);
		frame.getContentPane().add(textPane2);
		
		JTextPane textPane3 = new JTextPane();
		textPane3.setText("存款金額：");
		textPane3.setBounds(10, 61, 126, 41);
		frame.getContentPane().add(textPane3);
		
		JTextPane textPane4 = new JTextPane();
		textPane4.setText("累積存款：");
		textPane4.setBounds(10, 110, 126, 41);
		frame.getContentPane().add(textPane4);
		
		JTextPane textPane5 = new JTextPane();
		textPane5.setToolTipText("<html>\r\n委任第五職等<br>\r\n簡任第十二職等<br>\r\n第12屆臺北市長<br>\r\n第23任總統<br>\r\n中央銀行鋒兄分行\r\n</html>");
		textPane5.setText("彩蛋");
		textPane5.setBounds(10, 160, 126, 41);
		frame.getContentPane().add(textPane5);
		
	}
}
