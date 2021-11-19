package com.tiaot33.model;

import lombok.Data;

public @Data class MatchRequestBean{
	private String fileName;
	private int videoDuration;
	private long fileSize;
	private String fileHash;
	private String matchMode;
}