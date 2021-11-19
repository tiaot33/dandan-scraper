package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

public @Data class BangumiDetailsResponse{
	private boolean success;
	private String errorMessage;
	private int errorCode;
	@JSONField(name = "番剧详情")
	private BangumiDetails bangumi;
}