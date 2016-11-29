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
public class Cliente {
    
    private long id;
    private String nome;
    private long telefone;

    public Cliente(String nome, long telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public Cliente(long id, String nome, long telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
    
}
