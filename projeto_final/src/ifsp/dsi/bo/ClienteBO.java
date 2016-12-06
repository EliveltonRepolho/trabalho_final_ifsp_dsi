/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.ClienteDAO;
import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.MesaDAO;
import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.janela.JanelaLogin;
import ifsp.dsi.janela.util.MessageBox;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repolho
 */
public class ClienteBO {
        
    public ClienteBO() {}
    
    public Cliente salvar(Cliente c){
        ClienteDAO dao = new ClienteDAO();
        
        try {
            if(c.getId() == 0){
                c = dao.salvar(c);
            }else{
                dao.atualizar(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    public boolean apagar(Cliente c){
        
        ClienteDAO dao = new ClienteDAO();
        
        boolean deletado = true;
        
        try {
            dao.apagar(c);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Não foi possível exluir a Mesa !");            
        }
        
        return deletado;
    }
    
    public List<Cliente> listarTodos(){
        
        ClienteDAO dao = new ClienteDAO();
        
        List<Cliente> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public Cliente getByTelefone(long telefone){
                
        ClienteDAO dao = new ClienteDAO();
        
        Cliente cliente = null;
        
        try {
            cliente = dao.getByTelefone(telefone);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cliente;
        
    }
}
