package com.rmolinari.cursomc.resources.uteis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;

public class URL {
	
	public static List<Integer> decodeIntList(String s){
		String[] s2 = s.split(",");
		List<Integer> lista = new ArrayList<>();
		for (int i = 0; i < s2.length; i++) {
			lista.add(Integer.parseInt(s2[i]));
		}
		return lista;
	}
	
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
