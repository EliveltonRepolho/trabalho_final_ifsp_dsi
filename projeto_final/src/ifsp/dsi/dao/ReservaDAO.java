/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Funcionario;
import ifsp.dsi.entidade.Mesa;
import ifsp.dsi.entidade.Perfil;
import ifsp.dsi.entidade.Reserva;
import ifsp.dsi.enums.StatusMesa;
import ifsp.dsi.enums.StatusReserva;
import ifsp.dsi.util.DateUtil;
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
public class ReservaDAO extends AbstractDAO<Reserva> implements EntidadeDAO<Reserva>{

    @Override
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Reserva r) throws SQLException {
        String sql = "insert into lista_reserva(id_cliente, numero_mesa, id_reserva, data_hora) values (?,?,seq_reserva.NEXTVAL, ?)";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setLong(1, r.getCliente().getId());
        pStat.setInt(2, r.getMesa().getNumero());
        pStat.setDate(3, DateUtil.convertToSQL(r.getDataHora()));
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Reserva r) throws SQLException {
        String sql = "update lista_reserva set data_hora = ?, status = ? where id_cliente = ? and numero_mesa = ? and id_reserva = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setDate(1, DateUtil.convertToSQL(r.getDataHora()));
        pStat.setInt(2, r.getStatus().getStatus());
        pStat.setLong(3, r.getCliente().getId());
        pStat.setInt(4, r.getMesa().getNumero());
        pStat.setLong(5, r.getId());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Reserva r) throws SQLException {
        String sql = "delete from lista_reserva where id_cliente = ? and numero_mesa = ? and id_reserva = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
        pStat.setLong(1, r.getCliente().getId());
        pStat.setInt(2, r.getMesa().getNumero());
        pStat.setLong(3, r.getId());
            
        return pStat;
    }

    @Override
    protected List<Reserva> getListaTodos(ResultSet rs) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        
        while (rs.next()) {
            
            Cliente c = new Cliente(rs.getLong(1), rs.getString(2), rs.getLong(3));
            Mesa m = new Mesa(rs.getInt(4), rs.getInt(5), StatusMesa.getByStatus(rs.getInt(6)));
            
            Reserva r = new Reserva(
                    rs.getLong(7), 
                    m, 
                    c, 
                    DateUtil.convertToDate(rs.getDate(8)),
                    StatusReserva.getByStatus(rs.getInt(9))
            );
            
            reservas.add(r);
        }
        
        return reservas;
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select c.id_cliente,c.nome,c.telefone, m.numero_mesa,m.qtde_lugares,m.status, l.id_reserva, l.data_hora, l.status "
                   + "from lista_reserva l,cliente c, mesa m "
                   + "where l.id_cliente = c.id_cliente and l.numero_mesa = m.numero_mesa ";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
}
