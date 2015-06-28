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

package de.tu_berlin.cit.intercloud.gateway;

import de.tu_berlin.cit.intercloud.util.constants.ServiceNames;
import de.tu_berlin.cit.intercloud.xmpp.component.ResourceContainerComponent;
import de.tu_berlin.cit.intercloud.xmpp.rest.ResourceContainer;
import de.tu_berlin.cit.intercloud.xmpp.rest.XmppURI;

public class GatewayComponent extends ResourceContainerComponent {
	
	private XmppURI exchangeURI = null;
	
	private XmppURI rootURI = null;

	public GatewayComponent(ResourceContainer container) {
		super(container);
	}

	@Override
	public String getName() {
		return ServiceNames.GatewayComponentName;
	}

	@Override
	public String getDescription() {
		return "This is one Intercloud Gateway service.";
	}

	public void postComponentStart() {
		String domain = this.getDomain().replace("exchange.", "");
	}

}