package com.tiaot33.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

public @Data class BangumiEpisode {
	@JSONField(name = "剧集完整标题")
	private String episodeTitle;
	@JSONField(name = "上次观看时间（服务器时间，即北京时间）")
	private Object lastWatched;
	@JSONField(name = "本集上映时间（当地时间）")
	private String airDate;
	@JSONField(name = "剧集ID（弹幕库编号）")
	private int episodeId;
	@JSONField(name = "剧集短标题（可以用来排序，非纯数字，可能包含字母）")
	private String episodeNumber;
}