package com.hino.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;

public class DataExporter {
	private BasicDataSource bds;

	public BasicDataSource getBds() {
		return bds;
	}

	public void setBds(BasicDataSource bds) {
		this.bds = bds;
	}
	
	public List<Object[]> fetchData(String sql)
	{
		List list = new ArrayList<Object[]>();
		try {
			Connection conn = bds.getConnection();
			Object[] obj;
			String query = sql;
			try {
				Statement st = conn.prepareStatement(query);
				ResultSet rs = st.executeQuery(query);
				
				ResultSetMetaData rsMetaData = rs.getMetaData();
				int numberOfColumns = rsMetaData.getColumnCount();
				
				//System.out.println(numberOfColumns);
				while (rs.next()) {
					obj = new Object[numberOfColumns];
					for(int i=0;i<numberOfColumns;i++)
					{
						obj[i] = rs.getObject(i+1);
					}
					//System.out.println(obj);
					list.add(obj);
					//System.out.println(rs.getString(1));
				}
				

			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage() + 4);
		}
		
		return list;
	}
	
	
	
}
