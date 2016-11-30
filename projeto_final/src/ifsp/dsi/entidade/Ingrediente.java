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
public class Ingrediente extends ProdutoBase implements Serializable{

    private double qtdeEstoque;
    private double qtdeMinima;
    private boolean prato;
    private boolean bebida;
    
    public Ingrediente(String nome, BigDecimal valorCusto, double qtdeEstoque, double qtdeMinima, boolean prato, boolean bebida) {
        super(nome, valorCusto);
        this.prato = prato;
        this.bebida = bebida;
        this.qtdeEstoque = qtdeEstoque;
        this.qtdeMinima = qtdeMinima;
    }

    public Ingrediente(Long id, String nome, BigDecimal valorCusto, double qtdeEstoque, double qtdeMinima, boolean prato, boolean bebida) {
        super(id, nome, valorCusto);
        this.prato = prato;
        this.bebida = bebida;
        this.qtdeEstoque = qtdeEstoque;
        this.qtdeMinima = qtdeMinima;
    }

    public boolean isPresentPrato() {
        return prato;
    }

    public void setPrato(boolean prato) {
        this.prato = prato;
    }

    public boolean isPresentBebida() {
        return bebida;
    }

    public void setBebida(boolean bebida) {
        this.bebida = bebida;
    }

    public double getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(double qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public double getQtdeMinima() {
        return qtdeMinima;
    }

    public void setQtdeMinima(double qtdeMinima) {
        this.qtdeMinima = qtdeMinima;
    }

    
}
