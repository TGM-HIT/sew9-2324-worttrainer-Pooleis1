package tgm.ac.at.parnold;

public interface WordTrainerSaveManager {

    void save(String filepath, WordspellTrainer wordTrainer);

    WordspellTrainer load(String filepath);
}
