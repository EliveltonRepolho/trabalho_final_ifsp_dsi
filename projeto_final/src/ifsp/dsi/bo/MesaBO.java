/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.MesaDAO;
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
public class MesaBO {
        
    public MesaBO() {}
    
    public void salvar(Mesa m){
        MesaDAO dao = new MesaDAO();
        
        try {
            if(!dao.existe(m.getNumero())){
                dao.salvar(m);
            }else{
                dao.atualizar(m);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MesaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Mesa m){
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.MESA_DAO);
        
        boolean deletado = true;
        
        try {
            dao.apagar(m);
        } catch (SQLException ex) {
            Logger.getLogger(MesaBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Não foi possível exluir a Mesa !");            
        }
        
        return deletado;
    }
    
    public List<Mesa> listarTodos(){
        
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.MESA_DAO);
        
        List<Mesa> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(MesaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
}
