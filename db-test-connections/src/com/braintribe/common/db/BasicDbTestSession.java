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

import javax.sql.DataSource;

import com.braintribe.common.db.wire.DbTestConnectionsWireModule;
import com.braintribe.common.db.wire.contract.DbTestDataSourcesContract;
import com.braintribe.wire.api.Wire;
import com.braintribe.wire.api.context.WireContext;

/**
 * Access point to the {@link DbTestDataSourcesContract} which provides {@link DataSource DataSources} for all the supported {@link DbVendor vendors}
 * (except Derby).
 * <p>
 * For local it is recommended to use H2, cause it works out of the box.
 * <p>
 * To test DBs like Postgres, one can use Docker scripts in the docker-databases repository, which start containers with proper configuration.
 * <p>
 * Typically this test-session is acquired via {@link #startDbTest()}, and stored in a static field. It is then cleaned-up via
 * {@link #shutdownDbTest()}.
 * <p>
 * For an example see <tt>AbstractGmDbTestBase</tt> in <tt>jdbc-gm-support-test</tt> artifact (GM group).
 *
 * @author peter.gazdik
 */
public class BasicDbTestSession {

	public WireContext<DbTestDataSourcesContract> context;
	public DbTestDataSourcesContract contract;

	private BasicDbTestSession() {
		context = Wire.context(DbTestConnectionsWireModule.INSTANCE);
		contract = context.contract();
	}

	public static BasicDbTestSession startDbTest() {
		return new BasicDbTestSession();
	}

	public void shutdownDbTest() {
		if (context != null)
			context.shutdown();
	}

}
