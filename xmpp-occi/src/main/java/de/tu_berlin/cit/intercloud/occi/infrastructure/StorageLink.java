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
import de.tu_berlin.cit.intercloud.occi.core.annotations.LinkCategory;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute.AttributeType;

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>+
 * @author Daniel Thilo Schroeder <daniel.schroeder@mailbox.tu-berlin.de>
 */
@Link(schema = InfrastructureSchemas.InfrastructureSchema, term = StorageLink.StorageLinkTerm,
	relation = InfrastructureSchemas.InfrastructureSchema + StorageKind.StorageTerm)
public class StorageLink extends LinkCategory{

	public final static String StorageLinkTitle = "StorageLink Link";
	
	public final static String StorageLinkTerm = "storagelink";
	
	public StorageLink(){
		super(StorageLinkTitle);
	}
	
	public StorageLink(String title){
		super(title);
	}
	
	/**
	 * Device identifier as defined by the OCCI service provider.
	 */
	@Attribute(name = "occi.storagelink.deviceid",
			type = AttributeType.STRING,
			mutable = true,
			required = true,
			description = "Device identifier as defined by the OCCI service provider.")
	public String deviceid = null;
	
	/**
	 * Point to where the storage is mounted in the guest OS.
	 */
	@Attribute(name = "occi.storagelink.mountpoint",
			type = AttributeType.STRING,
			mutable = true,
			required = false,
			description = "Point to where the storage is mounted in the guest OS.")
	public String mountpoint = null;		
	
	public enum State {
		active,
		inactive,
		error
	} 
	
	/**
	 * Current status of the instance.
	 */
	@Attribute(name = "occi.storagelink.state",
			type = AttributeType.ENUM,
			mutable = false,
			required = true,
			description = "Current status of the instance.")
	public State state = null;
	
	
	/**
	 * Human-readable explanation of the current instance state.
	 */
	@Attribute(name = "occi.storagelink.state.message",
			type = AttributeType.STRING,
			mutable = false,
			required = false,
			description = "Human-readable explanation of the current instance state.")
	public String message = null;
	
}
