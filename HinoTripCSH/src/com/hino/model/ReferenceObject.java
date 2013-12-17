package com.hino.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReferenceObject {
	private long id;
	private int ro_type;
	private String ro_desc;
	private String ro_desc_en;
	private String ro_text1;
	private String ro_text2;
	private String ro_text3;
	private String ro_text4;
	private String ro_text5;
	private String ro_text6;
	private String ro_text7;
	private String ro_text8;
	private String ro_text9;
	private String ro_text10;
	private int ro_int1;
	private int ro_int2;
	private int ro_int3;
	private int ro_int4;
	private int ro_int5;
	private int ro_int6;
	private int ro_int7;
	private int ro_int8;
	private int ro_int9;
	private int ro_int10;
	private double ro_double1;
	private double ro_double2;
	private double ro_double3;
	private double ro_double4;
	private double ro_double5;
	private double ro_double6;
	private double ro_double7;
	private double ro_double8;
	private double ro_double9;
	private double ro_double10;
	private long ro_link1;
	private long ro_link2;
	private long ro_link3;
	private long ro_link4;
	private long ro_link5;
	private Calendar ro_cal1;
	private Calendar ro_cal2;
	private Calendar ro_cal3;
	private Calendar ro_cal4;
	private Calendar ro_cal5;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public Calendar getRo_cal1() {
		return ro_cal1;
	}
	public Calendar getRo_cal2() {
		return ro_cal2;
	}
	public Calendar getRo_cal3() {
		return ro_cal3;
	}
	public Calendar getRo_cal4() {
		return ro_cal4;
	}
	public Calendar getRo_cal5() {
		return ro_cal5;
	}
	public String getRo_desc() {
		return ro_desc;
	}
	public String getRo_desc_en() {
		return ro_desc_en;
	}
	public double getRo_double1() {
		return ro_double1;
	}
	public double getRo_double10() {
		return ro_double10;
	}
	public double getRo_double2() {
		return ro_double2;
	}
	public double getRo_double3() {
		return ro_double3;
	}
	public double getRo_double4() {
		return ro_double4;
	}
	public double getRo_double5() {
		return ro_double5;
	}
	public double getRo_double6() {
		return ro_double6;
	}
	public double getRo_double7() {
		return ro_double7;
	}
	public double getRo_double8() {
		return ro_double8;
	}
	public double getRo_double9() {
		return ro_double9;
	}
	public int getRo_int1() {
		return ro_int1;
	}
	public int getRo_int10() {
		return ro_int10;
	}
	public int getRo_int2() {
		return ro_int2;
	}
	public int getRo_int3() {
		return ro_int3;
	}
	public int getRo_int4() {
		return ro_int4;
	}
	public int getRo_int5() {
		return ro_int5;
	}
	public int getRo_int6() {
		return ro_int6;
	}
	public int getRo_int7() {
		return ro_int7;
	}
	public int getRo_int8() {
		return ro_int8;
	}
	public int getRo_int9() {
		return ro_int9;
	}
	public long getRo_link1() {
		return ro_link1;
	}
	public long getRo_link2() {
		return ro_link2;
	}
	public long getRo_link3() {
		return ro_link3;
	}
	public long getRo_link4() {
		return ro_link4;
	}
	public long getRo_link5() {
		return ro_link5;
	}
	public String getRo_text1() {
		return ro_text1;
	}
	public String getRo_text10() {
		return ro_text10;
	}
	public String getRo_text2() {
		return ro_text2;
	}
	public String getRo_text3() {
		return ro_text3;
	}
	public String getRo_text4() {
		return ro_text4;
	}
	public String getRo_text5() {
		return ro_text5;
	}
	public String getRo_text6() {
		return ro_text6;
	}
	public String getRo_text7() {
		return ro_text7;
	}
	public String getRo_text8() {
		return ro_text8;
	}
	public String getRo_text9() {
		return ro_text9;
	}
	public int getRo_type() {
		return ro_type;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setRo_cal1(Calendar ro_cal1) {
		this.ro_cal1 = ro_cal1;
	}
	public void setRo_cal2(Calendar ro_cal2) {
		this.ro_cal2 = ro_cal2;
	}
	public void setRo_cal3(Calendar ro_cal3) {
		this.ro_cal3 = ro_cal3;
	}
	
	public void setRo_cal4(Calendar ro_cal4) {
		this.ro_cal4 = ro_cal4;
	}
	public void setRo_cal5(Calendar ro_cal5) {
		this.ro_cal5 = ro_cal5;
	}
	public void setRo_desc(String ro_desc) {
		this.ro_desc = ro_desc;
	}
	public void setRo_desc_en(String ro_desc_en) {
		this.ro_desc_en = ro_desc_en;
	}
	public void setRo_double1(double ro_double1) {
		this.ro_double1 = ro_double1;
	}
	public void setRo_double10(double ro_double10) {
		this.ro_double10 = ro_double10;
	}
	public void setRo_double2(double ro_double2) {
		this.ro_double2 = ro_double2;
	}
	public void setRo_double3(double ro_double3) {
		this.ro_double3 = ro_double3;
	}
	public void setRo_double4(double ro_double4) {
		this.ro_double4 = ro_double4;
	}
	public void setRo_double5(double ro_double5) {
		this.ro_double5 = ro_double5;
	}
	
	public void setRo_double6(double ro_double6) {
		this.ro_double6 = ro_double6;
	}
	public void setRo_double7(double ro_double7) {
		this.ro_double7 = ro_double7;
	}
	public void setRo_double8(double ro_double8) {
		this.ro_double8 = ro_double8;
	}
	public void setRo_double9(double ro_double9) {
		this.ro_double9 = ro_double9;
	}
	public void setRo_int1(int ro_int1) {
		this.ro_int1 = ro_int1;
	}
	public void setRo_int10(int ro_int10) {
		this.ro_int10 = ro_int10;
	}
	public void setRo_int2(int ro_int2) {
		this.ro_int2 = ro_int2;
	}
	public void setRo_int3(int ro_int3) {
		this.ro_int3 = ro_int3;
	}
	public void setRo_int4(int ro_int4) {
		this.ro_int4 = ro_int4;
	}
	public void setRo_int5(int ro_int5) {
		this.ro_int5 = ro_int5;
	}
	
	public void setRo_int6(int ro_int6) {
		this.ro_int6 = ro_int6;
	}
	public void setRo_int7(int ro_int7) {
		this.ro_int7 = ro_int7;
	}
	public void setRo_int8(int ro_int8) {
		this.ro_int8 = ro_int8;
	}
	public void setRo_int9(int ro_int9) {
		this.ro_int9 = ro_int9;
	}
	public void setRo_link1(long ro_link1) {
		this.ro_link1 = ro_link1;
	}
	public void setRo_link2(long ro_link2) {
		this.ro_link2 = ro_link2;
	}
	public void setRo_link3(long ro_link3) {
		this.ro_link3 = ro_link3;
	}
	public void setRo_link4(long ro_link4) {
		this.ro_link4 = ro_link4;
	}
	public void setRo_link5(long ro_link5) {
		this.ro_link5 = ro_link5;
	}
	public void setRo_text1(String ro_text1) {
		this.ro_text1 = ro_text1;
	}
	
	public void setRo_text10(String ro_text10) {
		this.ro_text10 = ro_text10;
	}
	public void setRo_text2(String ro_text2) {
		this.ro_text2 = ro_text2;
	}
	public void setRo_text3(String ro_text3) {
		this.ro_text3 = ro_text3;
	}
	public void setRo_text4(String ro_text4) {
		this.ro_text4 = ro_text4;
	}
	public void setRo_text5(String ro_text5) {
		this.ro_text5 = ro_text5;
	}
	
	public void setRo_text6(String ro_text6) {
		this.ro_text6 = ro_text6;
	}
	public void setRo_text7(String ro_text7) {
		this.ro_text7 = ro_text7;
	}
	public void setRo_text8(String ro_text8) {
		this.ro_text8 = ro_text8;
	}
	public void setRo_text9(String ro_text9) {
		this.ro_text9 = ro_text9;
	}
	public void setRo_type(int ro_type) {
		this.ro_type = ro_type;
	}
	
}
