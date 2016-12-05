/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.tabela;

import ifsp.dsi.entidade.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author repolho
 */
public class ModeloTabelaFuncionario extends AbstractTableModel{
    
    private String[] columns = {"Cpf", "Nome", "Telefone", "Perfil", "Login", "#"};
    private List<Funcionario> funcionarios;

    public ModeloTabelaFuncionario(){
        funcionarios = new ArrayList<>();
    }
    
    private void atualizarAtIndex(int index, Funcionario funcionario){
        funcionarios.get(index).setCpf(funcionario.getCpf());
        funcionarios.get(index).setNome(funcionario.getNome());
        funcionarios.get(index).setTelefone(funcionario.getTelefone());
        funcionarios.get(index).setPerfil(funcionario.getPerfil());
        funcionarios.get(index).setLoginUsuario(funcionario.getLoginUsuario());
        funcionarios.get(index).setSenha(funcionario.getSenha());
    }
    
    public void addRow(Funcionario funcionario) {
        boolean encontrou = false;
        
        for (int j = 0; j < funcionarios.size() - 1; j++) {
            if(funcionarios.get(j).getCpf()== funcionario.getCpf()){
                encontrou = true;
                atualizarAtIndex(j,funcionario);
            }
        }
        
        if(!encontrou)
            funcionarios.add(funcionario);
        
        // Notifica a view que houve atualização
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    public void removeRow(int row){
        funcionarios.remove(row);
        fireTableDataChanged();  
    }
    
    public void removeRow(Funcionario remove){
        
        List<Funcionario> deletados = new ArrayList<>();
        
        for (Funcionario f : funcionarios) {
            if(f.getCpf()== remove.getCpf())
                deletados.add(f);
        }
        
        funcionarios.removeAll(deletados);
        
        fireTableDataChanged();  
    }
    
    public List<Funcionario> getSelecionados(){
        List<Funcionario> selecionados = new ArrayList<>();
        
        for (Funcionario f : funcionarios) {
            if(f.isSelecionado())
                selecionados.add(f);
        }
        
        return selecionados;
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Funcionario getValueAt(int rowIndex) {
        return funcionarios.get(rowIndex);        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return funcionarios.get(rowIndex).getCpf();
            case 1:
                return funcionarios.get(rowIndex).getNome();
            case 2:
                return funcionarios.get(rowIndex).getTelefone();
            case 3:
                return funcionarios.get(rowIndex).getPerfil() == 0 ? "Gerente" : "Atendente";
            case 4:
                return funcionarios.get(rowIndex).getLoginUsuario();
            default:
                break;
        }
        
        return funcionarios.get(rowIndex).isSelecionado();
        
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
            funcionarios.get(row).setSelecionado((boolean) value);
            fireTableCellUpdated(row, column);
        }
    }
    
    public void removeAllRows() {
        funcionarios.clear();
        fireTableDataChanged();
    }
    
    
}
