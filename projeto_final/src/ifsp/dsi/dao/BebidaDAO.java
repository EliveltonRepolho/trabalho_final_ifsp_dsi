/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Bebida;
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
public class BebidaDAO{

    public void salvar(Bebida bebida) throws SQLException {
        Connection con = null;
        PreparedStatement pStatProduto = null;
        PreparedStatement pStatBebida = null;
        PreparedStatement pStatItens = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
    
        String sqlProduto = "insert into produto(id_produto, nome,percentual_lucro)"
                + "values(seq_produto.NEXTVAL,?,?)";
        
        String sqlBebida = "insert into bebida(id_produto,tipo, valor_custo, qtde_estoque, qtde_min_estoque)"
                + "values(seq_produto.CURRVAL,?,?,?,?)";
        
        
                
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatProduto = con.prepareStatement(sqlProduto);
            pStatProduto.setString(1, bebida.getNome());
            pStatProduto.setBigDecimal(2, BigDecimal.valueOf(bebida.getPercentual_lucro()));

            pStatProduto.executeUpdate(); 
            
            pStatBebida = con.prepareStatement(sqlBebida);
            pStatBebida.setInt(1, bebida.getTipo());
            pStatBebida.setBigDecimal(2, bebida.getValorCusto());
            pStatBebida.setInt(3, bebida.getQtd_estoque());
            pStatBebida.setInt(4, bebida.getQtd_minima());

            pStatBebida.executeUpdate();
            
            
            con.commit();
        }
        catch(SQLException erro){
            if(con != null){
                con.rollback();
            }
            
            throw erro;
        }
        finally {
            EntidadeDAO.fecharRecursos(con, pStatProduto);
            EntidadeDAO.fecharRecursos(con, pStatItens);
        }
    }
    
    private boolean existe(Bebida b) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select 1 from produto "
                    +" where id_produto = ? ";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, b.getId());
          
          rs = pStat.executeQuery();
          
          if (!rs.next()){
              return false;
          }        
        }finally{
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return true;
    }
    
    public void atualizar(Bebida bebida) throws SQLException {
        Connection con = null;
        PreparedStatement pStatProduto = null;
        PreparedStatement pStatBebida = null;
        
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sqlUpdateProduto = "update produto set nome = ?, percentual_lucro = ? "
                                + "where id_produto = ?";
        
        
        String sqlUpdateBebida = "update bebida set tipo = ?, valor_custo = ?, qtde_estoque = ?, qtde_min_estoque = ? "
                             + "where id_produto = ? ";
                
        
    
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatProduto = con.prepareStatement(sqlUpdateProduto);
            pStatProduto.setString(1, bebida.getNome());
            pStatProduto.setBigDecimal(2, BigDecimal.valueOf(bebida.getPercentual_lucro()));
            pStatProduto.setLong(3, bebida.getId());

            pStatProduto.executeUpdate(); 
            
            pStatBebida = con.prepareStatement(sqlUpdateBebida);
            pStatBebida.setInt(1, bebida.getTipo());
            pStatBebida.setBigDecimal(2, bebida.getValorCusto());
            pStatBebida.setInt(3, bebida.getQtd_estoque());
            pStatBebida.setInt(4, bebida.getQtd_minima());
            pStatProduto.setLong(5, bebida.getId());

            pStatBebida.executeUpdate();
            
            con.commit();
        }
        catch(SQLException erro){
            if(con != null){
                con.rollback();
            }
            
            throw erro;
        }
        finally {
            EntidadeDAO.fecharRecursos(con, pStatProduto);
            EntidadeDAO.fecharRecursos(con, pStatBebida);
            
        }
    }

    public void apagar(Bebida b) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql = "delete produto where id_produto = ?";
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = con.prepareStatement(sql);
            pStat.setLong(1, b.getId());
            
            pStat.executeUpdate();

            con.commit();
        }
        catch(SQLException erro){
            if(con != null){
                con.rollback();
            }
            
            throw erro;
        }
        finally {
            EntidadeDAO.fecharRecursos(con, pStat);
        }
    }

    public List<Bebida> listarByTipo() throws SQLException {
        
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select p.id_produto, " //1
                   + "p.percentual_lucro, " //2
                   + "b.tipo, " //3
                   + "b.qtde_estoque, " //4
                   + "b.QTDE_MIN_ESTOQUE, " //5
                   + "p.nome, " //6
                   + "b.VALOR_CUSTO " + //7
                    "    from produto p," +
                    "          bebida b" +
                    "    where p.id_produto = b.id_produto" +
                    "     and b.tipo = 0";
        
        List<Bebida> list = new ArrayList<>();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql);
          
          rs = pStat.executeQuery();
          
          while (rs.next()) {
                Bebida b = new Bebida(
                        rs.getLong(1), 
                        rs.getString(6), 
                        rs.getBigDecimal(7), 
                        rs.getInt(3), 
                        rs.getInt(4), 
                        rs.getInt(5), 
                        rs.getDouble(2)
                );
                
                list.add(b);
         }
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return list;
    }
    
        
}
