На какие две группы разделяются классы, объявленные внутри другого класса?
Как они называются на инглише?
**Ответ.** Внутренние нестатические классы (inner classes) и вложенные статические классы (nested static classes) .<br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html <br />

Для каких целей они используются?
**Ответ.**  1)Это способ логической группировки классов, которые используются только в одном месте : если класс полезен только для одного другого класса, то логично встроить его в этот класс и сохранить их вместе. Вложение таких «вспомогательных классов» делает их пакет более упорядоченным.<br />
2)Это увеличивает инкапсуляцию : рассмотрим два класса верхнего уровня, A и B, где B требуется доступ к членам A, которые в противном случае были бы объявлены private. Скрывая класс B внутри класса A, члены A могут быть объявлены закрытыми, и B может получить к ним доступ. Кроме того, сам Б может быть скрыт от внешнего мира.<br />
3)Это может привести к более читаемому и удобному для сопровождения коду : вложение небольших классов в классы верхнего уровня помещает код ближе к тому месту, где он используется.<br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html <br />

Какие уровни доступа применяются к таким классам?<br />
**Ответ.** public,package private, private, protected.<br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/innerclasses.html<br />

Какие существуют варианты внутренних классов?
**Ответ.**  Локальные классы(local classes) и анонимные классы(anonymous classes).<br /><br />
**Источник.** 

Пусть объявлен класс Outer, а внутри него публичный вложенный класс NestedPublic и публичный внутренний класс InnerPublic. Создайте экземпляры каждого класса:<br />
а) внутри класса Outer,<br />
б) извне класса Outer?<br />
**Ответ.**  a) и б)NestedPublic nested = new NestedPublic  <br />
Outer.InnerPublic inner = new Outer().new InnerPublic().<br />
**Источник.** 

Пусть объявлен класс Outer, а внутри него приватный вложенный класс NestedPrivate и приватный внутренний класс InnerPrivate. Создайте экземпляры каждого класса:<br />
а) внутри класса Outer,<br />
б) извне класса Outer?<br />
**Ответ.** a)NestedPublic nested = new NestedPublic  <br />
Outer.InnerPublic inner = new Outer().new InnerPublic().<br />
б) Так как модификатор private, создание экземпляров возможно только внутри класса Outer.<br />
**Источник.** 

Пусть объявлен класс Outer, а внутри него внутренний класс Inner. Как обратиться внутри класса Inner: <br />
а) к экземпляру класса Inner,<br /> 
б) к объемлющему экземпляру класса Outer?<br />
**Ответ.**  .<br />
**Источник.** 

Пусть объявлен класс Outer, а внутри него вложенный класс Nested. Как обратиться внутри класса Nested: <br />
а) к экземпляру класса Nested, <br />
б) к объемлющему экземпляру класса Outer?<br />
**Ответ.** а) к экземпляру класса Nested .<br />
**Источник.** 

Можно ли из вложенного класса обратиться к членам внешнего класса?<br />
Если да, то приведите пример. <br />
**Ответ.** Можно обращаться к статическим членам внешенего класса напрямую.Можно обратиться к нестатическим членам класса через ссылку на обьект.<br /><br />
static <br />
public class OuterClass {<br />
<br />
    String outerField = "Outer field";<br />
    static String staticOuterField = "Static outer field";<br />
class StaticNestedClass {<br />
        void accessMembers(OuterClass outer) {<br />
            // Compiler error: Cannot make a static reference to the non-static<br />
            //     field outerField<br />
            // System.out.println(outerField);<br />
            System.out.println(outer.outerField);<br />
            System.out.println(staticOuterField);<br />
        }<br />
    }<br />
}<br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html<br />

Можно ли из внутреннего класса обратиться к экземпляру внешнего класса?<br />
Если да, то приведите пример. <br />
**Ответ.** Можно<br />
public class OuterClass {<br />

