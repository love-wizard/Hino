package com.hino.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.click.util.Bindable;
import org.apache.commons.fileupload.FileItem;

public class FileCenter {
	private String directory;// = "E:"+File.separator+"protected_files"+File.separator;
	private String protected_d = "WEB-INF" + File.separator + "resource" + File.separator;
	private String public_d = "internal" + File.separator +"resource" + File.separator;
	private String contract_d = "resource" + File.separator;
	private static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
	
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public String getProtectedDir()
	{
		if(directory==null)
		{
			directory = System.getProperty("baobaotao.root");
		}
		return directory +protected_d;
	}
	
	public String getPublicDir()
	{
		if(directory==null)
		{
			directory = System.getProperty("baobaotao.root");
		}
		return directory +public_d;
	}
		
	public String getContractDir()
	{
		if(directory==null)
		{
			directory = System.getProperty("baobaotao.root");
		}
		return directory +contract_d;
	}
	
	public void save_to(FileItem fi, String path, String filename) throws IOException
	{
		if(fi!=null)
		{
			if(directory==null)
			{
				directory = System.getProperty("baobaotao.root");
			}
			
			File file = new File(directory+path, filename);
			
			try {
				fi.write(file);
				System.out.println("File save to ->" + directory+path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String save_file(FileItem fi, boolean protect) throws IOException
	{
		String dir;
		
		if(fi==null)
		{
			return null;
		}
		
		//protected
		if(protect)
		{
			dir = getProtectedDir();
		} else
		{
			dir = getPublicDir();
		}
		
		String extension = "";
		String[] array = fi.getName().split("\\.");
		int i = array.length;
		
		if (i<2)
		{
			extension = "unknown";
		} else
		{
			extension = array[i-1];
		}
		String path = genUniqueFileNameCTime()+"."+extension;
		
		File dir_f = new File(dir);
		if(!dir_f.exists())
		{
			dir_f.mkdir();
		}
		
		File file = new File(dir,path);
		
		try {
			fi.write(file);
			//System.out.println("File save to ->" + dir+path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;	
	}
	
	public File load_file(String path)
	{
		if(directory==null)
		{
			directory = System.getProperty("baobaotao.root");
		}
		
		File file = new File(directory+protected_d, path);
		if(!file.exists())
		{
			return null;
		} else
		{
			return file.getAbsoluteFile();
		}
	}
	
	public boolean file_exist(String path)
	{
		
		if(directory==null)
		{
			directory = System.getProperty("baobaotao.root");
		}
		File file = new File(directory+protected_d, path);
		System.out.println(file.getAbsolutePath());
		return file.exists();
	}
	
	
	private String genRandomDigitString(int i)
	{
		//i should less than certain length perfered 10
		double d = Math.random();
		//System.out.println(d);
		int id = (int)(Math.pow(10, i)*d);
		//System.out.println(id);
		return Integer.toString(id);
	}
	
	private String genUniqueFileNameCTime() {
		 Calendar.getInstance();
		 String id;
	     id = sdf.format(Calendar.getInstance().getTime()); 
	     return id+genRandomDigitString(5);
	}
	
	public String getFileItemName(FileItem fileItem) {
		return fileItem.getName().substring(
				fileItem.getName().lastIndexOf(File.separatorChar)+1);
	}
}
