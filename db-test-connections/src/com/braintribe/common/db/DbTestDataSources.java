package com.braintribe.common.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author peter.gazdik
 */
public class DbTestDataSources implements DbTestConstants {

	public static HikariDataSource newDataSource(DbVendor dbVendor) {
		switch (dbVendor) {
			case derby:
				return newDerby();
			case h2:
				return newH2();
			case mssql:
				return newMssql();
			case mysql:
				return newMySql();
			case oracle:
				return newOracle();
			case postgres:
				return newPostgres();
			default:
				throw new IllegalArgumentException("Unknown db vendor: " + dbVendor);
		}
	}

	public static HikariDataSource newDerby() {
		HikariDataSource bean = newHikariDataSource();

		bean.setDriverClassName(derbyDriverSupplier.get());
		bean.setJdbcUrl(derbyUrl);

		validate(bean, "derby");

		return bean;
	}


	public static HikariDataSource newH2() {
		HikariDataSource bean = newHikariDataSource();

		bean.setDriverClassName(h2DriverSupplier.get());
		bean.setJdbcUrl(h2Url);

		validate(bean, "h2");

		return bean;
	}


	public static HikariDataSource newMssql() {
		HikariDataSource bean = newHikariDataSource();

		bean.setDriverClassName(mssqlDriverSupplier.get());
		bean.setJdbcUrl(mssqlUrl);

		validate(bean, "mssql");

		return bean;
	}

	

	public static HikariDataSource newMySql() {
		HikariDataSource bean = newHikariDataSource();

		bean.setDriverClassName(mysqlDriverSupplier.get());
		bean.setJdbcUrl(mysqlUrl);

		validate(bean, "mySql");

		return bean;
	}


	public static HikariDataSource newOracle() {
		HikariDataSource bean = newHikariDataSource();

		bean.setDriverClassName(oracleDriverSupplier.get());
		bean.setJdbcUrl(oracleUrl);

		validate(bean, "oracle");

		return bean;
	}

	
	public static HikariDataSource newPostgres() {
		HikariDataSource bean = newHikariDataSource();

		bean.setDriverClassName(postgresDriverSupplier.get());
		bean.setJdbcUrl(postgresUrl);

		validate(bean, "postgres");

		return bean;
	}

	private static HikariDataSource newHikariDataSource() {
		HikariDataSource bean = new HikariDataSource();
		bean.setUsername(dbTestUsername);
		bean.setPassword(dbTestPassword);

		bean.setConnectionTimeout(10_000L);
		bean.setMaximumPoolSize(10); // small number for tests

		return bean;
	}

	private static HikariDataSource validate(HikariDataSource bean, String vendorName) {
		try (Connection c = bean.getConnection()) {
			c.toString();

		} catch (SQLException e) {
			throw new RuntimeException("Could not connect to " + vendorName + ". Make sure the corresponding docker container is running."
					+ " Scripts for running the container is in github repository called 'docker-databases'.", e);
		}

		return bean;
	}


}
