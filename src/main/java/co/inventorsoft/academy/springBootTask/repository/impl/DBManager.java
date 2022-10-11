package co.inventorsoft.academy.springBootTask.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class DBManager {

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            log.info("DBManager getInstance() is done");
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        String name, pass, url;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/tournament";
            name = "root";
            pass = "root";
            con = DriverManager.getConnection(url, name, pass);
            log.info("Connection created");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return con;
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
