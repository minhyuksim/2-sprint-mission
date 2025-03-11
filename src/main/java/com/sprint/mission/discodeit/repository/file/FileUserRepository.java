package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileUserRepository implements UserRepository {
    private static final String FILE_PATH = "data/users.ser";

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
    public User save(User user) {
        List<User> users = loadFromFile();
        users.add(user);
        saveToFile(Paths.get(FILE_PATH), users);
        return user;
    }

    @Override
    public User findById(UUID id) {
        return loadFromFile().stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("채널이 없음"));
    }

    @Override
    public List<User> findAll() {
        return loadFromFile();
    }

    @Override
    public void update(User user) {
        List<User> users = loadFromFile();
        users.removeIf(u -> u.getId().equals(user.getId()));
        users.add(user);
        saveToFile(Paths.get(FILE_PATH), users);
    }

    @Override
    public void delete(UUID id) {
        List<User> users = loadFromFile();
        users.removeIf(u -> u.getId().equals(id));
        saveToFile(Paths.get(FILE_PATH), users);
    }

    private static <T> void saveToFile(Path filePath, T data) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }
    }

    private List<User> loadFromFile() {
        Path filePath = Paths.get(FILE_PATH);
        if (Files.exists(filePath)) {
            try (FileInputStream fis = new FileInputStream(filePath.toFile());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object data = ois.readObject();
                if (data instanceof List) {
                    return (List<User>) data;
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("파일 로드 중 오류 발생", e);
            }
        }
        return new ArrayList<>();
    }
}
