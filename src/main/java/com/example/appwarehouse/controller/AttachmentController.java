package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService service;

    @PostMapping("upload")
    public Result uploadFile(MultipartHttpServletRequest request) {
        return service.uploadFile(request);
    }

    @GetMapping("/info/{fileId}")
    public Result getFileInfo(@PathVariable Integer fileId) {
        return service.fileInfo(fileId);
    }

    @GetMapping("/info")
    public Result allFilesInfo() {
        List<Attachment> attachments = service.allFilesInfo();
        return new Result("All files info", true, attachments);
    }


    @GetMapping("/download/{fileId}")
    public void download(@PathVariable Integer fileId, HttpServletResponse response){
        service.downloadFile(fileId, response);
    }


    @DeleteMapping("/delete/{fileId}")
    public Result deleteFile(@PathVariable Integer fileId) {
        Result result = service.deleteFile(fileId);
        return result;
    }


}
