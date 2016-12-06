/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Mesa;
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
        pStat.setTimestamp(3, DateUtil.convertToSQLTimestamp(r.getDataHora()));
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Reserva r) throws SQLException {
        String sql = "update lista_reserva set data_hora = ?, status = ? where id_cliente = ? and numero_mesa = ? and id_reserva = ?";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setTimestamp(1, DateUtil.convertToSQLTimestamp(r.getDataHora()));
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
                    DateUtil.convertToDate(rs.getTimestamp(8)),
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
    
    public List<Reserva> listarTodos(StatusReserva status) throws SQLException{
        
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select c.id_cliente,c.nome,c.telefone, m.numero_mesa,m.qtde_lugares,m.status, l.id_reserva, l.data_hora, l.status "
                   + "from lista_reserva l,cliente c, mesa m "
                   + "where l.id_cliente = c.id_cliente and l.numero_mesa = m.numero_mesa "
                   + "      and l.data_hora >= systimestamp and l.status = ?"
                   + "order by l.data_hora,c.nome";
        
        List<Reserva> list = new ArrayList<>();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql);            
          pStat.setInt(1, status.getStatus());
          
          rs = pStat.executeQuery();
          
          list = getListaTodos(rs);
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return list;
        
    }
    
}
