/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.entidade;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author repolho
 */
public class Consumo {
    private long id;
    private int qtdePessoas;
    private Funcionario funcionario;
    private Calendar dataInicio;
    private Calendar dataFim;
    private List<ProdutoBase> produtos;

    public Consumo(long id, int qtdePessoas, Funcionario funcionario, Calendar dataInicio, Calendar dataFim, List<ProdutoBase> produtos) {
        this.id = id;
        this.qtdePessoas = qtdePessoas;
        this.funcionario = funcionario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.produtos = produtos;
    }

    public Consumo(int qtdePessoas, Funcionario funcionario, Calendar dataInicio, List<ProdutoBase> produtos) {
        this.qtdePessoas = qtdePessoas;
        this.funcionario = funcionario;
        this.dataInicio = dataInicio;
        this.produtos = produtos;
    }

    public Consumo(int qtdePessoas, Funcionario funcionario, Calendar dataInicio, Calendar dataFim, List<ProdutoBase> produtos) {
        this.qtdePessoas = qtdePessoas;
        this.funcionario = funcionario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.produtos = produtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQtdePessoas() {
        return qtdePessoas;
    }

    public void setQtdePessoas(int qtdePessoas) {
        this.qtdePessoas = qtdePessoas;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public List<ProdutoBase> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoBase> produtos) {
        this.produtos = produtos;
    }
    
    
}
