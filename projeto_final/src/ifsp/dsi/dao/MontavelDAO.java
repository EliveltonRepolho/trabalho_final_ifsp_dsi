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
import ifsp.dsi.enums.MontavelTipo;
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
                
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatProduto = con.prepareStatement(sqlProduto);
            pStatProduto.setString(1, montavel.getNome());
            pStatProduto.setBigDecimal(2, montavel.getPercentualLucro());

            pStatProduto.executeUpdate();
            
            pStatMontavel = con.prepareStatement(sqlMontavel);
            pStatMontavel.setInt(1, montavel.getTipo().getTipo());

            pStatMontavel.executeUpdate();
            
            pStatItens = con.prepareStatement(sqlItens);

            List<MontavelIngrediente> lista = montavelIngredientes.getMontavelIngredientes();

            for (MontavelIngrediente item : lista) {
                pStatItens.setLong(1, item.getIngrediente().getId());
                pStatItens.setDouble(2, item.getQtde());
                pStatItens.addBatch();
            }
            
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
    
    private boolean existe(Montavel m, Ingrediente i) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select 1 from item_montavel "
                    +" where id_produto = ? and ID_INGREDIENTE = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, m.getId());
          pStat.setLong(2, i.getId());
          
          rs = pStat.executeQuery();
          
          if (!rs.next()){
              return false;
          }        
        }finally{
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return true;
    }
    
    public void atualizar(MontavelIngredientes montavelIngredientes) throws SQLException {
        Connection con = null;
        PreparedStatement pStatProduto = null;
        
        PreparedStatement pStatItemInsert = null;
        PreparedStatement pStatItemUpdate = null;
        PreparedStatement pStatItemDelete = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sqlUpdateProduto = "update produto set nome = ?, percentual_lucro = ? "
                                + "where id_produto = ?";
        
        String sqlItemInsert = "insert into item_montavel(id_produto,id_ingrediente,qtde)"
                             + "values(?,?,?)";
        
        String sqlItemUpdate = "update item_montavel set qtde = ? "
                             + "where id_produto = ? and id_ingrediente = ? ";
                
        String sqlItemDelete = "delete from item_montavel "
                              +" where id_produto = ? and ID_INGREDIENTE = ?";
                 
        boolean temDelete = false, temInsert = false, temUpdate = false;
        
        Montavel montavel = montavelIngredientes.getMontavelIngredientes().get(0).getMontavel();
        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatProduto = con.prepareStatement(sqlUpdateProduto);
            pStatProduto.setString(1, montavel.getNome());
            pStatProduto.setBigDecimal(2, montavel.getPercentualLucro());
            pStatProduto.setLong(3, montavel.getId());
            
            pStatProduto.executeUpdate();

            List<MontavelIngrediente> lista = montavelIngredientes.getMontavelIngredientes();

            pStatItemInsert = con.prepareStatement(sqlItemInsert);
            pStatItemUpdate = con.prepareStatement(sqlItemUpdate);
            pStatItemDelete = con.prepareStatement(sqlItemDelete);
            
            for (MontavelIngrediente item : lista) {
                
                Ingrediente ingrediente = item.getIngrediente();
                
                if(ingrediente.isSelecionado()){
                    
                    pStatItemDelete.setLong(1, montavel.getId());
                    pStatItemDelete.setLong(2, ingrediente.getId());
                    pStatItemDelete.addBatch();
                    
                    temDelete = true;
                    
                }else if(!existe(montavel, ingrediente)){
                    
                    pStatItemInsert.setLong(1, montavel.getId());
                    pStatItemInsert.setLong(2, ingrediente.getId());
                    pStatItemInsert.setDouble(3, item.getQtde());
                    pStatItemInsert.addBatch();
                    
                    temInsert = true;
                    
                }else{
                    
                    pStatItemUpdate.setDouble(1, item.getQtde());
                    pStatItemUpdate.setLong(2, montavel.getId());
                    pStatItemUpdate.setLong(3, ingrediente.getId());
                    pStatItemUpdate.addBatch();
                    
                    temUpdate = true;
                    
                }
            }
            
            if(temInsert)
                pStatItemInsert.executeBatch();
            
            if(temUpdate)
                pStatItemUpdate.executeBatch();
            
            if(temDelete)
                pStatItemDelete.executeBatch();
            
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
            EntidadeDAO.fecharRecursos(con, pStatItemInsert);
            EntidadeDAO.fecharRecursos(con, pStatItemUpdate);
            EntidadeDAO.fecharRecursos(con, pStatItemDelete);
            
        }
    }

    public void apagar(Montavel m) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql = "delete produto where id_produto = ?";
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStat = con.prepareStatement(sql);
            pStat.setLong(1, m.getId());
            
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

    public List<Montavel> listarByTipo(MontavelTipo tipo) throws SQLException {
        
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select p.id_produto " //1
                    +"      ,p.nome " //2
                    +"      ,(select sum(i_ing.valor_custo * i_it.qtde) "
                    +"          from item_montavel i_it, "
                    +"               ingrediente   i_ing "
                    +"          where i_it.ID_INGREDIENTE = i_ing.ID_INGREDIENTE "
                    +"                and i_it.ID_PRODUTO = p.ID_PRODUTO "
                    +"      ) as custo_produto " //3
                    +"     ,m.tipo " //4
                    +"     ,p.percentual_lucro " //5
                    +"from produto  p, " 
                    +"     montavel m "
                    +"where p.ID_PRODUTO = m.ID_PRODUTO"
                    +"      and m.tipo = ?";
        
        List<Montavel> list = new ArrayList<>();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql);
          pStat.setInt(1, tipo.getTipo());
          
          rs = pStat.executeQuery();
          
          while (rs.next()) {
                Montavel m = new Montavel(
                        rs.getLong(1), 
                        rs.getString(2), 
                        rs.getBigDecimal(3), 
                        MontavelTipo.getByTipo(rs.getInt(4)), 
                        rs.getBigDecimal(5)
                );
                
                list.add(m);
         }
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return list;
    }
    
    public MontavelIngredientes listarByMontavel(Montavel montavel) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select p.id_produto, " //1
                   + "       p.nome, "       //2
                   + "       (select sum(i_ing.valor_custo * i_it.qtde) "
                   + "              from item_montavel i_it, " 
                   + "                   ingrediente   i_ing" 
                   + "              where i_it.ID_INGREDIENTE = i_ing.ID_INGREDIENTE "
                   + "                    and i_it.ID_PRODUTO = it.ID_PRODUTO "
                   + "       ) as custo_produto," //3
                   + "       p.percentual_lucro, " //4
                   + "       m.tipo," //5
                   + "       it.id_ingrediente," //6
                   + "       it.qtde, " //7
                   + "       ing.nome," //8
                   + "       ing.valor_custo," //9
                   + "       ing.qtde_estoque," //10
                   + "       ing.qtde_min_estoque," //11
                   + "       ing.flag_prato," //12
                   + "       ing.flag_bebida " //13
                   + "from produto       p,"
                   + "     montavel      m, "
                   + "     item_montavel it, "
                   + "     ingrediente   ing "
                   + "where     it.id_produto     = p.id_produto "
                   + "      and it.id_produto     = m.id_produto "
                   + "      and it.ID_INGREDIENTE = ing.ID_INGREDIENTE "
                   + "      and p.id_produto = ?"
                   + "order by p.nome";
        
        MontavelIngredientes list = new MontavelIngredientes();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, montavel.getId());
          
          rs = pStat.executeQuery();
          
          while (rs.next()) {
                Montavel m = new Montavel(
                        rs.getLong(1), 
                        rs.getString(2), 
                        rs.getBigDecimal(3), 
                        MontavelTipo.getByTipo(rs.getInt(5)), 
                        rs.getBigDecimal(4)
                );
                
                Ingrediente i = new Ingrediente(
                        rs.getLong(6), 
                        rs.getString(8), 
                        rs.getBigDecimal(9), 
                        rs.getDouble(10), 
                        rs.getDouble(11), 
                        rs.getString(12).equalsIgnoreCase("Y"), 
                        rs.getString(13).equalsIgnoreCase("Y")
                );
                
                double qtde = rs.getDouble(7);
                
                MontavelIngrediente mI = new MontavelIngrediente(
                        m, 
                        i, 
                        qtde
                );
                
                list.addMontavelIngrediente(mI);
         }
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStat, rs);
        }
        
        return list;
    }
        
}
