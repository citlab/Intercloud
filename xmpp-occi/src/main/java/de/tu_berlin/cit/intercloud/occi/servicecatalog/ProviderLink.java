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

package de.tu_berlin.cit.intercloud.occi.servicecatalog;

import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Link;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute.AttributeType;
import de.tu_berlin.cit.intercloud.occi.core.annotations.LinkCategory;

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 * 
 */
@Link(schema = CatalogSchemas.CatalogSchema, term = ProviderLink.ProviderLinkTerm,
		relation = CatalogSchemas.CatalogSchema + ServiceCatalogMixin.CatalogTerm)
public class ProviderLink extends LinkCategory {

	public final static String ProviderLinkTitle = "Provider Link";
	
	public final static String ProviderLinkTerm = "provider";
	
	public ProviderLink() {
		super(ProviderLinkTitle);
	}

	public ProviderLink(String title) {
		super(title);
	}

	/**
	 * The name of the organization that provides the service
	 */
	@Attribute(name = "intercloud.provider.organization",
			type = AttributeType.STRING,
			mutable = true,
			required = true,
			description = "The name of the organization that provides the service")
	public String organization = null;
	
	/**
	 * The International Providers Identification Number
	 */
	@Attribute(name = "intercloud.provider.ipin",
			type = AttributeType.STRING,
			mutable = true,
			required = true,
			description = "The International Providers Identification Number")
	public String ipin = null;

}
