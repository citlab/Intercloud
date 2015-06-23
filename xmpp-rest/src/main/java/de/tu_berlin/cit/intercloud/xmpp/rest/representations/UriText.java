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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import de.tu_berlin.cit.intercloud.xmpp.rest.representations.Representation;

public class UriText extends Representation {

	public static final String MEDIA_TYPE = "text/uri";

	private URI uri = null;
	
	public UriText() {
	}
	
	public UriText(String text) throws URISyntaxException {
		this.uri = new URI(text);
	}

	@Override
	public List<Representation> getTemplates() {
		return null;
	}

	@Override
	public void readRepresentation(String stringRepresentation) {
		try {
			this.uri = new URI(stringRepresentation);
		} catch (URISyntaxException e) {
			this.uri = null;
			e.printStackTrace();
		}
	}

	@Override
	public StringBuilder writeRepresentation(StringBuilder representationBuilder) {
		representationBuilder.append(this.uri.toASCIIString());
		return representationBuilder;
	}

	public URI getUri() {
		return this.uri;
	}
	
}
