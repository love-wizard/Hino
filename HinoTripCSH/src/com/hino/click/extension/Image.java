package com.hino.click.extension;

import org.apache.click.control.AbstractControl;

public class Image extends AbstractControl{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8190549012373086742L;
	
	
	public Image()
	{
		
		setAttribute("src", "");
	}
	
	public Image(String name)
	{
		setName(name);
		setAttribute("src", "");
	}
	
	
	@Override
	public String getTag() { 
         // Return the HTML tag 
         return "img"; 
    } 
	
	public void setSrc(String src)
	{
		setAttribute("src", src);
		
	}
	
	public void setSize(int width, int height)
	{
		setAttribute("width", ""+width);
		setAttribute("height", ""+height);
	}

	
	public void setTitle(String title)
	{
		setAttribute("title", title);
		
	}
	
}
