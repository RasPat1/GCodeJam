
public class Car {
	double speed;
	double originalPos;
	double pos;
	char lane;
	final static int LENGTH = 5;

	public Car(String[] s) {
		this.lane = s[0].charAt(0);
		this.speed = Integer.parseInt(s[1]);
		this.originalPos = Float.parseFloat(s[2]);
		this.pos = Float.parseFloat(s[2]);
	}

	public Car(char lane, double speed, double pos) {
		this.lane = lane;
		this.speed = speed;
		this.pos = pos;
		this.originalPos = pos;
	}

	public void changeLane() {
		if(lane == 'R') {
			lane ='L';
		} else lane = 'R';
	}

}
