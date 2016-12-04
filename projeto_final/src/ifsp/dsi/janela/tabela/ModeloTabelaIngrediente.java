/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Ingrediente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author repolho
 */
public class ModeloTabelaIngrediente extends AbstractTableModel{
    
    private String[] columns = {"Id", "Nome", "Estoque", "Qtde Mínima", "Valor de Custo", "Prato", "Bebida", "#"};
    private List<Ingrediente> ingredientes;

    public ModeloTabelaIngrediente(){
        ingredientes = new ArrayList<>();
    }
    
    private void atualizarAtIndex(int index, Ingrediente ingrediente){
        ingredientes.get(index).setId(ingrediente.getId());
        ingredientes.get(index).setNome(ingrediente.getNome());
        ingredientes.get(index).setQtdeEstoque(ingrediente.getQtdeEstoque());
        ingredientes.get(index).setQtdeMinima(ingrediente.getQtdeMinima());
        ingredientes.get(index).setBebida(ingrediente.isPresentBebida());
        ingredientes.get(index).setPrato(ingrediente.isPresentPrato());
    }
    
    public void addRow(Ingrediente ingrediente) {
        boolean encontrou = false;
        
        for (int j = 0; j < ingredientes.size() - 1; j++) {
            if(ingredientes.get(j).getId() == ingrediente.getId()){
                encontrou = true;
                atualizarAtIndex(j,ingrediente);
            }
        }
        
        if(!encontrou)
            ingredientes.add(ingrediente);
        
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
    
    public void removeRow(Ingrediente remove){
        
        List<Ingrediente> deletados = new ArrayList<>();
        
        for (Ingrediente i : ingredientes) {
            if(i.getId() == remove.getId())
                deletados.add(i);
        }
        
        ingredientes.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<Ingrediente> getSelecionados(){
        List<Ingrediente> selecionados = new ArrayList<>();
        
        for (Ingrediente i : ingredientes) {
            if(i.isSelecionado())
                selecionados.add(i);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Ingrediente getValueAt(int rowIndex) {
        return ingredientes.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return ingredientes.get(rowIndex).getId();
        else if (columnIndex == 1)
            return ingredientes.get(rowIndex).getNome();
        else if (columnIndex == 2)
            return ingredientes.get(rowIndex).getQtdeEstoque();
        else if (columnIndex == 3)
            return ingredientes.get(rowIndex).getQtdeMinima();
        else if (columnIndex == 4)
            return ingredientes.get(rowIndex).getValorCusto();
        else if (columnIndex == 5)
            return ingredientes.get(rowIndex).isPresentPrato();
        else if (columnIndex == 6)
            return ingredientes.get(rowIndex).isPresentBebida();
        
        return ingredientes.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 7) 
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
        if (column == 7) {
            ingredientes.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        ingredientes.clear();
        fireTableDataChanged();
    }
    
}