    String outerField = "Outer field";<br />
    static String staticOuterField = "Static outer field";<br />
<br />
    class InnerClass {<br />
        void accessMembers() {<br />
            System.out.println(outerField);<br />
            System.out.println(staticOuterField);<br />
        }<br />
    } <br />
**Источник.** 

Можно ли определить экземпляр вложенного класса, не определяя экземпляры внешнего класса?<br />
Если да, то приведите пример. <br />
**Ответ.** Нельзя создать экземпляр вложенного класса, не создав предварительно экземпляр внешнего класса <br />
**Источник.** https://programm.ws/page.php?id=248#:~:text=%D0%9C%D0%BE%D0%B6%D0%BD%D0%BE.,%D1%81%D0%BE%D0%BE%D1%82%D0%B2%D0%B5%D1%82%D1%81%D1%82%D0%B2%D1%83%D1%8E%D1%89%D0%B5%D0%B3%D0%BE%20%D1%81%D1%83%D0%BF%D0%B5%D1%80%D0%BA%D0%BB%D0%B0%D1%81%D1%81%D0%B0%2C%20%D0%BF%D0%BE%D0%B4%D0%BE%D0%B1%D0%BD%D0%BE%20%D0%BA%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B5%20this%20.

Есть ли ограничения на объявление локальных переменных в локальных внутренних классах?<br />
Есть ли да, то какие?<br />
**Ответ.**  локальный класс может обращаться к локальным переменным и параметрам включающего блока, которые являются окончательными или фактически окончательными.<br />
Фактически конечная переменная - это локальная переменная, которая:
Не определено как final<br />
Присваивается ТОЛЬКО один раз. <br />
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html#accessing-members-of-an-enclosing-class<br />

Можно ли наследовать вложенные классы? <br />
Если да, то приведите пример. <br />
**Ответ.** Статический вложенный класс может быть унаследован от:<br />
обычного класса<br />
статического вложенного класса, который объявлен во внешнем классе или его предках.<br />
 public static class Drawing {<br />

   }<br />

   public static class Boeing737Drawing extends Drawing {<br />

       public static int getMaxPassengersCount() {<br />

           return maxPassengersCount;<br />
       }<br />
   }<br />
**Источник.** 

Можно ли из подкласса обратиться к методу вложенного суперкласса? <br />
Если да, то приведите пример. <br />
**Ответ.**  <br />
**Источник.** 

Какие существуют варианты внутренних интерфейсов?<br />
**Ответ.** Вложенный интерфейс может быть объявлен как public, private или protected. Этим он от­личается от интерфейса верхнего уровня, который должен бьrгь объявлен как pub­lic или использовать уровень доступа по умолчанию.<br />
Когда вложенный интерфейс используется за пределами объемлющей его области дей­ствия, его имя должно быть дополнительно уточнено именем класса или интерфей­са, членом которого он является. .<br />
**Источник.** 

Можно ли объявить класс внутри интерфейса? <br />
Если да, то есть ли ограничения? Приведите пример. <br />
**Ответ.** Можно обьявлять статические классы внутри интерфейса.Нельзя объявлять нестатические классы внутри интерфейса Java. <br />
**Источник.** https://qastack.ru/programming/2400828/inner-class-within-interface <br />

Можно ли создать экземпляр анонимного класса на основе:<br />
а) абстрактного класса? <br />
б) интерфейса? <br />
в) неабстрактного класса? <br />
г) финального класса?<br />
Если да, то приведите пример. <br />
**Ответ.**  a)Можно создать экземпляр анонимного подкласса абстрактного класса.<br />
б)Можно<br />
в) Можно<br />
г)Нельзя<br />
**Источник.** <br />

