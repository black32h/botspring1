package org.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LaunchPoolReader {

    private static final Logger logger = LoggerFactory.getLogger(LaunchPoolReader.class);

    @Value("${launchpools.filePath}")
    private String filePath;  // Шлях до файлу з пуллами, передається через конфігурацію

    public List<LaunchPoolInfo> loadLaunchPools() {
        List<LaunchPoolInfo> pools = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Пропускаємо заголовок
            while ((line = reader.readLine()) != null) {
                pools.add(parseLaunchPoolInfo(line));
            }
        } catch (IOException e) {
            logger.error("Error reading launch pools from file: {}", e.getMessage(), e);
        }
        return pools;
    }

    private LaunchPoolInfo parseLaunchPoolInfo(String line) {
        String[] parts = line.split(",");
        String exchange = parts[0].trim();
        String launchPool = parts[1].trim();
        String pools = parts[2].trim();
        String period = parts[3].trim();
        PoolStatus status = PoolStatus.valueOf(parts[4].trim());
        return new LaunchPoolInfo(exchange, launchPool, pools, period, status);
    }

    public enum PoolStatus {
        ACTIVE,
        INACTIVE
    }
}
