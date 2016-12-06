/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.ProdutoBase;
import ifsp.dsi.entidade.ProdutoBase;
import ifsp.dsi.entidade.MontavelIngrediente;
import ifsp.dsi.entidade.ProdutoBase;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ModeloTabelaCardapioProdutos extends AbstractTableModel{
    
    private String[] columns = {"Nome", "cardápio ?"};
    private List<ProdutoBase> produtos;
    
    
    public List<ProdutoBase> getList() {
        return produtos;        
    }
    
    public void setList(List<ProdutoBase> lista) {
        this.produtos = lista;        
        fireTableDataChanged();
    }

    public ModeloTabelaCardapioProdutos(){
        produtos = new ArrayList<>();
    }
    
    public void addRow(ProdutoBase produto) {
        produtos.add(produto);        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return produtos.size();
    }
    
    public List<ProdutoBase> getSelecionados(){
        List<ProdutoBase> selecionados = new ArrayList<>();
        
        for (ProdutoBase i : produtos) {
            if(i.isSelecionado())
                selecionados.add(i);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public ProdutoBase getValueAt(int rowIndex) {
        return produtos.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return produtos.get(rowIndex).getNome();
            default:
                break;
        }
        
        return produtos.get(rowIndex).isSelecionado();
        
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       
        if (column == 1) 
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
            produtos.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        produtos.clear();
        fireTableDataChanged();
    }
    
}
