package com.tiaot33;

import cn.hutool.core.io.file.FileWriter;
import com.alibaba.fastjson.JSON;
import com.tiaot33.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Description:
 * <p>TODO<p/>
 *
 * @author tiaot33
 * @since 2021-11-19-13:46
 */
@Component
@Slf4j
public class ScraperAndMove {
    @Autowired
    DanDanApi api;
    @Autowired
    MoveFile moveFile;
    @Autowired
    PathProperty pathProperty;

    public void scraperDirsAndMove(){
        log.info("");
        List<String> sources = pathProperty.getSource();
        String targetStr = pathProperty.getTarget();
        Path targetPath = Paths.get(targetStr);
        for (String sourceStr : sources) {
            Path sourcePath = Paths.get(sourceStr);
            scraperAndMove(sourcePath,targetPath);
        }
    }

    @SneakyThrows
    public void scraperAndMove(Path sourcePath, Path targetPath) {
        List<Path> videos = FileOpsUtil.getVideos(sourcePath);
        for (Path video : videos) {
            MatchRequestBean matchRequest = FileOpsUtil.getMatchRequest(video.toFile());
            MatchResponse match = api.match(matchRequest);
            if (match == null || CollectionUtils.isEmpty(match.getMatches()) || match.getMatches().get(0) == null) {
                // TODO: 2021/11/19 移动到识别失败文件夹

                continue;
            }
            MatchesItem matchesItem = match.getMatches().get(0);
            int animeId = matchesItem.getAnimeId();
            BangumiDetailsResponse bangumiDetailsResponse = api.queryBangumi(animeId);
            BangumiDetails bangumi = bangumiDetailsResponse.getBangumi();
            String animeTitle = bangumi.getAnimeTitle();
            String videoTitle = matchesItem.getEpisodeTitle();
            File newFile = Paths.get(video.getParent().toString(), matchesItem.getEpisodeId()+"-"+videoTitle).toFile();
            video.toFile().renameTo(newFile);
            Path bangumiDir = Paths.get(targetPath.toString(), animeTitle.trim());
            if (Files.notExists(bangumiDir)) Files.createDirectories(bangumiDir);
            if (Files.notExists(Paths.get(bangumiDir.toString(), "bangumi.json"))) {
                Path bangumiJSONFile = Files.createFile(Paths.get(bangumiDir.toString(), "bangumi.json"));
                FileWriter writer = new FileWriter(bangumiJSONFile.toString());
                String bangumiJSON = JSON.toJSONString(bangumi);
                writer.write(bangumiJSON);
            }
            moveFile.moveFile(newFile.toPath(), bangumiDir);
        }
        FileOpsUtil.cleanEmptyDirectory(sourcePath);
    }
}
