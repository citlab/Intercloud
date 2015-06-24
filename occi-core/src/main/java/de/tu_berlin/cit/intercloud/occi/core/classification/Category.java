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

package de.tu_berlin.cit.intercloud.occi.core.classification;

import de.tu_berlin.cit.intercloud.occi.core.xml.classification.CategoryDocument;
import de.tu_berlin.cit.intercloud.occi.core.xml.classification.ClassType;

public abstract class Category {
	
	public final static String CategorySchema = "http://schema.ogf.org/occi/core#";
	
	private final CategoryDocument doc;
		
	protected Category(ClassType.Enum type, String schema, String term) {
		this(type, schema, term, null);
	}
	
	protected Category(ClassType.Enum type, String schema, String term, String title) {
		this.doc = CategoryDocument.Factory.newInstance();
		this.doc.addNewCategory().setClass1(type);
		this.doc.getCategory().setSchema(schema);
		this.doc.getCategory().setTerm(term);
		if(title != null)
			this.doc.getCategory().setTitle(title);
	}
	
	public String getSchema() {
		return this.doc.getCategory().getSchema();
	}
	
	public String getTerm() {
		return this.doc.getCategory().getTerm();
	}
	
	public String getTitle() {
		if(this.doc.getCategory().isSetTitle())
			return this.doc.getCategory().getTitle();
		else
			return "";
	}
	
	/*
	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		if(attributes != null)
			this.attributes = attributes;
	}
	*/
	
	public String toTextPlain() {
    	StringBuilder text = new StringBuilder();
    	text.append("Category: " + this.getTerm() + "; \n");
    	text.append("scheme=" + this.getSchema() + "; \n");
    	text.append("class=" + this.doc.getCategory().getClass1().toString() + "; \n");
    	text.append("title=" + this.getTitle() + "; \n");
//    	text.append("attributes=");
//    	for(int i = 0; i < this.attributes.size(); i++) {
//   		text.append(" " + this.attributes.get(i).getName());
//    	}
//    	text.append("; \n");
    	return text.toString();
	}

	@Override
	public String toString() {
		return this.doc.toString();
	}
	
}