Дан следующий java-файл.<br />
//-------------- begin --------------<br />
class Runner {<br />
public static void main(String[] args){<br />
Something something = new Something();<br />
something.doSomething(...);		//1<br />
}<br />
}<br />
interface Smthable {<br />
void doSmth(); <br />
}<br />
class Something {<br />
	void doSomething(...) {			//2<br />
		smth.doSmth();<br />
}<br />
}<br />
//--------------- end ---------------<br />
1. Замените многоточия в строках 1 и 2 на такой код, чтобы приложение после запуска с помощью экземпляра анонимного класса, порожденного от интерфейса Smthable, вывело на консоль текст Hello, World.<br />
2. Получите тот же результат, переместив:<br /> 
а) интерфейс Smthable внутрь класса Something,<br />
б) класс Something внутрь интерфейса Smthable. <br />
**Ответ.**  public,package private.<br />
**Источник.** 

Дан следующий java-файл.<br />
//-------------- begin --------------<br />
abstract class AbstractRunner {<br />
abstract int getYear();<br />
abstract class AbstarctInner {<br />
	abstract int getYear();<br />
}<br />
public static void main(String[] args) {<br />
	... //1<br />
	... //2<br />
	... //3<br />
}<br />
}<br />
//--------------- end ---------------<br />
Создайте в строке 1 ссылку runner на экземпляр подкласса класса AbstractRunner.<br />
Создайте в строке 2 ссылку inner на экземпляр подкласса класса AbstractInner.<br />
Выведите на консоль в строке 3 текст 2010;2015, используя данные ссылки.<br />
**Ответ.**  abstract class AbstractRunner {<br />
	abstract int getYear();<br />
	abstract class AbstarctInner {<br />
		abstract int getYear();<br />
	}<br />
	public static void main(String[] args) {<br />
	    AbstractRunner runner = new AbstractRunner() {<br />
		int getYear() {<br />
		    return 2010;<br />
		}<br />
	    };<br />
	    AbstractRunner.AbstarctInner inner = runner.new AbstarctInner() {<br />
		int getYear() {<br />
		    return 2015;<br />
		}<br />
	    };<br />
	    System.out.println(runner.getYear() + ";" + inner.getYear());<br />
	}<br />
	}<br />
**Источник.** 

Дан следующий java-файл.<br />
//-------------- begin --------------<br />
class Runner {<br />
	public static void main(String[] args) {<br />
		... 	//1<br />
	}<br />
}<br />
class Outer {<br />
	class Inner {<br />
		void go() {<br />
			System.out.println("Gone!");<br />
		}<br />
	}<br />
}	<br />
//--------------- end ---------------<br />
1. С помощью функционала классов Outer и Inner выведите на консоль в строке 1 текст Gone!.<br />
**Ответ.** Outer.Inner inner = new Outer().new Inner(); inner.go();<br />
**Источник.**	 <br /> 
2. Переместив класс Outer внутрь класса Runner, получите тот же результат:<br />
а) не изменяя строку 1.<br />
б) изменяя строку 1, <br />
**Ответ.**  a)static class Outer<br />
б)Runner.Outer.Inner inner = new Runner().new Outer().new Inner();<br />
**Источник.** 

Что представляют собой элементы перечисления?<br />
Подсказка. Откомпилируйте фабричный класс из задачи inheritance1 и посмотрите, какие получились .class-файлы<br />
**Ответ.**  <br />
**Источник.** 

Как образуются имена вложенных и внутренних .class-файлов после компиляции?<br />
Приведите примеры.<br />
**Ответ.**  Имя внешнего класса $ имя внутреннего класса.<br />
**Источник.** 

Может ли вложенный класс быть раннер-классом?<br />
Если да, то приведите пример, иначе поясните, почему нет. <br />
**Ответ.** Нет <br />
**Источник.** 

Может ли внутренний класс быть раннер-классом?<br />
Если да, то приведите пример, иначе поясните, почему нет. <br />
**Ответ.**  Нет<br />
**Источник.** 

Может ли интерфейс иметь раннер-класс?<br />
Если да, то приведите пример, иначе поясните, почему нет. <br />
**Ответ.**  Нет<br />
**Источник.** 
