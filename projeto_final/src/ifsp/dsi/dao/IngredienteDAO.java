/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.Montavel;
import ifsp.dsi.enums.MontavelTipo;
import ifsp.dsi.enums.StatusMesa;
import java.math.BigDecimal;
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
public class IngredienteDAO extends AbstractDAO<Ingrediente> implements EntidadeDAO<Ingrediente>{

    @Override
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Ingrediente i) throws SQLException {
        String sql = "insert into ingrediente(id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida) "
                   + "values (seq_ingrediente.NEXTVAL, ?,?,?,?,?,?)";        
        
        PreparedStatement pStat = con.prepareStatement(sql);
        pStat.setString(1, i.getNome());
        pStat.setBigDecimal(2, i.getValorCusto());
        pStat.setDouble(3, i.getQtdeEstoque());
        pStat.setDouble(4, i.getQtdeMinima());
        pStat.setString(5, i.isPresentPrato() ? "Y" : "N");
        pStat.setString(6, i.isPresentBebida() ? "Y" : "N");
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Ingrediente i) throws SQLException {
        String sql = "update ingrediente set nome = ?, valor_custo = ?, qtde_estoque = ?, qtde_min_estoque = ?, "
                   + "flag_prato = ?,  flag_bebida = ?"
                   + "where id_ingrediente = ?";
                 
        PreparedStatement pStat = con.prepareStatement(sql);
        pStat.setString(1, i.getNome());
        pStat.setBigDecimal(2, i.getValorCusto());
        pStat.setDouble(3, i.getQtdeEstoque());
        pStat.setDouble(4, i.getQtdeMinima());
        pStat.setString(5, i.isPresentPrato() ? "Y" : "N");
        pStat.setString(6, i.isPresentBebida() ? "Y" : "N");
        pStat.setLong(7, i.getId());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Ingrediente i) throws SQLException {
        String sql = "delete from ingrediente where id_ingrediente = ?";
        
        PreparedStatement pStat = con.prepareStatement(sql);        
        pStat.setLong(1, i.getId());
            
        return pStat;
    }

    @Override
    protected List<Ingrediente> getListaTodos(ResultSet rs) throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<>();
        
        while (rs.next()) {

            Ingrediente i = new Ingrediente(
                    rs.getLong(1), 
                    rs.getString(2), 
                    rs.getBigDecimal(3), 
                    rs.getDouble(4), 
                    rs.getDouble(5), 
                    rs.getString(6).equals("Y"),
                    rs.getString(7).equals("Y")
            );
            
            ingredientes.add(i);
        }
        
        return ingredientes;
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida "
                   + "from ingrediente "
                   + "order by nome";
        
        PreparedStatement pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
    public List<Ingrediente> listarByTipo(MontavelTipo tipo) throws SQLException {
        
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String whereClause = "where ";
               whereClause += tipo.isPresentePrato() ? "flag_prato = ? " :  "flag_bebida = ? ";
               
        String sql = "select id_ingrediente,nome,valor_custo,qtde_estoque,qtde_min_estoque,flag_prato,flag_bebida "
                   + "from ingrediente "
                   + whereClause
                   + "order by nome";
        
        List<Ingrediente> list = new ArrayList<>();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql);
          pStat.setString(1, "Y");
          
          rs = pStat.executeQuery();
          
          while (rs.next()) {
                Ingrediente i = new Ingrediente(
                        rs.getLong(1), 
                        rs.getString(2), 
                        rs.getBigDecimal(3), 
                        rs.getDouble(4), 
                        rs.getDouble(5), 
                        rs.getString(6).equalsIgnoreCase("Y"), 
                        rs.getString(7).equalsIgnoreCase("Y")
                );
                
                list.add(i);
         }
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return list;
    }
}
    