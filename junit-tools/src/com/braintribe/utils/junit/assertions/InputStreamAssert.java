// ============================================================================
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
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.utils.junit.assertions;

import java.io.InputStream;

import org.fest.assertions.GenericAssert;

/**
 * Assertion for {@code Class<?>}.
 */
public class InputStreamAssert extends GenericAssert<InputStreamAssert, InputStream> {

	public InputStreamAssert(final InputStream is) {
		super(InputStreamAssert.class, is);
	}

	@Override
	public InputStreamAssert isEqualTo(final InputStream expected) {
		if (expected == null) {
			if (actual == null) {
				return myself;
			}
			throw new IllegalArgumentException("The expected InputStream most not be null.");
		}
		if (actual == null) {
			throw new IllegalArgumentException("The actual InputStream most not be null.");
		}
		int actualReadCount = 0;
		int expectedReadCount = 0;
		int actualInput;
		int expectedInput;
		try {
			while ((actualInput = actual.read()) != -1) {
				actualReadCount++;
				expectedInput = expected.read();
				if (expectedInput == -1) {
					fail("The expected InputStream does not contain " + actualReadCount + " bytes as the actual InputStream.");
				}
				expectedReadCount++;
				if (actualInput != expectedInput) {
					fail("The InputStreams differ at position " + expectedReadCount + ": actual: " + actualInput + ", expected: " + expectedInput);
				}
			}
			if (expected.read() != -1) {
				fail("The actual InputStream does not contain " + expectedReadCount + " bytes as the expected InputStream.");
			}
			actual.close();
			expected.close();
		} catch (Exception e) {
			fail("Error while trying to compare the InputStreams", e);
		}
		return myself;
	}

}
