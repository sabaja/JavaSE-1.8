package com.miscellaneus;


abstract class AbstractShape {
	public abstract void paint();

}

class Triangle extends AbstractShape{
	@Override
	public void paint(){
	
	}
}

abstract class IsoTriangle extends Triangle{
	@Override
	abstract public void paint();
	
}

class RealISoTriangle extends IsoTriangle{

	@Override
	public void paint() {
		// TODO Auto-generated method stub
		
	}
	
}
