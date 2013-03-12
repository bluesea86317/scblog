package com.sc.auth.core;

import java.util.HashMap;
import java.util.Map;

public class ActionConfig {
	private String actionPath;
	private String actionclass;
	private Map<String,String> forwardMap = new HashMap<String,String>();
	
	
	public String getActionPath() {
		return actionPath;
	}
	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	public String getActionclass() {
		return actionclass;
	}
	public void setActionclass(String actionclass) {
		this.actionclass = actionclass;
	}
	public Map<String, String> getForwardMap() {
		return forwardMap;
	}
	public void setForwardMap(Map<String, String> forwardMap) {
		this.forwardMap = forwardMap;
	}
	
	
}
