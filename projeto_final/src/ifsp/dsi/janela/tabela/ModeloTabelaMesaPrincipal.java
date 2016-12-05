/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Mesa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author repolho
 */
public class ModeloTabelaMesaPrincipal extends AbstractTableModel{
    
    private String[] columns = {"Número", "Qtde Lugares", "Status"};
    private List<Mesa> mesas;

    public ModeloTabelaMesaPrincipal(){
        mesas = new ArrayList<>();
    }
    
    private void atualizarAtIndex(int index, Mesa mesa){
        mesas.get(index).setNumero(mesa.getNumero());
        mesas.get(index).setQuantidadeLugares(mesa.getQuantidadeLugares());
        mesas.get(index).setStatus(mesa.getStatus());
    }
    
    public void addRow(Mesa mesa) {
        boolean encontrou = false;
        
        for (int j = 0; j < mesas.size() - 1; j++) {
            if(mesas.get(j).getNumero()== mesa.getNumero()){
                encontrou = true;
                atualizarAtIndex(j,mesa);
            }
        }
        
        if(!encontrou)
            mesas.add(mesa);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return mesas.size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Mesa getValueAt(int rowIndex) {
        return mesas.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return mesas.get(rowIndex).getNumero();
            case 1:
                return mesas.get(rowIndex).getQuantidadeLugares();
            case 2:
                return mesas.get(rowIndex).getStatus().getDescricao();
            default:
                break;
        }
        
        return mesas.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
      
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
    
    public void removeAllRows() {
        mesas.clear();
        fireTableDataChanged();
    }
    
    
}
