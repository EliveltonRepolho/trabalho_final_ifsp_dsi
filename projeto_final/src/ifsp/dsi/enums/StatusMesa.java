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
public enum StatusMesa {
    LIVRE("Livre"),
    OCUPADA("Ocupada"),
    RESERVADA("Reservada");
    
    private String descricao;

    StatusMesa(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    private static final Map<String, StatusMesa > lookup
   = new HashMap<String, StatusMesa >();

   static {
   for (StatusMesa s : EnumSet.allOf(StatusMesa.class))
         lookup.put(s.descricao, s);
   }

   public static StatusMesa getValue(int intValue) {
      return lookup.get(intValue);
   }
}
