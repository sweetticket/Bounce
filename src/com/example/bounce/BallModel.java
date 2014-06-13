package com.example.bounce;

import com.example.bounce.MainActivity.PlaceholderFragment;

public class BallModel {
	private final float mRadius = 60;
	private float mKE;
	private float mPE;
	private float mPrevX;
	private float mPrevY;
	private float mElasticity;
	
	public BallModel(){
		mPrevX = PlaceholderFragment.getScreenWidth() / 2;
		mPrevY = PlaceholderFragment.getScreenHeight() / 2;
		mKE = 0;
		mPE = 1;
		mElasticity = 1;
	}
	
	/** Getter: previous x coordinate */
	public float getPrevX(){
		return mPrevX;
	}
	
	/** Setter: set previous x coordinate */
	public void setPrevX(float x){
		mPrevX = x;
	}
	
	/** Getter: previous x coordinate */
	public float getPrevY(){
		return mPrevY;
	}
	
	/** Setter: set previous y coordinate */
	public void setPrevY(float y){
		mPrevY = y;
	}
	
	/** Getter: PE */
	public float getPE(){
		return mPE;
	}
	
	/** Getter: KE */
	public float getKE(){
		return mKE;
	}
	
	/** Setter: set Energies */
	public void setEnergies(float y){
		//TODO
		//calculate PE and KE ratio according to y position dropped
		//set both PE and KE
	}
	
	/** Getter: Elastic PE */
	public float getElasticity(){
		return mElasticity;
	}
	
	/** Setter: Elasticity */
	public void setElasticity(){
		//TODO
		//on bounce
		//set PE and KE too
	}
	
	/** Getter: ball radius */
	public float getRadius(){
		return mRadius;
	}
}
