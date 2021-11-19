package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class MatchesItem{
	@JSONField(name = "剧集标题")
	private String episodeTitle;
	@JSONField(name = "作品ID")
	private int animeId;
	@JSONField(name = "弹幕偏移时间（弹幕应延迟多少秒出现）。此数字为负数时表示弹幕应提前多少秒出现")
	private double shift;
	@JSONField(name = "类型描述")
	private String typeDescription;
	@JSONField(name = "弹幕库ID")
	private int episodeId;
	@JSONField(name = "作品标题")
	private String animeTitle;
	//作品类别 = ['tvseries', 'tvspecial', 'ova', 'movie', 'musicvideo', 'web', 'other', 'jpmovie', 'jpdrama', 'unknown']
	@JSONField(name = "作品类别")
	private String type;
}