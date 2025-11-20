package com.video.service.video_service.controller;

import com.video.service.video_service.service.VideoStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoStorageService videoService;

    public VideoController(VideoStorageService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile video) throws Exception {
        String id = videoService.uploadVideo(video);
        return ResponseEntity.ok(id);   // return videoId
    }

    @GetMapping("/stream/{id}")
    public void stream(@PathVariable String id, HttpServletResponse response) throws Exception {
        GridFsResource resource = videoService.getVideo(id);

        response.setContentType(resource.getContentType());
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }
}
