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

package de.tu_berlin.cit.intercloud.occi.core;

import de.tu_berlin.cit.intercloud.xmpp.rest.MethodInvocation;
import de.tu_berlin.cit.intercloud.xmpp.rest.representations.PlainText;
import de.tu_berlin.cit.intercloud.xmpp.rest.representations.Representation;
import de.tu_berlin.cit.intercloud.xmpp.rest.representations.UriListText;
import de.tu_berlin.cit.intercloud.xmpp.rest.representations.UriText;
import de.tu_berlin.cit.intercloud.xmpp.rest.xml.MethodDocument.Method;
import de.tu_berlin.cit.intercloud.xmpp.rest.xml.ResourceDocument;

public class OcciMethodInvocation extends MethodInvocation {


	public OcciMethodInvocation(ResourceDocument resourceDoc, de.tu_berlin.cit.intercloud.xmpp.rest.xwadl.MethodDocument.Method method) {
		super(resourceDoc, method);
	}

	@Override
	public Class<? extends Representation> getResponseRepresentationClass(Method method) {
		if(method.isSetResponse()) {
			String mediaType = method.getResponse().getMediaType();
			if(mediaType.equals(PlainText.MEDIA_TYPE)) {
				return PlainText.class;
			} else if(mediaType.equals(UriText.MEDIA_TYPE)) {
				return UriText.class;
			} else if(mediaType.equals(UriListText.MEDIA_TYPE)) {
				return UriListText.class;
			} else if(mediaType.equals(OcciText.MEDIA_TYPE)) {
				return OcciText.class;
			} else if(mediaType.equals(OcciXml.MEDIA_TYPE)) {
				return OcciXml.class;
			} else if(mediaType.equals(OcciListXml.MEDIA_TYPE)) {
				return OcciListXml.class;
			}
		}
		return null;
	}
	
}
