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

import java.util.HashMap;

import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Category;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Mixin;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Attribute.AttributeType;

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 */
@Mixin(schema = CatalogSchemas.CatalogSchema, term = ServiceCatalogMixin.CatalogTerm)
public class ServiceCatalogMixin extends Category {

	public final static String CatalogTitle = "Service Catalog Resource";
	
	public final static String CatalogTerm = "service";
	
	public ServiceCatalogMixin() {
		super(CatalogTitle);
	}

	public ServiceCatalogMixin(String title) {
		super(title);
	}

	@Attribute(name = "intercloud.catalog.price",
			type = AttributeType.DOUBLE,
			mutable = true,
			required = true,
			description = "The price for the described service")
	public Double price = null;

	@Attribute(name = "intercloud.catalog.currency",
			type = AttributeType.STRING,
			mutable = true,
			required = true,
			value = "Euro",
			description = "The currency of the price")
	public String currency = null;

	@Attribute(name = "intercloud.catalog.billingincrements",
			type = AttributeType.STRING,
			mutable = true,
			required = true,
			value = "per hour",
			description = "The billing increments of the price, e.g. per GB, per hour, per second, etc.")
	public String billingIncrements = null;
	
	@Attribute(name = "intercloud.catalog.generaltermsandconditions",
			type = AttributeType.MAP,
			mutable = true,
			required = false,
			description = "The general terms and conditions (i.e. key = section title, value = section body)")
	public HashMap<String, String> termsAndConditions = null;
	
}
