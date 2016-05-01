package converter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * Graphics User Interface of Conventer
 * @author Lab9_Patipol_Wangjaitham_5810545432
 *
 */
public class ConverterUI extends JFrame{
	private JButton convertButton;
	private JButton clearButton;
	private UnitConverter unitConverter;
	private JComboBox unit1ComboBox;
	private JFrame contents;
	private JTextField inputField1;
	private JTextField inputField2;
	private JComboBox unit2ComboBox;
	private JRadioButton leftButton;
	private JRadioButton rightButton;
	private ButtonGroup radioButtonGroup = new ButtonGroup();
	private Boolean isLeftToRight = true;
	public ConverterUI(UnitConverter uc){
		this.unitConverter = uc;
		this.setTitle("Distance Converter");
		initComponents();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents(){
		unit1ComboBox = new JComboBox<Length>();
		Unit [] lengths = unitConverter.getUnits();

		
		unit1ComboBox = new JComboBox(lengths);
		unit2ComboBox = new JComboBox(lengths);
		inputField1 = new JTextField(10);
		inputField2 = new JTextField(10);
		inputField2.setEditable(false);
		clearButton = new JButton("Clear");
		convertButton = new JButton("Convert!");
		contents = new JFrame();
		leftButton = new JRadioButton("Left->Right", true);
		rightButton = new JRadioButton("Right->Left", false);
		
		contents.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		radioButtonGroup.add(leftButton);
		radioButtonGroup.add(rightButton);
		
		contents.add(leftButton);
		contents.add(rightButton);
		contents.setLayout(new FlowLayout());
		contents.add(inputField1);
		contents.add(unit1ComboBox);
		contents.add(new JLabel("="));
		contents.add(inputField2);
		contents.add(unit2ComboBox);
		contents.add(convertButton);
		contents.add(clearButton);
		contents.add(leftButton);
		contents.add(rightButton);
		
		contents.setVisible(true);
		contents.setResizable(false);
		contents.setBounds(0,0,670,100);
		
		contents.getRootPane().setDefaultButton(convertButton);
		
		inputField1.addKeyListener(new AutoDetectedListener());
		inputField2.addKeyListener(new AutoDetectedListener());
		
		clearButton.addActionListener(new ClearButtonListener());
		leftButton.addActionListener(new LeftListener());
		rightButton.addActionListener(new RightListener());
		convertButton.addActionListener(new ConvertButtonListener());
		
		
	}
	/*
	 * AutoDetectedListener is a KeyListener when you released the key from keyboard 
	 * it will call method convert
	 */
	class AutoDetectedListener implements KeyListener{
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {convert();}
		public void keyTyped(KeyEvent e) {}
	}
	/*
	 * ClearButtonListener is an ActionListener that use to reset two JTextfield to
	 * empty string and reset two JComboBox to the first one
	 */
	class ClearButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			inputField1.setText("");
			inputField2.setText("");
			unit1ComboBox.setSelectedIndex(0);
			unit2ComboBox.setSelectedIndex(0);
		}
	}
	/*
	 * ConverButtonListener is an ActionListener that call convert
	 */
	class ConvertButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
		}
	}
	/*
	 * LeftListtener is an ActionListener that operate when press leftButton
	 * It will set Left JTextfield editable to true and Right JTextfield editable to false
	 */
	class LeftListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
			isLeftToRight = true;
			inputField1.setEditable(true);
			inputField2.setEditable(false);
		}
	}
	/*
	 * RightListtener is an ActionListener that operate when press rightButton
	 * It will set Right JTextfield editable to true and Left JTextfield editable to false
	 */
	class RightListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
			isLeftToRight = false;
			inputField1.setEditable(false);
			inputField2.setEditable(true);
		}
	}
	/**
	 * Convert is use to convert what you want 
	 * It will get Text from JTextfield convert the value call convert from unitConverter to convert
	 * the value and set it in other JTextfield
	 */
	private void convert(){
		String s;
		if(isLeftToRight) s = inputField1.getText().trim();
		else s = inputField2.getText().trim();
		if(s.length() > 0){
			try{
				if(isLeftToRight){
					double value = Double.valueOf(s);
					double result = unitConverter.convert(value, (Unit)unit1ComboBox.getSelectedItem(), (Unit)unit2ComboBox.getSelectedItem());	
					inputField2.setText(result + "");	
				}else{
					double value = Double.valueOf(s);
					double result = unitConverter.convert(value, (Unit)unit2ComboBox.getSelectedItem(), (Unit)unit1ComboBox.getSelectedItem());	
					inputField1.setText(result + "");
				}
			}catch(NumberFormatException exception){
				JOptionPane.showMessageDialog(this, "Invalid Value", "Error !", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
}
