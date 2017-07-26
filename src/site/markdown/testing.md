# 性能测试
---

## 1. 测试所选框架介绍

    框架名称	版本
	JDK		    1.6
	XSON		1.0.1(feature-encode)
	FastJson	1.1.41
	Hessian		4.0.33
	JBoss		4.2.2.GA
	Kryo		2.24.0

	说明：
	1.以上选择的框架都是基于二进制序列化框架，除了FastJson，但是FastJson的性能是非常优秀的，甚至
	超过一些二进制框架。这里将其列出之也仅作二进制序列化和JSON文本序列化间的参考。
	2.另外也有一些框架同样也非常优秀。比如：protobuf,avro,thrift，但由于需要额外的结构数据支持
	未列入本次测试当中，感兴趣的朋友可以自行测试。

##  2. 测试说明

	2.1 硬件信息：
		<b>Test Platform</b>
		OS:Windows XP
		JVM:Sun Microsystems Inc. 1.6.0_22
		CPU:x86 Family 6 Model 15 Stepping 13, GenuineIntel os-arch:x86
		Cores (incl HT):2

	2.2 测试方法：（参考自https://github.com/eishay/jvm-serializers）
		<!--[if !supportLists]-->1、  <!--[endif]-->在正式测试之前，将测试用例运行10次对JVM进行预热。
		<!--[if !supportLists]-->2、  <!--[endif]-->对测试用例的每个方法，运行2000次，取平均值。

	2.3 测试基准：
		ser:	将对象序列化成byte数组的时间
		deser:	将byte数组反序列化成对象的时间
		total:	将对象序列化成byte数组再反序列化为对象的总时间
		size:	序列化后的数组大小
		write:	序列化是否支持
		read:	反序列化是否支持

	2.3 说明
		2.3.1	FastJson测试项中的size为字符串长度
		2.3.2	write和read的取值 OK:支持 ERROR:不支持	UNKNOW:未知。对于某种测试情况，如果不支持序列化，那么反序列化为UNKNOW

## 3. 测试结果说明

	3.1.结果说明
	JDK(unshared)
	JDK(shared)
	XSON			
	XSON(ext)			使用类名的关键字映射
	FastJson
	Hessian
	Hessian-deflat		deflat压缩
	JBoss
	Kryo-ref			reference选项开启
	Kryo			
	kryo-reg-ref		类注册开启,reference选项开启
	kryo-reg			类注册开启

## 4. 总结

	1.对象类型和对象结构支持情况
		XSON,JBoss,Kryo,Hessian
	2.序列化和反序列化速度
		kryo,XSON,Hessian,JBoss
	3.序列化的体积
		Kryo,XSON,Hessian,JBoss
	4.使用方式
		都比较简单，但是XSON更简洁：）
	5.复杂对象的测试
		本次测试未进行此项对比，感兴趣的朋友可以自行测试。
	6.改进计划
		XSON后期将在字符编码、基本类型体积优化和大对象特殊处理方面做进一步优化。

## 附录.测试结果列表

> 1.采用jvm-serializers中的media.1.cks数据源

    ------------------------------------------------------------------------------
    data[media.1.cks]                 ser   deser   total    size   write    read
    JDK(unshared)                   69945  179749  249695    1119      OK      OK
    JDK(shared)                     50139  141452  191591    1119      OK      OK
    XSON                            31348   41513   72862     512      OK      OK
    XSON(ext)                       16186   10926   27112     245      OK      OK
    FastJson                        43025   41664   84689     486      OK      OK
    Hessian                         46846   52808   99654     665      OK      OK
    Hessian-deflat                 349217  141464  490681     373      OK      OK
    JBoss                          204521  213587  418109    1152      OK      OK
    Kryo-ref                        29103   29227   58330     320      OK      OK
    Kryo                            10039   10498   20537     332      OK      OK
    kryo-reg-ref                    13816   11385   25201     200      OK      OK
    kryo-reg                         6909    7265   14175     212      OK      OK

