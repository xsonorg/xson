package com.github.xsonorg;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import com.github.xsonorg.deserializer.BigDecimalDeserializer;
import com.github.xsonorg.deserializer.BigIntegerDeserializer;
import com.github.xsonorg.deserializer.BooleanBasicArrayDeserializer;
import com.github.xsonorg.deserializer.BooleanDeserializer;
import com.github.xsonorg.deserializer.ByteBasicArrayDeserializer;
import com.github.xsonorg.deserializer.ByteDeserializer;
import com.github.xsonorg.deserializer.CharBasicArrayDeserializer;
import com.github.xsonorg.deserializer.CharDeserializer;
import com.github.xsonorg.deserializer.ClassDeserializer;
import com.github.xsonorg.deserializer.CollectionDeserializer;
import com.github.xsonorg.deserializer.CurrencyDeserializer;
import com.github.xsonorg.deserializer.DateSqlDeserializer;
import com.github.xsonorg.deserializer.DateUtilDeserializer;
import com.github.xsonorg.deserializer.DoubleBasicArrayDeserializer;
import com.github.xsonorg.deserializer.DoubleDeserializer;
import com.github.xsonorg.deserializer.EnumDeserializer;
import com.github.xsonorg.deserializer.FloatBasicArrayDeserializer;
import com.github.xsonorg.deserializer.FloatDeserializer;
import com.github.xsonorg.deserializer.Inet4AddressDeserializer;
import com.github.xsonorg.deserializer.Inet6AddressDeserializer;
import com.github.xsonorg.deserializer.InetSocketAddressDeserializer;
import com.github.xsonorg.deserializer.IntBasicArrayDeserializer;
import com.github.xsonorg.deserializer.IntegerDeserializer;
import com.github.xsonorg.deserializer.LocaleDeserializer;
import com.github.xsonorg.deserializer.LongBasicArrayDeserializer;
import com.github.xsonorg.deserializer.LongDeserializer;
import com.github.xsonorg.deserializer.MapDeserializer;
import com.github.xsonorg.deserializer.ObjectArrayDeserializer;
import com.github.xsonorg.deserializer.ShortBasicArrayDeserializer;
import com.github.xsonorg.deserializer.ShortDeserializer;
import com.github.xsonorg.deserializer.StringBufferDeserializer;
import com.github.xsonorg.deserializer.StringBuilderDeserializer;
import com.github.xsonorg.deserializer.StringDeserializer;
import com.github.xsonorg.deserializer.TimeSqlDeserializer;
import com.github.xsonorg.deserializer.TimeZoneDeserializer;
import com.github.xsonorg.deserializer.TimestampDeserializer;
import com.github.xsonorg.deserializer.URIDeserializer;
import com.github.xsonorg.deserializer.URLDeserializer;
import com.github.xsonorg.deserializer.UUIDDeserializer;
import com.github.xsonorg.generator.BooleanGenerator;
import com.github.xsonorg.generator.ByteGenerator;
import com.github.xsonorg.generator.CharGenerator;
import com.github.xsonorg.generator.DoubleGenerator;
import com.github.xsonorg.generator.FloatGenerator;
import com.github.xsonorg.generator.IntGenerator;
import com.github.xsonorg.generator.LongGenerator;
import com.github.xsonorg.generator.ShortGenerator;
import com.github.xsonorg.serializer.BigDecimalSerializer;
import com.github.xsonorg.serializer.BigIntegerSerializer;
import com.github.xsonorg.serializer.BooleanBasicArraySerializer;
import com.github.xsonorg.serializer.BooleanSerializer;
import com.github.xsonorg.serializer.ByteBasicArraySerializer;
import com.github.xsonorg.serializer.ByteSerializer;
import com.github.xsonorg.serializer.CharBasicArraySerializer;
import com.github.xsonorg.serializer.CharacterSerializer;
import com.github.xsonorg.serializer.ClassSerializer;
import com.github.xsonorg.serializer.CurrencySerializer;
import com.github.xsonorg.serializer.DateSqlSerializer;
import com.github.xsonorg.serializer.DateUtilSerializer;
import com.github.xsonorg.serializer.DoubleBasicArraySerializer;
import com.github.xsonorg.serializer.DoubleSerializer;
import com.github.xsonorg.serializer.FloatBasicArraySerializer;
import com.github.xsonorg.serializer.FloatSerializer;
import com.github.xsonorg.serializer.Inet4AddressSerializer;
import com.github.xsonorg.serializer.Inet6AddressSerializer;
import com.github.xsonorg.serializer.InetAddressSerializer;
import com.github.xsonorg.serializer.InetSocketAddressSerializer;
import com.github.xsonorg.serializer.IntBasicArraySerializer;
import com.github.xsonorg.serializer.IntegerSerializer;
import com.github.xsonorg.serializer.LocaleSerializer;
import com.github.xsonorg.serializer.LongBasicArraySerializer;
import com.github.xsonorg.serializer.LongSerializer;
import com.github.xsonorg.serializer.ShortBasicArraySerializer;
import com.github.xsonorg.serializer.ShortSerializer;
import com.github.xsonorg.serializer.StringBufferSerializer;
import com.github.xsonorg.serializer.StringBuilderSerializer;
import com.github.xsonorg.serializer.StringSerializer;
import com.github.xsonorg.serializer.TimeSqlSerializer;
import com.github.xsonorg.serializer.TimeZoneSerializer;
import com.github.xsonorg.serializer.TimestampSerializer;
import com.github.xsonorg.serializer.URISerializer;
import com.github.xsonorg.serializer.URLSerializer;
import com.github.xsonorg.serializer.UUIDSerializer;
import com.github.xsonorg.util.ByteUtils;
import com.github.xsonorg.util.FieldModel;


