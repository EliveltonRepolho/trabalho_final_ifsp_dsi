/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.io.Serializable;

/**
 *
 * @author repolho
 */
public class Vinho extends ProdutoBase implements Serializable{
    
    private int estoque;
    private int quantidadeMinima;
    private int safra;
    private String uva;
    
}
