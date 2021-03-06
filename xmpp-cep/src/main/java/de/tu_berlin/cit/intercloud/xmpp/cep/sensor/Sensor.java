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

package de.tu_berlin.cit.intercloud.xmpp.cep.sensor;

import de.tu_berlin.cit.intercloud.occi.core.Collection;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Classification;
import de.tu_berlin.cit.intercloud.occi.core.annotations.Summary;
import de.tu_berlin.cit.intercloud.occi.monitoring.ActiveSensorMixin;
import de.tu_berlin.cit.intercloud.occi.monitoring.CollectorLink;
import de.tu_berlin.cit.intercloud.occi.monitoring.MetricMixin;
import de.tu_berlin.cit.intercloud.occi.monitoring.PassiveSensorMixin;
import de.tu_berlin.cit.intercloud.occi.monitoring.SensorKind;
import de.tu_berlin.cit.intercloud.xmpp.rest.annotations.Path;

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 */
@Path("/sensor")
@Summary("This resource allows for manage sensor resources.")
@Classification(kind = SensorKind.class,
				mixins = {ActiveSensorMixin.class, PassiveSensorMixin.class, MetricMixin.class},
				links = {CollectorLink.class})
public class Sensor extends Collection {

}
