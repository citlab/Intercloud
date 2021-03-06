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

package de.tu_berlin.cit.intercloud.gateway.openstack;

import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Category;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Mixin;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute.AttributeType;
import de.tu_berlin.cit.intercloud.occi.infrastructure.ComputeKind;
import de.tu_berlin.cit.intercloud.occi.infrastructure.InfrastructureSchemas;


/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 */
@Mixin(schema = OpenStackSchemas.OpenStackImageMixinSchema, term = OpenStackImageMixin.OpenStackImageTerm,
		applies = InfrastructureSchemas.InfrastructureSchema + ComputeKind.ComputeTerm)
public class OpenStackImageMixin extends Category {

	public final static String OpenStackImageTitle = "Open Stack Image Mixin";
	
	public final static String OpenStackImageTerm = "openstackimage";
	
	public OpenStackImageMixin() {
		super(OpenStackImageTitle);
	}

	public OpenStackImageMixin(String title) {
		super(title);
	}
	
	/**
	 * The name of the image.
	 */
	@Attribute(name = "openstack.image.name",
			type = AttributeType.STRING,
			mutable = true,
			required = false,
			description = "The name of the image.")
	public String name = null;
	
	/**
	 * Minimum disk space required for this image.
	 */
	@Attribute(name = "openstack.image.mindisk",
			type = AttributeType.INTEGER,
			mutable = false,
			required = false,
			description = "Minimum disk space required for this image.")
	public Integer mindisk = null;
	
	/**
	 * Minimum memory required for this image.
	 */
	@Attribute(name = "openstack.image.minram",
			type = AttributeType.INTEGER,
			mutable = false,
			required = false,
			description = "Minimum memory required for this image.")
	public Integer minram = null;
	
}
