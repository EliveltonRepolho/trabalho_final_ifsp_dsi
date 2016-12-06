/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author repolho
 */
public class Cardapio {
    private long id;
    private String nome;
    private ProdutoBase couvert;
    private List<ProdutoBase> produtos;
    private boolean ativo;

    private boolean selecionado;
    
    public Cardapio(long id, String nome, ProdutoBase couvert, List<ProdutoBase> produtos, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.couvert = couvert;
        this.produtos = produtos;
        this.ativo = ativo;
    }
    
    public Cardapio(long id, String nome, ProdutoBase couvert,boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.couvert = couvert;
        this.produtos = new ArrayList<>();
        this.ativo = ativo;
    }

    public Cardapio(String nome, ProdutoBase couvert, List<ProdutoBase> produtos, boolean ativo) {
        this.nome = nome;
        this.couvert = couvert;
        this.produtos = produtos;
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProdutoBase getCouvert() {
        return couvert;
    }

    public void setCouvert(ProdutoBase couvert) {
        this.couvert = couvert;
    }

    public List<ProdutoBase> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoBase> produtos) {
        this.produtos = produtos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
    
    public void addProdutoBase(ProdutoBase p){
        produtos.add(p);
    }
    
    
    
}
