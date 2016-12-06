/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.BebidaDAO;
import ifsp.dsi.dao.MontavelDAO;
import ifsp.dsi.entidade.Bebida;
import ifsp.dsi.entidade.Montavel;
import ifsp.dsi.entidade.MontavelIngredientes;
import ifsp.dsi.enums.MontavelTipo;
import ifsp.dsi.janela.util.MessageBox;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repolho
 */
public class BebidaBO {
        
    public BebidaBO() {}
    
    public void salvar(Bebida bebida){
        
        BebidaDAO dao = new BebidaDAO();
        
        try {
            System.out.println(bebida.getId());
            if(bebida.getId() == null){
                dao.salvar(bebida);
            }else{
                dao.atualizar(bebida);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BebidaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Bebida bebida){
        
        BebidaDAO dao = new BebidaDAO();

        boolean deletado = true;
        
        try {
            dao.apagar(bebida);
        } catch (SQLException ex) {
            Logger.getLogger(BebidaBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Bebida sendo utilizada em algum produto !"+ex);            
        }
        
        return deletado;
    }
    
    public MontavelIngredientes listarByMontavel(Montavel montavel){
        
        MontavelDAO dao = new MontavelDAO();
        
        MontavelIngredientes lista = null;
        
        try {
            lista = dao.listarByMontavel(montavel);
        } catch (SQLException ex) {
            Logger.getLogger(BebidaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public List<Bebida> listarByTipo(){
        
        BebidaDAO dao = new BebidaDAO();
        
        List<Bebida> lista = new ArrayList<>();
        
        try {
            lista = dao.listarByTipo();
        } catch (SQLException ex) {
            Logger.getLogger(BebidaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
