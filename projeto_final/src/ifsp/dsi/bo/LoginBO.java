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

    private JanelaLogin mJanelaLogin;
    
    public LoginBO() {
    }
    
    public LoginBO(JanelaLogin janelaLogin) {
        this.mJanelaLogin = janelaLogin;
    }
    
    public boolean validaUsuario(String login, String senha){
        FabricaDAO fabrica = new FabricaDAO();
        FuncionarioDAO dao = (FuncionarioDAO) fabrica.getEntidadeDAO(FabricaDAO.FUNCIONARIO_DAO);
        
        login = login.toUpperCase();
        
        try {
            if(dao.autenticar(login, senha)){
                Funcionario f = dao.getByLogin(login);
                closeView();
                
                JanelaPrincipal janela = new JanelaPrincipal(f);
                janela.setVisible(true);
                
                
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public void closeView() {
        if(mJanelaLogin != null){
            mJanelaLogin.setVisible(false);
            mJanelaLogin.closeView();
        }
    }
    
    public void showView() {
        
        if(mJanelaLogin == null)
            mJanelaLogin = new JanelaLogin();
        
        mJanelaLogin.setVisible(true);
        
    }
    
}
