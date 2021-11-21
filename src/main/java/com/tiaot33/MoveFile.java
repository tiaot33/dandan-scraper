package com.tiaot33;

import cn.hutool.core.io.file.FileNameUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Description:
 * <p>TODO<p/>
 *
 * @author tiaot33
 * @see
 * @since 2021-11-19-11:32
 */
@Component
public class MoveFile {
    /**
     *
     * @param video
     * @param targetDir 目标文件夹，如 /media/anime/鬼灭之刃/
     */
    @SneakyThrows
    public void moveFile(Path video, Path targetDir){
        Path parent = video.getParent();
        List<Path> videos = FileOpsUtil.getVideos(parent);
        if (Files.notExists(Paths.get(targetDir.toString(), FileNameUtil.getName(video.toFile())))) {
            Files.move(video, Paths.get(targetDir.toString(), FileNameUtil.getName(video.toFile())));
        }
        if (videos.size() == 1) {
            //文件夹下只有一个视频，则把字幕等相关文件复制到others文件夹
            List<Path> extraFiles = new ArrayList<>();
            try (Stream<Path> paths = Files.walk(parent)) {
                paths.forEach(p -> {
                    if ("ass".equals(FileNameUtil.extName(p.toFile()))) {
                        try {
                            Files.move(p, Paths.get(targetDir.toString(), FileNameUtil.getName(p.toFile())), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (!Files.isDirectory(p)){
                        extraFiles.add(p);
                    }
                });
            }
            if (extraFiles.size() > 0) {
                Path extra = Paths.get(targetDir.toString(), "others");
                if (Files.notExists(extra)) {
                    Files.createDirectory(extra);
                }
                for (Path path : extraFiles) {
                    Files.move(path, Paths.get(extra.toString(), FileNameUtil.getName(path.toFile())), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
