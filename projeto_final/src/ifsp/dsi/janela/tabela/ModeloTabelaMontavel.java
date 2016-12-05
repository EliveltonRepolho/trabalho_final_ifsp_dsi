/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Montavel;
import ifsp.dsi.entidade.MontavelIngredientes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author repolho
 */
public class ModeloTabelaMontavel extends AbstractTableModel{
    
    private String[] columns = {"Id", "Nome", "Valor de Custo", "Lucro %", "Valor Venda", "#"};
    private List<Montavel> produtos;

    public ModeloTabelaMontavel(){
        produtos = new ArrayList<>();
    }
    
    private void atualizarAtIndex(int index, Montavel montavel){
        produtos.get(index).setId(montavel.getId());
        produtos.get(index).setNome(montavel.getNome());
        produtos.get(index).setValorCusto(montavel.getValorCusto());
        produtos.get(index).setPercentualLucro(montavel.getPercentualLucro());
        
    }
    
    public void addRow(Montavel montavel) {
        boolean encontrou = false;
        
        for (int j = 0; j < produtos.size() - 1; j++) {
            if(produtos.get(j).getId() == montavel.getId()){
                encontrou = true;
                atualizarAtIndex(j,montavel);
            }
        }
        
        if(!encontrou)
            produtos.add(montavel);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return produtos.size();
    }

    public void removeRow(int row){
        produtos.remove(row);
        fireTableDataChanged();  
    }
    
    public void removeRow(Montavel remove){
        
        List<Montavel> deletados = new ArrayList<>();
        
        for (Montavel m : produtos) {
            if(m.getId() == remove.getId())
                deletados.add(m);
        }
        
        produtos.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<Montavel> getSelecionados(){
        List<Montavel> selecionados = new ArrayList<>();
        
        for (Montavel m : produtos) {
            if(m.isSelecionado())
                selecionados.add(m);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Montavel getValueAt(int rowIndex) {
        return produtos.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return produtos.get(rowIndex).getId();
            case 1:
                return produtos.get(rowIndex).getNome();
            case 2:
                return produtos.get(rowIndex).getValorCusto();
            case 3:
                return produtos.get(rowIndex).getPercentualLucro();
            case 4:
                return produtos.get(rowIndex).getValorVenda();
            default:
                break;
        }
        
        return produtos.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 5) 
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
        if (column == 5) {
            produtos.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        produtos.clear();
        fireTableDataChanged();
    }
    
}
