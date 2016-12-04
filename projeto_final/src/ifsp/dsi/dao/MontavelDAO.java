/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Montavel;
import ifsp.dsi.entidade.MontavelIngrediente;
import ifsp.dsi.entidade.MontavelIngredientes;
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
public class MontavelDAO{


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

    protected PreparedStatement getPreparedStatementApagar(Connection con, Funcionario f) throws SQLException {
         String sql = "delete funcionario where cpf_funcionario = ?";
         
         PreparedStatement pStat = con.prepareStatement(sql);
         pStat.setLong(1, f.getCpf());
            
        return pStat;
    }

    protected PreparedStatement getPreparedStatementListarTodos(Connection con) throws SQLException {
        String sql = "select cpf_funcionario, nome,telefone, perfil, login_usuario,senha "
                   + "from funcionario "
                   + "order by nome";
        
        PreparedStatement pStat = con.prepareStatement(sql);        
            
        return pStat;
    }
    
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

    public void salvar(MontavelIngredientes montavelIngredientes) throws SQLException {
        Connection con = null;
        PreparedStatement pStatProduto = null;
        PreparedStatement pStatMontavel = null;
        PreparedStatement pStatItens = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
    
        String sqlProduto = "insert into produto(id_produto, nome,percentual_lucro)"
                + "values(seq_produto.NEXTVAL,?,?)";
        
        String sqlMontavel = "insert into montavel(id_produto,tipo)"
                + "values(seq_produto.CURRVAL,?)";
        
        String sqlItens = "insert into item_montavel(id_produto,id_ingrediente,qtde)"
                + "values(seq_produto.CURRVAL,?,?)";
        
        Montavel montavel = montavelIngredientes.getMontavelIngredientes().get(0).getMontavel();
        
        pStatProduto = con.prepareStatement(sqlProduto);
        pStatProduto.setString(1, montavel.getNome());
        pStatProduto.setBigDecimal(2, montavel.getPercentualLucro());
        
        pStatMontavel = con.prepareStatement(sqlMontavel);
        pStatMontavel.setInt(1, montavel.getTipo().getTipo());
            
        pStatItens = con.prepareStatement(sqlItens);
        
        List<MontavelIngrediente> lista = montavelIngredientes.getMontavelIngredientes();
        
        for (MontavelIngrediente item : lista) {
            pStatItens.setLong(1, item.getIngrediente().getId());
            pStatItens.setDouble(2, item.getQtde());
            pStatItens.addBatch();
        }
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatProduto.executeUpdate();
            pStatMontavel.executeUpdate();
            pStatItens.executeBatch();
            
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
            EntidadeDAO.fecharRecursos(con, pStatMontavel);
            EntidadeDAO.fecharRecursos(con, pStatItens);
        }
    }
    
    public void atualizar(MontavelIngredientes m) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = getPreparedStatementAtualizar(con, f);
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

    public void apagar(MontavelIngredientes m) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = getPreparedStatementApagar(con, f);
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

    public List<MontavelIngredientes> listarTodos() throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        List<MontavelIngrediente> list = new ArrayList<>();
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = getPreparedStatementListarTodos(con);
          
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
