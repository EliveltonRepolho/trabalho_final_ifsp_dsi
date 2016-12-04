/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.FuncionarioDAO;
import ifsp.dsi.entidade.Funcionario;
import ifsp.dsi.janela.JanelaLogin;
import ifsp.dsi.janela.util.MessageBox;
import ifsp.dsi.seguranca.CriptografiaMD5;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repolho
 */
public class FuncionarioBO {
        
    public FuncionarioBO() {}
    
    public void salvar(Funcionario f){
        FuncionarioDAO dao = new FuncionarioDAO();
        
        f.encriptarSenha();
        
        try {
            if(!dao.existe(f.getCpf())){
                dao.salvar(f);
            }else{
                dao.atualizar(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Funcionario f){
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.FUNCIONARIO_DAO);
        
        boolean deletado = true;
        
        try {
            dao.apagar(f);
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Não foi possível excluir o funcionário !");            
        }
        
        return deletado;
    }
    
    public List<Funcionario> listarTodos(){
        
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.FUNCIONARIO_DAO);
        
        List<Funcionario> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
