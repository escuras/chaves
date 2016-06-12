/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toze
 */
public class InsertRequest extends Thread {

    String sql;
    Statement smt;

    public InsertRequest(String sql, Connection con) {
        this.sql = sql;
        try {
            smt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(InsertRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void run() {
        if (smt != null) {
            try {
                smt.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(InsertRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
