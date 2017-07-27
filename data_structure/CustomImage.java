package com.image.utils;

import org.opencv.core.Mat;
import org.opencv.core.Point;

public class CustomImage {
	Mat imageMatrix;
	Point cog;
	int xUpper;
	int xLower;
	int yRight;
	int yLeft;
	double cogDistance;
	double cogAngle;
	int signaturePixelCount;
	public Mat getImageMatrix() {
		return imageMatrix;
	}
	public void setImageMatrix(Mat imageMatrix) {
		this.imageMatrix = imageMatrix;
	}
	public Point getCog() {
		return cog;
	}
	public void setCog(Point cog) {
		this.cog = cog;
	}
	public int getxUpper() {
		return xUpper;
	}
	public void setxUpper(int xUpper) {
		this.xUpper = xUpper;
	}
	public int getxLower() {
		return xLower;
	}
	public void setxLower(int xLower) {
		this.xLower = xLower;
	}
	public int getyRight() {
		return yRight;
	}
	public void setyRight(int yRight) {
		this.yRight = yRight;
	}
	public int getyLeft() {
		return yLeft;
	}
	public void setyLeft(int yLeft) {
		this.yLeft = yLeft;
	}
	public double getCogDistance() {
		return cogDistance;
	}
	public void setCogDistance(double cogDistance) {
		this.cogDistance = cogDistance;
	}
	public double getCogAngle() {
		return cogAngle;
	}
	public void setCogAngle(double cogAngle) {
		this.cogAngle = cogAngle;
	}
	public int getSignaturePixelCount() {
		return signaturePixelCount;
	}
	public void setSignaturePixelCount(int signaturePixelCount) {
		this.signaturePixelCount = signaturePixelCount;
	}
}
