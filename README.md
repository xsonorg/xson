xson
====

Java object serialization and de-serialization processor


## 1. 项目介绍

Xson是一个Java对象序列化和反序列化程序。支持Java对象到字节数组的序列化，和从字节数组到Java对象的反序列化。

## 2. 支持的对象类型

* 1.Java POJO对象
* 2.基本类型:byte,short,int,long,float,double,boolean,char
* 3.包装类型:Byte,Short,Integer,Long,Float,Double,Boolean,Character
* 4.String
* 5.Collection
* 6.Map
* 7.Enum
* 8.TimeZone
* 9.Class
* 10.BigDecimal
* 11.BigInteger
* 12.Class
* 13.StringBuffer
* 14.StringBuilder
* 15.URI
* 16.URL
* 17.UUID    
* 18.Locale
* 19.Currency
* 20.TimeZone
* 21.java.util.Date
* 22.java.sql.Date
* 23.java.sql.Time
* 24.java.sql.Timestamp
* 25.InetAddress
* 26.Inet4Address
* 27.Inet6Address
* 28.InetSocketAddress

## 3. 在maven中如何配置xson依赖

    <dependency>
		<groupId>com.github.xsonorg</groupId>
		<artifactId>xson-core</artifactId>
		<version>1.0.1</version>
	</dependency>

## 4. 使用示例

1.使用xson进行对象到字节数组的序列化

	User user = new User();
	//set....
	byte[] data = XSON.write(user);

2.使用xson进行字节数组到对象的反序列化

	//byte[] data = XSON.write(user);
	User user = XSON.parse(data);

## 5. 用户扩展

1.在xson中添加用户自定义的POJO类型（请在使用序列化的反序列化之前操作）

	Map<String, String> prop = new HashMap<String, String>();
	prop.put("org.xson.testmodel.XUser7", "x7");
	prop.put("org.xson.testmodel.XUser6", "x6");
	XsonSupport.addCustomAgreementType(prop);

2.用户自定义Serializer

	public class CustomerSerializer implements XsonWriter {
	
		@Override
		public void write(Object target, ByteModel model) {
			// Implementation code
		}
	}

3.用户自定义Deserializer

	public class CustomerDeserializer implements XsonReader {
		@Override
		public Object read(ReaderModel model) {
			// Implementation code
			return null;
		}
	}

4.在xson中添加用户自定义的Serializer和Deserializer

	XsonSupport.addCustomSupportType(XUser1.class, new CustomerSerializer(), new CustomerDeserializer());