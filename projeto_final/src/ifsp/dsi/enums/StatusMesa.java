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
    LIVRE(0,"Livre"),
    OCUPADA(1,"Ocupada"),
    RESERVADA(2,"Reservada");
    
    private String descricao;
    private int status;

    StatusMesa(int status,String descricao) {
        this.descricao = descricao;
        this.status = status;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public int getStatus() {
        return status;
    }
       
   private static final Map<Integer, StatusMesa> byStatus = new HashMap<Integer, StatusMesa>();
    static {
        for (StatusMesa e : StatusMesa.values()) {
            if (byStatus.put(e.getStatus(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getStatus());
            }
        }
    }

    public static StatusMesa getByStatus(int status) {
        return byStatus.get(status);
    }
}