> 2.同上,采用media.2.cks数据源

    ------------------------------------------------------------------------------
    data[media.2.cks]                 ser   deser   total    size   write    read
    JDK(unshared)                   72522  180323  252846    1182      OK      OK
    JDK(shared)                     53541  142679  196220    1182      OK      OK
    XSON                            31892   41961   73853     566      OK      OK
    XSON(ext)                       18213   12083   30296     299      OK      OK
    FastJson                        43733   38677   82411     555      OK      OK
    Hessian                         46444   55491  101936     702      OK      OK
    Hessian-deflat                 170215   90579  260795     402      OK      OK
    JBoss                          242831  248432  491263    1225      OK      OK
    Kryo-ref                        32841   31887   64729     373      OK      OK
    Kryo                            10913   11595   22509     369      OK      OK
    kryo-reg-ref                    14427   13552   27979     252      OK      OK
    kryo-reg                         7753    8591   16345     248      OK      OK


> 3.同上用data-stream/media.3.cks数据源

    ------------------------------------------------------------------------------
    data[media.3.1.cks]               ser   deser   total    size   write    read
    JDK(unshared)                   87754  198242  285996    2162      OK      OK
    JDK(shared)                     66467  152523  218991    2162      OK      OK
    XSON                            53815   61260  115075    1556      OK      OK
    XSON(ext)                       32872   22269   55141    1289      OK      OK
    FastJson                        51019   60536  111556    1827      OK      OK
    Hessian                         60650   81475  142126    2013      OK      OK
    Hessian-deflat                 233179  136082  369262     487      OK      OK
    JBoss                          207602  222201  429803    2195      OK      OK
    Kryo-ref                        38747   37030   75777    1377      OK      OK
    Kryo                            20560   17454   38015    1691      OK      OK
    kryo-reg-ref                    21518   17838   39356    1257      OK      OK
    kryo-reg                        17095   14380   31475    1571      OK      OK

> 4.同上,采用media.4.cks数据源

	------------------------------------------------------------------------------
	data[media.4.cks]                 ser   deser   total    size   write    read
	JDK(unshared)                   63334  180563  243897     980      OK      OK
	JDK(shared)                     44509  139261  183771     980      OK      OK
	XSON                            29169   39563   68733     371      OK      OK
	XSON(ext)                       14247    9235   23482     104      OK      OK
	FastJson                        41009   37000   78009     318      OK      OK
	Hessian                         47284   56667  103951     495      OK      OK
	Hessian-deflat                 351433  125167  476600     305      OK      OK
	JBoss                          198862  208120  406983    1008      OK      OK
	Kryo-ref                        28939   30025   58964     185      OK      OK
	Kryo                             9136    9827   18963     173      OK      OK
	kryo-reg-ref                    11476   11019   22496      65      OK      OK
	kryo-reg                         5625    6521   12146      53      OK      OK

> 5.POJO中使用255个int类型字段

    ------------------------------------------------------------------------------
    data[Multi Field 255 Ds]          ser   deser   total    size   write    read
    JDK(unshared)                   72505  294493  366998    2771      OK      OK
    JDK(shared)                     60583  270792  331376    2771      OK      OK
    XSON                            58146   37162   95309     570      OK      OK
    XSON(ext)                       43709   22033   65743     519      OK      OK
    FastJson                       122758  141813  264572    2590      OK      OK
    Hessian                         44469   92728  137198    1693      OK      OK
    Hessian-deflat                 461387  153822  615210     993      OK      OK
    JBoss                          573162  209652  782815    3144      OK      OK
    Kryo-ref                        15750   16109   31860     448      OK      OK
    Kryo                             9102   10016   19119     447      OK      OK
    kryo-reg-ref                     9615   10845   20461     448      OK      OK
    kryo-reg                         9353   10262   19616     447      OK      OK

> 6.这里使用了XSON所支持的所有对象类型

    ------------------------------------------------------------------------------
    data[多类型支持]                       ser   deser   total    size   write    read
    JDK(unshared)                       0       0       0       0   ERROR  UNKNOW
    JDK(shared)                         0       0       0       0   ERROR  UNKNOW
    XSON                            99060  148520  247581     917      OK      OK
    XSON(ext)                       58050   63524  121574     800      OK      OK
    FastJson                       145633   89447  235081    1252      OK      OK
    Hessian                             0       0       0       0   ERROR  UNKNOW
    Hessian-deflat                      0       0       0       0   ERROR  UNKNOW
    JBoss                          765159  807891 1573051    4122      OK      OK
    Kryo-ref                        75403       0   75403       0      OK   ERROR
    Kryo                            30106       0   30106       0      OK   ERROR
    kryo-reg-ref                        0       0       0       0   ERROR  UNKNOW
    kryo-reg                            0       0       0       0   ERROR  UNKNOW

