package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.BinaryContentCreateRequest;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.service.BinaryContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BasicBinaryContentService implements BinaryContentService {
    private final BinaryContentRepository binaryContentRepository;

    @Override
    public BinaryContent create(BinaryContentCreateRequest request) {
        String fileName = request.getFileName();
        byte[] bytes = request.getBytes();
        String contentType = request.getContentType();
        BinaryContent binaryContent = BinaryContent.builder()
                .fileName(fileName)
                .size((long)bytes.length)
                .contentType(contentType)
                .bytes(bytes).build();
        return binaryContentRepository.save(binaryContent);
    }

    @Override
    public BinaryContent find(UUID binaryContentId) {
        return binaryContentRepository.findById(binaryContentId)
                .orElseThrow(() -> new NoSuchElementException("BinaryContent with id " + binaryContentId + " not found"));
    }

    @Override
    public List<BinaryContent> findAllByIdIn(List<UUID> binaryContentIds) {
        return binaryContentRepository.findAllByIdIn(binaryContentIds).stream()
                .toList();
    }

    @Override
    public void delete(UUID binaryContentId) {
        if (!binaryContentRepository.existsById(binaryContentId)) {
            throw new NoSuchElementException("BinaryContent with id " + binaryContentId + " not found");
        }
        binaryContentRepository.deleteById(binaryContentId);
    }
}
