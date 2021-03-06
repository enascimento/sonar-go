/*
 * SonarQube Go Plugin
 * Copyright (C) 2018-2018 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.go.plugin.utils;

import java.io.InputStream;
import java.util.Scanner;

public class TestUtils {

  private TestUtils() {
    // utility class, forbidden constructor
  }

  public static String readTestResource(Class<?> clazz, String filename) {
    String resource = clazz.getSimpleName() + "/" + filename;
    InputStream resourceAsStream = clazz.getResourceAsStream(resource);
    if (resourceAsStream == null) {
      throw new IllegalStateException("Resource " + resource + " not found on classpath.");
    }
    Scanner scanner = new java.util.Scanner(resourceAsStream).useDelimiter("\\A");
    return scanner.hasNext() ? scanner.next() : "";
  }
}
