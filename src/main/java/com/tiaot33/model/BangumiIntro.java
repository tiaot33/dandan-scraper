package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

public @Data class BangumiIntro {
	@JSONField(name = "周几上映，0代表周日，1-6代表周一至周六")
	private int airDay;
	@JSONField(name = "作品编号")
	private int animeId;
	@JSONField(name = "是否正在连载中")
	private boolean isOnAir;
	@JSONField(name = "海报图片地址")
	private String imageUrl;
	@JSONField(name = "番剧综合评分（综合多个来源的评分求出的加权平均值，0-10分）")
	private double rating;
	@JSONField(name = "当前用户是否已关注（无论是否为已弃番等附加状态）")
	private boolean isFavorited;
	@JSONField(name = "作品标题")
	private String animeTitle;
	@JSONField(name = "搜索关键词")
	private String searchKeyword;
	@JSONField(name = "是否为限制级别的内容（例如属于R18分级）")
	private boolean isRestricted;
}