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
	 * initialize everything
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
	 * AutoDetectedListener use for add to inputField1 and inputField2 to make it
	 * auto-detected when released from keyboard it will call convert.
	 */
	class AutoDetectedListener implements KeyListener{
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {convert();}
		public void keyTyped(KeyEvent e) {}
	}
	/*
	 * ClearButtonListener use for add to clearButton to make it clear
	 */
	class ClearButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			inputField1.setText("");
			inputField2.setText("");
		}
	}
	/*
	 * ConverButtonListener use for add to convertButton to make it call convert;
	 */
	class ConvertButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			convert();
		}
	}
	/*
	 * LeftListtener use for add to leftButton when click Left -> Right it will disable
	 * inputField2 and enable inputField1
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
	 * RightListtener use for add to rightButton when click Right -> Left it will disable
	 * inputField1 and enable inputField2
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
	 * Convert use to convert the number that input from 1 unit to other unit and then
	 * it will show the result in other TextField.
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
				exception.printStackTrace();
			}
		}
	}
	/**
	 * Main method to make Graphics User Interfaced run
	 * 
	 */
	public static void main(String[] args) {
		ConverterUI test = new ConverterUI(new UnitConverter());
	}
}
