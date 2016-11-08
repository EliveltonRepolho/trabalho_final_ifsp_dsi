/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.bd;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author repolho
 */
public class ConexaoBD {
    
    private static ConexaoBD instance;
    private BasicDataSource ds;
    private String url = "jdbc:oracle:thin:@localhost:1521:XE";
    private String user = "repolho";
    private String pass = "repolho";
    private String driver = "oracle.jdbc.OracleDriver";
    
    private ConexaoBD() {
        ds = new BasicDataSource();
        ds.setUsername(user);
        ds.setPassword(pass);
        ds.setUrl(url);
        ds.setDriverClassName(driver);
    }
    
    public static ConexaoBD getInstance() {
        return instance == null ? new ConexaoBD() : instance;
    }
    
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
