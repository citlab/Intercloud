/**
 * Copyright (C) 2012-2015 TU Berlin. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tu_berlin.cit.intercloud.xmpp.rest.representations;

import java.util.List;

public abstract class Representation {

	public abstract List<Representation> getTemplates();
	
	public abstract void readRepresentation(String stringRepresentation);
	
	public abstract StringBuilder writeRepresentation(StringBuilder representationBuilder);

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder = this.writeRepresentation(builder);
		return builder.toString();
	}
}
