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

package de.tu_berlin.cit.intercloud.util.monitoring;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO
 * 
 * @author Alexander Stanik <alexander.stanik@tu-berlin.de>
 */
public class CpuMeter {

	private final OperatingSystemMXBean osManager;
	
	private final PrintWriter fileWriter;
	
	private Timer timer = null;
	
	private long seconds = 0;
	
	@SuppressWarnings("restriction")
	public CpuMeter() throws FileNotFoundException, UnsupportedEncodingException {
		java.lang.management.OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
		if(bean instanceof OperatingSystemMXBean)
			this.osManager = (OperatingSystemMXBean) bean;
		else
			throw new RuntimeException("com.sun.management.OperatingSystemMXBean is not supported.");
		
		fileWriter = new PrintWriter("cpuMeasurements.txt", "UTF-8");
		fileWriter.println("Seconds SystemCPU JvmCPU TotalMemBytes UsedMemBytes");
	}

	public void start() {
        TimerTask timerTask = new CpuTimerTask();
        //running timer task as daemon thread
        this.timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        System.out.println("TimerTask started");
	}

	public void stop() {
        this.timer.cancel();
        System.out.println("TimerTask cancelled");
        this.fileWriter.close();
	}
	
	private class CpuTimerTask extends TimerTask {
	    @Override
	    public void run() {
	    	double sysCpu = osManager.getSystemCpuLoad() * 100;
	    	double jvmCpu = osManager.getProcessCpuLoad() * 100;
	    	long totMem = osManager.getTotalPhysicalMemorySize();
	    	long useMem = totMem - osManager.getFreePhysicalMemorySize();
//	        fileWriter.println(new Date().getTime() + " " + sysCpu + " " + jvmCpu + " " + totMem + " " + useMem);
	        fileWriter.println(seconds + " " + sysCpu + " " + jvmCpu + " " + totMem + " " + useMem);
	        seconds++;
	    }
	}
}
