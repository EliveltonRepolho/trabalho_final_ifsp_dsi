/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author repolho
 */
public enum MontavelTipo {
    PRATO(0,"Prato"),
    DRINK(1,"Drink"),
    SUCO(2,"Suco Natural");
    
    private String descricao;
    private int tipo;

    MontavelTipo(int tipo,String descricao) {
        this.descricao = descricao;
        this.tipo = tipo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public int getTipo() {
        return tipo;
    }
       
   private static final Map<Integer, MontavelTipo> byStatus = new HashMap<Integer, MontavelTipo>();
    static {
        for (MontavelTipo e : MontavelTipo.values()) {
            if (byStatus.put(e.getTipo(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getTipo());
            }
        }
    }

    public static MontavelTipo getByTipo(int status) {
        return byStatus.get(status);
    }
}
