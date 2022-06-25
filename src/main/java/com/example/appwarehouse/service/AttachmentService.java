package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.entity.AttachmentContent;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.AttachmentContentRepository;
import com.example.appwarehouse.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        //
        Attachment attachment = new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        //
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("file is saved", true, savedAttachment.getId());
    }

    public List<Attachment> allFilesInfo() {
        List<Attachment> all = attachmentRepository.findAll();
        return all;
    }

    public Result fileInfo(Integer fileId) {
        Optional<Attachment> byId = attachmentRepository.findById(fileId);
        if (!byId.isPresent())
            return new Result("File not found", false);
        Attachment attachment = byId.get();
        return new Result("Here are all info about file", true, attachment);
    }


    public Result deleteFile(Integer fileId) {
        Optional<Attachment> byId = attachmentRepository.findById(fileId);
        if (!byId.isPresent())
            return new Result("file not found", false);
        attachmentContentRepository.deleteByAttachmentId(fileId);
        attachmentRepository.deleteById(fileId);

        return new Result("File deleted", true);
    }

    @SneakyThrows
    public void downloadFile(Integer fileId, HttpServletResponse response) {
        Optional<Attachment> byId = attachmentRepository.findById(fileId);
        if (byId.isPresent()) {
            Attachment attachment = byId.get();

            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachment_Id(attachment.getId());
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();

                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }

    }
}
