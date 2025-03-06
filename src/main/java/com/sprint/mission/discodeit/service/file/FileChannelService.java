package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.ChannelType;
import com.sprint.mission.discodeit.service.ChannelService;

import java.io.*;
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

    private void saveChannelToFile(Channel channel) {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ){
            oos.writeObject(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}