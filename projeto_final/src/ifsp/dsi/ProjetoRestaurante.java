package ifsp.dsi;

import ifsp.dsi.bo.LoginBO;
import ifsp.dsi.janela.JanelaLogin;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author repolho
 */
public class ProjetoRestaurante {
    
    public static void main(String[] args) {
        
        LoginBO loginBO = new LoginBO();
        loginBO.showView();
        
        
    }
}
