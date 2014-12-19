package org.kuali.coeus.dc.common.db;

import java.sql.*;

public abstract class AbstractConnectionDaoService implements ConnectionDaoService {

    private String riceConnectionString;
    private String coeusConnectionString;
    private String riceUser;
    private String coeusUser;
    private String ricePassword;
    private String coeusPassword;

    private static final ThreadLocal<Connection> COEUS_CONNECTIONS = new ThreadLocal<>();
    private static final ThreadLocal<Connection> RICE_CONNECTIONS = new ThreadLocal<>();


    {
        try {
            Class.forName(getDriverClassName()).newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String getDriverClassName();

    @Override
    public Connection getCoeusConnection() {
        Connection c = COEUS_CONNECTIONS.get();
        if (c == null) {
            try {
                c = getConnection(getCoeusConnectionString(), getCoeusUser(), getCoeusPassword());
                c.setAutoCommit(false);
            } catch (SQLException e) {
            	e.printStackTrace();
                throw new RuntimeException(e);
            }

            COEUS_CONNECTIONS.set(c);
        }

        return c;
    }

	@Override
    public Connection getRiceConnection() {
        Connection c = RICE_CONNECTIONS.get();
        if (c == null) {
            try {
                c = getConnection(getRiceConnectionString(),getRiceUser(),getRicePassword());
                c.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            RICE_CONNECTIONS.set(c);
        }

        return c;
    }

    private Connection getConnection(String con, String user, String pass) throws SQLException {
        if (user != null && !user.trim().equals("")) {
            return DriverManager.getConnection(con, user, pass);
        } else {
            return DriverManager.getConnection(con);
        }
    }

	public String getRiceConnectionString() {
        return riceConnectionString;
    }

    public void setRiceConnectionString(String riceConnectionString) {
        this.riceConnectionString = riceConnectionString;
    }

    public String getCoeusConnectionString() {
        return coeusConnectionString;
    }

    public void setCoeusConnectionString(String coeusConnectionString) {
        this.coeusConnectionString = coeusConnectionString;
    }

    private String getRiceUser() {
		return riceUser;
	}

	public void setRiceUser(String riceUser) {
		this.riceUser = riceUser;
	}

	private String getCoeusUser() {
		return coeusUser;
	}
	public void setCoeusUser(String coeusUser) {
		this.coeusUser = coeusUser;
	}
	private String getRicePassword() {
		return ricePassword;
	}
	public void setRicePassword(String ricePassword) {
		this.ricePassword = ricePassword;
	}
    private String getCoeusPassword() {
		return coeusPassword;
	}
	public void setCoeusPassword(String coeusPassword) {
		this.coeusPassword = coeusPassword;
	}
}
