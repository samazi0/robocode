package grupp1;

import robocode.*;
import java.awt.Color;

public class SandraRobot extends TeamRobot {

    public void run() {

        // Ändrar färgen på min robot
        setRobotColors();

        while (true) {

            // Svänger om roboten är för nära en vägg
            if (isNearWall()) {
                turnRight(90);
            }

            ahead(50);
            turnGunLeft(90);
        }
    }

    // Om roboten kör in i en vägg ska den backa och svänga
    public void onHitWall(HitWallEvent event) {
        back(100);
        turnRight(90);
    }

    // När roboten upptäcker en annan robot
    public void onScannedRobot(ScannedRobotEvent event) {

        // Kollar så att det inte är någon i mitt eget lag
        if (isTeammate(event.getName())) {
            return;
        }

        // Skjuter hårdare om fienden är nära
        if (event.getDistance() < 100) {
            fire(3);
        } else {
            fire(1);
        }
    }

    // Om min robot krockar med en annan robot
    public void onHitRobot(HitRobotEvent event) {

        // Backar om den andra roboten är framför min,
        // annars kör den framåt
        if (event.getBearing() > -90 && event.getBearing() <= 90) {
            back(100);
        } else {
            ahead(100);
        }
    }

    // Byter riktning om roboten blir träffad
    public void onHitByBullet(HitByBulletEvent event) {
        turnRight(90);
        ahead(100);
    }

    // Kollar om roboten är för nära någon av väggarna
    public boolean isNearWall() {

        // Avståndet jag vill hålla till väggarna
        double margin = 100;

        // Returnerar true om roboten är för nära en vägg
        return getX() < margin
                || getY() < margin
                || getX() > getBattleFieldWidth() - margin
                || getY() > getBattleFieldHeight() - margin;
    }

    // Gör min robot lila och rosa
    public void setRobotColors() {
        setBodyColor(new Color(180, 150, 220));
        setGunColor(new Color(255, 20, 147));
        setRadarColor(new Color(255, 182, 193));
        setBulletColor(new Color(148, 0, 211));
        setScanColor(new Color(255, 105, 180));
    }
}