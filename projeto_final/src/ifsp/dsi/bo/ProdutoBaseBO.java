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
import ifsp.dsi.dao.ProdutoBaseDAO;
import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.ProdutoBase;
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
public class ProdutoBaseBO {
        
    public ProdutoBaseBO() {}
    
    public List<ProdutoBase> listarTodos(){
        
        ProdutoBaseDAO dao = new ProdutoBaseDAO();
        
        List<ProdutoBase> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoBaseBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
}
