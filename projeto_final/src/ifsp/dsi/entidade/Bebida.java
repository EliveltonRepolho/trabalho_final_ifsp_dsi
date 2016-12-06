/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import ifsp.dsi.enums.MontavelTipo;
import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class Bebida extends ProdutoBase {
    
    int tipo, qtd_estoque, qtd_minima;
    double percentual_lucro;
    double valorVenda;

    public Bebida(String nome, BigDecimal valorCusto, int tipo, int qtd_estoque, int qtd_minima, double percentual_lucro) {
        super(nome, valorCusto);
        this.tipo = tipo;
        this.qtd_estoque = qtd_estoque;
        this.qtd_minima = qtd_minima;
        this.percentual_lucro = percentual_lucro;
        calculaValorVenda();
    }

    public Bebida(Long id, String nome, BigDecimal valorCusto, int tipo, int qtd_estoque, int qtd_minima, double percentual_lucro) {
        super(id, nome, valorCusto);
        this.tipo = tipo;
        this.qtd_estoque = qtd_estoque;
        this.qtd_minima = qtd_minima;
        this.percentual_lucro = percentual_lucro;
        calculaValorVenda();
    }
        
    public double getValorVenda() {
        return valorVenda;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    

    public double getPercentual_lucro() {
        return percentual_lucro;
    }

    public void setPercentual_lucro(double percentual_lucro) {
        this.percentual_lucro = percentual_lucro;
    }

    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public int getQtd_minima() {
        return qtd_minima;
    }

    public void setQtd_minima(int qtd_minima) {
        this.qtd_minima = qtd_minima;
    }
    
    
    public void calculaValorVenda(){
        Double vlrCusto = super.getValorCusto().doubleValue();
         this.valorVenda = (((vlrCusto / 100)*this.getPercentual_lucro())+vlrCusto);
    } 
    
    public Bebida getBebida(){
        return this;
    }
    
    @Override
    public String toString() {
        return getNome();
    }
    
}
