package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
import java.util.Map;

public @Data class BangumiDetails {
	@JSONField(name = "番剧简介")
	private String summary;
	@JSONField(name = "番剧元数据")
	private List<String> metadata;
	@JSONField(name = "作品编号")
	private int animeId;
	@JSONField(name = "与此作品相似的其他作品")
	private List<BangumiIntro> similars;
	@JSONField(name = "番剧综合评分")//（综合多个来源的评分求出的加权平均值，0-10分）
	private double rating;
	@JSONField(name = "类型描述")
	private String typeDescription;
	@JSONField(name = "当前用户是否已关注")
	private boolean isFavorited;
	@JSONField(name = "关注状态")
	private Object favoriteStatus;
	@JSONField(name = "作品类型")
	private String type;
	@JSONField(name = "作品标题")
	private String animeTitle;
	@JSONField(name = "搜索关键词")
	private String searchKeyword;
	@JSONField(name = "用户个人评分")
	private int userRating;
	@JSONField(name = "标签列表")
	private List<BangumiTag> tags;
	@JSONField(name = "Bangumi.tv页面地址")
	private String bangumiUrl;
	@JSONField(name = "周几上映(0代表周日，1-6代表周一至周六)")
	private int airDay;
	@JSONField(name = "是否正在连载中")
	private boolean isOnAir;
	@JSONField(name = "海报图片地址")
	private String imageUrl;
	@JSONField(name = "漏勺网页面地址")
	private String loushaoUrl;
	@JSONField(name = "用户对此番剧的备注/评论/标签")
	private Object comment;
	@JSONField(name = "与此作品直接关联的其他作品（例如同一作品的不同季、剧场版、OVA等）")
	private List<Object> relateds;
	@JSONField(name = "是否为限制级别的内容（例如属于R18分级）")
	private boolean isRestricted;
	@JSONField(name = "剧集列表")
	private List<BangumiEpisode> episodes;
	@JSONField(name = "各个站点的评分详情")
	private Map<String, String> ratingDetails;
}