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
package com.braintribe.common.db.wire.space;

import static com.braintribe.common.db.DbTestDataSources.newDerby;
import static com.braintribe.common.db.DbTestDataSources.newH2;
import static com.braintribe.common.db.DbTestDataSources.newMssql;
import static com.braintribe.common.db.DbTestDataSources.newMySql;
import static com.braintribe.common.db.DbTestDataSources.newOracle;
import static com.braintribe.common.db.DbTestDataSources.newPostgres;
import static com.braintribe.wire.api.scope.InstanceConfiguration.currentInstance;

import javax.sql.DataSource;

import com.braintribe.common.db.DbTestConstants;
import com.braintribe.common.db.DbVendor;
import com.braintribe.common.db.wire.contract.DbTestDataSourcesContract;
import com.braintribe.wire.api.annotation.Managed;
import com.zaxxer.hikari.HikariDataSource;

@Managed
public class DbTestDataSourcesSpace implements DbTestDataSourcesContract, DbTestConstants {

	@Override
	public DataSource dataSource(DbVendor dbVendor) {
		switch (dbVendor) {
			case derby:
				return derby();
			case h2:
				return h2();
			case mssql:
				return mssql();
			case mysql:
				return mySql();
			case oracle:
				return oracle();
			case postgres:
				return postgres();
			default:
				throw new IllegalArgumentException("Unknown db vendor: " + dbVendor);
		}
	}

	@Override
	@Managed
	public DataSource derby() {
		HikariDataSource bean = newDerby();
		currentInstance().onDestroy(bean::close);
		return bean;
	}

	@Override
	@Managed
	public DataSource h2() {
		HikariDataSource bean = newH2();
		currentInstance().onDestroy(bean::close);
		return bean;
	}

	@Override
	@Managed
	public DataSource mssql(Integer port) {
		HikariDataSource bean = newMssql(port);
		currentInstance().onDestroy(bean::close);
		return bean;
	}

	@Override
	@Managed
	public DataSource mySql(Integer port) {
		HikariDataSource bean = newMySql(port);
		currentInstance().onDestroy(bean::close);
		return bean;
	}

	@Override
	@Managed
	public DataSource oracle(Integer port) {
		HikariDataSource bean = newOracle(port);
		currentInstance().onDestroy(bean::close);
		return bean;
	}

	@Override
	@Managed
	public DataSource postgres(Integer port) {
		HikariDataSource bean = newPostgres(port);
		currentInstance().onDestroy(bean::close);
		return bean;
	}

}