/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonConst {
	
	public final static byte BASIC_MASK 	= 0x40;
	
	public final static byte WRAP_MASK 		= 0x1F;
	
	// true:false
	public final static byte TRUE 			= 0x01;
	public final static byte FALSE 			= 0x00;

	public final static byte CLASS_REF 		= 0x01;
	public final static byte CLASS_DES 		= 0x02;
	
	
	public final static byte HEAD_0 		= 0x01;
	public final static byte HEAD_1 		= 0x02;
	public final static byte HEAD_2 		= 0x03;
	public final static byte HEAD_3 		= 0x04;
	public final static byte HEAD_4 		= 0x05;
	
	/////////////////////////////////////////////////////////
	
	
	public final static byte NULL 			= 0x00;// Null
	
	////////////////////////////////////////////////////BASIC
	
	public final static byte BYTE 			= 0x01;//1byte
	
	public final static byte BOOLEAN 		= 0x02;//1byte
	
	public final static byte SHORT 			= 0x03;//2byte
	public final static byte SHORT1			= 0x04;//1byte
	
	public final static byte CHAR	 		= 0x05;//2byte
	public final static byte CHAR1	 		= 0x06;//1byte
	
	public final static byte INT 			= 0x07;//4byte
	public final static byte INT1			= 0x08;//1byte
	public final static byte INT2			= 0x09;//2byte
	public final static byte INT3			= 0x0A;//3byte
	
	public final static byte LONG 			= 0x0B;//8byte
	public final static byte LONG1			= 0x0C;//1byte
	public final static byte LONG2 			= 0x0D;//2byte
	public final static byte LONG3 			= 0x0E;//3byte
	public final static byte LONG4 			= 0x0F;//4byte
	public final static byte LONG5 			= 0x10;//5byte
	public final static byte LONG6 			= 0x11;//6byte
	public final static byte LONG7 			= 0x12;//7byte
	
	public final static byte FLOAT 			= 0x13;//4byte
	public final static byte FLOAT1			= 0x14;//1byte
	public final static byte FLOAT2			= 0x15;//2byte
	public final static byte FLOAT3			= 0x16;//3byte
	
	public final static byte DOUBLE 		= 0x17;//8byte
	public final static byte DOUBLE1 		= 0x18;//1byte
	public final static byte DOUBLE2 		= 0x19;//2byte
	public final static byte DOUBLE3 		= 0x1A;//3byte
	public final static byte DOUBLE4 		= 0x1B;//4byte
	public final static byte DOUBLE5 		= 0x1C;//5byte
	public final static byte DOUBLE6 		= 0x1D;//6byte
	public final static byte DOUBLE7 		= 0x1E;//7byte
	
	////////////////////////////////////////////////////WRAP
	
	public final static byte BYTE_WRAP 		= 0x41;//
	
	public final static byte BOOLEAN_WRAP 	= 0x42;//
	
	public final static byte SHORT_WRAP 	= 0x43;//
	public final static byte SHORT_WRAP1 	= 0x44;//
	
	public final static byte CHAR_WRAP	 	= 0x45;//
	public final static byte CHAR_WRAP1	 	= 0x46;//
	
	public final static byte INT_WRAP 		= 0x47;//
	public final static byte INT_WRAP1 		= 0x48;//
	public final static byte INT_WRAP2 		= 0x49;//
	public final static byte INT_WRAP3 		= 0x4A;//
	
	public final static byte LONG_WRAP 		= 0x4B;//
	public final static byte LONG_WRAP1 	= 0x4C;//
	public final static byte LONG_WRAP2 	= 0x4D;//
	public final static byte LONG_WRAP3 	= 0x4E;//
	public final static byte LONG_WRAP4 	= 0x4F;//
	public final static byte LONG_WRAP5 	= 0x50;//
	public final static byte LONG_WRAP6 	= 0x51;//
	public final static byte LONG_WRAP7 	= 0x52;//
	
	public final static byte FLOAT_WRAP 	= 0x53;//
	public final static byte FLOAT_WRAP1 	= 0x54;//
	public final static byte FLOAT_WRAP2 	= 0x55;//
	public final static byte FLOAT_WRAP3 	= 0x56;//
	
	public final static byte DOUBLE_WRAP 	= 0x57;//
	public final static byte DOUBLE_WRAP1 	= 0x58;//
	public final static byte DOUBLE_WRAP2 	= 0x59;//
	public final static byte DOUBLE_WRAP3 	= 0x5A;//
	public final static byte DOUBLE_WRAP4 	= 0x5B;//
	public final static byte DOUBLE_WRAP5 	= 0x5C;//
	public final static byte DOUBLE_WRAP6 	= 0x5D;//
	public final static byte DOUBLE_WRAP7 	= 0x5E;//
	
	public final static byte STRING 		= 0x1F;//4byte length
	public final static byte STRING1 		= 0x20;//1byte length
	public final static byte STRING2 		= 0x21;//2byte length
	public final static byte STRING3 		= 0x22;//3byte length
	
	public final static byte BIGDECIMAL 	= 0x23;//
	public final static byte BIGDECIMAL1 	= 0x24;//
	public final static byte BIGINTEGER 	= 0x25;//
	public final static byte BIGINTEGER1 	= 0x26;//
	public final static byte CLASS 			= 0x27;//
	
	public final static byte STRING_BUFFER 	= 0x28;//
	public final static byte STRING_BUILDER = 0x29;//

	public final static byte URI_WRAP 		= 0x2A;//
	public final static byte URL_WRAP 		= 0x2B;//
	public final static byte UUID_WRAP 		= 0x2C;//
	
	public final static byte LOCALE 		= 0x2D;//
	public final static byte CURRENCY 		= 0x2E;//
	public final static byte TIMEZONE 		= 0x2F;//
	
	public final static byte DATE_UTIL		= 0x30;//
	public final static byte DATE_SQL		= 0x31;//
	public final static byte TIME_SQL		= 0x32;//
	public final static byte TIMESTAMP_SQL	= 0x33;//
	
	public final static byte INETADDRESS	= 0x34;//
	public final static byte INET4ADDRESS	= 0x35;//
	public final static byte INET6ADDRESS	= 0x36;//
	public final static byte INETSOCKETADDRESS	= 0x37;//
	
	//Clob.class Blob SQLXML NClob
	
	public final static byte OBJECT 	= (byte) 0x60;
	
	public final static byte CONTROL_CREATE_USER_OBJECT 		= (byte) 0xF6;
	
	public final static byte CONTROL_CREATE_USER_ARRAY		 	= (byte) 0xF7;//4byte length
	public final static byte CONTROL_CREATE_USER_ARRAY1		 	= (byte) 0xF8;//1byte length
	
	public final static byte CONTROL_CREATE_SYS_ARRAY	 		= (byte) 0xF9;//4byte length
	public final static byte CONTROL_CREATE_SYS_ARRAY1	 		= (byte) 0xFA;//1byte length
	public final static byte CONTROL_CREATE_SYS_ARRAY2	 		= (byte) 0xFB;//2byte length
	public final static byte CONTROL_CREATE_SYS_ARRAY3	 		= (byte) 0xFC;//3byte length
	
	public final static byte CONTROL_REF		 				= (byte) 0xFD;//4byte length
	public final static byte CONTROL_REF1		 				= (byte) 0xFE;//1byte length
	
	public final static byte CONTROL_CREATE_END 				= (byte) 0xFF;
	
	public final static byte[] NULL_OBJECT_BYTES 				= {NULL};
	
	public static Map<Class<?>, Integer> FIELD_TYPE_MAP 			= new HashMap<Class<?>, Integer>(128);
	
	public static Map<Integer, Class<?>> SYSTEM_TYPE_MAP 			= new HashMap<Integer, Class<?>>(128);
	
	public static Map<Class<?>, XsonWriter> WRITER_MAP 				= new HashMap<Class<?>, XsonWriter>(128);
	
	public static Map<Class<?>, XsonReader> USER_READER_MAP 		= new HashMap<Class<?>, XsonReader>(128);
	
	public static Map<Integer, XsonReader> SYSTEM_READER_MAP 		= new HashMap<Integer, XsonReader>(128);
	
	public static Map<Integer, XsonGenerator> GENERATOR_MAP 		= new HashMap<Integer, XsonGenerator>(16);
	
	public static Map<Class<?>, List<FieldModel>> FieldModel_Cache 	= new HashMap<Class<?>, List<FieldModel>>(32);
	
	public static Map<Class<?>, String> CUSTOM_AGREEMENT_TYPE_MAP 	= new HashMap<Class<?>, String>(32);
	
	public static Map<String, Class<?>> CUSTOM_AGREEMENT_KEY_MAP 	= new HashMap<String, Class<?>>(32);
	
	static{
		
		/*FIELD_TYPE_MAP-Initialization*/
		FIELD_TYPE_MAP.put(byte.class, 					ByteUtils.byteToInt(BYTE));
		FIELD_TYPE_MAP.put(short.class, 				ByteUtils.byteToInt(SHORT));
		FIELD_TYPE_MAP.put(int.class, 					ByteUtils.byteToInt(INT));
		FIELD_TYPE_MAP.put(long.class, 					ByteUtils.byteToInt(LONG));
		FIELD_TYPE_MAP.put(float.class, 				ByteUtils.byteToInt(FLOAT));
		FIELD_TYPE_MAP.put(double.class, 				ByteUtils.byteToInt(DOUBLE));
		FIELD_TYPE_MAP.put(boolean.class, 				ByteUtils.byteToInt(BOOLEAN));
		FIELD_TYPE_MAP.put(char.class, 					ByteUtils.byteToInt(CHAR));
		
		FIELD_TYPE_MAP.put(Byte.class, 					ByteUtils.byteToInt(BYTE_WRAP));
		FIELD_TYPE_MAP.put(Short.class, 				ByteUtils.byteToInt(SHORT_WRAP));
		FIELD_TYPE_MAP.put(Integer.class, 				ByteUtils.byteToInt(INT_WRAP));
		FIELD_TYPE_MAP.put(Long.class, 					ByteUtils.byteToInt(LONG_WRAP));
		FIELD_TYPE_MAP.put(Float.class, 				ByteUtils.byteToInt(FLOAT_WRAP));
		FIELD_TYPE_MAP.put(Double.class, 				ByteUtils.byteToInt(DOUBLE_WRAP));
		FIELD_TYPE_MAP.put(Boolean.class, 				ByteUtils.byteToInt(BOOLEAN_WRAP));
		FIELD_TYPE_MAP.put(Character.class, 			ByteUtils.byteToInt(CHAR_WRAP));
		
		FIELD_TYPE_MAP.put(String.class, 				ByteUtils.byteToInt(STRING));
		
		FIELD_TYPE_MAP.put(BigDecimal.class, 			ByteUtils.byteToInt(BIGDECIMAL));
		FIELD_TYPE_MAP.put(BigInteger.class, 			ByteUtils.byteToInt(BIGINTEGER));
		FIELD_TYPE_MAP.put(Class.class, 				ByteUtils.byteToInt(CLASS));
		
		FIELD_TYPE_MAP.put(StringBuffer.class, 			ByteUtils.byteToInt(STRING_BUFFER));
		FIELD_TYPE_MAP.put(StringBuilder.class, 		ByteUtils.byteToInt(STRING_BUILDER));
		
		FIELD_TYPE_MAP.put(URI.class, 					ByteUtils.byteToInt(URI_WRAP));
		FIELD_TYPE_MAP.put(URL.class,					ByteUtils.byteToInt(URL_WRAP));
		FIELD_TYPE_MAP.put(UUID.class,					ByteUtils.byteToInt(UUID_WRAP));
		
		FIELD_TYPE_MAP.put(Locale.class, 				ByteUtils.byteToInt(LOCALE));
		FIELD_TYPE_MAP.put(Currency.class,				ByteUtils.byteToInt(CURRENCY));
		FIELD_TYPE_MAP.put(TimeZone.class,				ByteUtils.byteToInt(TIMEZONE));
		
		FIELD_TYPE_MAP.put(java.util.Date.class, 		ByteUtils.byteToInt(DATE_UTIL));
		FIELD_TYPE_MAP.put(java.sql.Date.class,			ByteUtils.byteToInt(DATE_SQL));
		FIELD_TYPE_MAP.put(java.sql.Time.class,			ByteUtils.byteToInt(TIME_SQL));
		FIELD_TYPE_MAP.put(java.sql.Timestamp.class,	ByteUtils.byteToInt(TIMESTAMP_SQL));
		
		FIELD_TYPE_MAP.put(InetAddress.class, 			ByteUtils.byteToInt(INETADDRESS));
		FIELD_TYPE_MAP.put(Inet4Address.class,			ByteUtils.byteToInt(INET4ADDRESS));
		FIELD_TYPE_MAP.put(Inet6Address.class,			ByteUtils.byteToInt(INET6ADDRESS));
		FIELD_TYPE_MAP.put(InetSocketAddress.class,		ByteUtils.byteToInt(INETSOCKETADDRESS));
		
		/*SYSTEM_TYPE_MAP-Initialization*/
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BYTE), 				byte.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(SHORT), 			short.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INT), 				int.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(LONG), 				long.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(FLOAT), 			float.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DOUBLE), 			double.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BOOLEAN),			boolean.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CHAR), 				char.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BYTE_WRAP), 		Byte.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(SHORT_WRAP), 		Short.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INT_WRAP), 			Integer.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(LONG_WRAP), 		Long.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP), 		Float.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP), 		Double.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BOOLEAN_WRAP), 		Boolean.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CHAR_WRAP), 		Character.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(STRING), 			String.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BIGDECIMAL), 		BigDecimal.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BIGINTEGER), 		BigInteger.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CLASS), 			Class.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(STRING_BUFFER),		StringBuffer.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(STRING_BUILDER),	StringBuilder.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(URI_WRAP),			URI.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(URL_WRAP),			URL.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(UUID_WRAP), 		UUID.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(LOCALE), 			Locale.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CURRENCY),			Currency.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(TIMEZONE),			TimeZone.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DATE_UTIL),			java.util.Date.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DATE_SQL),			java.sql.Date.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(TIME_SQL),			java.sql.Time.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(TIMESTAMP_SQL),		java.sql.Timestamp.class);
		
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INETADDRESS),		InetAddress.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INET4ADDRESS),		Inet4Address.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INET6ADDRESS),		Inet6Address.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INETSOCKETADDRESS),	InetSocketAddress.class);
		
		/*WRITER_MAP-Initialization*/
		WRITER_MAP.put(Byte.class, 					new ByteSerializer());
		WRITER_MAP.put(Short.class, 				new ShortSerializer());
		WRITER_MAP.put(Integer.class, 				new IntegerSerializer());
		WRITER_MAP.put(Long.class, 					new LongSerializer());
		WRITER_MAP.put(Float.class, 				new FloatSerializer());
		WRITER_MAP.put(Double.class, 				new DoubleSerializer());
		WRITER_MAP.put(Boolean.class, 				new BooleanSerializer());
		WRITER_MAP.put(Character.class, 			new CharacterSerializer());
		
		WRITER_MAP.put(String.class, 				new StringSerializer());
		
		WRITER_MAP.put(byte[].class, 				new ByteBasicArraySerializer());
		WRITER_MAP.put(short[].class, 				new ShortBasicArraySerializer());
		WRITER_MAP.put(int[].class, 				new IntBasicArraySerializer());
		WRITER_MAP.put(long[].class, 				new LongBasicArraySerializer());
		WRITER_MAP.put(float[].class, 				new FloatBasicArraySerializer());
		WRITER_MAP.put(double[].class, 				new DoubleBasicArraySerializer());
		WRITER_MAP.put(boolean[].class, 			new BooleanBasicArraySerializer());
		WRITER_MAP.put(char[].class, 				new CharBasicArraySerializer());
		
		WRITER_MAP.put(BigDecimal.class, 			new BigDecimalSerializer());
		WRITER_MAP.put(BigInteger.class, 			new BigIntegerSerializer());
		WRITER_MAP.put(Class.class, 				new ClassSerializer());
		
		WRITER_MAP.put(StringBuffer.class, 			new StringBufferSerializer());
		WRITER_MAP.put(StringBuilder.class, 		new StringBuilderSerializer());
		
		WRITER_MAP.put(URI.class, 					new URISerializer());
		WRITER_MAP.put(URL.class, 					new URLSerializer());
		WRITER_MAP.put(UUID.class, 					new UUIDSerializer());
		
		WRITER_MAP.put(Locale.class, 				new LocaleSerializer());
		WRITER_MAP.put(Currency.class, 				new CurrencySerializer());
		//WRITER_MAP.put(TimeZone.class, 				new TimeZoneSerializer());
		WRITER_MAP.put(TimeZone.class, 				TimeZoneSerializer.instance);
		
		WRITER_MAP.put(java.util.Date.class, 		new DateUtilSerializer());
		WRITER_MAP.put(java.sql.Date.class, 		new DateSqlSerializer());
		WRITER_MAP.put(java.sql.Time.class, 		new TimeSqlSerializer());
		WRITER_MAP.put(java.sql.Timestamp.class, 	new TimestampSerializer());
		
		WRITER_MAP.put(InetAddress.class, 			InetAddressSerializer.instance);
		WRITER_MAP.put(Inet4Address.class, 			Inet4AddressSerializer.instance);
		WRITER_MAP.put(Inet6Address.class, 			Inet6AddressSerializer.instance);
		WRITER_MAP.put(InetSocketAddress.class, 	new InetSocketAddressSerializer());	
		
		/*READER-Initialization*/
		StringDeserializer stringDeserializer = new StringDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING1), 		stringDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING2), 		stringDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING3), 		stringDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING), 			stringDeserializer);
		
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BYTE_WRAP), 		new ByteDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BOOLEAN_WRAP), 	new BooleanDeserializer());

		ShortDeserializer shortDeserializer = new ShortDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(SHORT_WRAP), 		shortDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(SHORT_WRAP1), 	shortDeserializer);

		CharDeserializer charDeserializer = new CharDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CHAR_WRAP), 		charDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CHAR_WRAP1), 		charDeserializer);

		IntegerDeserializer integerDeserializer = new IntegerDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP), 		integerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP1), 		integerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP2), 		integerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP3), 		integerDeserializer);
		
		LongDeserializer longDeserializer = new LongDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP1), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP2), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP3), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP4), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP5), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP6), 		longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP7), 		longDeserializer);
		
		FloatDeserializer floatDeserializer = new FloatDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP), 		floatDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP1), 	floatDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP2), 	floatDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP3), 	floatDeserializer);
		
		DoubleDeserializer doubleDeserializer = new DoubleDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP1), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP2), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP3), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP4), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP5), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP6), 	doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP7), 	doubleDeserializer);

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BYTE) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new ByteBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(SHORT) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new ShortBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT) 		+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new IntBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new LongBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new FloatBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new DoubleBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BOOLEAN) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new BooleanBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CHAR) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new CharBasicArrayDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(OBJECT) 	+ ByteUtils.byteToInt(CONTROL_CREATE_END), 		new ObjectArrayDeserializer());
		
		BigDecimalDeserializer bigDecimalDeserializer = new BigDecimalDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGDECIMAL), 		bigDecimalDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGDECIMAL1), 	bigDecimalDeserializer);
		
		BigIntegerDeserializer bigIntegerDeserializer = new BigIntegerDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGINTEGER), 		bigIntegerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGINTEGER1), 	bigIntegerDeserializer);
		
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CLASS), 			new ClassDeserializer());
		
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING_BUFFER), 	new StringBufferDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING_BUILDER), 	new StringBuilderDeserializer());
		
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(URI_WRAP), 		new URIDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(URL_WRAP), 		new URLDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(UUID_WRAP), 		new UUIDDeserializer());
		
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LOCALE), 			new LocaleDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CURRENCY), 		new CurrencyDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(TIMEZONE), 		new TimeZoneDeserializer());
		
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DATE_UTIL),		new DateUtilDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DATE_SQL),		new DateSqlDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(TIME_SQL),		new TimeSqlDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(TIMESTAMP_SQL),	new TimestampDeserializer());
		
		//SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INETADDRESS),			new InetAddressDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INET4ADDRESS),		new Inet4AddressDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INET6ADDRESS),		new Inet6AddressDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INETSOCKETADDRESS),	new InetSocketAddressDeserializer());		
		
		/*READER-Initialization*/
		USER_READER_MAP.put(Collection.class, 			new CollectionDeserializer());
		USER_READER_MAP.put(Map.class, 					new MapDeserializer());
		USER_READER_MAP.put(Enum.class, 				new EnumDeserializer());
		
		/*Generator-Initialization*/
		GENERATOR_MAP.put(ByteUtils.byteToInt(BYTE), 	new ByteGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(SHORT), 	new ShortGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(INT), 	new IntGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(LONG), 	new LongGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(FLOAT), 	new FloatGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(DOUBLE), 	new DoubleGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(BOOLEAN), new BooleanGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(CHAR), 	new CharGenerator());

		
		/*Add some commonly used keywords such as
		Map<String, String> prop = new HashMap<String, String>();
		prop.put("java.util.ArrayList", 					"0");
		prop.put("java.util.EnumSet", 						"1");
		prop.put("java.util.HashSet", 						"2");
		prop.put("java.util.LinkedHashSet", 				"3");
		prop.put("java.util.LinkedList", 					"4");
		prop.put("java.util.Stack", 						"5");
		prop.put("java.util.TreeSet", 						"6");
		prop.put("java.util.Vector", 						"7");

		prop.put("java.util.EnumMap", 						"8");
		prop.put("java.util.HashMap", 						"9");
		prop.put("java.util.Hashtable", 					"a");
		prop.put("java.util.IdentityHashMap", 				"b");
		prop.put("java.util.LinkedHashMap", 				"c");
		prop.put("java.util.Properties", 					"d");
		prop.put("java.util.TreeMap", 						"e");
		prop.put("java.util.concurrent.ConcurrentHashMap", 	"f");
		XsonSupport.addCustomAgreementType(prop);*/
	}

}
