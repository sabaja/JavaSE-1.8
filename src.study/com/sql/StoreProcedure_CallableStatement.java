package com.sql;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author sabaja
 * Callable serve per richiamare una storeprocedure
 */

public class StoreProcedure_CallableStatement {
    public static void main(String[] args) {
        StoreProcedure.cb_selectTableActionPerformed();
    }

}

class CallableStatementExample {

    static final String driver = "com.mysql.jdbc.Driver";
    private static CallableStatement cs; // oggetto CallableStatement ...
    private static Connection conn = null;

    /**
     * inizializzo Connection
     */
    static {
        try {
            Class.forName(driver).newInstance();
            String url = "jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false";
            conn = DriverManager.getConnection(url, "root", "Rinaldi1976");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo per prepare una callableStatement con una store procedure come
     * paramentro
     *
     * @param procedure      = store procedure da invocare
     * @param full_navigable navigabilità del Resultset
     * @return
     */
    public static CallableStatement getCallableStatement(String procedure, boolean full_navigable) {
        if (conn != null) {
            try {// creo una callable statement
                if (full_navigable) {
                    cs = conn.prepareCall(procedure, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                } else {
                    cs = conn.prepareCall(procedure);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return cs;
        } else {
            System.out.println("Nessuna connessione attiva per eseguire la preparazione della procedura!");
            return null;
        }
    }
}

class StoreProcedure {

    private static CallableStatement cs;
    private static String procedures_def = "PROCEDURE showConnectedUser(OUT cu varchar(50), INOUT tt datetime)\n" + "BEGIN\n"
            + "-- leggo la data attuale di richiesta\n" + "SELECT tt;\n" + "\n/*\nne imposto una nuova\n*/\n"
            + "SET tt = NOW();\n" + "\n-- utente connesso\n" + "SELECT CURRENT_USER() INTO cu;" + "\nEND";


    //ottengo quale item è stato selezionato, il suo valore e costruisco la query
    public static void cb_selectTableActionPerformed() {
        // ottengo un oggetto CallableStatement
        String procedure = "{CALL " + procedures_def + "(?, ?)}";
        cs = CallableStatementExample.getCallableStatement(procedure, false); // variabili che
        // conterranno
        // i
        // valori
        // ritornati
        // dalla
        // procedura
        // String
        String us = "";
        // Converto una data(temp) in localdatetime (istante ) per poi
        // ricovertirla in Date t
        Date temp = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(temp.toInstant(), ZoneId.systemDefault());
        Date t = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        try { // registro il parametro di tipo OUT
            cs.registerOutParameter(1, java.sql.Types.VARCHAR); // registro il
            // parametro di
            // tipo INOUT
            cs.setDate(2, new java.sql.Date(t.getTime()));
            cs.registerOutParameter(2, java.sql.Types.DATE);
            cs.execute(); // eseguo la stored procedure
            // utente e ora
            us = cs.getString(1);
            t = new Date(cs.getTimestamp(2).getTime());
            System.out.println(us + "\n" + t);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
