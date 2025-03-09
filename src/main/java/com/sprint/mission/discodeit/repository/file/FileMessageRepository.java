package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileMessageRepository implements MessageRepository {
    private static final String FILE_PATH = "data/messages.ser";

    private static void init() {
        if (!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createDirectories(Paths.get(FILE_PATH));
            } catch (IOException e) {
                throw new RuntimeException("파일 초기화 중 오류 발생", e);
            }
        }
    }
    @Override
    public Message save(Message message) {
        List<Message> messages = loadFromFile();
        messages.add(message);
        saveToFile(Paths.get(FILE_PATH), messages);
        return message;
    }

    @Override
    public Message findById(UUID id) {
        return loadFromFile().stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("채널이 없음"));
    }

    @Override
    public List<Message> findAll() {
        return loadFromFile();
    }

    @Override
    public void update(Message message) {
        List<Message> messages = loadFromFile();
        messages.removeIf(m -> m.getId().equals(message.getId()));
        messages.add(message);
        saveToFile(Paths.get(FILE_PATH), messages);
    }

    @Override
    public void delete(UUID id) {
        List<Message> messages = loadFromFile();
        messages.removeIf(m -> m.getId().equals(id));
        saveToFile(Paths.get(FILE_PATH), messages);
    }

    private static <T> void saveToFile(Path filePath, T data) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    private List<Message> loadFromFile() {
        Path filePath = Paths.get(FILE_PATH);
        if (Files.exists(filePath)) {
            try (FileInputStream fis = new FileInputStream(filePath.toFile());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object data = ois.readObject();
                if (data instanceof List) {
                    return (List<Message>) data;
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("파일 로드 중 오류 발생", e);
            }
        }
        return new ArrayList<>();
    }
}
