package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileMessageService implements MessageService {

    private static final String FILE_PATH = "messages.ser";

    private final ChannelService channelService;
    private final UserService userService;

    public FileMessageService(ChannelService channelService, UserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    public Message create(String content, UUID channelId, UUID authorId) {
        try{
            channelService.find(channelId);
            userService.find(authorId);
        }catch (NoSuchElementException e){
            throw e;
        }

        Message message = new Message(content, channelId, authorId);
        List<Message> messages = loadMessageFromFile();
        messages.add(message);
        saveMessageToFile(Path.of(FILE_PATH), messages);

        return message;
    }

    @Override
    public Message find(UUID messageId) {
        List<Message> messages = loadMessageFromFile();
        return messages.stream()
                .filter(message -> message.getId().equals(messageId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("메세지가 없음"));
    }

    @Override
    public List<Message> findAll() {
        return loadMessageFromFile();
    }

    @Override
    public Message update(UUID messageId, String newContent) {
        List<Message> messages = loadMessageFromFile();
        Message updatedmessage = messages.stream()
                .filter(message -> message.getId().equals(messageId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("메세지가 없음"));
        updatedmessage.update(newContent);
        saveMessageToFile(Path.of(FILE_PATH), messages);

        return updatedmessage;
    }

    @Override
    public void delete(UUID messageId) {
        List<Message> messages = loadMessageFromFile();
        messages.removeIf(message -> message.getId().equals(messageId));
    }

    public static void messageinit() {
        if(!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createDirectories(Paths.get(FILE_PATH));
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void saveMessageToFile(Path filePath, T data) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Message> loadMessageFromFile() {
        List<Message> messages = new ArrayList<>();
        if (Files.exists(Paths.get(FILE_PATH))) {
            try (FileInputStream fis = new FileInputStream(FILE_PATH);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object data = ois.readObject();
                if (data instanceof List) {
                    messages = (List<Message>) data;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }
}
