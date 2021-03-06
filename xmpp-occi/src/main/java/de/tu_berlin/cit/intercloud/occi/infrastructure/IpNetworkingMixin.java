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
import de.tu_berlin.cit.intercloud.occi.core.annotations.Category;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Mixin;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute.AttributeType;


/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 * @author Daniel Thilo Schroeder <daniel.schroeder@mailbox.tu-berlin.de>
 */
@Mixin(schema = InfrastructureSchemas.NetworkMixinSchema, term = IpNetworkingMixin.IpNetworkingTerm,
	  	applies = InfrastructureSchemas.InfrastructureSchema + NetworkKind.NetworkTerm)
public class IpNetworkingMixin extends Category{
	
	public final static String IpNetworkingTitle = "IPNetworking Mixin";
	
	public final static String IpNetworkingTerm = "ipnetwork";
	
	public IpNetworkingMixin() {
		super(IpNetworkingTitle);
	}

	public IpNetworkingMixin(String title) {
		super(title);
	}
	
	/**
	 * Internet Protocol(IP) network address(e.g. 192.168.0.1/24, fc00::/7)
	 */
	@Attribute(name = "occi.network.address",
			type = AttributeType.STRING,
			mutable = true,
			required = false,
			description = "Internet Protocol(IP) network address(e.g. 192.168.0.1/24, fc00::/7)")
	public String address = null;
	
	/**
	 * Internet Protocol(IP) network address(e.g. 192.168.0.1, fc00::)
	 */
	@Attribute(name = "occi.network.gateway",
			type = AttributeType.STRING,
			mutable = true,
			required = false,
			description = "Internet Protocol(IP) network address(e.g. 192.168.0.1, fc00::)")
	public String gateway = null;
	
	public enum Allocation {
		dynamic,
		Static
	}
	
	/**
	 * Address allocation mechanism: dynamic
	 * e.g. uses the dynamic host configuration
	 * protocol, static e.g. uses user supplied
	 * static network configurations.
	 */
	@Attribute(name = "occi.network.allocation",
			type = AttributeType.ENUM,
			mutable = true,
			required = false,
			description = "Address allocation mechanism: dynamic"
					+ "e.g. uses the dynamic host configuration"
					+ "protocol, static e.g. uses user supplied"
					+ "static network configurations.")
	public Allocation allocation = null;


}
