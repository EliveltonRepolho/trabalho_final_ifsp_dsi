/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Funcionario;
import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.Perfil;
import ifsp.dsi.enums.StatusMesa;
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
public class MesaDAO extends AbstractDAO<Mesa> implements EntidadeDAO<Mesa>{

    public boolean existe(int numeroMesa) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select NUMERO_MESA from mesa "
                    +" where NUMERO_MESA = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setInt(1, numeroMesa);
          
          rs = pStat.executeQuery();
          
          if (!rs.next()){
              return false;
          }        
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        return true;
    }
    
    @Override
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Mesa m) throws SQLException {
        String sql = "insert into mesa(NUMERO_MESA,QTDE_LUGARES) values (seq_mesa.NEXTVAL, ?)";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setInt(1, m.getQuantidadeLugares());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Mesa m) throws SQLException {
        String sql = "update mesa set QTDE_LUGARES = ? where NUMERO_MESA = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setInt(1, m.getQuantidadeLugares());
        pStat.setInt(2, m.getNumero());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Mesa m) throws SQLException {
        String sql = "delete from mesa where NUMERO_MESA = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
        pStat.setInt(1, m.getNumero());
            
        return pStat;
    }

    @Override
    protected List<Mesa> getListaTodos(ResultSet rs) throws SQLException {
        List<Mesa> mesas = new ArrayList<>();
        
        while (rs.next()) {
            int numero = rs.getInt(1);
            int qtde = rs.getInt(2);
            StatusMesa status = StatusMesa.getByStatus(rs.getInt(3));
            
            Mesa m = new Mesa(
                numero, 
                qtde, 
                status
            );
            
            mesas.add(m);
        }
        
        return mesas;
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select NUMERO_MESA,QTDE_LUGARES,STATUS from mesa";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
}
