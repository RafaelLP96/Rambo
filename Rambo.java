package sample;
import robocode.*;

import robocode.*;
import robocode.HitRobotEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import java.awt.*;
import java.lang.Math;

public class Rambo extends AdvancedRobot {
    boolean movingForward;
	boolean lockForward = false;
	boolean getOutOfBourder=false;
	boolean type = false;	
	
int count = 0;
	
    public void run() {
	
        // Cores
        setBodyColor(java.awt.Color.yellow);
        setGunColor(java.awt.Color.blue);
        setRadarColor(java.awt.Color.black);
        setBulletColor(java.awt.Color.red);
        setScanColor(java.awt.Color.blue);

        // Loop 
        while (true) {
            // Movimentar
			if (lockForward=true){
			
				movingForward = true;
	            ahead(200);
				turnRight(85);
				setTurnRadarRight(360);
			}
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

			double distance = e.getDistance();
			
	        double power = 3 /distance*200; // Quanto maior a distância, menor a força do tiro
	        
	        
	        if (power <1) {
	            power = 1;
	        } else if (power > 3) {
	            power = 3;
	        }	
		
        setTurnGunRight((getHeading() + e.getBearing()) - getGunHeading());
        // Atirar com a força calculada e nao atirar se estiver longe
	
		if (distance<300){
			setBack(90);
			lockForward=true;
		}else{
			lockForward=false;
		}
	if (distance<800){
	        fire(power);
			//out.println(Math.round(power));
		}
    }
	
	public void onHitByBullet(HitByBulletEvent e){
		back(50);
		setTurnLeft(30);
		
	}
	
	public void reverseDirection() {
		if (movingForward) {
			setBack(40000);
			movingForward = false;
		} else {
			setAhead(40000);
			movingForward = true;
		}
	}
	
	public void onHitRobot(HitRobotEvent e) {
		
		if (movingForward)
				back(50);
			else
				setAhead(50);
			setTurnRight(180);
	}
	
	public void onHitWall(HitWallEvent e) {
	
			if (movingForward)
				back(50);
			else
				setAhead(50);
			turnRight(180);
			//setTurnRight();
			//reverseDirection();
		
	}
	

}
