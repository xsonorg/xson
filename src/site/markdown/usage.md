# 使用说明

### 1. 基本使用

> a. 添加依赖

	<dependency>
		<groupId>org.xson</groupId>
		<artifactId>xson</artifactId>
		<version>1.0.2</version>
	</dependency>

> b. 序列化

	User user = new User();
	// set...
	byte[] data = XSON.encode(user);

> c. 反序列化

	// byte[] data
	User user = XSON.decode(data);


> d. 带有偏移内容的序列化和反序列化

![偏移内容](images/01.png)

	int x = 6;

	User user = new User();
	// set...
	byte[] data = XSON.encode(x, user);

	// byte[] data
	User user = XSON.decode(x, data);


### 2. 配置文件

> 1.xson.properties文件配置示例：

	# Support for XCO
	xco=true

	# ByteArrayManager configuration
	byteArray.number=100
	byteArray.capacity=512
	
	# User classname mapping configuration
	java.util.ArrayList=0
	java.util.EnumSet=1
	java.util.HashSet=2
	java.util.LinkedHashSet=3
	java.util.LinkedList=4
	java.util.Stack=5
	java.util.TreeSet=6
	java.util.Vector=7
	java.util.EnumMap=8
	java.util.HashMap=9
	java.util.Hashtable=a
	java.util.IdentityHashMap=b
	java.util.LinkedHashMap=c
	java.util.Properties=d
	java.util.TreeMap=e
	java.util.concurrent.ConcurrentHashMap=f

> 2.配置说明

`xco=true`	开启对XCO对象的支持，默认不开启；

`byteArray.number`ByteArrayManager管理的byte[]数量，默认100；

`byteArray.capacity`ByteArrayManager管理的每个byte[]的容量，默认512；

`java.util.ArrayList=0` 用户类名自定义映射；比如：当XSON序列化的User对象时，序列化结果的类信息描述区中会记录User类的全类名，如org.xson.User，如果我们通过此处配置`org.xson.User=user`，那么类信息描述区中将只记录`user`，此处配置可缩小序列化后数据的体积；

**注意** 配置文件使用固定名称`xson.properties`，使用时请放入classpath根路径下。

### 3. 自定义序列化处理器

> 1.用户自定义Serializer和Deserializer

	public class CustomerSerializer implements XsonWriter {
	
		@Override
		public void write(Object target, ByteModel model) {
			// Implementation code
		}
	}

	public class CustomerDeserializer implements XsonReader {
		@Override
		public Object read(ReaderModel model) {
			// Implementation code
			return null;
		}
	}

> 2.添加用户自定义的Serializer和Deserializer

	XsonSupport.addCustomSerializer(User.class, new CustomerSerializer(), new CustomerDeserializer());


**注意:** Serializer和Deserializer必须成对设置.

