/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import ifsp.dsi.enums.StatusReserva;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author repolho
 */
public class Reserva implements Serializable{
    private long id;
    private Mesa mesa;
    private Cliente cliente;
    private Calendar dataHora;
    private StatusReserva status;

    public Reserva(Mesa mesa, Cliente cliente, Calendar dataHora, StatusReserva status) {
        this.mesa = mesa;
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.status = status;
    }

    public Reserva(long id, Mesa mesa, Cliente cliente, Calendar dataHora, StatusReserva status) {
        this.id = id;
        this.mesa = mesa;
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
    
    
}
