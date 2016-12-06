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
public enum StatusReserva {
    RESERVADA(0,"Reservada"),
    ESPERA(1,"Liste de Espera"),
    FINALIZADA(2,"Finalizada"),
    CANCELADA(9,"Cancelada");
    
    private int status;
    private String descricao;

    StatusReserva(int status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public int getStatus() {
        return status;
    }
    
    private static final Map<Integer, StatusReserva> byStatus = new HashMap<Integer, StatusReserva>();
    static {
        for (StatusReserva e : StatusReserva.values()) {
            if (byStatus.put(e.getStatus(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getStatus());
            }
        }
    }

    public static StatusReserva getByStatus(int status) {
        return byStatus.get(status);
    }
}
