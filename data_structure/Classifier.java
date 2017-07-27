package com.image.utils;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.ml.SVM;

public class Classifier {
	
	SVM svm;
	
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		SVM svm = SVM.create();
		
	}
	
	public Classifier() {
		
	}
	
	public List<Double> calculateMean(Mat image,int row,int col) {
		List<Double> mean = new ArrayList<Double>();
		for(int i=0;i<col;i++) {
			mean.add(0.0);
		}
		for(int i=0;i<row;i ++) {
			for(int j=0; j<col; j++) {
				mean.add( j, (mean.get(j) + image.get(i, j)[0]) );
			}
		}
		for(int i=0;i<col;i++) {
			mean.add( i, (mean.get(i)/row) );
		}
		return mean;
	}
	
	public List<Double> calculateSTDDeviation(Mat image,int row,int col, List<Double> mean) {
		List<Double> stdDeviation = new ArrayList<Double>();
		for(int i=0;i<col;i++) {
			stdDeviation.add(0.0);
		}
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				stdDeviation.add(j, (stdDeviation.get(j)+Math.pow(image.get(i, j)[0]-mean.get(j), 2)) );
			}
		}
		
		for(int i=0;i<col;i++) {
			stdDeviation.add(i, Math.sqrt(stdDeviation.get(i)/row));
		}
		return stdDeviation;
	}
	
}
