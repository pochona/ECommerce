package utilities;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TabModel extends AbstractTableModel{

	private Object[][] data;
	private String[] title;

	/**
	 * Constructeur TabModel.
	 * Ce constructeur cr�� la table model
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 * 
	 */
	public TabModel(Object[][] data, String[] title){
		this.data = data;
		this.title = title;
	}

	/**
	 * M�thode getColumnCount()
	 * Retourne le nombre de colonnes
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.title.length;
	}

	/**
	 * M�thode getRowCount()
	 * Retourne le nombre de lignes
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.data.length;

	}

	/**
	 * M�thode getValueAt()
	 * Retourne la valeur du tableau selectionn�e au click
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.data[rowIndex][columnIndex];
	}

	/**
	 * M�thode getColumnName()
	 * Retourne le nom de la colonne (indispensable sinon on perd le nom)
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public String getColumnName(int col) {
		return this.title[col];

	}


	/**
	 * M�thode isCellEditable()
	 * Rend toutes les cellule de a*la table model insaisissable
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}

	/**
	 * M�thode removeRow()
	 * M�thode qui supprime la ligne pass�e en parametre
	 * @author lb
	 * @version 1.0 - 27/05/2016
	 */
	public void removeRow(int position){

		int indice = 0, indice2 = 0;
		int nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
		Object temp[][] = new Object[nbRow][nbCol];

		for(Object[] value : this.data){
			if(indice != position){
				temp[indice2++] = value;
			}

			indice++;
		}
		this.data = temp;
		temp = null;
		//On informe le tableau que les donn�es on �t� chang�es
		this.fireTableDataChanged();
	}

}
