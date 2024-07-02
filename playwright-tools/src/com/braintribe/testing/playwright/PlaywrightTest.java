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
package com.braintribe.testing.playwright;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Page;

/**
 * First (experimental) version of an interface which provides <i>Playwright</i> related helpers. Purpose is to make writing <i>Playwright</i> based
 * tests easier / more convenient.
 *
 * @author michael.lafite
 */
public interface PlaywrightTest {

	default boolean headless() {
		return System.getenv().getOrDefault("CI_ENVIRONMENT", "false").equals("true");
	}

	default String testReportDir() {
		return "dist/test-reports/" + getClass().getName();
	}

	default Path screenshot(Page page, String name) {
		File dir = new File(testReportDir());
		if (!dir.exists())
			dir.mkdirs(); 
		Path path = Paths.get(testReportDir(), name + ".png");
		page.screenshot(new Page.ScreenshotOptions().setPath(path));
		return path;
	}
}
