/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author repolho
 */
public class MontavelIngredientes implements Serializable{
    
    private List<MontavelIngrediente> montavelIngredientes;

    public MontavelIngredientes(List<MontavelIngrediente> montavelIngredientes) {
        this.montavelIngredientes = montavelIngredientes;
    }

    public MontavelIngredientes() {
        montavelIngredientes = new ArrayList<>();
    }
    
    public void addMontavelIngrediente(MontavelIngrediente m){
        montavelIngredientes.add(m);
    }

    public List<MontavelIngrediente> getMontavelIngredientes() {
        return montavelIngredientes;
    }
    
    
    
}
