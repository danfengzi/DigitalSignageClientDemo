package com.aktek.digitalsignage.entity;

public class LayoutMedia {
	String id;
	String type;
	String duration;
	String lkid;
	String userId;	
	String schemaVersion;
	String render;
	
	OptionsMedia options;
	Raw raw;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getLkid() {
		return lkid;
	}
	public void setLkid(String lkid) {
		this.lkid = lkid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSchemaVersion() {
		return schemaVersion;
	}
	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}
	public String getRender() {
		return render;
	}
	public void setRender(String render) {
		this.render = render;
	}
	public OptionsMedia getOptions() {
		return options;
	}
	public void setOptions(OptionsMedia options) {
		this.options = options;
	}
	public Raw getRaw() {
		return raw;
	}
	public void setRaw(Raw raw) {
		this.raw = raw;
	}
	
}
