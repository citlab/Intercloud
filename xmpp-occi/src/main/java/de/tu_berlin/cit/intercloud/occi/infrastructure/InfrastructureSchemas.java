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

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 * @author Daniel Thilo Schroeder <daniel.schroeder@mailbox.tu-berlin.de>
 */
public class InfrastructureSchemas {
	
	
	private final static String SchemaURL = "http://schema.ogf.org/occi/";
	
	private final static String InfrastructureTag = "infrastructure";
	
	private final static String InfrastructureSchema = SchemaURL + InfrastructureTag +"#";
	
	/**
	 * 
	 * -infrastructure Kinds/Links
	 * ---Compute
	 * ---Storage
	 * ---storageLink
	 * ---Network
	 * -----IpNetworkMixin
	 * ---NetworkInterfaceLink
	 * -----IpNetworkInterfaceMixin
	 */
	
	//Kinds/Links
	public final static String ComputeSchema = InfrastructureSchema;
	
	public final static String StorageSchema = InfrastructureSchema;
	
	public final static String StorageLinkSchema = InfrastructureSchema;
	
	public final static String NetworkSchema = InfrastructureSchema;
	
	public final static String NetworkInterfaceSchema = InfrastructureSchema;
	
	
	//Mixins
	public final static String NetworkInterfaceMixinSchema = SchemaURL + InfrastructureTag + NetworkInterfaceLink.NetworkInterfaceTerm + "#";
	
	public final static String NetworkMixinSchema = SchemaURL + InfrastructureTag + NetworkKind.NetworkTerm + "#";
}
