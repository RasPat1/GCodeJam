
public class Car {
	long speed;
	double originalPos;
	double pos;
	char lane;
	static int length =5;

	public Car(String[] s) {
		this.lane = s[0].charAt(0);
		this.speed = Integer.parseInt(s[1]);
		this.originalPos = Float.parseFloat(s[2]);
		this.pos = Float.parseFloat(s[2]);
	}
	
	public void changeLane() {
		if(lane == 'R') {
			lane ='L';
		} else lane = 'R';
	}
	
}
