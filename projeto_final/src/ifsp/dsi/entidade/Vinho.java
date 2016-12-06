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
public class Vinho extends ProdutoBase {
    
    int tipo, qtd_estoque, qtd_minima, safra;
    double percentual_lucro;
    double valorVenda;
    boolean selecionado;
    String tipoUva;
   

    public Vinho(double percentual_lucro, int tipo, int qtd_estoque, int qtd_minima, String nome, BigDecimal valorCusto, int safra, String tipoUva) {
        super(nome, valorCusto);
        this.percentual_lucro = percentual_lucro;
        this.tipo = tipo;
        this.qtd_estoque = qtd_estoque;
        this.qtd_minima = qtd_minima;
        this.safra = safra;
        this.tipoUva = tipoUva;
        calculaValorVenda();
    }

    
    
        public Vinho(long id, double percentual_lucro, int tipo, int qtd_estoque, int qtd_minima, String nome, BigDecimal valorCusto, int safra, String tipoUva) {
        super(id, nome, valorCusto);
        this.percentual_lucro = percentual_lucro;
        this.tipo = tipo;
        this.qtd_estoque = qtd_estoque;
        this.qtd_minima = qtd_minima;
        this.safra = safra;
        this.tipoUva = tipoUva;
        calculaValorVenda();
    }

    public int getSafra() {
        return safra;
    }

    public void setSafra(int safra) {
        this.safra = safra;
    }

    public String getTipoUva() {
        return tipoUva;
    }

    public void setTipoUva(String tipoUva) {
        this.tipoUva = tipoUva;
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
    
    public Vinho getBebida(){
        return this;
    }
    
    
    
}
