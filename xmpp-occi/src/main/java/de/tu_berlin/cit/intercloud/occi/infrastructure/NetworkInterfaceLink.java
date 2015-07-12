/**
 * Copyright 2010-2015 Complex and Distributed IT Systems, TU Berlin
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

package de.tu_berlin.cit.intercloud.occi.infrastructure;

import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Link;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute.AttributeType;
import de.tu_berlin.cit.intercloud.occi.core.annotations.LinkCategory;

@Link(schema = ComputeKind.ComputeSchema, term = NetworkInterfaceLink.NetworkInterfaceTerm,
		relation = NetworkKind.NetworkSchema + NetworkKind.NetworkTerm)
public class NetworkInterfaceLink extends LinkCategory {

	public final static String NetworkInterfaceTitle = "Network Interface Link";
	
	public final static String NetworkInterfaceTerm = "networkinterface";

	public NetworkInterfaceLink() {
		super(NetworkInterfaceTitle);
	}

	public NetworkInterfaceLink(String title) {
		super(title);
	}

	@Attribute(name = "occi.networkinterface.mac",
			type = AttributeType.STRING,
			mutable = true,
			required = true,
			description = "MAC address associated with the link's device interface")
	public String mac;

	public enum State {
		active, 
		inactive, 
		error
	}
	
	@Attribute(name = "occi.networkinterface.state",
			type = AttributeType.ENUM,
			required = true,
			description = "Current state of the instance: Enum{active, inactive, error}")
	public State state;

	@Attribute(name = "occi.networkinterface.message",
			type = AttributeType.STRING,
			description = "Human-readable explanation of the current instance state")
	public String message;

}
