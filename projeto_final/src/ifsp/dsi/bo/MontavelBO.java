/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.MontavelDAO;
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
public class MontavelBO {
        
    public MontavelBO() {}
    
    public void salvar(MontavelIngredientes montavelIngredientes){
        
        MontavelDAO dao = new MontavelDAO();
        
        Montavel montavel = montavelIngredientes.getMontavelIngredientes().get(0).getMontavel();
        
        try {
            if(montavel.getId() == 0L){
                dao.salvar(montavelIngredientes);
            }else{
                dao.atualizar(montavelIngredientes);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MontavelBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Montavel montavel){
        
        MontavelDAO dao = new MontavelDAO();

        boolean deletado = true;
        
        try {
            dao.apagar(montavel);
        } catch (SQLException ex) {
            Logger.getLogger(MontavelBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Ingrediente sendo utilizado em algum produto !");            
        }
        
        return deletado;
    }
    
    public MontavelIngredientes listarByMontavel(Montavel montavel){
        
        MontavelDAO dao = new MontavelDAO();
        
        MontavelIngredientes lista = null;
        
        try {
            lista = dao.listarByMontavel(montavel);
        } catch (SQLException ex) {
            Logger.getLogger(MontavelBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public List<Montavel> listarByTipo(MontavelTipo tipo){
        
        MontavelDAO dao = new MontavelDAO();
        
        List<Montavel> lista = new ArrayList<>();
        
        try {
            lista = dao.listarByTipo(tipo);
        } catch (SQLException ex) {
            Logger.getLogger(MontavelBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
