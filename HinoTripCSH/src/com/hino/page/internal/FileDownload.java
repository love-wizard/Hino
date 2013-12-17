package com.hino.page.internal;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.click.Page;
import org.apache.click.util.Bindable;
import org.apache.click.util.ClickUtils;


import com.hino.util.FileCenter;
import java.io.InputStream;

public class FileDownload extends BasePage{
	@Bindable protected String filename;

	public void onInit()
	{
		//HttpServletResponse r = this.getContext().getResponse();
		//System.out.println(filename);
		
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
		    	//System.out.println(ClickUtils.getMimeType(contenttype));
		    	response.setContentType(ClickUtils.getMimeType(contenttype));
			    response.setHeader("Content-Disposition","attachment;filename="+filename);
			    response.setHeader("Pragma", "no-cache");
			    ServletOutputStream out = null;
			    InputStream inputStream = null;
			    try {
			    	
			        inputStream = new FileInputStream(fc.load_file(filename));
			        /*
			        byte[] b1=new byte[fis.available()];
			        fis.read(b1);
			        FileOutputStream out=new FileOutputStream("文件路径");
			        out.write(b1);
			        */
			        out = response.getOutputStream();
			        BufferedOutputStream bos = new BufferedOutputStream(out);

			        byte[] buff = new byte[inputStream.available()];
			        int bytesRead;

			        while(-1 != (bytesRead = inputStream.read(buff, 0, buff.length))) {
			        	bos.write(buff, 0, bytesRead);
			        } 
			        /*
			        
		            byte [] loader = new byte [inputStream.available() ];
		            while ( (inputStream.read( loader )) > 0 ) {
		              out.write( loader );
		            }
			        
			        PrintWriter writer = response.getWriter();

			        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			        String line = reader.readLine();

			        while (line != null) {
			            writer.println(line);
			            
			            line = reader.readLine();
			        }
					*/
			        setPath(null);

			    } catch (IOException ioe) {
			        ioe.printStackTrace();
			        

			    } finally {
			        ClickUtils.close(inputStream);
			        ClickUtils.close(out);
			      
		            

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
