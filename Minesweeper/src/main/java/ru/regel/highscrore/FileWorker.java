package ru.regel.highscrore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileWorker {

    private static final String FILENAME = System.getProperty("user.home") + "/records.txt";
    private static final String COMMA = ",";

    public void write(List<HighScore> highScores) {
        List<String> records = highScores.stream()
                .map(e -> e.getName() + COMMA + e.getTime())
                .collect(Collectors.toList());

        try {
            Files.write(Paths.get(FILENAME), records);
        } catch (IOException e) {
            System.out.println("File not found " + FILENAME);
        }
    }

    public List<HighScore> read() {
        List<HighScore> highScores = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILENAME));
            for (String line : lines) {
                String[] param = line.split(COMMA);
                if (param.length == 2) {
                    highScores.add(new HighScore(param[0], Integer.parseInt(param[1])));
                }
            }
        } catch (IOException e) {
            System.out.println("File not found" + FILENAME);
        }
        return highScores;
    }
}
