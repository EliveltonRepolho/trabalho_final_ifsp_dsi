/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.CardapioDAO;
import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.MesaDAO;
import ifsp.dsi.entidade.Cardapio;
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
public class CardapioBO {
        
    public CardapioBO() {}
    
    public void salvar(Cardapio c){
        CardapioDAO dao = new CardapioDAO();
        
        try {
            if(c.getId() == 0){
                dao.salvar(c);
            }else{
                dao.atualizar(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CardapioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Cardapio c){
        CardapioDAO dao = new CardapioDAO();
        
        boolean deletado = true;
        
        try {
            dao.apagar(c);
        } catch (SQLException ex) {
            Logger.getLogger(CardapioBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Não foi possível exluir a Mesa !");            
        }
        
        return deletado;
    }
    
    public List<Cardapio> listarTodos(){
        
        CardapioDAO dao = new CardapioDAO();
        
        List<Cardapio> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(CardapioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public Cardapio getAtivo(){
        
        CardapioDAO dao = new CardapioDAO();
        
        Cardapio lista = null;
        
        try {
            lista = dao.getAtivo();
        } catch (SQLException ex) {
            Logger.getLogger(CardapioBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
