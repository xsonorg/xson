package org.xson.core;

import java.io.InputStream;
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
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.xson.core.buffer.ByteArrayManager;
import org.xson.core.deserializer.BigDecimalDeserializer;
import org.xson.core.deserializer.BigIntegerDeserializer;
import org.xson.core.deserializer.BooleanBasicArrayDeserializer;
import org.xson.core.deserializer.BooleanDeserializer;
import org.xson.core.deserializer.ByteBasicArrayDeserializer;
import org.xson.core.deserializer.ByteDeserializer;
import org.xson.core.deserializer.CharBasicArrayDeserializer;
import org.xson.core.deserializer.CharDeserializer;
import org.xson.core.deserializer.ClassDeserializer;
import org.xson.core.deserializer.CollectionDeserializer;
import org.xson.core.deserializer.CurrencyDeserializer;
import org.xson.core.deserializer.DateSqlDeserializer;
import org.xson.core.deserializer.DateUtilDeserializer;
import org.xson.core.deserializer.DoubleBasicArrayDeserializer;
import org.xson.core.deserializer.DoubleDeserializer;
import org.xson.core.deserializer.EnumDeserializer;
import org.xson.core.deserializer.FloatBasicArrayDeserializer;
import org.xson.core.deserializer.FloatDeserializer;
import org.xson.core.deserializer.Inet4AddressDeserializer;
import org.xson.core.deserializer.Inet6AddressDeserializer;
import org.xson.core.deserializer.InetSocketAddressDeserializer;
import org.xson.core.deserializer.IntBasicArrayDeserializer;
import org.xson.core.deserializer.IntegerDeserializer;
import org.xson.core.deserializer.LocaleDeserializer;
import org.xson.core.deserializer.LongBasicArrayDeserializer;
import org.xson.core.deserializer.LongDeserializer;
import org.xson.core.deserializer.MapDeserializer;
import org.xson.core.deserializer.ShortBasicArrayDeserializer;
import org.xson.core.deserializer.ShortDeserializer;
import org.xson.core.deserializer.StringBufferDeserializer;
import org.xson.core.deserializer.StringBuilderDeserializer;
import org.xson.core.deserializer.StringDeserializer;
import org.xson.core.deserializer.TimeSqlDeserializer;
import org.xson.core.deserializer.TimeZoneDeserializer;
import org.xson.core.deserializer.TimestampDeserializer;
import org.xson.core.deserializer.URIDeserializer;
import org.xson.core.deserializer.URLDeserializer;
import org.xson.core.deserializer.UUIDDeserializer;
import org.xson.core.generator.BooleanGenerator;
import org.xson.core.generator.ByteGenerator;
import org.xson.core.generator.CharGenerator;
import org.xson.core.generator.DoubleGenerator;
import org.xson.core.generator.FloatGenerator;
import org.xson.core.generator.IntGenerator;
import org.xson.core.generator.LongGenerator;
import org.xson.core.generator.ShortGenerator;
import org.xson.core.serializer.BigDecimalSerializer;
import org.xson.core.serializer.BigIntegerSerializer;
import org.xson.core.serializer.BooleanBasicArraySerializer;
import org.xson.core.serializer.BooleanSerializer;
import org.xson.core.serializer.ByteBasicArraySerializer;
import org.xson.core.serializer.ByteSerializer;
import org.xson.core.serializer.CharBasicArraySerializer;
import org.xson.core.serializer.CharacterSerializer;
import org.xson.core.serializer.ClassSerializer;
import org.xson.core.serializer.CurrencySerializer;
import org.xson.core.serializer.DateSqlSerializer;
import org.xson.core.serializer.DateUtilSerializer;
import org.xson.core.serializer.DoubleBasicArraySerializer;
import org.xson.core.serializer.DoubleSerializer;
import org.xson.core.serializer.FloatBasicArraySerializer;
import org.xson.core.serializer.FloatSerializer;
import org.xson.core.serializer.Inet4AddressSerializer;
import org.xson.core.serializer.Inet6AddressSerializer;
import org.xson.core.serializer.InetAddressSerializer;
import org.xson.core.serializer.InetSocketAddressSerializer;
import org.xson.core.serializer.IntBasicArraySerializer;
import org.xson.core.serializer.IntegerSerializer;
import org.xson.core.serializer.LocaleSerializer;
import org.xson.core.serializer.LongBasicArraySerializer;
import org.xson.core.serializer.LongSerializer;
import org.xson.core.serializer.ShortBasicArraySerializer;
import org.xson.core.serializer.ShortSerializer;
import org.xson.core.serializer.StringBufferSerializer;
import org.xson.core.serializer.StringBuilderSerializer;
import org.xson.core.serializer.StringSerializer;
import org.xson.core.serializer.TimeSqlSerializer;
import org.xson.core.serializer.TimeZoneSerializer;
import org.xson.core.serializer.TimestampSerializer;
import org.xson.core.serializer.URISerializer;
import org.xson.core.serializer.URLSerializer;
import org.xson.core.serializer.UUIDSerializer;
import org.xson.core.util.ByteUtils;
import org.xson.core.util.FieldModel;
import org.xson.core.util.XsonTypeUtils;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class XsonConst {

	public final static byte						BASIC_MASK					= 0x40;
	public final static byte						WRAP_MASK					= 0x1F;
	// true:false
	public final static byte						TRUE						= 0x01;
	public final static byte						FALSE						= 0x00;

	public final static byte						CLASS_REF					= 0x01;
	public final static byte						CLASS_DES					= 0x02;

	public final static byte						HEAD_0						= 0x01;
	public final static byte						HEAD_1						= 0x02;
	// public final static byte HEAD_2 = 0x03;
	// public final static byte HEAD_3 = 0x04;
	// public final static byte HEAD_4 = 0x05;

	/////////////////////////////////////////////////////////

	public final static byte						NULL						= 0x00;														// Null

	//////////////////////////////////////////////////// BASIC

	public final static byte						BYTE						= 0x01;														// 1byte

	public final static byte						BOOLEAN						= 0x02;														// 1byte

	public final static byte						SHORT						= 0x03;														// 2byte
	public final static byte						SHORT1						= 0x04;														// 1byte

	public final static byte						CHAR						= 0x05;														// 2byte
	public final static byte						CHAR1						= 0x06;														// 1byte

	public final static byte						INT							= 0x07;														// 4byte
	public final static byte						INT1						= 0x08;														// 1byte
	public final static byte						INT2						= 0x09;														// 2byte
	public final static byte						INT3						= 0x0A;														// 3byte

	public final static byte						LONG						= 0x0B;														// 8byte
	public final static byte						LONG1						= 0x0C;														// 1byte
	public final static byte						LONG2						= 0x0D;														// 2byte
	public final static byte						LONG3						= 0x0E;														// 3byte
	public final static byte						LONG4						= 0x0F;														// 4byte
	public final static byte						LONG5						= 0x10;														// 5byte
	public final static byte						LONG6						= 0x11;														// 6byte
	public final static byte						LONG7						= 0x12;														// 7byte

	public final static byte						FLOAT						= 0x13;														// 4byte
	public final static byte						FLOAT1						= 0x14;														// 1byte
	public final static byte						FLOAT2						= 0x15;														// 2byte
	public final static byte						FLOAT3						= 0x16;														// 3byte

	public final static byte						DOUBLE						= 0x17;														// 8byte
	public final static byte						DOUBLE1						= 0x18;														// 1byte
	public final static byte						DOUBLE2						= 0x19;														// 2byte
	public final static byte						DOUBLE3						= 0x1A;														// 3byte
	public final static byte						DOUBLE4						= 0x1B;														// 4byte
	public final static byte						DOUBLE5						= 0x1C;														// 5byte
	public final static byte						DOUBLE6						= 0x1D;														// 6byte
	public final static byte						DOUBLE7						= 0x1E;														// 7byte

	//////////////////////////////////////////////////// WRAP

	public final static byte						BYTE_WRAP					= 0x41;														//

	public final static byte						BOOLEAN_WRAP				= 0x42;														//

	public final static byte						SHORT_WRAP					= 0x43;														//
	public final static byte						SHORT_WRAP1					= 0x44;														//

	public final static byte						CHAR_WRAP					= 0x45;														//
	public final static byte						CHAR_WRAP1					= 0x46;														//

	public final static byte						INT_WRAP					= 0x47;														//
	public final static byte						INT_WRAP1					= 0x48;														//
	public final static byte						INT_WRAP2					= 0x49;														//
	public final static byte						INT_WRAP3					= 0x4A;														//

	public final static byte						LONG_WRAP					= 0x4B;														//
	public final static byte						LONG_WRAP1					= 0x4C;														//
	public final static byte						LONG_WRAP2					= 0x4D;														//
	public final static byte						LONG_WRAP3					= 0x4E;														//
	public final static byte						LONG_WRAP4					= 0x4F;														//
	public final static byte						LONG_WRAP5					= 0x50;														//
	public final static byte						LONG_WRAP6					= 0x51;														//
	public final static byte						LONG_WRAP7					= 0x52;														//

	public final static byte						FLOAT_WRAP					= 0x53;														//
	public final static byte						FLOAT_WRAP1					= 0x54;														//
	public final static byte						FLOAT_WRAP2					= 0x55;														//
	public final static byte						FLOAT_WRAP3					= 0x56;														//

	public final static byte						DOUBLE_WRAP					= 0x57;														//
	public final static byte						DOUBLE_WRAP1				= 0x58;														//
	public final static byte						DOUBLE_WRAP2				= 0x59;														//
	public final static byte						DOUBLE_WRAP3				= 0x5A;														//
	public final static byte						DOUBLE_WRAP4				= 0x5B;														//
	public final static byte						DOUBLE_WRAP5				= 0x5C;														//
	public final static byte						DOUBLE_WRAP6				= 0x5D;														//
	public final static byte						DOUBLE_WRAP7				= 0x5E;														//

	public final static byte						STRING						= 0x1F;														// 4b len
	public final static byte						STRING1						= 0x20;														// 1b len
	public final static byte						STRING2						= 0x21;														// 2b len
	public final static byte						STRING3						= 0x22;														// 3b len

	public final static byte						BIGDECIMAL					= 0x23;														//
	public final static byte						BIGDECIMAL1					= 0x24;														//
	public final static byte						BIGINTEGER					= 0x25;														//
	public final static byte						BIGINTEGER1					= 0x26;														//
	public final static byte						CLASS						= 0x27;														//

	public final static byte						STRING_BUFFER				= 0x28;														//
	public final static byte						STRING_BUILDER				= 0x29;														//

	public final static byte						URI_WRAP					= 0x2A;														//
	public final static byte						URL_WRAP					= 0x2B;														//
	public final static byte						UUID_WRAP					= 0x2C;														//

	public final static byte						LOCALE						= 0x2D;														//
	public final static byte						CURRENCY					= 0x2E;														//
	public final static byte						TIMEZONE					= 0x2F;														//

	public final static byte						DATE_UTIL					= 0x30;														//
	public final static byte						DATE_SQL					= 0x31;														//
	public final static byte						TIME_SQL					= 0x32;														//
	public final static byte						TIMESTAMP_SQL				= 0x33;														//

	public final static byte						INETADDRESS					= 0x34;														//
	public final static byte						INET4ADDRESS				= 0x35;														//
	public final static byte						INET6ADDRESS				= 0x36;														//
	public final static byte						INETSOCKETADDRESS			= 0x37;

	public final static byte						XCO							= 0x38;

	public final static byte						OBJECT						= (byte) 0x60;

	public final static byte						CONTROL_CREATE_USER_OBJECT	= (byte) 0xF5;
	public final static byte						CONTROL_CREATE_USER_OBJECT1	= (byte) 0xF6;

	public final static byte						CONTROL_CREATE_USER_ARRAY	= (byte) 0xF7;												// 4byte
	public final static byte						CONTROL_CREATE_USER_ARRAY1	= (byte) 0xF8;												// 1byte

	public final static byte						CONTROL_CREATE_SYS_ARRAY	= (byte) 0xF9;												// 4b len
	public final static byte						CONTROL_CREATE_SYS_ARRAY1	= (byte) 0xFA;												// 1b len
	public final static byte						CONTROL_CREATE_SYS_ARRAY2	= (byte) 0xFB;												// 2b len
	// public final static byte CONTROL_CREATE_SYS_ARRAY3 = (byte) 0xFC;

	public final static byte						CONTROL_REF					= (byte) 0xFD;												// 4b len
	public final static byte						CONTROL_REF1				= (byte) 0xFE;												// 1b len

	public final static byte						CONTROL_CREATE_END			= (byte) 0xFF;

	// public final static byte[] NULL_OBJECT_BYTES = { NULL };

	/** Class->Mark Index */
	public static Map<Class<?>, Integer>			FIELD_TYPE_MAP				= new HashMap<Class<?>, Integer>(128);
	/** Mark Index-->Class */
	public static Map<Integer, Class<?>>			SYSTEM_TYPE_MAP				= new HashMap<Integer, Class<?>>(128);

	/** 序列化器容器,所有的 // fix bug */
	public static Map<Class<?>, XsonWriter>			WRITER_MAP					= new ConcurrentHashMap<Class<?>, XsonWriter>(128);

	/** 用户类型反序列化器容器 // fix bug */
	public static Map<Class<?>, XsonReader>			USER_READER_MAP				= new ConcurrentHashMap<Class<?>, XsonReader>(128);
	/** 系统类型反序列化器容器 */
	public static Map<Integer, XsonReader>			SYSTEM_READER_MAP			= new HashMap<Integer, XsonReader>(128);
	/** 基本类型数组反序列化器容器 */
	public static Map<Integer, XsonReader>			BASE_TYPE_ARRAY_READER_MAP	= new HashMap<Integer, XsonReader>(8, 1.0F);

	/** Bean-->FieldModel */
	public static Map<Class<?>, List<FieldModel>>	BEAN_FIELD_MODEL_MAP		= new ConcurrentHashMap<Class<?>, List<FieldModel>>(128);

	public static Map<Integer, XsonGenerator>		GENERATOR_MAP				= new HashMap<Integer, XsonGenerator>(8, 1.0F);

	public static Map<Class<?>, String>				CUSTOM_AGREEMENT_TYPE_MAP	= new HashMap<Class<?>, String>(32);
	public static Map<String, Class<?>>				CUSTOM_AGREEMENT_KEY_MAP	= new HashMap<String, Class<?>>(32);

	public static ByteArrayManager					byteArrayManager			= null;

	static {

		/* FIELD_TYPE_MAP-Initialization */
		FIELD_TYPE_MAP.put(byte.class, ByteUtils.byteToInt(BYTE));
		FIELD_TYPE_MAP.put(short.class, ByteUtils.byteToInt(SHORT));
		FIELD_TYPE_MAP.put(int.class, ByteUtils.byteToInt(INT));
		FIELD_TYPE_MAP.put(long.class, ByteUtils.byteToInt(LONG));
		FIELD_TYPE_MAP.put(float.class, ByteUtils.byteToInt(FLOAT));
		FIELD_TYPE_MAP.put(double.class, ByteUtils.byteToInt(DOUBLE));
		FIELD_TYPE_MAP.put(boolean.class, ByteUtils.byteToInt(BOOLEAN));
		FIELD_TYPE_MAP.put(char.class, ByteUtils.byteToInt(CHAR));

		FIELD_TYPE_MAP.put(Byte.class, ByteUtils.byteToInt(BYTE_WRAP));
		FIELD_TYPE_MAP.put(Short.class, ByteUtils.byteToInt(SHORT_WRAP));
		FIELD_TYPE_MAP.put(Integer.class, ByteUtils.byteToInt(INT_WRAP));
		FIELD_TYPE_MAP.put(Long.class, ByteUtils.byteToInt(LONG_WRAP));
		FIELD_TYPE_MAP.put(Float.class, ByteUtils.byteToInt(FLOAT_WRAP));
		FIELD_TYPE_MAP.put(Double.class, ByteUtils.byteToInt(DOUBLE_WRAP));
		FIELD_TYPE_MAP.put(Boolean.class, ByteUtils.byteToInt(BOOLEAN_WRAP));
		FIELD_TYPE_MAP.put(Character.class, ByteUtils.byteToInt(CHAR_WRAP));

		FIELD_TYPE_MAP.put(String.class, ByteUtils.byteToInt(STRING));

		FIELD_TYPE_MAP.put(BigDecimal.class, ByteUtils.byteToInt(BIGDECIMAL));
		FIELD_TYPE_MAP.put(BigInteger.class, ByteUtils.byteToInt(BIGINTEGER));
		FIELD_TYPE_MAP.put(Class.class, ByteUtils.byteToInt(CLASS));

		FIELD_TYPE_MAP.put(StringBuffer.class, ByteUtils.byteToInt(STRING_BUFFER));
		FIELD_TYPE_MAP.put(StringBuilder.class, ByteUtils.byteToInt(STRING_BUILDER));

		FIELD_TYPE_MAP.put(URI.class, ByteUtils.byteToInt(URI_WRAP));
		FIELD_TYPE_MAP.put(URL.class, ByteUtils.byteToInt(URL_WRAP));
		FIELD_TYPE_MAP.put(UUID.class, ByteUtils.byteToInt(UUID_WRAP));

		FIELD_TYPE_MAP.put(Locale.class, ByteUtils.byteToInt(LOCALE));
		FIELD_TYPE_MAP.put(Currency.class, ByteUtils.byteToInt(CURRENCY));
		FIELD_TYPE_MAP.put(TimeZone.class, ByteUtils.byteToInt(TIMEZONE));

		FIELD_TYPE_MAP.put(java.util.Date.class, ByteUtils.byteToInt(DATE_UTIL));
		FIELD_TYPE_MAP.put(java.sql.Date.class, ByteUtils.byteToInt(DATE_SQL));
		FIELD_TYPE_MAP.put(java.sql.Time.class, ByteUtils.byteToInt(TIME_SQL));
		FIELD_TYPE_MAP.put(java.sql.Timestamp.class, ByteUtils.byteToInt(TIMESTAMP_SQL));

		FIELD_TYPE_MAP.put(InetAddress.class, ByteUtils.byteToInt(INETADDRESS));
		FIELD_TYPE_MAP.put(Inet4Address.class, ByteUtils.byteToInt(INET4ADDRESS));
		FIELD_TYPE_MAP.put(Inet6Address.class, ByteUtils.byteToInt(INET6ADDRESS));
		FIELD_TYPE_MAP.put(InetSocketAddress.class, ByteUtils.byteToInt(INETSOCKETADDRESS));

		// FIELD_TYPE_MAP.put(XCO.class, ByteUtils.byteToInt(XCO));

		/* 系统类型容器 */
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BYTE), byte.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(SHORT), short.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INT), int.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(LONG), long.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(FLOAT), float.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DOUBLE), double.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BOOLEAN), boolean.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CHAR), char.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BYTE_WRAP), Byte.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(SHORT_WRAP), Short.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INT_WRAP), Integer.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(LONG_WRAP), Long.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP), Float.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP), Double.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BOOLEAN_WRAP), Boolean.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CHAR_WRAP), Character.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(STRING), String.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BIGDECIMAL), BigDecimal.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(BIGINTEGER), BigInteger.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CLASS), Class.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(STRING_BUFFER), StringBuffer.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(STRING_BUILDER), StringBuilder.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(URI_WRAP), URI.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(URL_WRAP), URL.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(UUID_WRAP), UUID.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(LOCALE), Locale.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(CURRENCY), Currency.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(TIMEZONE), TimeZone.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DATE_UTIL), java.util.Date.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(DATE_SQL), java.sql.Date.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(TIME_SQL), java.sql.Time.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(TIMESTAMP_SQL), java.sql.Timestamp.class);

		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INETADDRESS), InetAddress.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INET4ADDRESS), Inet4Address.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INET6ADDRESS), Inet6Address.class);
		SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(INETSOCKETADDRESS), InetSocketAddress.class);

		// SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(XCO), XCO.class);

		/* WRITER_MAP-Initialization */
		WRITER_MAP.put(Byte.class, new ByteSerializer());
		WRITER_MAP.put(Short.class, new ShortSerializer());
		WRITER_MAP.put(Integer.class, new IntegerSerializer());
		WRITER_MAP.put(Long.class, new LongSerializer());
		WRITER_MAP.put(Float.class, new FloatSerializer());
		WRITER_MAP.put(Double.class, new DoubleSerializer());
		WRITER_MAP.put(Boolean.class, new BooleanSerializer());
		WRITER_MAP.put(Character.class, new CharacterSerializer());

		WRITER_MAP.put(String.class, new StringSerializer());

		WRITER_MAP.put(byte[].class, new ByteBasicArraySerializer());
		WRITER_MAP.put(short[].class, new ShortBasicArraySerializer());
		WRITER_MAP.put(int[].class, new IntBasicArraySerializer());
		WRITER_MAP.put(long[].class, new LongBasicArraySerializer());
		WRITER_MAP.put(float[].class, new FloatBasicArraySerializer());
		WRITER_MAP.put(double[].class, new DoubleBasicArraySerializer());
		WRITER_MAP.put(boolean[].class, new BooleanBasicArraySerializer());
		WRITER_MAP.put(char[].class, new CharBasicArraySerializer());

		WRITER_MAP.put(BigDecimal.class, new BigDecimalSerializer());
		WRITER_MAP.put(BigInteger.class, new BigIntegerSerializer());
		WRITER_MAP.put(Class.class, new ClassSerializer());

		WRITER_MAP.put(StringBuffer.class, new StringBufferSerializer());
		WRITER_MAP.put(StringBuilder.class, new StringBuilderSerializer());

		WRITER_MAP.put(URI.class, new URISerializer());
		WRITER_MAP.put(URL.class, new URLSerializer());
		WRITER_MAP.put(UUID.class, new UUIDSerializer());

		WRITER_MAP.put(Locale.class, new LocaleSerializer());
		WRITER_MAP.put(Currency.class, new CurrencySerializer());
		WRITER_MAP.put(TimeZone.class, TimeZoneSerializer.instance);

		WRITER_MAP.put(java.util.Date.class, new DateUtilSerializer());
		WRITER_MAP.put(java.sql.Date.class, new DateSqlSerializer());
		WRITER_MAP.put(java.sql.Time.class, new TimeSqlSerializer());
		WRITER_MAP.put(java.sql.Timestamp.class, new TimestampSerializer());

		WRITER_MAP.put(InetAddress.class, InetAddressSerializer.instance);
		WRITER_MAP.put(Inet4Address.class, Inet4AddressSerializer.instance);
		WRITER_MAP.put(Inet6Address.class, Inet6AddressSerializer.instance);
		WRITER_MAP.put(InetSocketAddress.class, new InetSocketAddressSerializer());

		// WRITER_MAP.put(XCO.class, new XCOSerializer());

		/* READER-Initialization */
		StringDeserializer stringDeserializer = new StringDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING1), stringDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING2), stringDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING3), stringDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING), stringDeserializer);

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BYTE_WRAP), new ByteDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BOOLEAN_WRAP), new BooleanDeserializer());

		ShortDeserializer shortDeserializer = new ShortDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(SHORT_WRAP), shortDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(SHORT_WRAP1), shortDeserializer);

		CharDeserializer charDeserializer = new CharDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CHAR_WRAP), charDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CHAR_WRAP1), charDeserializer);

		IntegerDeserializer integerDeserializer = new IntegerDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP), integerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP1), integerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP2), integerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT_WRAP3), integerDeserializer);

		LongDeserializer longDeserializer = new LongDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP1), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP2), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP3), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP4), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP5), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP6), longDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG_WRAP7), longDeserializer);

		FloatDeserializer floatDeserializer = new FloatDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP), floatDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP1), floatDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP2), floatDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT_WRAP3), floatDeserializer);

		DoubleDeserializer doubleDeserializer = new DoubleDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP1), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP2), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP3), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP4), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP5), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP6), doubleDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE_WRAP7), doubleDeserializer);

		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BYTE) + ByteUtils.byteToInt(CONTROL_CREATE_END), new ByteBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(SHORT) + ByteUtils.byteToInt(CONTROL_CREATE_END), new ShortBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INT) + ByteUtils.byteToInt(CONTROL_CREATE_END), new IntBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LONG) + ByteUtils.byteToInt(CONTROL_CREATE_END), new LongBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(FLOAT) + ByteUtils.byteToInt(CONTROL_CREATE_END), new FloatBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DOUBLE) + ByteUtils.byteToInt(CONTROL_CREATE_END), new DoubleBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BOOLEAN) + ByteUtils.byteToInt(CONTROL_CREATE_END), new BooleanBasicArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CHAR) + ByteUtils.byteToInt(CONTROL_CREATE_END), new CharBasicArrayDeserializer());
		// // SYSTEM_READER_MAP.put(ByteUtils.byteToInt(OBJECT) + ByteUtils.byteToInt(CONTROL_CREATE_END), new ObjectArrayDeserializer());
		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(OBJECT) + ByteUtils.byteToInt(CONTROL_CREATE_END), ObjectArrayDeserializer.instance);

		BigDecimalDeserializer bigDecimalDeserializer = new BigDecimalDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGDECIMAL), bigDecimalDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGDECIMAL1), bigDecimalDeserializer);

		BigIntegerDeserializer bigIntegerDeserializer = new BigIntegerDeserializer();
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGINTEGER), bigIntegerDeserializer);
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(BIGINTEGER1), bigIntegerDeserializer);

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CLASS), new ClassDeserializer());

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING_BUFFER), new StringBufferDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(STRING_BUILDER), new StringBuilderDeserializer());

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(URI_WRAP), new URIDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(URL_WRAP), new URLDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(UUID_WRAP), new UUIDDeserializer());

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(LOCALE), new LocaleDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(CURRENCY), new CurrencyDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(TIMEZONE), new TimeZoneDeserializer());

		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DATE_UTIL), new DateUtilDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(DATE_SQL), new DateSqlDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(TIME_SQL), new TimeSqlDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(TIMESTAMP_SQL), new TimestampDeserializer());

		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INETADDRESS), new InetAddressDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INET4ADDRESS), new Inet4AddressDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INET6ADDRESS), new Inet6AddressDeserializer());
		SYSTEM_READER_MAP.put(ByteUtils.byteToInt(INETSOCKETADDRESS), new InetSocketAddressDeserializer());

		// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(XCO), new XCODeserializer());

		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(BYTE), new ByteBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(SHORT), new ShortBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(INT), new IntBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(LONG), new LongBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(FLOAT), new FloatBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(DOUBLE), new DoubleBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(BOOLEAN), new BooleanBasicArrayDeserializer());
		BASE_TYPE_ARRAY_READER_MAP.put(ByteUtils.byteToInt(CHAR), new CharBasicArrayDeserializer());

		/* READER-Initialization */
		USER_READER_MAP.put(Collection.class, new CollectionDeserializer());
		USER_READER_MAP.put(Map.class, new MapDeserializer());
		USER_READER_MAP.put(Enum.class, new EnumDeserializer());

		/* Generator-Initialization */
		GENERATOR_MAP.put(ByteUtils.byteToInt(BYTE), new ByteGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(SHORT), new ShortGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(INT), new IntGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(LONG), new LongGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(FLOAT), new FloatGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(DOUBLE), new DoubleGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(BOOLEAN), new BooleanGenerator());
		GENERATOR_MAP.put(ByteUtils.byteToInt(CHAR), new CharGenerator());

		// load Properties
		Properties properties = new Properties();
		try {
			InputStream inputStream = XsonConst.class.getClassLoader().getResourceAsStream("xson.properties");
			if (null != inputStream) {
				properties.load(inputStream);
				inputStream.close();
				System.out.println("load xson.properties success...");
			}
		} catch (Throwable e) {
			System.err.println("load xson.properties error, message: " + e.getMessage());
		}

		// Support for XCO
		if (properties.containsKey("xco")) {
			// Class<?> xcoClass = XsonTypeUtils.findClass("org.xson.common.object.XCO");
			// FIELD_TYPE_MAP.put(xcoClass, ByteUtils.byteToInt(XCO));
			// SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(XCO), xcoClass);
			// WRITER_MAP.put(xcoClass, new XCOSerializer());
			// SYSTEM_READER_MAP.put(ByteUtils.byteToInt(XCO), new XCODeserializer());
			try {
				Class<?> xcoForXsonClass = XsonTypeUtils.findClass("org.xson.common.object.XCOForXSON");
				Class<?> xcoClass = (Class<?>) xcoForXsonClass.getMethod("getXCOClass", new Class[0]).invoke(null, new Object[0]);
				FIELD_TYPE_MAP.put(xcoClass, ByteUtils.byteToInt(XCO));
				SYSTEM_TYPE_MAP.put(ByteUtils.byteToInt(XCO), xcoClass);
				WRITER_MAP.put(xcoClass, (XsonWriter) xcoForXsonClass.getMethod("getSerializer", new Class[0]).invoke(null, new Object[0]));
				SYSTEM_READER_MAP.put(ByteUtils.byteToInt(XCO),
						(XsonReader) xcoForXsonClass.getMethod("getDeserializer", new Class[0]).invoke(null, new Object[0]));
			} catch (Throwable e) {
				throw new XsonException("init xco support error.", e);
			}
		}

		// # User bean classname mapping configuration
		for (Object keyO : properties.keySet()) {
			String key = keyO.toString();
			if (key.equals("xco") || key.equals("byteArray.number") || key.equals("byteArray.capacity")) {
				continue;
			}
			String val = properties.getProperty(key);
			if (CUSTOM_AGREEMENT_KEY_MAP.containsKey(val)) {
				throw new XsonException("Type found in already existing : type = '" + key + "', value = " + val);
			}
			Class<?> clazz = XsonTypeUtils.findClass(key);
			CUSTOM_AGREEMENT_TYPE_MAP.put(clazz, val);
			CUSTOM_AGREEMENT_KEY_MAP.put(val, clazz);
		}

		// # Byte array management configuration
		int number = 100;
		int capacity = 512;
		try {
			String _number = properties.getProperty("byteArray.number");
			if (null != _number) {
				number = Integer.parseInt(_number.trim());
			}
			String _capacity = properties.getProperty("byteArray.capacity");
			if (null != _capacity) {
				capacity = Integer.parseInt(_capacity.trim());
			}
		} catch (Throwable e) {
			throw new XsonException("init xson byteArray error.", e);
		}
		byteArrayManager = new ByteArrayManager(number, capacity);
	}

}
