/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Vinho;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ModeloTabelaVinho extends AbstractTableModel{
    
    private String[] columns = {"Id", "Nome", "Estoque", "Qtde Mínima", "Valor de Custo","Bebida","Safra","Tipo Uva", "#"};
    private List<Vinho> bebidas;
    
    
    public List<Vinho> getList() {
        return bebidas;        
    }

    public ModeloTabelaVinho(){
        bebidas = new ArrayList<>();
    }
    
    private void atualizarAtIndex(int index, Vinho bebida){
        bebidas.get(index).setId(bebida.getId());
        bebidas.get(index).setNome(bebida.getNome());
        bebidas.get(index).setQtd_estoque(bebida.getQtd_estoque());
        bebidas.get(index).setQtd_minima(bebida.getQtd_minima());
        bebidas.get(index).setValorCusto(bebida.getValorCusto());
        bebidas.get(index).setTipo(bebida.getTipo());
        bebidas.get(index).setSafra(bebida.getSafra());
        bebidas.get(index).setTipoUva(bebida.getTipoUva());
    }
    
    private String retornaTipoBebida(int tipo){
        if(tipo == 0){
            return "Bebida Simples";
        }
        if(tipo == 1){
            return "Vinho";
        }
        return "";
    }    
    public void addRow(Vinho bebida) {
        boolean encontrou = false;
        
        for (int j = 0; j < bebidas.size() - 1; j++) {
            if(bebidas.get(j).getId() == bebida.getId()){
                encontrou = true;
                atualizarAtIndex(j,bebida);
            }
        }
        
        if(!encontrou)
            bebidas.add(bebida);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return bebidas.size();
    }

    public void removeRow(int row){
        bebidas.remove(row);
        fireTableDataChanged();  
    }
    
    public void removeRow(Vinho remove){
        
        List<Vinho> deletados = new ArrayList<>();
        
        for (Vinho i : bebidas) {
            if(i.getId() == remove.getId())
                deletados.add(i);
        }
        
        bebidas.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<Vinho> getSelecionados(){
        List<Vinho> selecionados = new ArrayList<>();
        
        for (Vinho i : bebidas) {
            if(i.isSelecionado())
                selecionados.add(i);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Vinho getValueAt(int rowIndex) {
        return bebidas.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return bebidas.get(rowIndex).getId();
            case 1:
                return bebidas.get(rowIndex).getNome();
            case 2:
                return bebidas.get(rowIndex).getQtd_estoque();
            case 3:
                return bebidas.get(rowIndex).getQtd_minima();
            case 4:
                return bebidas.get(rowIndex).getValorCusto();
            case 5:
                return retornaTipoBebida(bebidas.get(rowIndex).getTipo());
            case 6:
                return bebidas.get(rowIndex).getSafra();
            case 7:
                return bebidas.get(rowIndex).getTipoUva();
            default:
                break;
        }
        
        return bebidas.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 8) 
            return true;
        
        return false;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Class getColumnClass(int c) {
        int columnCount;
  // dataModel is an object of the data Model class(default or abstract)
    columnCount= getRowCount();
    if(columnCount<=1){
       return String.class;
    }
    
    return getValueAt(0, c).getClass();
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
        if (column == 8) {
            bebidas.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        bebidas.clear();
        fireTableDataChanged();
    }
    
}
