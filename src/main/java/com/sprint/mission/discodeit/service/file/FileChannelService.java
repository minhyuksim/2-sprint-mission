package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileChannelService implements ChannelService {

    private static final String FILE_PATH = "channels.ser";


    @Override
    public Channel create(ChannelType type, String name, String description) {
        Channel channel = new Channel(type, name, description);
        List<Channel> channels = loadChannelsFromFile();
        channels.add(channel);
        saveChannelToFile(Paths.get(FILE_PATH), channels);
        return channel;
    }

    @Override
    public Channel find(UUID channelId) {
        List<Channel> channels = loadChannelsFromFile();
        return channels.stream()
                .filter(channel -> channel.getId().equals(channelId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("채널이 없음"));
    }

    @Override
    public List<Channel> findAll() {
        return loadChannelsFromFile();
    }

    @Override
    public Channel update(UUID channelId, String newName, String newDescription) {
        List<Channel> channels = loadChannelsFromFile();
        Channel updatedChannel = channels.stream()
                .filter(channel -> channel.getId().equals(channelId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("채널이 없음"));
        updatedChannel.update(newName, newDescription);
        saveChannelToFile(Paths.get(FILE_PATH), channels);
        return updatedChannel;
    }

    @Override
    public void delete(UUID channelId) {
        List<Channel> channels = loadChannelsFromFile();
        channels.remove(channelId);
        saveChannelToFile(Paths.get(FILE_PATH), channels);
    }

    public static void channelinit() {
        if(!Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.createDirectories(Paths.get(FILE_PATH));
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
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