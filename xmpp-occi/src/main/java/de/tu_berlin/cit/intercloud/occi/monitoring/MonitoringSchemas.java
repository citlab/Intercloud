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

package de.tu_berlin.cit.intercloud.occi.monitoring;

import de.tu_berlin.cit.intercloud.occi.core.IntercloudSchemas;


/**
 * Constants definition for the CIT Monitoring Module.
 *  
 * -monitoring Kinds/Links
 * ---SensorKind
 * -----ActiveSensorMixin
 * -----PassiveSensorMixin
 * ---MeterKind
 * -----AggregationMixin
 * ---CollectorLink
 * -----MetricMixin
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 * @author Daniel Thilo Schroeder <daniel.schroeder@mailbox.tu-berlin.de>
 */

public class MonitoringSchemas {
	
	public final static String MonitoringTag = "monitoring";
		
	public final static String MonitoringExtension = IntercloudSchemas.CitSchemaURL + MonitoringTag;

	public final static String MonitoringSchema = MonitoringExtension +"#";

	//Mixin Predefinition
	public final static String SensorMixinSchema = MonitoringExtension + "/" + SensorKind.SensorTerm + "#";

	public final static String MeterMixinSchema = MonitoringExtension + "/" + MeterKind.MeterTerm + "#";

	public final static String CollectorMixinSchema = MonitoringExtension + "/" + CollectorLink.CollectorTerm + "#";

}
