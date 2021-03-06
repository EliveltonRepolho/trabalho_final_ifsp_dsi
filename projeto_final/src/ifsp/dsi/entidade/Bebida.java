/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.math.BigDecimal;

/**
 *
 * @author HP
 */
public class Bebida extends ProdutoBase {
    
    int tipo, qtd_estoque, qtd_minima, id;
    double percentual_lucro;
    double valorVenda;
    boolean selecionado;
    



    


    public Bebida(double percentual_lucro, int tipo, int qtd_estoque, int qtd_minima, String nome, BigDecimal valorCusto) {
        super(nome, valorCusto);
        this.percentual_lucro = percentual_lucro;
        this.tipo = tipo;
        this.qtd_estoque = qtd_estoque;
        this.qtd_minima = qtd_minima;
        calculaValorVenda();
    }

    
    
        public Bebida(long id, double percentual_lucro, int tipo, int qtd_estoque, int qtd_minima, String nome, BigDecimal valorCusto) {
        super(id, nome, valorCusto);
        this.percentual_lucro = percentual_lucro;
        this.tipo = tipo;
        this.qtd_estoque = qtd_estoque;
        this.qtd_minima = qtd_minima;
        calculaValorVenda();
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
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
    
    
    
}