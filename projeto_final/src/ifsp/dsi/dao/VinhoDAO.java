/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Vinho;
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
public class VinhoDAO{

    public void salvar(Vinho bebida) throws SQLException {
        Connection con = null;
        PreparedStatement pStatProduto = null;
        PreparedStatement pStatBebida = null;
        PreparedStatement pStatVinho = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
    
        String sqlProduto = "insert into produto(id_produto, nome,percentual_lucro)"
                + "values(seq_produto.NEXTVAL,?,?)";
        
        String sqlBebida = "insert into bebida(id_produto,tipo, valor_custo, qtde_estoque, qtde_min_estoque)"
                + "values(seq_produto.CURRVAL,?,?,?,?)";
        
        
        String sqlVinho = "insert into detalhe_vinho(id_produto, safra,tipo_uva)"
                + "values(seq_produto.CURRVAL,?,?)";
        
                
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
            
            pStatVinho = con.prepareStatement(sqlVinho);
            pStatVinho.setInt(1,bebida.getSafra());
            pStatVinho.setString(2, bebida.getTipoUva());
            
            pStatVinho.executeUpdate();
            
            
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
            EntidadeDAO.fecharRecursos(con, pStatVinho);
        }
    }
    
    private boolean existe(Vinho b) throws SQLException{
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
    
    public void atualizar(Vinho bebida) throws SQLException {
        Connection con = null;
        PreparedStatement pStatProduto = null;
        PreparedStatement pStatBebida = null;
        PreparedStatement pStatVinho = null;
        
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sqlUpdateProduto = "update produto set nome = ?, percentual_lucro = ? "
                                + "where id_produto = ?";
        
        
        String sqlUpdateBebida = "update bebida set tipo = ?, valor_custo = ?, qtde_estoque = ?, qtde_min_estoque = ? "
                             + "where id_produto = ? ";
                
        String sqlUpdateVinho = "update detalhe_vinho set safra = ?, tipo_uva = ? "
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
            pStatBebida.setLong(5, bebida.getId());

            pStatBebida.executeUpdate();
            
            pStatVinho = con.prepareStatement(sqlUpdateVinho);
            pStatVinho.setInt(1,bebida.getSafra());
            pStatVinho.setString(2, bebida.getTipoUva());
            pStatVinho.setLong(3, bebida.getId());
            
            pStatVinho.executeUpdate();
            
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

    public void apagar(Vinho b) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql1 = "delete produto where id_produto = ?";
        String sql2 = "delete bebida where id_produto = ?";
        String sql3 = "delete vinho where id_produto = ?";
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = con.prepareStatement(sql1);
            pStat.setLong(1, b.getId());
            
            pStat.executeUpdate();
            
            pStat = con.prepareStatement(sql2);
            pStat.setLong(1, b.getId());
            
            pStat.executeUpdate();
            
            pStat = con.prepareStatement(sql3);
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

    public List<Vinho> listarByTipo() throws SQLException {
        
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select p.percentual_lucro, b.tipo, b.qtde_estoque, b.QTDE_MIN_ESTOQUE, p.nome, b.VALOR_CUSTO, p.id_produto, v.safra, v.tipo_uva" +
                    "    from produto p," +
                    "          bebida b," +
                    "           detalhe_vinho v" +
                    "    where p.id_produto = b.id_produto" +
                    "    and v.id_produto = p.id_produto  " +
                    "     and b.tipo = 1";
        
        List<Vinho> list = new ArrayList<>();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql);
          
          rs = pStat.executeQuery();
          
          while (rs.next()) {
                Vinho b = new Vinho(
                        rs.getLong(7),
                        rs.getDouble(1),
                        rs.getInt(2), 
                        rs.getInt(3), 
                        rs.getInt(4), 
                        rs.getString(5),
                        rs.getBigDecimal(6),
                        rs.getInt(8),
                        rs.getString(9)
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
