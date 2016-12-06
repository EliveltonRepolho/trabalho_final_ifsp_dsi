/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.VinhoDAO;
import ifsp.dsi.entidade.Vinho;
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
public class VinhoBO {
        
    public VinhoBO() {}
    
    public void salvar(Vinho bebida){
        
        VinhoDAO dao = new VinhoDAO();
        
        try {
            System.out.println(bebida.getId());
            if(bebida.getId() == 0){
                dao.salvar(bebida);
            }else{
                dao.atualizar(bebida);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VinhoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Vinho bebida){
        
        VinhoDAO dao = new VinhoDAO();

        boolean deletado = true;
        
        try {
            dao.apagar(bebida);
        } catch (SQLException ex) {
            Logger.getLogger(VinhoBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Bebida sendo utilizada em algum produto !"+ex);            
        }
        
        return deletado;
    }
    
    
    public List<Vinho> listarByTipo(){
        
        VinhoDAO dao = new VinhoDAO();
        
        List<Vinho> lista = new ArrayList<>();
        
        try {
            lista = dao.listarByTipo();
        } catch (SQLException ex) {
            Logger.getLogger(VinhoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
