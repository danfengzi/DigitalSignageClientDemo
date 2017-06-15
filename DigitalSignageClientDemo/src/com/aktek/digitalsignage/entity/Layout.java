package com.aktek.digitalsignage.entity;

import java.util.List;

public class Layout {
	String schemaVersion;
	String width;
	String height;
	String bgColor;
	String background;
	String resolutionid;
	Tags tags;
	
	List<Region> regionList;

	public List<Region> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}

	public String getSchemaVersion() {
		return schemaVersion;
	}

	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getResolutionid() {
		return resolutionid;
	}

	public void setResolutionid(String resolutionid) {
		this.resolutionid = resolutionid;
	}

	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}		
	
	
}
