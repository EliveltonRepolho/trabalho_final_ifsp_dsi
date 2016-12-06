/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import ifsp.dsi.bd.ConexaoBD;
import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.Ingrediente;
import ifsp.dsi.entidade.Montavel;
import ifsp.dsi.entidade.MontavelIngrediente;
import ifsp.dsi.entidade.MontavelIngredientes;
import ifsp.dsi.entidade.ProdutoBase;
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
public class CardapioDAO{

    public void salvar(Cardapio cardapio) throws SQLException {
        Connection con = null;
        PreparedStatement pStatCardapio = null;
        PreparedStatement pStatItens = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
    
        String sqlCardapio = "insert into cardapio(id_cardapio, descricao,couvert)"
                + "values(seq_cardapio.NEXTVAL,?,?)";
          
        String sqlItens = "insert into cardapio_produto(id_cardapio,id_produto)"
                + "values(seq_cardapio.CURRVAL,?)";
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatCardapio = con.prepareStatement(sqlCardapio);
            pStatCardapio.setString(1, cardapio.getNome());
            pStatCardapio.setLong(2, cardapio.getCouvert().getId());

            pStatCardapio.executeUpdate();
            
            pStatItens = con.prepareStatement(sqlItens);

            List<ProdutoBase> lista = cardapio.getProdutos();

            for (ProdutoBase item : lista) {
                if(item.isSelecionado()){
                    pStatItens.setLong(1, item.getId());
                    pStatItens.addBatch();
                }                
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
            EntidadeDAO.fecharRecursos(con, pStatCardapio);
            EntidadeDAO.fecharRecursos(con, pStatItens);
        }
    }
    
