/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import ifsp.dsi.enums.StatusMesa;
import java.io.Serializable;

/**
 *
 * @author repolho
 */
public class Mesa implements Serializable{
  
    private int numero;
    private int quantidadeLugares;
    private StatusMesa status;
    
    private boolean selecionado;

    public Mesa(int numero, int quantidadeLugares) {
        this.numero = numero;
        this.quantidadeLugares = quantidadeLugares;
    }

    public Mesa(int numero, int quantidadeLugares, StatusMesa status) {
        this.numero = numero;
        this.quantidadeLugares = quantidadeLugares;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQuantidadeLugares() {
        return quantidadeLugares;
    }

    public void setQuantidadeLugares(int quantidadeLugares) {
        this.quantidadeLugares = quantidadeLugares;
    }

    public StatusMesa getStatus() {
        return status;
    }

    public void setStatus(StatusMesa status) {
        this.status = status;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    @Override
    public String toString() {
        return String.valueOf(getNumero());
    }
    
    
    
    
    
}
