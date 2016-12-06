/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import ifsp.dsi.enums.MontavelTipo;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author repolho
 */
public class Montavel extends ProdutoBase{
    
    private MontavelTipo tipo;
    private BigDecimal percentualLucro;

    public Montavel(String nome, BigDecimal valorCusto, MontavelTipo tipo, BigDecimal percentualLucro) {
        super(nome, valorCusto);
        this.tipo = tipo;
        this.percentualLucro = percentualLucro;
    }

    public Montavel(Long id, String nome, BigDecimal valorCusto, MontavelTipo tipo, BigDecimal percentualLucro) {
        super(id, nome, valorCusto);
        this.tipo = tipo;
        this.percentualLucro = percentualLucro;
    }

    public MontavelTipo getTipo() {
        return tipo;
    }

    public void setTipo(MontavelTipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPercentualLucro() {
        return percentualLucro;
    }

    public void setPercentualLucro(BigDecimal percentualLucro) {
        this.percentualLucro = percentualLucro;
    }
    
    
    public BigDecimal getValorVenda(){
        
        BigDecimal bigDecimal = getValorCusto().multiply(getPercentualLucro()).divide(new BigDecimal(100));
        
        bigDecimal.add(getValorCusto());
                
        return bigDecimal;
    }

}
