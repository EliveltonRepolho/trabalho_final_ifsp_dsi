/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author repolho
 */
public class ModeloTabelaListaEspera extends AbstractTableModel{
    
    private String[] columns = {"Cliente", "Telefone", "#"};
    private List<Reserva> reservas;

    public ModeloTabelaListaEspera(){
        reservas = new ArrayList<>();
    }
    
    public void addRow(Reserva reserva) {
        reservas.add(reserva);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return reservas.size();
    }

    public void removeRow(int row){
        reservas.remove(row);
        fireTableDataChanged();  
    }
    
    public void removeRow(Reserva remove){
        
        List<Reserva> deletados = new ArrayList<>();
        
        for (Reserva r : reservas) {
            if(r.getId()== remove.getId())
                deletados.add(r);
        }
        
        reservas.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<Reserva> getSelecionados(){
        List<Reserva> selecionados = new ArrayList<>();
        
        for (Reserva r : reservas) {
            if(r.isSelecionado())
                selecionados.add(r);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Reserva getValueAt(int rowIndex) {
        return reservas.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return reservas.get(rowIndex).getCliente().getNome();
            case 1:
                return reservas.get(rowIndex).getCliente().getTelefone();
            default:
                break;
        }
        
        return reservas.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 2) 
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
        if (column == 2) {
            reservas.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        reservas.clear();
        fireTableDataChanged();
    }
    
    
}
