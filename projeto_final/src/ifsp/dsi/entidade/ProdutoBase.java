/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author repolho
 */
public class ProdutoBase implements Serializable{
    private Long id;
    private String nome;    
    private BigDecimal valorCusto;

    private boolean selecionado;
    
    public ProdutoBase(String nome, BigDecimal valorCusto) {
        this.nome = nome;
        this.valorCusto = valorCusto;
    }

    public ProdutoBase(Long id, String nome, BigDecimal valorCusto) {
        this.id = id;
        this.nome = nome;
        this.valorCusto = valorCusto;
    }
    
    public ProdutoBase(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.valorCusto = valorCusto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoBase other = (ProdutoBase) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
