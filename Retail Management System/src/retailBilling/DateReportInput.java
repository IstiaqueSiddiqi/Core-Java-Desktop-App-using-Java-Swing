package retailBilling;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DateReportInput extends JFrame implements ActionListener
{
	private JTextField inputDate_txt;
	private JLabel inputDate_lbl, info_lbl;
	private JButton ok_btn,cancel_btn;
	private JPanel panel1,panel2, panel3;
	
	//Constructor
	public DateReportInput()
	{
		super("Date Wise Report");
		
		inputDate_txt=new JTextField("",20);
		
		inputDate_lbl=new JLabel("Enter Date (dd/mm/yy)");
		info_lbl = new JLabel("Exclude preceding zeros. [ 01/01/13 = wrong. 1/1/13 = right ]");
		
		ok_btn=new JButton("OK");
		cancel_btn=new JButton("CANCEL");
		
		panel1=new JPanel();		
		panel2=new JPanel();
		panel3 = new JPanel();
		
		panel1.add(inputDate_lbl);
		panel1.add(inputDate_txt);
		
		panel2.add(ok_btn);
		panel2.add(cancel_btn);
		
		panel3.add(info_lbl);
		
		add(panel1,BorderLayout.NORTH);
		add(panel3, BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		
		//setting the size of the window
		setSize(400,200);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//making the window visible
		setVisible(true);
		
		//resizing the window
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//registering window components
		ok_btn.addActionListener(this);
		cancel_btn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//action performed when clicked on ok button
		if(e.getSource() == ok_btn)
		{
			String date = inputDate_txt.getText().trim();
			
			dispose();
			new DateWiseReportTable(date);
		}
		
		//action performed when clicked on cancel button
		if(e.getSource() == cancel_btn)
		{
			dispose();
		}
	}
}
