package src;

import java.awt.Color;

import javax.swing.JLabel;

public class CellView extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Boolean assigned;
	public int intValue;
	String strValue;
	Boolean startingValue;

	public CellView() {
		NewCell();
	}

	private void NewCell() {
		startingValue = false;
		intValue = 1;
		strValue = "0";
		assigned = false;
	}

	public int getTextValue() {
		strValue = this.getText();
		int value = 0;
		if (strValue.equals(" ") || strValue.equals("")) {
			System.out.println("Value: " + strValue);
		} else

			value = Integer.parseInt(strValue);

		return value;
	}

	public int getValue() {

		return intValue;
	}

	public void setValue(int value) {
		if(this.startingValue == true){
		intValue = value;
		SudokuMenuView view = new SudokuMenuView(null);
		view.setStartButton(value, this);
		}
		else{
		intValue = value;
		SudokuMenuView.setButton(value, this);
		}
	}

	public String getPencil() {
		String strValue = this.getText();

		return strValue;
	}

	public void setPencil(String value) {

		strValue = value;

		SudokuMenuView.possibleValue(value, this);

	}
	
	public void current(){
		
		this.setBackground(Color.red);
		this.setOpaque(true);
	}
	
	public void notCurrent(){
		
		this.setBackground(Color.gray);
		this.setOpaque(true);
	}
}
