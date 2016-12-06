/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import ifsp.dsi.seguranca.CriptografiaMD5;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author repolho
 */
public class Funcionario implements Serializable {
    
    private long cpf;
    private String nome;
    private long telefone;
    private String loginUsuario;
    private String senha;
    private int perfil;
    
    private boolean selecionado;

    public Funcionario(long cpf, String nome, long telefone, int perfil, String loginUsuario, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.loginUsuario = loginUsuario;
        this.senha = senha;
        this.perfil = perfil;
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

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
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

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public void encriptarSenha() {
        senha = CriptografiaMD5.cryptWithMD5(senha);
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    
    
}
