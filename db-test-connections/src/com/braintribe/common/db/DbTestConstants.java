// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
package com.braintribe.common.db;

import java.util.function.Supplier;

import com.braintribe.common.db.wire.contract.DbTestDataSourcesContract;

/**
 * @see DbTestDataSourcesContract
 *
 * @author peter.gazdik
 */
public interface DbTestConstants {

	String derbyUrl = "jdbc:derby://localhost:1527/res/db/dbtest;create=true";
	String derbyDriver = "org.apache.derby.jdbc.ClientDriver";
	Supplier<String> derbyDriverSupplier = () -> org.apache.derby.jdbc.ClientDriver.class.getName();

	String h2Url = "jdbc:h2:mem:dbtest;DB_CLOSE_DELAY=-1";
	String h2Driver = "org.h2.Driver";
	Supplier<String> h2DriverSupplier = () -> org.h2.Driver.class.getName();

	int mssqlDefaultPort = 51433;
	String mssqlUrl = "jdbc:sqlserver://localhost:${PORT};databaseName=dbtest";
	String mssqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	Supplier<String> mssqlDriverSupplier = () -> com.microsoft.sqlserver.jdbc.SQLServerDriver.class.getName();

	int mySqlDefaultPort = 53306;
	String mysqlUrl = "jdbc:mysql://localhost:${PORT}/dbtest";
	String mysqlDriver = "com.mysql.jdbc.Driver";
	Supplier<String> mysqlDriverSupplier = () -> com.mysql.jdbc.Driver.class.getName();

	int oracleDefaultPort = 51521;
	String oracleUrl = "jdbc:oracle:thin:@localhost:${PORT}/xe";
	String oracleDriver = "oracle.jdbc.pool.OracleDataSource";
	Supplier<String> oracleDriverSupplier = () -> oracle.jdbc.pool.OracleDataSource.class.getName();

	int postgresDefaultPort = 55432;
	String postgresUrl = "jdbc:postgresql://localhost:${PORT}/dbtest";
	String postgresDriver = "org.postgresql.Driver";
	Supplier<String> postgresDriverSupplier = () -> org.postgresql.Driver.class.getName();

	String dbTestUsername = "cortex";
	String dbTestPassword = "cortex";

}
