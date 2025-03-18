/*package com.sprint.mission.discodeit.service.file;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileUserService implements UserService {
    private static final String FILE_PATH = "users.ser";

    @Override
    public User create(String username, String email, String password) {
        User user = new User(username, email, password);
        List<User> users = loadUsersFromFile();
        users.add(user);
        saveUserToFile(Paths.get(FILE_PATH), users);
        return user;
    }

    @Override
    public User find(UUID userId) {
        List<User> users = loadUsersFromFile();
        return users.stream().filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("유저가 없음"));
    }

    @Override
    public List<User> findAll() {
        return loadUsersFromFile();
    }

    @Override
    public User update(UUID userId, String newUsername, String newEmail, String newPassword) {
        List<User> users = loadUsersFromFile();
        User updateduser = users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("유저가 없음"));
        return updateduser;
    }

    @Override
    public void delete(UUID userId) {
        List<User> users = loadUsersFromFile();
        users.removeIf(user -> user.getId().equals(userId));
        saveUserToFile(Paths.get(FILE_PATH), users);
    }

    public static void userinit() {
        if(!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createDirectories(Paths.get(FILE_PATH));
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void saveUserToFile(Path filePath, T data) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> loadUsersFromFile() {
        List<User> users = new ArrayList<>();
        if (Files.exists(Paths.get(FILE_PATH))) {
            try (FileInputStream fis = new FileInputStream(FILE_PATH);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object data = ois.readObject();
                if (data instanceof List) {
                    users = (List<User>) data;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
