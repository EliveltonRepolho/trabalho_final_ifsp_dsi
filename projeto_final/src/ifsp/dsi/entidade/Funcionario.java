/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author repolho
 */
public class Funcionario implements Serializable {
    
    private long cpf;
    private String nome;
    private List<Long> telefones;
    private String loginUsuario;
    private String senha;
    private List<Perfil> perfis;

    public Funcionario(long cpf, String nome, List<Long> telefones, String loginUsuario, String senha, List<Perfil> perfis) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefones = telefones;
        this.loginUsuario = loginUsuario;
        this.senha = senha;
        this.perfis = perfis;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Long> telefones) {
        this.telefones = telefones;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
    
    
}
