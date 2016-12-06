/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import static ifsp.dsi.dao.AbstractDAO.fecharRecursos;
import ifsp.dsi.entidade.Cliente;
import ifsp.dsi.entidade.Funcionario;
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
public class ClienteDAO{

    public Cliente salvar(Cliente c) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql = "insert into cliente(id_cliente, nome,telefone) values (seq_cliente.NEXTVAL, ?,?)";
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = con.prepareStatement(sql,new String[]{"id_cliente"});
            
            pStat.setString(1, c.getNome());
            pStat.setLong(2, c.getTelefone());
            
            pStat.executeUpdate();
            
            ResultSet rs = pStat.getGeneratedKeys();
            
            long key = 0;
            if (rs != null && rs.next()) {
                key = rs.getLong(1);
            }
            
            c.setId(key);

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
        
        return c;
    }
    
    public void atualizar(Cliente c) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql = "update cliente set nome = ?, telefone = ? where id_cliente = ?";
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = con.prepareStatement(sql);
            pStat.setString(1, c.getNome());
            pStat.setLong(2, c.getTelefone());
        
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
    
    public void apagar(Cliente c) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql = "delete from cliente where id_cliente = ?";
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = con.prepareStatement(sql);        
            pStat.setLong(1, c.getId());
            
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
    

    public List<Cliente> listarTodos()throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select id_cliente, nome,telefone from cliente";
        
        List<Cliente> list = new ArrayList<>();
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
    
    private List<Cliente> getListaTodos(ResultSet rs) throws SQLException {
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

    
    public Cliente getByTelefone(long telefeone) throws SQLException{
        
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select id_cliente, nome,telefone from cliente "
                    +" where telefone = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        Cliente c = null;
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, telefeone);
          
          rs = pStat.executeQuery();
          
          if (rs.next()){
            c = new Cliente(
                      rs.getLong(1), 
                      rs.getString(2), 
                      rs.getLong(3)
            );
              
          }        
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        
        return c;
        
    }
    
}
