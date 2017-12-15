package com.unv.loadtime.controller;

import org.springframework.stereotype.Component;

@Component
public class Video {
	public String frontEndLink;
	public String mcpId;
	public String syndicator;

	public String getFrontEndLink() {
		return frontEndLink;
	}

	public void setFrontEndLink(String frontEndLink) {
		this.frontEndLink = frontEndLink;
	}

	public String getMcpId() {
		return mcpId;
	}

	public void setMcpId(String mcpId) {
		this.mcpId = mcpId;
	}

	public String getSyndicator() {
		return syndicator;
	}

	public void setSyndicator(String syndicator) {
		this.syndicator = syndicator;
	}

	@Override
	public String toString() {
		return "Video [frontEndLink=" + frontEndLink + ", mcpId=" + mcpId + ", syndicator=" + syndicator + "]";
	}

}