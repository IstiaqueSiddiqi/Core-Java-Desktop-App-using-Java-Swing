package retailBilling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BillDisplay extends JFrame 
{
	private JTable billTable;
	private JScrollPane jsp;
	private JLabel billNo_lbl, date_lbl, memberId_lbl, totalAmount_lbl, info_lbl, discount_lbl, netAmount_lbl,amtInWords_lbl;
	private JTextField billNo_txt, date_txt, memberId_txt, totalAmount_txt, discount_txt,netAmount_txt;
	private BillHandler billHandler;
	private BoughtListFileHandler boughtListFileHandler;
	private JPanel panel1, panel2, panel3, panel4, panel5,panel6,panel7,panel8;
	private ArrayList<Item> boughtList;
	private Object data[][];
	private Date date;
	private Double totalAmount = 0.0;
	private LastBillNo lastBillNo;
	private ArrayList<Bill> billList;
	private Bill bill;
	private DateFormat dt;
	private double discount = 0.0, net = 0.0;	
	
	public BillDisplay(int memberId)
	{
		//setting the title of the window
		this.setTitle("Bill Display");
		
		//creating the object of the JPanel
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();		
		
		//creating object of JtextField
		billNo_txt = new JTextField("",10);
		date_txt = new JTextField("",05);
		memberId_txt = new JTextField("",10);
		totalAmount_txt = new JTextField("",15);
		discount_txt = new JTextField("",15);
		netAmount_txt=new JTextField("",15);
		
		//setting the value of memeberId text field
		memberId_txt.setText(""+memberId);
		memberId_txt.setEditable(false);
		
		//if non member, then id set to zero
		if(memberId == 0)
			discount_txt.setText("0");
		
		//disabling required window components
		billNo_txt.setEditable(false);
		date_txt.setEditable(false);
		totalAmount_txt.setEditable(false);
		
		//creating object of JLabel
		billNo_lbl =new JLabel("Bill No");
		date_lbl = new JLabel("Date");
		memberId_lbl = new JLabel("Member ID");
		totalAmount_lbl = new JLabel("Total Amount");
		netAmount_lbl = new JLabel("Net Amount");
		info_lbl = new JLabel("Thank You. Visit Again");
		discount_lbl = new JLabel("Discount [4% for Member]");
		amtInWords_lbl = new JLabel();
		
		//setting color of amtInWords label
		amtInWords_lbl.setOpaque(true);
		amtInWords_lbl.setForeground(Color.BLUE);		
		
		//getting date
		date = new Date();
		
		// SHORT Format
		dt=DateFormat.getDateInstance (DateFormat.SHORT);
		String sDate=dt.format(date);
		date_txt.setText(sDate);
		
		//creating object of JTable
		try
		{
			boughtListFileHandler = new BoughtListFileHandler();
			boughtList = boughtListFileHandler.readFromFile();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Read from BoughtListDB file error..!!","Error", JOptionPane.ERROR_MESSAGE);
			boughtList = new ArrayList<Item>();
		}
		
		String colHead[] = {"Sl No","Item Code","Brand Name","Item Name","Unit Price","No of Items", "Amount"};
		data = new Object[boughtList.size()][7];
		int index = 0;
		for(Item ref:boughtList)
		{
			data[index][0] = index+1;
			data[index][1] = ref.getItemCode();
			data[index][2] = ref.getProductName();
			data[index][3] = ref.getItemName();
			data[index][4] = ref.getPrice();
			data[index][5] = ref.getQuantity();
			data[index++][6] = (ref.getQuantity() * ref.getPrice());
			totalAmount += (ref.getQuantity() * ref.getPrice());
		}
		
		//displaying amount in text field
		totalAmount_txt.setText(""+totalAmount);
		
		//displaying discount in text field
		if(memberId != 0)
		{
			discount =  totalAmount*0.04;
			discount_txt.setText(""+discount);
		}
		discount_txt.setEditable(false);
		
		
		//displaying net amount
		 net = totalAmount - discount;
		netAmount_txt.setText(""+net);
		netAmount_txt.setEditable(false);
		
		//converting the double value to int type
		int intNet = (int)net;		
		
		//setting the label to print the amount in words
		NumberToWords obj = new NumberToWords();
		obj.input(intNet);
		amtInWords_lbl.setText(obj.abc.toString().toUpperCase()+" RUPEES ONLY.");
		
		//displaying bill no in text field
		lastBillNo = new LastBillNo();
		int lastBill = lastBillNo.getLastBillNo();
		billNo_txt.setText(""+ lastBill);		
		
		//writing to file BillDB
		try
		{
			billHandler = new BillHandler();
			billList = (ArrayList<Bill>)billHandler.readFromFile();
		}
		catch(Exception e)
		{
			billList = new ArrayList<Bill>();
		}
		
		//traversing to last Bill
		Iterator<Bill> itr = billList.iterator();
		while(itr.hasNext())
		{
			bill = itr.next();
		}
		
		//setting the values to bill object
		bill.setDate(sDate);
		bill.setItemBought(boughtList);
		bill.setTotalAmount(net);		
		
		//creating object of JTable
		billTable =new JTable(data, colHead);
		billTable.setEnabled(false);
		
		//setting the size of the table
		billTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		billTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		billTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		billTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		billTable.getColumnModel().getColumn(3).setPreferredWidth(300);
		billTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		billTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		billTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		//creating object of JScrollPane
		jsp = new JScrollPane(billTable);
		
		//setting the size of the table
		Dimension d = billTable.getPreferredSize();
		billTable.setPreferredScrollableViewportSize(d);	
		
		
		//adding components to panel
		panel1.add(billNo_lbl);
		panel1.add(billNo_txt);
		panel1.add(date_lbl);
		panel1.add(date_txt);
		panel1.add(memberId_lbl);
		panel1.add(memberId_txt);		
		panel2.add(jsp);		
		panel3.add(totalAmount_lbl);
		panel3.add(totalAmount_txt);
		panel6.add(discount_lbl);
		panel6.add(discount_txt);
		panel7.add(netAmount_lbl);
		panel7.add(netAmount_txt);
		panel8.add(amtInWords_lbl);		
		
		panel4.add(panel2);
		panel4.add(panel3);
		panel4.add(panel6);
		panel4.add(panel7);
		panel4.add(panel8);		
		panel5.add(info_lbl);
		
		//adding component to JFrame
		add(panel1, BorderLayout.NORTH);
		
		//add(panel2, BorderLayout.CENTER);
		add(panel4, BorderLayout.CENTER);
		add(panel5, BorderLayout.SOUTH);		
		
		//making the window visible
		this.setVisible(true);
		
		//setting the size of the window
		this.setSize(950,550);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//resizing the window
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//writing bill to the file
		try
		{
			billHandler.writeToFile(billList);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Write to BillDB File Error","Error Message",JOptionPane.ERROR_MESSAGE);
		}
	}	
}
