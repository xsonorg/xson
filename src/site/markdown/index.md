# Home

### 1. 项目介绍

![Xson](images/00.png)

Xson是一个Java对象序列化和反序列化框架。支持Java对象到字节数组的序列化，和从字节数组到Java对象的反序列化。

### 2. 支持的对象类型

01. Java Bean对象
02. 基本类型:byte,short,int,long,float,double,boolean,char
03. 包装类型:Byte,Short,Integer,Long,Float,Double,Boolean,Character
04. 数组
05. String
06. Collection
07. Map
08. Enum
09. TimeZone
10. Class
11. BigDecimal
12. BigInteger
13. Class
14. StringBuffer
15. StringBuilder
16. URI
17. URL
18. UUID    
19. Locale
20. Currency
21. TimeZone
22. java.util.Date
23. java.sql.Date
24. java.sql.Time
25. java.sql.Timestamp
26. Inet4Address
27. Inet6Address
28. InetSocketAddress
29. XCO

### 3. 版本和引用

当前版本：1.2.0

源码地址：<https://github.com/xsonorg/xson>

Maven使用：

	<dependency>
		<groupId>org.xson</groupId>
		<artifactId>xson</artifactId>
		<version>1.2.0</version>
	</dependency>

### 4. 新版本特性

1. 新增`buffer`包，此包中的相关类提供对序列化过程中的byte[]进行分配、使用、回收的管理；进一步提供序列化的速度，并减少Full GC。
2. 提供带有偏移内容的API支持；
3. 提供XCO对象序列化和反序列的支持；
4. 提供扩展配置文件的支持；
