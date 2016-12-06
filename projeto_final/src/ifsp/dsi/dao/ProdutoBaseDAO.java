/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Montavel;
import ifsp.dsi.entidade.MontavelIngrediente;
import ifsp.dsi.entidade.MontavelIngredientes;
import ifsp.dsi.entidade.ProdutoBase;
import ifsp.dsi.enums.MontavelTipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author repolho
 */
public class ProdutoBaseDAO{

    public List<ProdutoBase> listarTodos()throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select id_produto, nome from produto";
        
        List<ProdutoBase> list = new ArrayList<>();
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql); 
          
          rs = pStat.executeQuery();
          list = getListaTodos(rs);
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return list;
    }
    
    private List<ProdutoBase> getListaTodos(ResultSet rs) throws SQLException {
        List<ProdutoBase> produtos = new ArrayList<>();
        
        while (rs.next()) {
            
            ProdutoBase p = new ProdutoBase(
                rs.getLong(1), 
                rs.getString(2)
            );
            
            produtos.add(p);
        }
        
        return produtos;
    }

        
}
