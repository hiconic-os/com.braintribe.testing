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

import com.braintribe.common.db.wire.DbTestConnectionsWireModule;
import com.braintribe.common.db.wire.contract.DbTestDataSourcesContract;
import com.braintribe.wire.api.Wire;
import com.braintribe.wire.api.context.WireContext;

/**
 * Similar to {@link DerbySupportingDbTestSession}, but doesn't support Derby.
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
