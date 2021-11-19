package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

public @Data class BangumiTag {
	@JSONField(name = "标签内容")
	private String name;
	@JSONField(name = "观众为此标签+1次数")
	private int count;
	@JSONField(name = "标签编号")
	private int id;
}