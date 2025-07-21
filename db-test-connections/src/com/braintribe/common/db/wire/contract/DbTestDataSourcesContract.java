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
package com.braintribe.common.db.wire.contract;

import javax.sql.DataSource;

import com.braintribe.common.db.BasicDbTestSession;
import com.braintribe.common.db.DbTestConstants;
import com.braintribe.common.db.DbVendor;
import com.braintribe.common.db.DerbySupportingDbTestSession;
import com.braintribe.wire.api.space.WireSpace;

/**
 * See {@link BasicDbTestSession}
 * 
 * @author peter.gazdik
 */
public interface DbTestDataSourcesContract extends WireSpace {

	DataSource dataSource(DbVendor dbVendor);

	/** Works out of the box, without Docker. */
	DataSource h2();

	/** More complicated, see docker-databases GH repo. */
	default DataSource mssql() {
		return mssql(DbTestConstants.mssqlDefaultPort);
	}

	DataSource mssql(Integer port);

	/**
	 * <pre>
	 * docker run --name hc-test-mysql --rm -d -p 53306:3306 -e MYSQL_DATABASE=dbtest -e MYSQL_USER=cortex -e MYSQL_PASSWORD=cortex -e MYSQL_ROOT_PASSWORD=cortex mysql:5
	 * </pre>
	 */
	default DataSource mySql() {
		return mySql(DbTestConstants.mySqlDefaultPort);
	}

	DataSource mySql(Integer port);

	/** More complicated, see docker-databases GH repo. */
	default DataSource oracle() {
		return oracle(DbTestConstants.oracleDefaultPort);
	}

	DataSource oracle(Integer port);

	/**
	 * <pre>
	 * docker run --name hc-test-postgres --rm -d -p 55432:5432 -e POSTGRES_DB=dbtest -e POSTGRES_USER=cortex -e POSTGRES_PASSWORD=cortex postgres:latest
	 * </pre>
	 */
	default DataSource postgres() {
		return postgres(DbTestConstants.postgresDefaultPort);
	}

	DataSource postgres(Integer port);

	// Not recommended

	/** Works out of the box, but you need to use {@link DerbySupportingDbTestSession}. */
	DataSource derby();

}
