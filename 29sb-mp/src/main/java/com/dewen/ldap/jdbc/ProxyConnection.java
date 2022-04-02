package com.dewen.ldap.jdbc;

import java.sql.Connection;

public class ProxyConnection extends ConnectionWrapper {

	public ProxyConnection(Connection connection) {
		super(connection);
	}

}
