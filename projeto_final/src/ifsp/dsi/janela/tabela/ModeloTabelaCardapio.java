/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.MontavelIngrediente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ModeloTabelaCardapio extends AbstractTableModel{
    
    private String[] columns = {"Id", "Nome", "Status", "#"};
    private List<Cardapio> cardapios;
    
    
    public List<Cardapio> getList() {
        return cardapios;        
    }

    public ModeloTabelaCardapio(){
        cardapios = new ArrayList<>();
    }
    
    private void atualizarAtIndex(int index, Cardapio cardapio){
        cardapios.get(index).setId(cardapio.getId());
        cardapios.get(index).setNome(cardapio.getNome());
        cardapios.get(index).setCouvert(cardapio.getCouvert());
        cardapios.get(index).setProdutos(cardapio.getProdutos());
    }
    
    public void addRow(Cardapio cardapio) {
        boolean encontrou = false;
        
        for (int j = 0; j < cardapios.size() - 1; j++) {
            if(cardapios.get(j).getId() == cardapio.getId()){
                encontrou = true;
                atualizarAtIndex(j,cardapio);
            }
        }
        
        if(!encontrou)
            cardapios.add(cardapio);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return cardapios.size();
    }

    public void removeRow(int row){
        cardapios.remove(row);
        fireTableDataChanged();  
    }
    
    public void removeRow(Cardapio remove){
        
        List<Cardapio> deletados = new ArrayList<>();
        
        for (Cardapio i : cardapios) {
            if(i.getId() == remove.getId())
                deletados.add(i);
        }
        
        cardapios.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<Cardapio> getSelecionados(){
        List<Cardapio> selecionados = new ArrayList<>();
        
        for (Cardapio i : cardapios) {
            if(i.isSelecionado())
                selecionados.add(i);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Cardapio getValueAt(int rowIndex) {
        return cardapios.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return cardapios.get(rowIndex).getId();
            case 1:
                return cardapios.get(rowIndex).getNome();
            case 2:
                return cardapios.get(rowIndex).isAtivo();
            default:
                break;
        }
        
        return cardapios.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 3) 
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
        if (column == 3) {
            cardapios.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        cardapios.clear();
        fireTableDataChanged();
    }
    
}
