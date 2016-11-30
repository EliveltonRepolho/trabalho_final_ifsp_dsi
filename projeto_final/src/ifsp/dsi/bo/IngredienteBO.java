/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.janela.JanelaLogin;
import java.sql.SQLException;
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
}
