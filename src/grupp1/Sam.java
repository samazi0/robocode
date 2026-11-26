package grupp1;
import robocode.*;

public class Sam extends TeamRobot {
    private int direction = 1;

    public void run() {
        while (true) {
            //avoidWalls();
            ahead(40 * direction);
        }
    }

    private void avoidWalls() {
        // My own method to help prevent hitting walls in advance
    }

    private void attack(String robotName, double robotDistance) {
        if (isTeammate(robotName)) {
            return;
        }

        if (robotDistance < 100) {
            fire(3);
        } else {
            fire(1);
        }
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        direction = -direction; // Change to opposite direction
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        attack(event.getName(), event.getDistance());
    }
}
