package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public @Data class MatchResponse{
	@JSONField(name = "是否已精确关联到某个弹幕库")
	private boolean isMatched;
	@JSONField(name = "接口是否调用成功")
	private boolean success;
	//当发生错误时，说明错误具体原因
	@JSONField(name = "错误具体原因")
	private String errorMessage;
	//错误代码，0表示没有发生错误，非0表示有错误，详细信息会包含在errorMessage属性中
	@JSONField(name = "错误代码")
	private int errorCode;
	@JSONField(name = "搜索匹配的结果")
	private List<MatchesItem> matches;
}