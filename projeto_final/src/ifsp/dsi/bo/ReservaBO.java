/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.MesaDAO;
import ifsp.dsi.dao.ReservaDAO;
import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.Reserva;
import ifsp.dsi.enums.StatusReserva;
import ifsp.dsi.janela.JanelaLogin;
import ifsp.dsi.janela.util.MessageBox;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repolho
 */
public class ReservaBO {
        
    public ReservaBO() {}
    
    public void salvar(Reserva r){
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.RESERVA_DAO);
        
        try {
            if(r.getId() == 0 ){
                
                Cliente cliente = r.getCliente();
                
                if(cliente.getId() == 0){
                    ClienteBO clienteBO = new ClienteBO();
                    cliente = clienteBO.salvar(cliente);
                    
                    r.setCliente(cliente);
                }
                
                dao.salvar(r);
            }else{
                dao.atualizar(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean apagar(Reserva r){
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.RESERVA_DAO);
        
        boolean deletado = true;
        
        try {
            dao.apagar(r);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBO.class.getName()).log(Level.SEVERE, null, ex);
            
            deletado = false;
            
            MessageBox.showError("Não foi possível exluir a Mesa !");            
        }
        
        return deletado;
    }
    
    public List<Reserva> listarTodos(){
        
        FabricaDAO fabrica = new FabricaDAO();
        EntidadeDAO dao = fabrica.getEntidadeDAO(FabricaDAO.RESERVA_DAO);
        
        List<Reserva> lista = null;
        
        try {
            lista = dao.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
    public List<Reserva> listarTodos(StatusReserva status){
        
        ReservaDAO dao = new ReservaDAO();
        
        List<Reserva> lista = null;
        
        try {
            lista = dao.listarTodos(status);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
        
    }
    
}
