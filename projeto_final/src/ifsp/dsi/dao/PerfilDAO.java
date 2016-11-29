/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

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
public class PerfilDAO extends AbstractDAO<Perfil> implements EntidadeDAO<Perfil>{

    @Override
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Perfil p) throws SQLException {
        String sql = "insert into perfil(role_perfil,descricao) values (?,?)";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setString(1, p.getRole());
        pStat.setString(2, p.getDescricao());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Perfil p) throws SQLException {
        String sql = "update perfil set descricao = ? where role_perfil = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setString(1, p.getRole());
        pStat.setString(2, p.getDescricao());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Perfil p) throws SQLException {
        String sql = "delete from perfil where role_perfil = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
        pStat.setString(1, p.getRole());
            
        return pStat;
    }

    @Override
    protected List<Perfil> getListaTodos(ResultSet rs) throws SQLException {
        List<Perfil> mesas = new ArrayList<>();
        
        while (rs.next()) {
            Perfil p = new Perfil(
                rs.getString(1), 
                rs.getString(2)
            );
            
            mesas.add(p);
        }
        
        return mesas;
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select role_perfil,descricao from perfil";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
}
