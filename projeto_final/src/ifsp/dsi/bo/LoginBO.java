/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bo;

import ifsp.dsi.dao.EntidadeDAO;
import ifsp.dsi.dao.FabricaDAO;
import ifsp.dsi.dao.FuncionarioDAO;
import ifsp.dsi.entidade.Funcionario;
import ifsp.dsi.janela.JanelaLogin;
import ifsp.dsi.janela.JanelaPrincipal;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repolho
 */
public class LoginBO {

    private JanelaLogin janelaLogin;
    
    public LoginBO() {
    }
    
    public boolean validaUsuario(String login, String senha){
        FabricaDAO fabrica = new FabricaDAO();
        FuncionarioDAO dao = (FuncionarioDAO) fabrica.getEntidadeDAO(FabricaDAO.FUNCIONARIO_DAO);
        
        try {
            if(dao.autenticar(login, senha)){
                Funcionario f = dao.buscarPorLogin(login);
                JanelaPrincipal janela = new JanelaPrincipal(f);
                janela.setVisible(true);
                
                closeView();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public void closeView() {
        if(janelaLogin != null){
            janelaLogin.closeView();
        }
    }
    
    public void showView() {
        janelaLogin = new JanelaLogin();
        janelaLogin.setVisible(true);
        
    }
    
}
