package org.xson.util;

import java.io.UnsupportedEncodingException;

import org.xson.XsonConst;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ByteUtils {
	
	public static byte[] stringToByte(String x) {
		return x.getBytes();
	}
	
	public static byte[] stringToByte(String x, String charsetName) 
			throws UnsupportedEncodingException {
		return x.getBytes(charsetName);
	}	
	
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
	
	public static byte[] booleanToByte(boolean x) {
		if(x){
			return new byte[]{(byte) 1};
		}else{
			return new byte[]{(byte) 0};
		}
	}
	
	public static boolean byteToBoolean(byte[] b) {
		if((b[0] & 0xFF) == 0){
			return false;
		}else{
			return true;
		}
	}
	
	public static byte[] charToByte(char x) {
		if((x & 0xFF00) == 0){				//前1字节为空
			return new byte[]{(byte) x};
		}else{
			return new byte[]{(byte) (x >> 8), (byte) x};
		}
	}
	
	public static byte[] charTo2Byte(char x) {
		return new byte[]{(byte) (x >> 8), (byte) x};
	}
	
	public static byte[] charToByteWithMark(char x) {
		if((x & 0xFF00) == 0){				//前1字节为空
			return new byte[]{XsonConst.CHAR1, (byte) x};
		}else{
			return new byte[]{XsonConst.CHAR, (byte) (x >> 8), (byte) x};
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
	

	public static byte[] shortToByte(short x) {
		if((x & 0xFF00) == 0){				//前1字节为空
			return new byte[]{(byte) x};
		}else{
			return new byte[]{(byte) (x >> 8), (byte) x};
		}
	}
	
	public static byte[] shortTo2Byte(short x) {
		return new byte[]{(byte) (x >> 8), (byte) x};
	}
	
	public static byte[] shortToByteWithMark(short x) {
		if((x & 0xFF00) == 0){				//前1字节为空
			return new byte[]{XsonConst.SHORT1, (byte) x};
		}else{
			return new byte[]{XsonConst.SHORT, (byte) (x >> 8), (byte) x};
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
	
	public static byte[] intToByte(int x) {
		if((x & 0xFFFFFF00) == 0){			//前3字节为空
			return new byte[]{(byte) x};
		}else if((x & 0xFFFF0000) == 0){	//前2字节为空
			return new byte[]{(byte) (x >> 8), (byte) x};
		}else if((x & 0xFF000000) == 0){	//前1字节为空
			return new byte[]{(byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{(byte) (x >> 24),  (byte) (x >> 16), (byte) (x >> 8), (byte) x};
	}
	
	public static byte[] intToByteWithMark(int x) {
		if((x & 0xFFFFFF00) == 0){			//前3字节为空
			return new byte[]{XsonConst.INT1, (byte) x};
		}else if((x & 0xFFFF0000) == 0){	//前2字节为空
			return new byte[]{XsonConst.INT2, (byte) (x >> 8), (byte) x};
		}else if((x & 0xFF000000) == 0){	//前1字节为空
			return new byte[]{XsonConst.INT3, (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{XsonConst.INT, (byte) (x >> 24),  (byte) (x >> 16), (byte) (x >> 8), (byte) x};
	}
	
	public static byte[] intTo4Byte(int x) {
		return new byte[]{(byte) (x >> 24),  (byte) (x >> 16), (byte) (x >> 8), (byte) x};
	}
	
	public static byte[] intTo4ByteWithMark(int x) {
		return new byte[]{XsonConst.INT, (byte) (x >> 24),  (byte) (x >> 16), (byte) (x >> 8), (byte) x};
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
	
	public static byte[] longToByte(long x) {
		if((x & 0xFFFFFFFFFFFFFF00L) == 0L){
			return new byte[]{(byte) x};
		}else if((x & 0xFFFFFFFFFFFF0000L) == 0L){
			return new byte[]{(byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFFFF000000L) == 0L){
			return new byte[]{(byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFF00000000L) == 0L){
			return new byte[]{(byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFF0000000000L) == 0L){
			return new byte[]{(byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFF000000000000L) == 0L){
			return new byte[]{(byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFF00000000000000L) == 0L){
			return new byte[]{(byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{(byte) (x >> 56), (byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
	}
	
	public static byte[] longToByteWithMark(long x) {
		if((x & 0xFFFFFFFFFFFFFF00L) == 0L){
			return new byte[]{XsonConst.LONG1, (byte) x};
		}else if((x & 0xFFFFFFFFFFFF0000L) == 0L){
			return new byte[]{XsonConst.LONG2, (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFFFF000000L) == 0L){
			return new byte[]{XsonConst.LONG3, (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFF00000000L) == 0L){
			return new byte[]{XsonConst.LONG4, (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFF0000000000L) == 0L){
			return new byte[]{XsonConst.LONG5, (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFF000000000000L) == 0L){
			return new byte[]{XsonConst.LONG6, (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFF00000000000000L) == 0L){
			return new byte[]{XsonConst.LONG7, (byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{XsonConst.LONG, (byte) (x >> 56), (byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
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
	
	public static byte[] floatToByte(float f) {
		int x = Float.floatToIntBits(f);
		if((x & 0xFFFFFF00) == 0){			//前3字节为空
			return new byte[]{(byte) x};
		}else if((x & 0xFFFF0000) == 0){	//前2字节为空
			return new byte[]{(byte) (x >> 8), (byte) x};
		}else if((x & 0xFF000000) == 0){	//前1字节为空
			return new byte[]{(byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{(byte) (x >> 24),  (byte) (x >> 16), (byte) (x >> 8), (byte) x};
	}
	
	public static byte[] floatToByteWithMark(float f) {
		int x = Float.floatToIntBits(f);
		if((x & 0xFFFFFF00) == 0){			//前3字节为空
			return new byte[]{XsonConst.FLOAT1, (byte) x};
		}else if((x & 0xFFFF0000) == 0){	//前2字节为空
			return new byte[]{XsonConst.FLOAT2, (byte) (x >> 8), (byte) x};
		}else if((x & 0xFF000000) == 0){	//前1字节为空
			return new byte[]{XsonConst.FLOAT3, (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{XsonConst.FLOAT, (byte) (x >> 24),  (byte) (x >> 16), (byte) (x >> 8), (byte) x};
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
	
	public static byte[] doubleToByte(double d) {
		long x = Double.doubleToLongBits(d);
		if((x & 0xFFFFFFFFFFFFFF00L) == 0L){
			return new byte[]{(byte) x};
		}else if((x & 0xFFFFFFFFFFFF0000L) == 0L){
			return new byte[]{(byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFFFF000000L) == 0L){
			return new byte[]{(byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFF00000000L) == 0L){
			return new byte[]{(byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFF0000000000L) == 0L){
			return new byte[]{(byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFF000000000000L) == 0L){
			return new byte[]{(byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFF00000000000000L) == 0L){
			return new byte[]{(byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{(byte) (x >> 56), (byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
	}
	
	public static byte[] doubleToByteWithMark(double d) {
		long x = Double.doubleToLongBits(d);
		if((x & 0xFFFFFFFFFFFFFF00L) == 0L){
			return new byte[]{XsonConst.DOUBLE1, (byte) x};
		}else if((x & 0xFFFFFFFFFFFF0000L) == 0L){
			return new byte[]{XsonConst.DOUBLE2, (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFFFF000000L) == 0L){
			return new byte[]{XsonConst.DOUBLE3, (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFFFF00000000L) == 0L){
			return new byte[]{XsonConst.DOUBLE4, (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFFFF0000000000L) == 0L){
			return new byte[]{XsonConst.DOUBLE5, (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFFFF000000000000L) == 0L){
			return new byte[]{XsonConst.DOUBLE6, (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}else if((x & 0xFF00000000000000L) == 0L){
			return new byte[]{XsonConst.DOUBLE7, (byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
		}
		return new byte[]{XsonConst.DOUBLE, (byte) (x >> 56), (byte) (x >> 48), (byte) (x >> 40), (byte) (x >> 32), (byte) (x >> 24), (byte) (x >> 16), (byte) (x >> 8), (byte) x};
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
