package com.image.utils;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Point;

public class Methods {
	public void calculateCOG(CustomImage image) {
		Point cog = new Point(0,0);
		Mat imageMat = image.getImageMatrix();
		int sign_pixel_count = 0;
		int cog_x = 0;
		int cog_y = 0;
		for(int i=image.getxUpper()+1; i < image.getxLower()-1; i++) {
			for(int j=image.getyLeft()+1; j< image.getyRight()-1; j++) {
				if(imageMat.get(i, j)[0] == 0) {
					cog_x += i;
					cog_y += j;
					sign_pixel_count++;
				}
			}
		}
		if(sign_pixel_count != 0) {
			cog_x /= sign_pixel_count;
			cog_y /= sign_pixel_count;
		} else {
			cog_x = (image.getxLower() + image.getxUpper()) / 2;
			cog_y = (image.getyLeft() + image.getyRight()) / 2;
		}
		cog.x = cog_x;
		cog.y = cog_y;
		image.setCog(cog);
		image.setSignaturePixelCount(sign_pixel_count);
	}
	
	public void calculateCOG(List<CustomImage> images) {
		int index = 0;
		for(index = 0; index < images.size(); index++) {
			calculateCOG(images.get(index));
		}
	}
	
	public double calculateDistance(Point point) {
		return Math.sqrt(Math.pow(point.x, 2) + Math.pow(point.y, 2));
	}
	
	public void calculateCOGDistances(List<CustomImage> images) {
		for(CustomImage image : images) {
			image.setCogDistance(calculateDistance(image.getCog()));
		}
	}
	
	public double calculateAngle(Point point) {
		if(point.x == 0) {
			return 1.57;
		} else {
			return Math.abs(Math.atan(point.y/point.x));
		}
	}
	
	public void calculateCOGAngles(List<CustomImage> subImages) {
		for(CustomImage image : subImages) {
			image.setCogAngle(calculateAngle(image.getCog()));
		}
	}
	public List<Double> generateFeatureVector(List<CustomImage> subImages) {
		calculateCOGAngles(subImages);
		calculateCOGDistances(subImages);
		List<Double> fv = new ArrayList<Double>();
		for(CustomImage image: subImages) {
			fv.add(image.getSignaturePixelCount()*1.0); //adding signpixel count as fv
		}
		for(CustomImage image: subImages) {
			fv.add(image.getCogDistance());
		}
		for(CustomImage image: subImages) {
			fv.add(image.getCogAngle());
		}
		
		return fv;
	}
}
