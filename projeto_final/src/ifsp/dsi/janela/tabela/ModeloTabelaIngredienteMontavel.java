/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.MontavelIngrediente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author repolho
 */
public class ModeloTabelaIngredienteMontavel extends AbstractTableModel{
    
    private String[] columns = { "Ingrediente", "Quantidade", "Retirar ?"};
    private List<MontavelIngrediente> ingredientes;

    public ModeloTabelaIngredienteMontavel(){
        ingredientes = new ArrayList<>();
    }
    
    public void addRow(MontavelIngrediente montavelIngrediente) {
        
        ingredientes.add(montavelIngrediente);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return ingredientes.size();
    }

    public void removeRow(int row){
        ingredientes.remove(row);
        fireTableDataChanged();  
    }
    
    public void removeRow(MontavelIngrediente remove){
        
        List<MontavelIngrediente> deletados = new ArrayList<>();
        
        for (MontavelIngrediente m : ingredientes) {
            if(m.getIngrediente().getId() == remove.getIngrediente().getId())
                deletados.add(m);
        }
        
        ingredientes.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<MontavelIngrediente> getSelecionados(){
        
        List<MontavelIngrediente> selecionados = new ArrayList<>();
        
        for (MontavelIngrediente m : ingredientes) {
            if(m.getIngrediente().isSelecionado())
                selecionados.add(m);
        }
        
        return selecionados;
    }
    
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public MontavelIngrediente getValueAt(int rowIndex) {
        return ingredientes.get(rowIndex);        
    }
    
    public List<MontavelIngrediente> getList() {
        return ingredientes;        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return ingredientes.get(rowIndex).getIngrediente().getNome();
        else if (columnIndex == 1)
            return ingredientes.get(rowIndex).getQtde();
        
        return ingredientes.get(rowIndex).getIngrediente().isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 1) 
            return true;
        else if(column == 2)
            return true;
        
        return false;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Class getColumnClass(int row) {
        return getValueAt(0, row).getClass();
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
        if (column == 1) {
            ingredientes.get(row).setQtde((double)value);
            fireTableCellUpdated(row, column);
        }
        
        if (column == 2) {
            ingredientes.get(row).getIngrediente().setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        ingredientes.clear();
        fireTableDataChanged();
    }
    
}
