package com.hino.util;

import java.util.ArrayList;

public class ServiceResponse {
	private boolean succ = false;
	private Object response;
	private ArrayList response_c;
	private ArrayList<String> msg_list = new ArrayList<String>();
	private ArrayList<Integer> info_code;

	public ArrayList<String> getMsg_list() {
		return msg_list;
	}

	public void setMsg_list(ArrayList<String> msgList) {
		msg_list = msgList;
	}

	public ArrayList<Integer> getInfo_code() {
		return info_code;
	}

	public Object getResponse() {
		return response;
	}

	public ArrayList getResponse_c() {
		return response_c;
	}

	public boolean isSucc() {
		return succ;
	}

	public void setInfo_code(ArrayList<Integer> infoCode) {
		info_code = infoCode;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public void setResponse_c(ArrayList responseC) {
		response_c = responseC;
	}

	public void setSucc(boolean succ) {
		this.succ = succ;
	}

	public void addInfo(int i) {
		if (info_code == null) {
			info_code = new ArrayList<Integer>();
		}

		info_code.add(i);

	}

	public void addMsg(String msg) {
		msg_list.add(msg);
	}

	public String list_msg() {
		String str = "";
		for (int i = 0; i < msg_list.size(); i++) {
			str += msg_list.get(i) + "\n";
		}
		return str;
	}

	public boolean has_msg() {
		return (msg_list.size() == 0) ? false : true;
	}
}
