package com.video.service.video_service.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoStorageService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    public VideoStorageService(GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

    public String uploadVideo(MultipartFile file) throws IOException {
        ObjectId id = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType()
        );
        return id.toString();
    }

    public GridFsResource getVideo(String id) {
        GridFSFile file = gridFsTemplate.findOne(
                Query.query(Criteria.where("_id").is(new ObjectId(id)))
        );
        return operations.getResource(file);
    }
}
