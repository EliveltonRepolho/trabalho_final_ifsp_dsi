/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Funcionario;
import ifsp.dsi.entidade.Perfil;
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
        String sql = "select CPF_FUNCIONARIO,NOME,LOGIN_USUARIO,SENHA from funcionario "
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

            List<Long> telefones = getTelefones(rs.getLong(1));
            List<Perfil> perfis = getPerfis(usuario);
            
            f = new Funcionario(
                      rs.getLong(1), 
                      rs.getString(2), 
                      telefones, 
                      rs.getString(3), 
                      rs.getString(4),
                      perfis
            );
              
          }        
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        
        return f;
        
    }

    private List<Long> getTelefones(long cpf) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select CPF_FUNCIONARIO,telefone from funcionario_telefone "
                    +" where CPF_FUNCIONARIO = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        List<Long> telefones = new ArrayList<>();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, cpf);
          
          rs = pStat.executeQuery();
          
          while(rs.next()){
              telefones.add(rs.getLong(2));
          }        
          
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        
        return telefones;
    }
    
    private List<Perfil> getPerfis(String usuario) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        
        String sql = "select f.login_usuario,f.role_perfil,p.descricao from funcionario_perfil f, perfil p "
                    +" where f.ROLE_PERFIL = p.ROLE_PERFIL"
                    +" and login_usuario = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        List<Perfil> perfis = new ArrayList<>();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setString(1, usuario);
          
          rs = pStat.executeQuery();
          
          while(rs.next()){
              perfis.add(
                      new Perfil(rs.getString(2),rs.getString(3))
              );
          }        
          
        }finally{
            fecharRecursos(con, pStat, rs);
        }
        
        
        return perfis;
    }

    @Override
    protected PreparedStatement getPreparedStatementSalvar(Connection con, Funcionario f) throws SQLException {
        String sql = "insert into  values (?,?)";
        PreparedStatement pStat = null;
        
        pStat = con.prepareStatement(sql);
        pStat.setLong(1, f.getCpf());
            
        return pStat;
    }

    @Override
    protected PreparedStatement getPreparedStatementAtualizar(Connection con, Funcionario f) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement getPreparedStatementApagar(Connection con, Funcionario f) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected List<Funcionario> getListaTodos(ResultSet rs) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        while (rs.next()) {
            long cpf = rs.getLong(1);
            String nome = rs.getString(2);
            List<Long> telefones = getTelefones(cpf);
            String loginUsuario = rs.getString(3);
            String senha = rs.getString(4);
            List<Perfil> perfis = getPerfis(loginUsuario);
            
            Funcionario f = new Funcionario(
                cpf, 
                nome, 
                telefones, 
                loginUsuario, 
                senha,
                perfis
            );
            
            funcionarios.add(f);
        }
        
        return funcionarios;
    }    
    
}
