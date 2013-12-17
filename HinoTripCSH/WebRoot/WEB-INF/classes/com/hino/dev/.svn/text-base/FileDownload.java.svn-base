package com.hino.dev;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.click.Page;
import org.apache.click.util.Bindable;
import org.apache.click.util.ClickUtils;


import com.hino.util.FileCenter;
import java.io.InputStream;

public class FileDownload extends Page{
	@Bindable protected String filename;

	public void onInit()
	{
		//HttpServletResponse r = this.getContext().getResponse();
		System.out.println(filename);
		
	}
	
	
	
	public void onGet() {
	    
		fileOutput();
	    
	    
	}
	
	public void onPost()
	{
		//filename ="xxx";
		fileOutput();
	}
	
	public void fileOutput()
	{
		HttpServletResponse response = getContext().getResponse();
	    String contenttype = "text/plain";
	    FileCenter fc = new FileCenter();
	    if(filename!=null)
	    {
	    	if(fc.file_exist(filename))
	    	{
	    		String[] str = filename.split("\\.");
		    	if (str.length>1)
		    	{
		    		contenttype = str[str.length-1];
		    	}
		    	
		    	response.setContentType(ClickUtils.getMimeType(contenttype));
			    response.setHeader("Content-Disposition","attachment;filename="+filename);
			    response.setHeader("Pragma", "no-cache");

			    InputStream inputStream = null;
			    try {
			    	
			        inputStream = new FileInputStream(fc.load_file(filename));

			        PrintWriter writer = response.getWriter();

			        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			        String line = reader.readLine();

			        while (line != null) {
			            writer.println(line);
			            line = reader.readLine();
			        }

			        setPath(null);

			    } catch (IOException ioe) {
			        ioe.printStackTrace();
			        

			    } finally {
			        ClickUtils.close(inputStream);
			    }
		    	
		    	
		    	
	    	} else
	    	{
	    		//no such a file
	    	}
	    	
	    	
	    	
	    } else
	    {
	    	//no such a file
	    }
	}
	
	
}
