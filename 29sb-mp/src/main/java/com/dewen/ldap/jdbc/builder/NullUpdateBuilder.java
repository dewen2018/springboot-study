package com.dewen.ldap.jdbc.builder;

import java.util.Date;

public class NullUpdateBuilder extends UpdateBuilder {

	public NullUpdateBuilder(String tableName) {
		super(tableName);
	}

	@Override
	public void setInt(String fieldName, Integer value) {
		if (value != null) {
			super.setInt(fieldName, value);
		}
	}

	@Override
	public void setLong(String fieldName, Long value) {
		if (value != null) {
			super.setLong(fieldName, value);
		}
	}

	@Override
	public void setFloat(String fieldName, Float value) {
		if (value != null) {
			super.setFloat(fieldName, value);
		}
	}

	@Override
	public void setDouble(String fieldName, Double value) {
		if (value != null) {
			super.setDouble(fieldName, value);
		}
	}

	@Override
	public void setDate(String fieldName, Date value) {
		if (value != null) {
			super.setDate(fieldName, value);
		}
	}

	@Override
	public void setString(String fieldName, String value) {
		if (value != null) {
			super.setString(fieldName, value);
		}
	}
}
