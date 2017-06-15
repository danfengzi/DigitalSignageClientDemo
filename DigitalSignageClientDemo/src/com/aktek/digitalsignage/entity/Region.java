package com.aktek.digitalsignage.entity;

import java.util.List;

public class Region {
	String id;	
	Double width;
	Double height;
	Double top;
	Double left;
	String userId;	
	OptionsRegion options;
	List<LayoutMedia> mediaList;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getTop() {
		return top;
	}
	public void setTop(Double top) {
		this.top = top;
	}
	public Double getLeft() {
		return left;
	}
	public void setLeft(Double left) {
		this.left = left;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public OptionsRegion getOptions() {
		return options;
	}
	public void setOptions(OptionsRegion options) {
		this.options = options;
	}
	public List<LayoutMedia> getMediaList() {
		return mediaList;
	}
	public void setMediaList(List<LayoutMedia> mediaList) {
		this.mediaList = mediaList;
	}
	
	
}
