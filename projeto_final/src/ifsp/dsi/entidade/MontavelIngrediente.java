/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

/**
 *
 * @author repolho
 */
public class MontavelIngrediente{
    private Montavel montavel;
    private Ingrediente ingrediente;
    private double qtde;

    public MontavelIngrediente(Montavel montavel, Ingrediente ingrediente, double qtde) {
        this.montavel = montavel;
        this.ingrediente = ingrediente;
        this.qtde = qtde;
    }

    public Montavel getMontavel() {
        return montavel;
    }

    public void setMontavel(Montavel montavel) {
        this.montavel = montavel;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }
    
    
}
