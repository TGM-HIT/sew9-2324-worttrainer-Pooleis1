package tgm.ac.at.parnold;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface WordTrainerSaveManager {

    void save(String filepath, WordspellTrainer wordTrainer) throws IOException;

    WordspellTrainer load(String filepath) throws FileNotFoundException,IOException;
}
