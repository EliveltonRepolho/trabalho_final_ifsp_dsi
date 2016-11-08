/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author repolho
 */
public class Prato extends ProdutoBase implements Serializable{
    
    private List<ItemProduto> itens;
    
}
