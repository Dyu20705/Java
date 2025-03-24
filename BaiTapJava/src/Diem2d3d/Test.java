package Diem2d3d;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void init() {
		List<Point2d> points = new ArrayList<>();
		
		points.add(new Point2d(1, 2));
		points.add(new Point3d(3, 4, 5));
		points.add(new Point2d(6, 7));
        points.add(new Point3d(8, 9, 10));
        
        int sum = 0;
        for (int i = 0; i < points.size(); i++) {
            Point2d p = points.get(i);
            if (p.whoAmI() == 2) {
                sum += p.distance(new Point2d(0, 0));
            } else { 
                sum += p.distance(new Point3d(0, 0, 0));
            }
        }
        System.out.println("Tổng khoảng cách: " + sum);
        
        List<Point2d> symmetricPoints = new ArrayList<>();
        for (Point2d point : points) {
            symmetricPoints.add(point.symmetric());
        }
        
        points.addAll(symmetricPoints);

        System.out.println(points.toString());
		
	}
	
	public static void main(String[] args) {
		init();
	}

}
