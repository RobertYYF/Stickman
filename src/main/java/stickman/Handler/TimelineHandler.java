package stickman.Handler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import stickman.levels.LevelImp;
import stickman.view.GameWindow;

import static stickman.levels.LevelImp.currentLevelScore;
import static stickman.levels.LevelImp.score;

public class TimelineHandler {

    private Timeline timeline;
    private int countTime;
    private double previousTime;

    public TimelineHandler() {
        this.timeline =  new Timeline();
    }

    public void init() {
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void increment() {
        Duration newValue= timeline.getCurrentTime();
        if (newValue.toSeconds() - previousTime < 0) {
            countTime ++;
            GameWindow.time.setText("Time: " + countTime);
            if (countTime < LevelImp.targetTime) {
                score ++;
                currentLevelScore ++;
            } else {
                if (currentLevelScore > 0) {
                    score --;
                    currentLevelScore --;
                }
            }
            GameWindow.currentLevelScore.setText("Level Score: " + String.valueOf(currentLevelScore));
            GameWindow.totalScore.setText("Total Score: " + String.valueOf(score));
        }

        previousTime = newValue.toSeconds();
    }

    public void stopTime() {
        timeline.stop();
    }

}
