/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

/**
 *
 * @author repolho
 */
public class FabricaDAO {
    public static final String FUNCIONARIO_DAO = "funcionario";
    public static final String MESA_DAO = "mesa";
    
    public EntidadeDAO getEntidadeDAO(String nome){
    
        if(MESA_DAO.equalsIgnoreCase(nome)){
           return new MesaDAO();
        }
        return new FuncionarioDAO();
        
    }
}
