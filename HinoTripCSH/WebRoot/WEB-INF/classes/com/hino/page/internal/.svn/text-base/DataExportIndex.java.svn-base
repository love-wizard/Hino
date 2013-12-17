package com.hino.page.internal;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.click.Page;
import org.apache.click.util.Bindable;
import org.apache.click.util.ClickUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import com.hino.util.FileCenter;
import java.io.InputStream;
import java.util.List;

public class DataExportIndex extends BasePage{

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
	   
    	//System.out.println(ClickUtils.getMimeType(contenttype));
    	response.setContentType(ClickUtils.getMimeType("application/xls"));
	    response.setHeader("Content-Disposition","attachment;filename=data.xls");
	    response.setHeader("Pragma", "no-cache");
	    ServletOutputStream out = null;
	    BufferedOutputStream bos = null;
	    try {
	    	
	        /*
	        byte[] b1=new byte[fis.available()];
	        fis.read(b1);
	        FileOutputStream out=new FileOutputStream("文件路径");
	        out.write(b1);
	        */
	        out = response.getOutputStream();
	        bos = new BufferedOutputStream(out);

	        Workbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet");
			List<Object[]> list = getCustomerService().output_data();
			int rown = 0;
			
			Row row;

			for (Object[] obja : list)
			{
				row = sheet.createRow(rown++);
				
				for (int i = 0; i < obja.length; i++) {
					row.createCell(i).setCellValue((String)obja[i]);
					System.out.println((String)obja[i]);
				}
			}
			/*
			for (int i = 1; i < numberOfColumns + 1; i++) {
				row.createCell(i - 1).setCellValue(
						rsMetaData.getColumnLabel(i));
			}

			while (rs.next()) {
				row = sheet.createRow(++rown);
				for (int i = 1; i < numberOfColumns + 1; i++) {
					
				}

			}*/
			wb.write(bos);
		
			
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
			bos.flush();
	        setPath(null);

	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	        

	    } finally {
	    	
	        ClickUtils.close(bos);
	      
            

	    }
		    	
		    	
		  
	}
	
	
}
