package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class FileChannelService implements ChannelService {

    private static final String FILE_PATH = "channels.ser";


    @Override
    public Channel create(ChannelType type, String name, String description) {
        return null;
    }

    @Override
    public Channel find(UUID channelId) {
        return null;
    }

    @Override
    public List<Channel> findAll() {
        return List.of();
    }

    @Override
    public Channel update(UUID channelId, String newName, String newDescription) {
        return null;
    }

    @Override
    public void delete(UUID channelId) {

    }

    public static <T> void saveChannelToFile(Path filePath, T data) {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ){
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Channel> loadChannelsFromFile() {
        List<Channel> channels = new ArrayList<>();
        if (Files.exists(Paths.get(FILE_PATH))) {
            try (FileInputStream fis = new FileInputStream(FILE_PATH);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Object data = ois.readObject();
                if (data instanceof List) {
                    channels = (List<Channel>) data;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return channels;
    }

}