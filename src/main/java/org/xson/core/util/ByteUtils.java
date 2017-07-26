package org.xson.core.util;

import java.io.UnsupportedEncodingException;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteUtils {
	
	public static String byteToString(byte[] b) {
		return new String(b);
	}
	
	public static String byteToString(byte[] b, int offset, int length) {
		return new String(b, offset, length);
	}
	
	public static String byteToString(byte[] b, String charsetName) 
			throws UnsupportedEncodingException{
		return new String(b, charsetName);
	}
	
	public static boolean byteToBoolean(byte[] b) {
		if((b[0] & 0xFF) == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public static char byteToChar(byte[] b) {
		if(b.length == 1){
			return (char) (b[0] & 0xFF);
		}else {	//前2字节为空
			return (char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
		}
	}
	
	public static char byteToChar(byte[] b, int offset, int length) {
		if(length == 1){		//前3字节为空
			return (char) (b[offset + 0] & 0xFF);
		}else {	//前2字节为空
			return (char) (((b[offset + 0] & 0xFF) << 8) | (b[offset + 1] & 0xFF));
		}
	}

	public static short byteToShort(byte[] b) {
		if(b.length == 1){					//前1字节为空
			return (short) (b[0] & 0xFF);
		}else {	//前2字节为空
			return (short) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
		}
	}
	
	public static short byteToShort(byte[] b, int offset, int length) {
		if(length == 1){		//前3字节为空
			return (short) (b[offset + 0] & 0xFF);
		}else {	//前2字节为空
			return (short) (((b[offset + 0] & 0xFF) << 8) | (b[offset + 1] & 0xFF));
		}
	}

	public static int byteToInt(byte b) {
		return b & 0xFF;
	}
	
	public static int byteToInt(byte[] b) {
		if(b.length == 1){			//前3字节为空
			return (int) b[0] & 0xFF;
		}else if(b.length == 2){	//前2字节为空
			return (int) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
		}else if(b.length == 3){	//前1字节为空
			return (int) (((b[0] & 0xFF) << 16) | ((b[1] & 0xFF) << 8) | (b[2] & 0xFF));
		}
		return (int) (((b[0] & 0xFF) << 24) | ((b[1] & 0xFF) << 16) | ((b[2] & 0xFF) << 8) | (b[3] & 0xFF));
	}
	
	public static int byteToInt(byte[] b, int offset, int length) {
		if(length == 1){		//前3字节为空
			return b[offset + 0] & 0xFF;
		}else if(length == 2){	//前2字节为空
			return (int) (((b[offset + 0] & 0xFF) << 8) | (b[offset + 1] & 0xFF));
		}else if(length == 3){	//前1字节为空
			return (int) (((b[offset + 0] & 0xFF) << 16) | ((b[offset + 1] & 0xFF) << 8) | (b[offset + 2] & 0xFF));
		}
		return (int) (((b[offset + 0] & 0xFF) << 24) | ((b[offset + 1] & 0xFF) << 16) | ((b[offset + 2] & 0xFF) << 8) | (b[offset + 3] & 0xFF));
	}

	public static long byteToLong(byte[] b) {
		if(b.length == 1){
			return b[0] & 0xFF;
		}else if(b.length == 2){
			return (long)((b[0] & 0xFF) << 8)
					| (long) (b[1] & 0xFF);
		}else if(b.length == 3){
			return (long)((b[0] & 0xFF) << 16)
					| (long)((b[1] & 0xFF) << 8)
					| (long) (b[2] & 0xFF);
		}else if(b.length == 4){
			return (long)((b[0] & 0xFF) << 24) 
					| (long)((b[1] & 0xFF) << 16) 
					| (long)((b[2] & 0xFF) << 8) 
					| (long) (b[3] & 0xFF);
		}else if(b.length == 5){
			return (((long)b[0] & 0xFF) << 32) 
					| (((long)b[1] & 0xFF) << 24) 
					| (((long)b[2] & 0xFF) << 16) 
					| (((long)b[3] & 0xFF) << 8) 
					| ((long)b[4] & 0xFF);
		}else if(b.length == 6){
			return (((long)b[0] & 0xFF) << 40) 
					| (((long)b[1] & 0xFF) << 32) 
					| (((long)b[2] & 0xFF) << 24) 
					| (((long)b[3] & 0xFF) << 16) 
					| (((long)b[4] & 0xFF) << 8) 
					| ((long)b[5] & 0xFF);
		}else if(b.length == 7){
			return (((long)b[0] & 0xFF) << 48) 
					| (((long)b[1] & 0xFF) << 40) 
					| (((long)b[2] & 0xFF) << 32) 
					| (((long)b[3] & 0xFF) << 24) 
					| (((long)b[4] & 0xFF) << 16) 
					| (((long)b[5] & 0xFF) << 8) 
					| ((long)b[6] & 0xFF);
		}
		return (((long)b[0] & 0xFF) << 56) 
				| (((long)b[1] & 0xFF) << 48) 
				| (((long)b[2] & 0xFF) << 40) 
				| (((long)b[3] & 0xFF) << 32) 
				| (((long)b[4] & 0xFF) << 24) 
				| (((long)b[5] & 0xFF) << 16) 
				| (((long)b[6] & 0xFF) << 8) 
				| ((long)b[7] & 0xFF);
	}
	
	public static long byteToLong(byte[] b, int offset, int length) {
		if(length == 1){
			return b[offset + 0] & 0xFF;
		}else if(length == 2){
			return (long)((b[offset + 0] & 0xFF) << 8)
					| (long) (b[offset + 1] & 0xFF);
		}else if(length == 3){
			return (long)((b[offset + 0] & 0xFF) << 16)
					| (long)((b[offset + 1] & 0xFF) << 8)
					| (long) (b[offset + 2] & 0xFF);
		}else if(length == 4){
			return (long)((b[offset + 0] & 0xFF) << 24) 
					| (long)((b[offset + 1] & 0xFF) << 16) 
					| (long)((b[offset + 2] & 0xFF) << 8) 
					| (long) (b[offset + 3] & 0xFF);
		}else if(length == 5){
			return (((long)b[offset + 0] & 0xFF) << 32) 
					| (((long)b[offset + 1] & 0xFF) << 24) 
					| (((long)b[offset + 2] & 0xFF) << 16) 
					| (((long)b[offset + 3] & 0xFF) << 8) 
					| ((long)b[offset + 4] & 0xFF);
		}else if(length == 6){
			return (((long)b[offset + 0] & 0xFF) << 40) 
					| (((long)b[offset + 1] & 0xFF) << 32) 
					| (((long)b[offset + 2] & 0xFF) << 24) 
					| (((long)b[offset + 3] & 0xFF) << 16) 
					| (((long)b[offset + 4] & 0xFF) << 8) 
					| ((long)b[offset + 5] & 0xFF);
		}else if(length == 7){
			return (((long)b[offset + 0] & 0xFF) << 48) 
					| (((long)b[offset + 1] & 0xFF) << 40) 
					| (((long)b[offset + 2] & 0xFF) << 32) 
					| (((long)b[offset + 3] & 0xFF) << 24) 
					| (((long)b[offset + 4] & 0xFF) << 16) 
					| (((long)b[offset + 5] & 0xFF) << 8) 
					| ((long)b[offset + 6] & 0xFF);
		}
		return (((long)b[offset + 0] & 0xFF) << 56) 
				| (((long)b[offset + 1] & 0xFF) << 48) 
				| (((long)b[offset + 2] & 0xFF) << 40) 
				| (((long)b[offset + 3] & 0xFF) << 32) 
				| (((long)b[offset + 4] & 0xFF) << 24) 
				| (((long)b[offset + 5] & 0xFF) << 16) 
				| (((long)b[offset + 6] & 0xFF) << 8) 
				| ((long)b[offset + 7] & 0xFF);
	}

	public static float byteToFloat(byte[] b) {
		int x = 0;
		if(b.length == 1){			//前3字节为空
			x = b[0] & 0xFF;
		}else if(b.length == 2){	//前2字节为空
			x = (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
		}else if(b.length == 3){	//前1字节为空
			x = (((b[0] & 0xFF) << 16) | ((b[1] & 0xFF) << 8) | (b[2] & 0xFF));
		}else{
			x = (((b[0] & 0xFF) << 24) | ((b[1] & 0xFF) << 16) | ((b[2] & 0xFF) << 8) | (b[3] & 0xFF));
		}
		return Float.intBitsToFloat(x);
	}
	
	public static float byteToFloat(byte[] b, int offset, int length) {
		int x = 0;
		if(length == 1){		//前3字节为空
			x = b[offset + 0] & 0xFF;
		}else if(length == 2){	//前2字节为空
			x = ((b[offset + 0] & 0xFF) << 8) | (b[offset + 1] & 0xFF);
		}else if(length == 3){	//前1字节为空
			x = ((b[offset + 0] & 0xFF) << 16) | ((b[offset + 1] & 0xFF) << 8) | (b[offset + 2] & 0xFF);
		}else {
			x = (((b[offset + 0] & 0xFF) << 24) | ((b[offset + 1] & 0xFF) << 16) | ((b[offset + 2] & 0xFF) << 8) | (b[offset + 3] & 0xFF));
		}
		return Float.intBitsToFloat(x);
	}

	public static double byteToDouble(byte[] b) {
		long x = 0L;
		if(b.length == 1){
			x = b[0] & 0xFF;
		}else if(b.length == 2){
			x = (long)((b[0] & 0xFF) << 8)
					| (long) (b[1] & 0xFF);
		}else if(b.length == 3){
			x = (long)((b[0] & 0xFF) << 16)
					| (long)((b[1] & 0xFF) << 8)
					| (long) (b[2] & 0xFF);
		}else if(b.length == 4){
			x = (long)((b[0] & 0xFF) << 24) 
					| (long)((b[1] & 0xFF) << 16) 
					| (long)((b[2] & 0xFF) << 8) 
					| (long) (b[3] & 0xFF);
		}else if(b.length == 5){
			x = (((long)b[0] & 0xFF) << 32) 
					| (((long)b[1] & 0xFF) << 24) 
					| (((long)b[2] & 0xFF) << 16) 
					| (((long)b[3] & 0xFF) << 8) 
					| ((long)b[4] & 0xFF);
		}else if(b.length == 6){
			x = (((long)b[0] & 0xFF) << 40) 
					| (((long)b[1] & 0xFF) << 32) 
					| (((long)b[2] & 0xFF) << 24) 
					| (((long)b[3] & 0xFF) << 16) 
					| (((long)b[4] & 0xFF) << 8) 
					| ((long)b[5] & 0xFF);
		}else if(b.length == 7){
			x = (((long)b[0] & 0xFF) << 48) 
					| (((long)b[1] & 0xFF) << 40) 
					| (((long)b[2] & 0xFF) << 32) 
					| (((long)b[3] & 0xFF) << 24) 
					| (((long)b[4] & 0xFF) << 16) 
					| (((long)b[5] & 0xFF) << 8) 
					| ((long)b[6] & 0xFF);
		}else{
			x = (((long)b[0] & 0xFF) << 56) 
					| (((long)b[1] & 0xFF) << 48) 
					| (((long)b[2] & 0xFF) << 40) 
					| (((long)b[3] & 0xFF) << 32) 
					| (((long)b[4] & 0xFF) << 24) 
					| (((long)b[5] & 0xFF) << 16) 
					| (((long)b[6] & 0xFF) << 8) 
					| ((long)b[7] & 0xFF);
		}
		return Double.longBitsToDouble(x);
	}
	
	public static double byteToDouble(byte[] b, int offset, int length) {
		long x = 0L;
		if(length == 1){
			x = b[offset + 0] & 0xFF;
		}else if(length == 2){
			x = (long)((b[offset + 0] & 0xFF) << 8)
					| (long) (b[offset + 1] & 0xFF);
		}else if(length == 3){
			x = (long)((b[offset + 0] & 0xFF) << 16)
					| (long)((b[offset + 1] & 0xFF) << 8)
					| (long) (b[offset + 2] & 0xFF);
		}else if(length == 4){
			x = (long)((b[offset + 0] & 0xFF) << 24) 
					| (long)((b[offset + 1] & 0xFF) << 16) 
					| (long)((b[offset + 2] & 0xFF) << 8) 
					| (long) (b[offset + 3] & 0xFF);
		}else if(length == 5){
			x = (((long)b[offset + 0] & 0xFF) << 32) 
					| (((long)b[offset + 1] & 0xFF) << 24) 
					| (((long)b[offset + 2] & 0xFF) << 16) 
					| (((long)b[offset + 3] & 0xFF) << 8) 
					| ((long)b[offset + 4] & 0xFF);
		}else if(length == 6){
			x = (((long)b[offset + 0] & 0xFF) << 40) 
					| (((long)b[offset + 1] & 0xFF) << 32) 
					| (((long)b[offset + 2] & 0xFF) << 24) 
					| (((long)b[offset + 3] & 0xFF) << 16) 
					| (((long)b[offset + 4] & 0xFF) << 8) 
					| ((long)b[offset + 5] & 0xFF);
		}else if(length == 7){
			x = (((long)b[offset + 0] & 0xFF) << 48) 
					| (((long)b[offset + 1] & 0xFF) << 40) 
					| (((long)b[offset + 2] & 0xFF) << 32) 
					| (((long)b[offset + 3] & 0xFF) << 24) 
					| (((long)b[offset + 4] & 0xFF) << 16) 
					| (((long)b[offset + 5] & 0xFF) << 8) 
					| ((long)b[offset + 6] & 0xFF);
		}else{
			x =  (((long)b[offset + 0] & 0xFF) << 56) 
					| (((long)b[offset + 1] & 0xFF) << 48) 
					| (((long)b[offset + 2] & 0xFF) << 40) 
					| (((long)b[offset + 3] & 0xFF) << 32) 
					| (((long)b[offset + 4] & 0xFF) << 24) 
					| (((long)b[offset + 5] & 0xFF) << 16) 
					| (((long)b[offset + 6] & 0xFF) << 8) 
					| ((long)b[offset + 7] & 0xFF);
		}
		return Double.longBitsToDouble(x);
	}
	
}
