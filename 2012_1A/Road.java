import java.util.Arrays;
import java.math.*;
public class Road {
	Car[] r;
	int roadIndex=0;
	double minCrashTime;

	public Road(int numberOfCars) {
		r = new Car[numberOfCars];
	}

	public void addCar(Car c) {
		r[roadIndex] = c;
		roadIndex++;
	}

	public void sortByPos() {
	Arrays.sort(r, new java.util.Comparator<Car>() {
		@Override
		public int compare(Car a, Car b) {
//			if(a.pos < b.pos) return -1;
//			if(a.pos > b.pos)return 1;
//			return 0;
			return Double.compare(a.pos, b.pos);
		}
	});
}


	//checks if any two cars are collided
	public boolean isValid() {
		for(int i = 0; i < r.length -1; i++) {
			for(int j=i+1; j< r.length; j++) {
				if(Math.abs(r[i].pos - r[j].pos) < Car.LENGTH
						&& r[i].lane == r[j].lane ) {
						return false;
					}
				}
			}
		return true;
	}

	public boolean canChange(Car c) {
		for(Car i : r) {
			if(i == c) continue;
			if(Math.abs(c.pos - i.pos) < Car.LENGTH) return false;
		}
		return true;
	}

	public boolean willCollide(Car a, Car b) {
		if(a.lane==b.lane && a.speed > b.speed) return true;
		return false;
	}

	public double whenCollide(Car a, Car b) {
		double time = 0;
//getting closer >0 , farther < 0 , touching = 0, stationary to each other = -1
		if(a.speed == b.speed) return -1;
		time = (double) (b.pos-a.pos - Car.LENGTH)  / (double) (a.speed - b.speed);
//returning zero means touching with relative speed.  Highest priority imminent collision.
		return (time>=0)?time:-2;
	}

	public void changeLane() {
		double minCrashTime = 1000002;
		for(int i = 0; i < r.length; i++){
			double curLaneTime=1000001, otherLaneTime=1000001;
			for(int j = 0; j < r.length; j++) {
				if(i==j) continue;
				double time = whenCollide(r[i], r[j]);
				if(time>=0) {
					if(r[i].lane == r[j].lane) {
						curLaneTime = (time<curLaneTime)?time:curLaneTime;
					} else {
            otherLaneTime = (time<otherLaneTime)?time:otherLaneTime;
          }
          minCrashTime = (time<minCrashTime)?time:minCrashTime;
				}
			}
			if(otherLaneTime > curLaneTime  && canChange(r[i])) r[i].changeLane();
		}
//		sortByPos();
	}


	public boolean isStable() {
		for(int i =0; i < r.length-1; i++) {
			for(int j=i + 1; j < r.length; j++) {
				if(r[i].lane==r[j].lane && r[i].speed > r[j].speed) return false;
			}
		}
		return true;
	}


	public void move(double time) {
//		double time = timeStep*stepNumber;
//		time = (int) (time*10000);
//		time = time / 10000;
//		int round = 10000;
		for(Car c : r) {
			c.pos = c.originalPos + c.speed*time;
//			c.pos = (int) (c.pos * round);
//			c.pos = c.pos / round;
		}
		sortByPos();
	}

	public void print() {
		for(Car c: r) {
			System.out.println(c.lane+" "+c.speed+" "+String.format("%.2f", c.pos));
		}
	}
}
