package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    private static final String FILE_PATH = "data/channels.ser";


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
    public Channel save(Channel channel) {
        List<Channel> channels = loadFromFile();
        channels.add(channel);
        saveToFile(Paths.get(FILE_PATH), channels);
        return channel;
    }

    @Override
    public Channel findById(UUID id) {
        return loadFromFile().stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("채널이 없음"));
    }

    @Override
    public List<Channel> findAll() {
        return loadFromFile();
    }

    @Override
    public void update(Channel channel) {
        List<Channel> channels = loadFromFile();
        channels.removeIf(c -> c.getId().equals(channel.getId()));
        channels.add(channel);
        saveToFile(Paths.get(FILE_PATH), channels);
    }

    @Override
    public void delete(UUID id) {
        List<Channel> channels = loadFromFile();
        channels.removeIf(channel -> channel.getId().equals(id));
        saveToFile(Paths.get(FILE_PATH), channels);
    }

    private static <T> void saveToFile(Path filePath, T data) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Channel> loadFromFile() {
        Path filePath = Paths.get(FILE_PATH);
        if (Files.exists(filePath)) {
            try (FileInputStream fis = new FileInputStream(filePath.toFile());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object data = ois.readObject();
                if (data instanceof List) {
                    return (List<Channel>) data;
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("파일 로드 중 오류 발생", e);
            }
        }
        return new ArrayList<>();
    }
}