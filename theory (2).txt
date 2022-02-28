В какой версии Java появились параметризованные типы? <br />
**Ответ.**  5. <br />
**Источник.** <br />

Приведите 2 примера кода: первый без параметризованного типа; второй - этот же код с параметризованным типом, иллюстрирующий преимущество данного варианта. 
**Ответ.**  <br />
List list = new ArrayList(); <br />
list.add("hello"); <br />
String s = (String) list.get(0);. <br />
 <br />
List<String> list = new ArrayList<String>(); <br />
list.add("hello"); <br />
String s = list.get(0); <br />
Преимущества дженериков: <br />
1)Более строгие проверки типов во время компиляции. <br />
2)Устранение преобразований типов. <br />
3)Предоставление программистам возможности реализовывать общие алгоритмы. <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/generics/why.html  <br />

Какие типы данных запрещены в качестве параметров классов? <br />
**Ответ.** Переменная типа может быть любым указанным вами непримитивным типом: любым типом класса, любым типом интерфейса, любым типом массива или даже переменной другого типа. <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/generics/types.html  <br />

Дан код: <br />
class Gen <T1, T2 extends Number, T3 extends Object> { … } <br />
Какие типы можно использовать в качестве аргументов T1, T2, T3? <br />
**Ответ.** T1 - любой, T2 - Number и его подтипы, T3 - любой. <br />
**Источник.**

Дан код: <br />
class Gen1 <T> { … } <br />
class Gen2 <T extends Object> { … } <br />
class Runner { <br />
	private final static Gen1<Object> g11 = new Gen1<>(); <br />
	private final static Gen1 g12 = new Gen1(); <br />
	private final static Gen2<Object> g21 = new Gen2<>(); <br />
	private final static Gen2 g22 = new Gen2(); <br />
	... <br />
} <br />
В чем различие объявления классов Gen1 и Gen2? <br />
Есть ли преимущество в объявлении g11 по сравнению с g12? Обоснуйте ответ.  <br />
Есть ли преимущество в объявлении g21 по сравнению с  g12? Обоснуйте ответ.  <br />
В каком случае используется второй способ (g12, g22)?  <br />
**Ответ.** Синонимы. <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />

Дан код: <br />
class SubInfo extends Info { … } <br />
class Gen1 <T> { … } <br />
class Gen2 <T extends Info> { … } <br />
Является ли декларация Gen1<Info> подклассом Gen2<Info>? <br />
Является ли декларация Gen1<SubInfo> подклассом Gen1<Info>? <br />
Является ли декларация Gen2<SubInfo> подклассом Gen2<Info>? <br />
**Ответ.** 
1)Нет  <br />
2)Нет  <br />
3)Нет  <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />

Почему нельзя вызвать конструктор generic-типа? <br />
**Ответ.** -  <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />

Почему нельзя создать generic-поле? <br />
Почему статический метод не может иметь generic-параметр? <br />
**Ответ.**  Так как идет привязка к обьекту <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />

Предложите более эффективную запись данного кода: <br />
<T> void make1(Gen <T extends Object> gen) { … } <br />
<T, S extends T> void make2(Info<T> info1, Info<S> info2) { … } <br />
**Ответ.**  цуацу<br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />

Дан код:
class Info { 
	public <T1> Info() { … }
	public <T2> Info(T2 t2) { … }
	public <T1> void make1(T1 t1) { … }
	public <T3> void make2() { … }
}
Создайте какой-либо экземпляр класса Info 
конструктором без аргументов, 
конструктором с аргументом.
Синтаксически правильно вызовите методы make1() и make2(). 
**Ответ.** ыавыыа . <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />

Поясните данный код:
static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)
**Ответ.** . <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  <br />