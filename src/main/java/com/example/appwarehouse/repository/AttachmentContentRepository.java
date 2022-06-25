package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    void deleteByAttachmentId(Integer attachment_id);

    Optional<AttachmentContent> findByAttachment_Id(Integer attachment_id);
}
