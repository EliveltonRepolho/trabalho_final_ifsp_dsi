/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.IngredienteDAO;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.enums.MontavelTipo;
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
public class IngredienteBO {
        
    public IngredienteBO() {}
    
    public void salvar(Ingrediente i){
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.INGREDIENTE_DAO);
        
        try {
            if(i.getId() == 0L){
                dao.salvar(i);
            }else{
                dao.atualizar(i);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Ingrediente i){
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.INGREDIENTE_DAO);
        
        boolean deletado = true;
        
        try {
            dao.apagar(i);
        } catch (SQLException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Ingrediente sendo utilizado em algum produto !");            
        }
        
        return deletado;
    }
    
    public List<Ingrediente> listarTodos(){
        
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.INGREDIENTE_DAO);
        
        List<Ingrediente> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public List<Ingrediente> listarByTipo(MontavelTipo tipo){
        
        IngredienteDAO dao = new IngredienteDAO();
        
        List<Ingrediente> lista = null;
        
        try {
            lista = dao.listarByTipo(tipo);
        } catch (SQLException ex) {
            Logger.getLogger(IngredienteBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
