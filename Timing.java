public class Timing {
private static int ship = 0, shipDefault = 30;
private static int laser = 2, laserDefault = 2;
private static int shipFire = 5, shipFireDefault = 20;
private static int turretFire = 5, turretFireDefault = 20;

	public static boolean shipCanMove() {
		if(ship < 1) {
			ship = shipDefault;
			return true;
		}
		else {
			ship--;
			return false;
		}
	}
	
	public static boolean shipCanShoot() {
		if(shipFire < 1) {
			shipFire = shipFireDefault;
			return true;
		}
		else {
			shipFire--;
			return false;
		}
	}
	
	public static boolean turretCanShoot() {
		if(turretFire < 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void turretCounter() {
		turretFire--;
	}
	
	public static void turretCanShoot(boolean canShoot) {
		turretFire = turretFireDefault;
	}
	
	public static boolean laserCanMove() {
		if(laser < 1) {
			laser = laserDefault;
			return true;
		}
		else {
			laser--;
			return false;
		}
	}
}
