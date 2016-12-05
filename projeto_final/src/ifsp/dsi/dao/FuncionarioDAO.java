/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Funcionario;
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
public class FuncionarioDAO extends AbstractDAO<Funcionario> implements EntidadeDAO<Funcionario>{

    public boolean autenticar(String usuario, String senha) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select CPF_FUNCIONARIO from funcionario "
                    +" where LOGIN_USUARIO = ? and SENHA = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setString(1, usuario);
          pStat.setString(2, senha);
          
          rs = pStat.executeQuery();
          
          if (!rs.next()){
              return false;
          }        
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        
        return true;
    }
    
    public Funcionario getByLogin(String usuario) throws SQLException{
        
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select CPF_FUNCIONARIO,NOME,TELEFONE,PERFIL,LOGIN_USUARIO,SENHA from funcionario "
                    +" where LOGIN_USUARIO = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        Funcionario f = null;
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setString(1, usuario);
          
          rs = pStat.executeQuery();
          
          if (rs.next()){
            f = new Funcionario(
                      rs.getLong(1), 
                      rs.getString(2), 
                      rs.getLong(3),
                      rs.getInt(4),
                      rs.getString(5), 
                      rs.getString(6)
            );
              
          }        
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        
        return f;
        
    }

    public boolean existe(long cpf) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select CPF_FUNCIONARIO from funcionario "
                    +" where CPF_FUNCIONARIO = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, cpf);
          
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
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Funcionario f) throws SQLException {
        String sql = "insert into funcionario(cpf_funcionario, nome,telefone, perfil, login_usuario,senha)"
                + "values(?,?,?,?,?,?)";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setLong(1, f.getCpf());
        pStat.setString(2, f.getNome());
        pStat.setLong(3, f.getTelefone());
        pStat.setInt(4, f.getPerfil());
        pStat.setString(5, f.getLoginUsuario());
        pStat.setString(6, f.getSenha());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Funcionario f) throws SQLException {
        String sql = "update funcionario set nome = ?, telefone = ?, perfil = ?, login_usuario = ?, senha = ? "
                   + "where cpf_funcionario = ?";
                 
        PreparedStatement pStat = con.prepareStatement(sql);
        pStat.setString(1, f.getNome());
        pStat.setLong(2, f.getTelefone());
        pStat.setInt(3, f.getPerfil());
        pStat.setString(4, f.getLoginUsuario());
        pStat.setString(5, f.getSenha());
        pStat.setLong(6, f.getCpf());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Funcionario f) throws SQLException {
         String sql = "delete funcionario where cpf_funcionario = ?";
         
         PreparedStatement pStat = con.prepareStatement(sql);
         pStat.setLong(1, f.getCpf());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select cpf_funcionario, nome,telefone, perfil, login_usuario,senha "
                   + "from funcionario "
                   + "order by nome";
        
        PreparedStatement pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
    @Override
    protected List<Funcionario> getListaTodos(ResultSet rs) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        while (rs.next()) {
            long cpf = rs.getLong(1);
            String nome = rs.getString(2);
            long telefones = rs.getLong(3);
            int perfil = rs.getInt(4);
            String loginUsuario = rs.getString(5);
            String senha = rs.getString(6);
            
            
            Funcionario f = new Funcionario(
                cpf, 
                nome, 
                telefones, 
                perfil,
                loginUsuario, 
                senha
            );
            
            funcionarios.add(f);
        }
        
        return funcionarios;
    }    
    
}
