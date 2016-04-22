import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
	
	private void initComponents(){
		unit1ComboBox = new JComboBox<Length>();
		Length [] lengths = unitConverter.getUnits();

		
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
		
		
	}class AutoDetectedListener implements KeyListener{
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {convert();}
		public void keyTyped(KeyEvent e) {}
	}
	class ClearButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			inputField1.setText("");
			inputField2.setText("");
		}
	}
	class ConvertButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
		}
	}
	class LeftListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
			isLeftToRight = true;
			inputField1.setEditable(true);
			inputField2.setEditable(false);
		}
	}
	class RightListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
			isLeftToRight = false;
			inputField1.setEditable(false);
			inputField2.setEditable(true);
		}
	}
	private void convert(){
		String s;
		if(isLeftToRight) s = inputField1.getText().trim();
		else s = inputField2.getText().trim();
		if(s.length() > 0){
			try{
				if(isLeftToRight){
					double value = Double.valueOf(s);
					double result = unitConverter.convert(value, (Length)unit1ComboBox.getSelectedItem(), (Length)unit2ComboBox.getSelectedItem());	
					inputField2.setText(result + "");	
				}else{
					double value = Double.valueOf(s);
					double result = unitConverter.convert(value, (Length)unit2ComboBox.getSelectedItem(), (Length)unit1ComboBox.getSelectedItem());	
					inputField1.setText(result + "");
				}
			}catch(NumberFormatException exception){
				exception.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ConverterUI test = new ConverterUI(new UnitConverter());
	}
}