    private boolean existe(Cardapio c, ProdutoBase i) throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        String sql = "select 1 from cardapio_produto "
                    +" where id_cardapio = ? and id_produto = ?";
        
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        try{
          con = conexaoBD.getConnection();         
        
          pStat = con.prepareStatement(sql);
          pStat.setLong(1, c.getId());
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
    
    public void atualizar(Cardapio cardapio) throws SQLException {
        Connection con = null;
        PreparedStatement pStatCardapio = null;
        
        PreparedStatement pStatItemInsert = null;
        PreparedStatement pStatItemDelete = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sqlUpdateProduto = "update cardapio set descricao = ?, couvert = ?"
                                + "where id_cardapio = ?";
        
        String sqlItemInsert = "insert into cardapio_produto(id_cardapio,id_produto)"
                             + "values(?,?)";

        String sqlItemDelete = "delete from cardapio_produto "
                              +" where id_cardapio = ? and id_produto = ?";
                 
        boolean temDelete = false, temInsert = false;
        
        
        try {
            con = conexaoBD.getConnection();
            con.setAutoCommit(false);
            
            pStatCardapio = con.prepareStatement(sqlUpdateProduto);
            pStatCardapio.setString(1, cardapio.getNome());
            pStatCardapio.setLong(2, cardapio.getCouvert().getId());
            pStatCardapio.setLong(3, cardapio.getId());
            
            pStatCardapio.executeUpdate();

            List<ProdutoBase> lista = cardapio.getProdutos();

            pStatItemInsert = con.prepareStatement(sqlItemInsert);
            pStatItemDelete = con.prepareStatement(sqlItemDelete);
            
            for (ProdutoBase item : lista) {
                
                if(item.isSelecionado() && !existe(cardapio, item)){
                    
                    pStatItemInsert.setLong(1, cardapio.getId());
                    pStatItemInsert.setLong(2, item.getId());
                    pStatItemInsert.addBatch();
                    
                    temDelete = true;
                    
                }else if(!item.isSelecionado() && existe(cardapio, item)){
                    
                    pStatItemDelete.setLong(1, cardapio.getId());
                    pStatItemDelete.setLong(2, item.getId());
                    pStatItemDelete.addBatch();
                    
                    temInsert = true;
                    
                }
            }
            
            if(temInsert)
                pStatItemInsert.executeBatch();
            
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
            EntidadeDAO.fecharRecursos(con, pStatCardapio);
            EntidadeDAO.fecharRecursos(con, pStatItemInsert);
            EntidadeDAO.fecharRecursos(con, pStatItemDelete);
            
        }
    }

    public void apagar(Cardapio m) throws SQLException {
        Connection con = null;
        PreparedStatement pStat = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();        
        
        String sql = "delete cardapio where id_cardapio = ?";
        
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

    public void ativar(Cardapio cardapio) throws SQLException {
        Connection con = null;
        PreparedStatement pStatDesativa = null;
        PreparedStatement pStatAtiva = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sqlDesativa = "update cardapio set ativo_hoje = 'N'";
        
        String sqlAtiva = "update cardapio set ativo_hoje = 'Y' where id_cardapio = ?";
        
        MontavelIngredientes list = new MontavelIngredientes();
        
        try
        {
          con = conexaoBD.getConnection();
          
          pStatDesativa = con.prepareStatement(sqlDesativa);
          
          pStatDesativa.executeUpdate();
          
          pStatAtiva = con.prepareStatement(sqlAtiva);
          pStatAtiva.setLong(1, cardapio.getId());
          
          pStatAtiva.executeUpdate();
          
        }
        finally
        {
            EntidadeDAO.fecharRecursos(con, pStatDesativa);
            EntidadeDAO.fecharRecursos(con, pStatAtiva);
        }
        
    }
        
    public List<Cardapio> listarTodos()throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select c.id_cardapio," + //1
                     "       c.descricao, " + //2
                     "       c.ativo_hoje," + //3
                     "       c.couvert," + //4
                     "       p_couvert.nome as couvert_nome,"+ //5
                     "       cp.id_produto," + //6
                     "       p.nome " + //7
                     "from cardapio         c, " +
                     "     cardapio_produto cp," +
                     "     produto          p_couvert,"+
                     "     produto          p " +
                     "where c.id_cardapio = cp.id_cardapio " +
                     "      and cp.ID_PRODUTO = p.ID_PRODUTO " +
                     "      and p_couvert.ID_PRODUTO  = c.couvert " +
                     "order by c.id_cardapio, cp.id_produto";
        
        List<Cardapio> list = new ArrayList<>();
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
    
    private List<Cardapio> getListaTodos(ResultSet rs) throws SQLException {
        List<Cardapio> cardapios = new ArrayList<>();
        
        long ultimo;
        
        Cardapio cardapio = null;
        boolean adicionar = false;
        
        while (rs.next()) {
            
            ultimo = rs.getLong(1);
            
            if(cardapio == null || cardapio.getId() != ultimo){
                ProdutoBase couvert = new ProdutoBase(rs.getLong(4), rs.getString(5));
                cardapio = new Cardapio(rs.getLong(1), rs.getString(2), couvert,rs.getString(3).equals("Y"));
                
                adicionar = true;
            }
            ProdutoBase p = new ProdutoBase(
                rs.getLong(6), 
                rs.getString(7)
            );
            
            cardapio.addProdutoBase(p);
            
            if(adicionar){
                cardapios.add(cardapio);
                adicionar = false;
            }
                
            
        }
        
        return cardapios;
    }
    
    public Cardapio getAtivo()throws SQLException{
        Connection con = null;
        PreparedStatement pStat = null;
        ResultSet rs = null;
        ConexaoBD conexaoBD = ConexaoBD.getInstance();
        
        String sql = "select c.id_cardapio," + //1
                     "       c.descricao, " + //2
                     "       c.ativo_hoje," + //3
                     "       c.couvert," + //4
                     "       couvert.nome as couvert_nome,"+ //5
                     "       cp.id_produto," + //6
                     "       p.nome " + //7
                     "from cardapio         c, " +
                     "     cardapio_produto cp," +
                     "     produto          couvert,"+
                     "     produto          p " +
                     "where c.id_cardapio = cp.id_cardapio " +
                     "      and cp.ID_PRODUTO = p.ID_PRODUTO " +
                     "      and couvert.ID_PRODUTO  = c.couvert " +
                     "      and c.ativo_hoje = 'Y'" +
                     "order by c.id_cardapio, cp.id_produto";
        
        List<Cardapio> list = new ArrayList<>();
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
        
        if(list.isEmpty())
            return null;
        
        return list.get(0);
    }
}
