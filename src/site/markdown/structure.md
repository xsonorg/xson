XSON数据结构说明
---

[TOC]

### 1. 标记说明

- [*] 可选部分
- (*) 必有部分

### 2. 整体结构

> 格式：\[E](H)(D)[C][L]

- E：偏移内容区
- H：Head标记；1 byte长度，取值如下：
	- 0x01：无[C][L]部分
	- 0x02：有[C][L]部分
- D：对象序列化后的字节数据；
- C：类信息描述区；
- L：类信息描述区长度，固定4 byte长度；

### 3. 类信息描述区{#class_desc}

> 格式：((H)(D))+

- H：类型标记；1 byte长度，取值如下：
	- 0x01：后续的类名内容使用约定的类名映射
	- 0x02：后续的类名内容使用完整的类名描述
- D：类名内容，参考[String类型](#string)；

### 4. 对象引用{#ref}

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0xFD：引用对象的索引是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xFE：后续1 byte长度表示引用对象的索引；
- D：引用对象的索引，长度由类型标记而定；

### 5. 基本类型-byte

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x01：标记是byte类型
- D：byte类型数据的内容；

### 6. 基本类型-boolean

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x02：标记是boolean类型
- D：boolean类型数据的字节内容，值固定如下：
	- 0x01：true
	- 0x00：false

### 7. 基本类型-short

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x03：标记是short类型，后续2 byte长度表示一个short类型数据；
	- 0x04：标记是short类型，后续1 byte长度表示一个short类型数据；
- D：short类型数据的字节内容；

### 8. 基本类型-char

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x05：标记是char类型，后续2 byte长度表示一个char类型数据；
	- 0x06：标记是char类型，后续1 byte长度表示一个char类型数据；
- D：char类型数据的字节内容；

### 9. 基本类型-int{#int}

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x07：标记是int类型，后续4 byte长度表示一个int类型数据；
	- 0x08：标记是int类型，后续1 byte长度表示一个int类型数据；
	- 0x09：标记是int类型，后续2 byte长度表示一个int类型数据；
	- 0x0A：标记是int类型，后续3 byte长度表示一个int类型数据；
- D：int类型数据的字节内容；

### 10. 基本类型-long{#long}

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x0B：标记是long类型，后续8 byte长度表示一个long类型数据；
	- 0x0C：标记是long类型，后续1 byte长度表示一个long类型数据；
	- 0x0D：标记是long类型，后续2 byte长度表示一个long类型数据；
	- 0x0E：标记是long类型，后续3 byte长度表示一个long类型数据；
	- 0x0F：标记是long类型，后续4 byte长度表示一个long类型数据；
	- 0x10：标记是long类型，后续5 byte长度表示一个long类型数据；
	- 0x11：标记是long类型，后续6 byte长度表示一个long类型数据；
	- 0x12：标记是long类型，后续7 byte长度表示一个long类型数据；
- D：long类型数据的字节内容；

### 11. 基本类型-float

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x13：标记是float类型，后续4 byte长度表示一个float类型数据；
	- 0x14：标记是float类型，后续1 byte长度表示一个float类型数据；
	- 0x15：标记是float类型，后续2 byte长度表示一个float类型数据；
	- 0x16：标记是float类型，后续3 byte长度表示一个float类型数据；
- D：float类型数据的字节内容；

### 12. 基本类型-double

> 格式：(H)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x17：标记是double类型，后续8 byte长度表示一个double类型数据；
	- 0x18：标记是double类型，后续1 byte长度表示一个double类型数据；
	- 0x19：标记是double类型，后续2 byte长度表示一个double类型数据；
	- 0x1A：标记是double类型，后续3 byte长度表示一个double类型数据；
	- 0x1B：标记是double类型，后续4 byte长度表示一个double类型数据；
	- 0x1C：标记是double类型，后续5 byte长度表示一个double类型数据；
	- 0x1D：标记是double类型，后续6 byte长度表示一个double类型数据；
	- 0x1E：标记是double类型，后续7 byte长度表示一个double类型数据；
- D：double类型数据的字节内容；

### 13. 基本类型的包装类

包装类的结构格式同基本类型相同，只是类型标记不同，取值如下：

	包装类型标记 = 基本类型标记 | 0x40


### 14. String类型{#string}

> 格式：(H)(L)(D)

- H：类型标记；1 byte长度，取值如下：
	- 0x1F：标记是String类型，后续4 byte长度表示长度；
	- 0x21：标记是String类型，后续1 byte长度表示长度；
	- 0x22：标记是String类型，后续2 byte长度表示长度；
	- 0x23：标记是String类型，后续3 byte长度表示长度；
- L：长度；String的长度；
- D：String类型数据的字节内容；

### 15. 用户对象{#用户对象}

用户对象包括Java Bean、Map、List、Enum等。

> 格式：(H)(I)(D)

- H：类型标记；取值如下：
	- 0xF5：类型索引是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xF6：后续1 byte长度表示类型索引；
- I：类型索引；类信息描述区中类名的索引，参考[类信息描述区](#class_desc)，长度视类型标记而定：
- D：用户对象的字节内容；


### 16. 用户对象数组

> 格式：(H)(I)(AD)(L)(D)

- H：类型标记；取值如下：
	- 0xF7：标记是用户对象数组，数组的长度是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xF8：标记是用户对象数组，数组的长度是1 byte长度的int类型；
- I：类型索引；数组元素的类型索引；位于类信息描述区中类名的索引，参考[类信息描述区](#class_desc)，该索引是一个完整的int类型，参考[基本类型-int](#int)；
- AD：数组维度，长度是1 byte长度的int类型；
- L：数组长度；长度视类型标记而定；
- D：用户对象数组的字节内容；

### 17. 系统对象数组

系统对象是包括基本类型和除了用户对象以为的对象。

> 格式：(H)(AT)(AD)(L)(D)

- H：类型标记；取值如下：
	- 0xF9：标记是系统对象数组，数组的长度是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xFA：标记是系统对象数组，数组的长度是1 byte长度的int类型；
	- 0xFB：标记是系统对象数组，数组的长度是2 byte长度的int类型；
- AT：元素类型；数组元素的类型标记；该标记是一个1 byte长度的byte数据；
- AD：数组维度，长度是1 byte长度的int类型；
- L：数组长度；长度视类型标记而定；
- D：系统对象数组的字节内容；

### 18. java.util.Date

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x30：标记是java.util.Date类型；
- D：java.util.Date对象表示的毫秒数；是一个完整的long类型数据，参考[基本类型-long](#long)；

### 19. java.sql.Date

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x31：标记是java.sql.Date类型；
- D：java.sql.Date对象表示的毫秒数；是一个完整的long类型数据，参考[基本类型-long](#long)；

### 20. java.sql.Time

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x32：标记是java.sql.Time类型；
- D：java.sql.Time对象表示的毫秒数；是一个完整的long类型数据，参考[基本类型-long](#long)；

### 21. java.sql.Timestamp

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x33：标记是java.sql.Timestamp类型；
- D：java.sql.Timestamp对象表示的毫秒数；是一个完整的long类型数据，参考[基本类型-long](#long)；

### 22. BigInteger

> 格式：(H)(L)(D)

- H：类型标记；取值如下：
	- 0x25：(L)是一个完整的int类型，参考[基本类型-int](#int)；
	- 0x26：后续1 byte长度表示(D)字节数组的长度；
- L：该对象的二进制补码表示形式的字节数组长度；(L)长度视类型标记而定；
- D：数组的字节内容；

### 23. BigDecimal

> 格式：(H)(S)(L)(D)

- H：类型标记；取值如下：
	- 0x23：(S)(L)是一个完整的int类型，参考[基本类型-int](#int)；
	- 0x24：(S)(L)均为1 byte长度；
- S：标度；内容长度视类型标记而定；
- L：该对象的非标度值的BigInteger的二进制补码表示形式的字节数组长度；内容长度视类型标记而定；
- D：数组的字节内容；

### 24. StringBuilder

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x29：标记是java.lang.StringBuilder类型；
- D：String对象内容，参考[String类型](#string)；

### 25. StringBuffer

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x28：标记是java.lang.StringBuffer类型；
- D：String对象内容，参考[String类型](#string)；

### 26. Collection

> 格式：(H)(I)(D)*(END)

- H：类型标记；取值如下：
	- 0xF5：类型索引是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xF6：后续1 byte长度表示类型索引；
- I：类型索引；类信息描述区中类名的索引，参考[类信息描述区](#class_desc)，长度视类型标记而定：
- D：参考[用户对象](#用户对象)
- END：结束标记 0xF6；

### 27. Map

> 格式：(H)(I)((DK)(DV))*(END)

- H：类型标记；取值如下：
	- 0xF5：类型索引是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xF6：后续1 byte长度表示类型索引；
- I：类型索引；类信息描述区中类名的索引，参考[类信息描述区](#class_desc)，长度视类型标记而定：
- DK：key对象，参考[用户对象](#用户对象)
- DV：value对象，参考[用户对象](#用户对象)
- END：结束标记 0xF6；

### 28. Enum

> 格式：(H)(I)(D)

- H：类型标记；取值如下：
	- 0xF5：类型索引是一个完整的int类型，参考[基本类型-int](#int)；
	- 0xF6：后续1 byte长度表示类型索引；
- I：类型索引；类信息描述区中类名的索引，参考[类信息描述区](#class_desc)，长度视类型标记而定：
- D：枚举常量的序数，参考[基本类型-int](#int)；

### 29. URI

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x2A：标记是java.net.URI类型；
- D：URI对象的字符串表示形式；参考[String类型](#string)；

### 30. URL

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x2B：标记是java.net.URL类型；
- D：URL对象的字符串表示形式；参考[String类型](#string)；

### 31. UUID

> 格式：(H)(HD)(LD)

- H：类型标记；取值如下：
	- 0x2C：标记是java.util.UUID类型；
- HD：此UUID对象128位值中的最高有效64位；[基本类型-long](#long)；
- LD：此UUID对象128位值中的最低有效64位；[基本类型-long](#long)；

### 32. Locale

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x2D：标记是java.util.Locale类型；
- D：使用由下划线分隔的语言、国家/地区和变量来获取整个语言环境的编程名称，String类型，参考[String类型](#string)；

### 33. Currency

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x2E：标记是java.util.Currency类型；
- D：此货币的ISO 4217货币代码，String类型，参考[String类型](#string)；

### 34. TimeZone

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x2F：标记是java.util.TimeZone类型；
- D：时区ID：String类型，参考[String类型](#string)；

### 35. Inet4Address{#inet4}

- H：类型标记；取值如下：
	- 0x35：标记是java.net.Inet4Address类型；
- D：此InetAddress对象的原始IP地址。结果按网络字节顺序；4 byte长度的字节数组；

### 36. Inet6Address{#inet6}

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x36：标记是java.net.Inet6Address类型；
- D：此InetAddress对象的原始IP地址。结果按网络字节顺序；16 byte长度的字节数组；


### 37. InetSocketAddress

> 格式：(H)(D)(P)

- H：类型标记；取值如下：
	- 0x37：标记是java.net.InetSocketAddress类型；
- D：[Inet4Address类型](#inet4)或者[Inet6Address类型](#inet6)；
- P：端口号；2 byte长度的int类型；

### 38. Class

> 格式：(H)(D)

- H：类型标记；取值如下：
	- 0x27：标记是java.lang.Class类型；
- D：类的完全限定名；String类型，参考[String类型](#string)；