> 7.在一个数组中既有子类，又有父类

    ------------------------------------------------------------------------------
    data[父类子类数组]                      ser   deser   total    size   write    read
    JDK(unshared)                       0       0       0       0   ERROR  UNKNOW
    JDK(shared)                         0       0       0       0   ERROR  UNKNOW
    XSON                            18161   15711   33872     110      OK      OK
    XSON(ext)                        6294    3702    9997      29      OK      OK
    FastJson                        24617   29198   53815      64      OK      OK
    Hessian                             0       0       0       0   ERROR  UNKNOW
    Hessian-deflat                      0       0       0       0   ERROR  UNKNOW
    JBoss                           74830   78720  153550     266      OK      OK
    Kryo-ref                        14133   17328   31462     103      OK      OK
    Kryo                             5155    6252   11408     101      OK      OK
    kryo-reg-ref                        0       0       0       0   ERROR  UNKNOW
    kryo-reg                            0       0       0       0   ERROR  UNKNOW

> 8.同上

    ------------------------------------------------------------------------------
    data[父类子类集合]                      ser   deser   total    size   write    read
    JDK(unshared)                       0       0       0       0   ERROR  UNKNOW
    JDK(shared)                         0       0       0       0   ERROR  UNKNOW
    XSON                            15750   20278   36029     130      OK      OK
    XSON(ext)                        6158    4496   10655      31      OK      OK
    FastJson                        26755   30366   57121      64      OK      OK
    Hessian                             0       0       0       0   ERROR  UNKNOW
    Hessian-deflat                      0       0       0       0   ERROR  UNKNOW
    JBoss                           87253   83433  170686     250      OK      OK
    Kryo-ref                        22028   23779   45808     103      OK      OK
    Kryo                             5191    5316   10508     101      OK      OK
    kryo-reg-ref                     5487    4435    9923      14      OK      OK
    kryo-reg                         3525    3772    7297      12      OK      OK

> 9.A->B,B->A,A->A

    ------------------------------------------------------------------------------
    data[循环引用]                        ser   deser   total    size   write    read
    JDK(unshared)                       0       0       0       0   ERROR  UNKNOW
    JDK(shared)                         0       0       0       0   ERROR  UNKNOW
    XSON                            21841   21859   43700     180      OK      OK
    XSON(ext)                       12686    7195   19882     114      OK      OK
    FastJson                        37967   45260   83228     305      OK      OK
    Hessian                             0       0       0       0   ERROR  UNKNOW
    Hessian-deflat                      0       0       0       0   ERROR  UNKNOW
    JBoss                          217637  181835  399473     410      OK      OK
    Kryo-ref                        27152   25845   52997     183      OK      OK
    Kryo                                0       0       0       0   ERROR  UNKNOW
    kryo-reg-ref                    12086   11140   23226     100      OK      OK
    kryo-reg                            0       0       0       0   ERROR  UNKNOW

> 10.

    ------------------------------------------------------------------------------
    data[大对象]                         ser   deser   total    size   write    read
    JDK(unshared)                  63767881 7888991 71656872 4194331      OK      OK
    JDK(shared)                    30730163 6869030 37599193 4194331      OK      OK
    XSON                           27394543 6054121 33448664 4194311      OK      OK
    XSON(ext)                      22648968 5806324 28455292 4194311      OK      OK
    FastJson                       67561939 104171620 171733559 5592410   OK      OK
    Hessian                        25781489 340022824 365804313 4197895   OK      OK
    Hessian-deflat                 86885345 348947143 435832488   18610   OK      OK
    JBoss                          13621284 6420928 20042212 4194341      OK      OK
    Kryo-ref                       105032344 8473703 113506047 4194309    OK      OK
    Kryo                           42527751 5910528 48438279 4194308      OK      OK
    kryo-reg-ref                        0       0       0       0   ERROR  UNKNOW
    kryo-reg                            0       0       0       0   ERROR  UNKNOW