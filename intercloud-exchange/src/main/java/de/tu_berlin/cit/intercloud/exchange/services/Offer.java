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

package de.tu_berlin.cit.intercloud.exchange.services;

import java.net.URISyntaxException;

import de.tu_berlin.cit.intercloud.occi.core.annotations.Summary;
import de.tu_berlin.cit.intercloud.xmpp.rest.CollectionResourceInstance;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.Consumes;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.Path;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.Produces;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.XmppMethod;
import de.tu_berlin.cit.intercloud.xmpp.rest.representations.OcciXml;
import de.tu_berlin.cit.intercloud.xmpp.rest.representations.UriText;

@Path("/manager")
@Summary("This resource allows for manage "
		+ "SLA offer creation and agreement negotiation.")
public class Offer extends CollectionResourceInstance {

	public Offer() {
		super();
	}
	
	@XmppMethod(XmppMethod.POST)
    @Consumes(value = OcciXml.MEDIA_TYPE, serializer = OcciXml.class)
    @Produces(value = UriText.MEDIA_TYPE, serializer = UriText.class)
	public UriText createOffer(OcciXml offerXml) {
		// create an offer and return its uri
		OfferInstance offer = new OfferInstance(offerXml);
		String path = this.addResource(offer);
		try {
			UriText uri = new UriText(path);
			return uri;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new UriText(); 
		}
	}

}