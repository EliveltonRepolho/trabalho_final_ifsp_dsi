/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author repolho
 */
public class ProdutoBase implements Serializable{
    private Long id;
    private String nome;    
    private BigDecimal valorCusto;

    public ProdutoBase(String nome, BigDecimal valorCusto) {
        this.nome = nome;
        this.valorCusto = valorCusto;
    }

    public ProdutoBase(Long id, String nome, BigDecimal valorCusto) {
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
    
    
}
