package com.tiaot33;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.tiaot33.model.MatchRequestBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 * <p>TODO<p/>
 *
 * @author tiaot33
 * @see
 * @since 2021-11-18-16:21
 */
@Slf4j
public class FileOpsUtil {
    public static Path[] sourcePaths = {Paths.get("/share/Container/qbittorrent/downloads")};
    public static Path targetPath = Paths.get("/share/homes/jiaran-diana/视频");
    @SneakyThrows
    public static String getFileHash(File file){
        byte[] buf = new byte[16777216];
        try (InputStream inputStream = Files.newInputStream(file.toPath());) {
            int read = inputStream.read(buf, 0, 16777216);
            if (read!=16777216) log.warn("读取文件大小错误[{}]",read);
        }
        return DigestUtil.md5Hex(buf);
    }
    @SneakyThrows
    public static MatchRequestBean getMatchRequest(File file){
        MatchRequestBean requestBean = new MatchRequestBean();
        requestBean.setFileName(FileNameUtil.getPrefix(file));
        requestBean.setVideoDuration(0);
        requestBean.setFileSize(Files.size(file.toPath()));
        requestBean.setFileHash(getFileHash(file));
        requestBean.setMatchMode("hashAndFileName");
        return requestBean;
    }
    @SneakyThrows
    public static List<Path> readPathFromFile(File file) {
        ArrayList<Path> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())){
            String line = reader.readLine();
            if (StringUtils.hasText(line)) {
                Path path = Paths.get(line);
                list.add(path);
            }
        }
        return list;
    }

    public static boolean hasVideo(Path path) {
        List<Path> videos = getVideos(path);
        return !CollectionUtils.isEmpty(videos);
    }

    @SneakyThrows
    public static List<Path> getVideos(Path path) {
        List<String> videoExts = Arrays.asList("flv", "avi", "wmv", "asf", "wmvhd", "mp4", "mpg", "mpeg", "3gp", "mkv", "rm", "rmvb", "mov", "qt", "ogg", "ogv", "oga", "mod");
        List<Path> res;
        try (Stream<Path> paths = Files.walk(path, FileVisitOption.FOLLOW_LINKS)) {
            res = paths.filter(f -> videoExts.contains(FileNameUtil.extName(f.toFile()))).collect(Collectors.toList());
        }
        return res;
    }
    @SneakyThrows
    public static void cleanEmptyDirectory(Path path, List<Path> excludeDirs) {
        try (Stream<Path> paths = Files.walk(path)) {
            List<Path> dirs = paths.filter(Files::isDirectory).collect(Collectors.toList());
            for (Path d : dirs) {
                if (!Files.list(d).findAny().isPresent()) {
                    if (excludeDirs.contains(d)) continue;
                    try {
                        Files.deleteIfExists(d);
                    } catch (Exception e) {
                        log.error("[{}]空文件夹删除失败",d);
                    }
                }
            }
        }
    }
}
