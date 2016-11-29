/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author repolho
 */
public interface EntidadeDAO<T> {
    
    public void salvar(T entidade) throws SQLException;
    public void atualizar(T entidade) throws SQLException;
    public void apagar(T entidade) throws SQLException;
    public List<T> listarTodos() throws SQLException;
    
}