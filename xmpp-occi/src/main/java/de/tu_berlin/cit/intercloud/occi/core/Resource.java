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

package de.tu_berlin.cit.intercloud.occi.core;

import de.tu_berlin.cit.intercloud.occi.core.xml.representation.LinkType;
import de.tu_berlin.cit.intercloud.xmpp.rest.CollectionResourceInstance;
import de.tu_berlin.cit.intercloud.xmpp.rest.ResourceInstance;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.Produces;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.XmppMethod;

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 */
public class Resource extends CollectionResourceInstance {

	protected final OcciXml representation;

	/**
	 * If this parameter is true, the representation for this resource is 
	 * created on the fly by the createRepresentation method.
	 */
	private final boolean onTheFlyRepresentation;

	public Resource() {
		super();
		// set representation creation mode
		this.onTheFlyRepresentation = true;
		this.representation = new OcciXml();
	}
	
	public Resource(OcciXml occiRepresentation) {
		super();
		// set representation creation mode
		this.onTheFlyRepresentation = false;
		// set representation
		this.representation = occiRepresentation;
		// check for links
		LinkType[] links = this.representation.getLinks();
		for(int i = 0; i < links.length; i++) {
			createLink(links[i]);
		}
		this.representation.removeLinks();
	}
	
	@XmppMethod(value = XmppMethod.GET, documentation = "This method returns the representation of this resource.")
	@Produces(value = OcciXml.MEDIA_TYPE, serializer = OcciXml.class)
	public OcciXml getRepresentation() {
		OcciXml newRep;
		if(this.onTheFlyRepresentation)
			newRep = this.createRepresentation();
		else
			newRep = this.representation.getCopy();
		
		// add link representation to resource representation
		for(ResourceInstance potLink : this.getResources()) {
			if(potLink instanceof Link) {
				newRep.addLink(((Link) potLink).getLinkTypeRepresentation());
			}
		}
		return newRep;
	}
	
	/**
	 * Override this method in order to provide a representation on the fly.
	 * @return
	 */
	public OcciXml createRepresentation() {
		return this.representation;
	}
	
	@XmppMethod(XmppMethod.DELETE)
	public void deleteResource() {
		this.getParent().removeResource(this);
	}
	
	public String createLink(LinkType link) {
		// create a simple link and return its path
		Link ln = new Link(link);
		return this.addResource(ln);
	}

}
