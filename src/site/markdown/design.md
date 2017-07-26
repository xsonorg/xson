技术设计
---

### 1. 类图设计

![类图设计](images/03.png)

1. XSON：用户入口类，提供序列化和反序列化方法；
2. WriterModel：序列化写入模型类；
3. XsonWriter：序列化接口；
4. ArraySerializer：对象数组序列化类；
5. CollectionSerializer：集合对象序列化类；
6. EnumSerializer：枚举对象序列化类；
7. MapSerializer：Map对象序列化类；
8. Other Serializer：其他类型对象序列化类，详见源码；
9. ReaderModel：反序列化读取模型类；
10. XsonReader：反序列化接口；
11. CurrencyDeserializer：货币对象反列化类；
12. DateUtilDeserializer：时间对象反列化类；
13. LocaleDeserializer：地区对象反列化类；
14. LongDeserializer：Long包装对象反列化类；
15. Other Deserializer：其他类型对象反列化类，详见源码；
16. XsonConst：常量类，持有所有的序列化类和反序列化类实例；

### 2. byte[]管理

![Xson](images/04.png)

1. 浅绿色：`byte[]`，由`ByteArrayManager`类进行管理，在其内部持有多个`byte[]`，容量均一致；在序列化过程中`ByteArrayManager`提供`byte[]`的申请和回收管理；
2. 深绿色：`byte[]`，序列化过程中临时创建的，不受`ByteArrayManager`管理，使用后由GC处理；
3. 淡蓝色：`ByteArrayItem`类；基于`byte[]`的封装类，其内容记录`byte[]`的`capacity`、`limit`等使用属性；
4. 黄色：`XSONByteArray`类；内部持有一个或者多个`ByteArrayItem`类，通过此类可将基本类型、字符串、字节数组序列化到`ByteArrayItem`类所封装的`byte[]`中；

### 3. ASM技术使用

对于Java Bean对象的处理，XSON框架使用ASM技术，动态的为每个Bean对象生成相应的Serializer和Deserializer，用于序列化和反序列化。