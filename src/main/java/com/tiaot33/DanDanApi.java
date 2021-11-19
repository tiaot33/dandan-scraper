package com.tiaot33;

import com.alibaba.fastjson.JSON;
import com.tiaot33.model.BangumiDetailsResponse;
import com.tiaot33.model.MatchRequestBean;
import com.tiaot33.model.MatchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Description:
 * <p>TODO<p/>
 *
 * @author tiaot33
 * @see
 * @since 2021-11-18-16:54
 */
@Component
@Slf4j
public class DanDanApi {
    String host = "https://api.acplay.net";
    RestTemplate restTemplate = new RestTemplateBuilder().setBufferRequestBody(false).errorHandler(new DefaultResponseErrorHandler() {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            if (response.getStatusCode().series()== HttpStatus.Series.SERVER_ERROR) {
                log.error(new String(getResponseBody(response)));
            }
            return super.hasError(response);
        }
    }).build();
    //{/api/v2/match}
    public MatchResponse match(MatchRequestBean matchRequestBean){
        String url = host + "/api/v2/match";
        MatchResponse r = restTemplate.postForObject(url, matchRequestBean, MatchResponse.class);
        System.out.println(r);
        return r;
    }
    //GET /api/v2/bangumi/{animeId} 获取番剧详情
    public BangumiDetailsResponse queryBangumi(Integer id){
        String url = host + "/api/v2/bangumi/" + id;
        return restTemplate.getForObject(url, BangumiDetailsResponse.class);
    }

    public static void main(String[] args) {
        DanDanApi danDanApi = new DanDanApi();
        //File file = new File("D:\\Desktop\\[HYSUB]Ganbare Douki-chan[09][GB_MP4][1920X1080].mp4");
        //MatchRequestBean matchRequest = FileOpsUtil.getMatchRequest(file);
        //MatchResponse match = danDanApi.match(matchRequest);
        BangumiDetailsResponse res = danDanApi.queryBangumi(16536);
        String s = JSON.toJSONString(res);
        System.out.println(s);
        BangumiDetailsResponse bangumiDetailsResponse = JSON.parseObject(s, BangumiDetailsResponse.class);
        System.out.println(bangumiDetailsResponse);
    }
}
