/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
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
public abstract class AbstractDAO<T> {
    
    public void salvar(T c) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = getPreparedStatementSalvar(con, c);
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
            fecharRecursos(con, pStat);
        }
    }
    
    protected abstract PreparedStatement getPreparedStatementSalvar(Connection con, T t) throws SQLException;
    
    
    public void atualizar(T c) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = getPreparedStatementAtualizar(con, c);
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
            fecharRecursos(con, pStat);
        }
    }
    
    protected abstract PreparedStatement getPreparedStatementAtualizar(Connection con, T t) throws SQLException;
    
    public void apagar(T c) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = getPreparedStatementApagar(con, c);
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
            fecharRecursos(con, pStat);
        }
    }
    
    protected abstract PreparedStatement getPreparedStatementApagar(Connection con, T t) throws SQLException;
    
    public List<T> listarTodos()throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        List<T> list = new ArrayList<>();
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = getPreparedStatementListarTodos(con);
          
          rs = pStat.executeQuery();
          list = getListaTodos(rs);
          
        }
        finally
        {
            fecharRecursos(con, pStat, rs);
        }
        
        return list;
    }
    
    protected void fecharRecursos(Connection conn, PreparedStatement pStat, ResultSet rs) throws SQLException{
      fecharRecursos(conn, pStat);
      if(rs!= null) rs.close();
    }
    
    protected void fecharRecursos(Connection con, PreparedStatement pStat) throws SQLException {
        
        con.setAutoCommit(true);
        
        if (con != null) con.close();
        if (pStat != null) pStat.close();
    }

    protected abstract List<T> getListaTodos(ResultSet rs)throws SQLException;
    protected abstract PreparedStatement getPreparedStatementListarTodos(Connection con)throws SQLException;
}

