/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Mesa;
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
public class ClienteDAO extends AbstractDAO<Cliente> implements EntidadeDAO<Cliente>{

    @Override
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Cliente c) throws SQLException {
        String sql = "insert into cliente(id_cliente, nome,telefone) values (seq_cliente.NEXTVAL, ?,?)";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setString(1, c.getNome());
        pStat.setLong(2, c.getTelefone());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Cliente c) throws SQLException {
        String sql = "update cliente set nome = ?, telefone = ? where id_cliente = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setString(1, c.getNome());
        pStat.setLong(2, c.getTelefone());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Cliente c) throws SQLException {
        String sql = "delete from cliente where id_cliente = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
        pStat.setLong(1, c.getId());
            
        return pStat;
    }

    @Override
    protected List<Cliente> getListaTodos(ResultSet rs) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        
        while (rs.next()) {
            
            Cliente c = new Cliente(
                rs.getLong(1), 
                rs.getString(2), 
                rs.getLong(3)
            );
            
            clientes.add(c);
        }
        
        return clientes;
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select id_cliente, nome,telefone from cliente";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
}
