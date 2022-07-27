1. Что такое исключения? <br/>
**Ответ.**  Исключение — это событие, возникающее во время выполнения программы и нарушающее нормальный ход инструкций программы. <br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html>

2. Какие действия производит система времени выполнения при возникновении исключения? <br/>
**Ответ.** Когда в методе возникает ошибка, метод создает объект и передает его системе времени выполнения. Объект, называемый объектом исключения , содержит информацию об ошибке, в том числе ее тип и состояние программы в момент возникновения ошибки. Создание объекта исключения и передача его системе выполнения называется созданием исключения .
После того, как метод выдает исключение, система выполнения пытается найти что-то для его обработки. Набор возможных «что-то» для обработки исключения — это упорядоченный список методов, которые были вызваны, чтобы добраться до метода, в котором произошла ошибка.
Система выполнения ищет в стеке вызовов метод, содержащий блок кода, способный обработать исключение. Поиск начинается с метода, в котором возникла ошибка, и продолжается по стеку вызовов в порядке, обратном тому, в котором были вызваны методы. Когда соответствующий обработчик найден, система выполнения передает исключение обработчику. Обработчик исключения считается подходящим, если тип создаваемого объекта исключения соответствует типу, который может быть обработан обработчиком. Если исполняющая система исчерпывающим образом просматривает все методы в стеке вызовов, не находя подходящего обработчика исключений, исполняющая система (и, следовательно, программа) завершает работу.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html>
 
3. Как называется блок кода, который обрабатывает исключение? <br/>
**Ответ.** Обработчик исключений. <br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html>

4. Как реализуется требование «Поймай или Укажи» (Catch or Specify)? <br/>
**Ответ.** Код, вызвавший исключение должен быть обработан обработчиком исключений,либо метод должен сообщать об исключении на верхний уровень с помощью throws. Требование распространяется на проверяемые исключения. <br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html>
 
5. Какая иерархия классов исключений? <br/>
**Ответ.** Базовым классом для всех исключений является класс Throwable. От него уже наследуются два класса: Exception и Error. От класса Exception наследуется класс RuntimeException.<br/> 
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/throwing.html>

6. Какие виды исключений относятся к непроверяемым? <br/>
**Ответ.** Непроверяемые исключения - подклассы класса Runtime Exception.<br/> 
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html>
 
7. Какие компоненты могут входить в обработчик исключений? <br/>
**Ответ.** try,catch,finally. <br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html>
 
8. Для каких ситуаций используется оператор try-with-resources? <br/>
**Ответ.** Этот оператор используется для закрытия ресурсов.<br/>
**Источник.**
 
9. Какой код заключается в блок try? <br/>
**Ответ.** Блок кода, который может вызвать исключение.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/try.html>
 
10. Выполняется ли весь код блока try в случае возникновения исключения? <br/>
**Ответ.** После генерации исключения выполнение программы продолжится в блоке catch <br/>
**Источник.**
 
11. Может ли использоваться только один блок try (без catch или finally)? <br/>
**Ответ.** Нет, блок try используется с catch/finally или catch-finally.<br/>
**Источник.**
 
12. Какое назначение блока catch? <br/>
**Ответ.** Блок catch используется для обработки ошибок, которые возникают в блоке catch.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html>
 
13. Сколько блоков catch может содержаться после try-оператора? <br/>
**Ответ.** Взависимости от того, сколько исключений может выброситься.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html>
 
14. Если применяется несколько блоков catch, то в каком порядке в случае возникновения исключения они вызываются? <br/>
**Ответ.** Блоки catch вызываются в порядке постановки. Первый блок будет вызван первым(если выброшенное исключение имеет одинаковый тип с аргументом обработчика), последний - последним. <br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html>
 
15. Какой код может быть между блоками try и catch?<br/>
**Ответ.** Никакой код не может быть между коноцом блока try и началом первого блока catch.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html>
 
16. Может ли использоваться блок catch без блока try?<br/>
**Ответ.** Блок catch без блока try не может существовать.<br/>
**Источник.**

17. Сколько типов исключений может обрабатывать один блок catch?<br/>
**Ответ.** В Java SE 7 и более поздних версиях один блок catch может обрабатывать несколько типов исключений с помощью оператора |. 

```java
try{
	...
} catch(SomeException | SomeException2 e){
 	...
}
```
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html> 

18. В случае отсутствия исключения в блоке try выполняется ли блок catch?<br/>
**Ответ.** Нет. Блок catch выполняется в случае вознкновения исключений.<br/>
**Источник.**  

19. Для чего используется блок finally?<br/>
**Ответ.** Блок всегда выполняется, когда есть блок finally. Это гарантирует, что блок будет выполнен, даже если произойдет непредвиденное исключение. Но он полезен не только для обработки исключений — он позволяет программисту избежать случайного обхода кода очистки с помощью return, break, continue.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html> 

20. В случае отсутствия исключения в блоке try выполняется ли блок finally (при его наличии)?<br/>
**Ответ.** Да.Блок catch выполняется всегда.Блок finally может не выполниться, если JVM завершит работу во время выполнения кода try или catch.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html> 

21. Могут ли использоваться блоки try-finally без блока catch?<br/>
**Ответ.** Да.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html>
 
22. Приведите пример кода, в котором используется оператор try-with-resources.<br/>
**Ответ.**
 
```
try(Scanner sc = new Scanner(new FileReader("wfw"))){
...
}
```
**Источник.**

23. Какой оператор может использоваться вместо try-with-resources?<br/>
**Ответ.** Блок finally также можно использовать для освобождения ресурсов.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html> 

24. Может ли применяться оператор try-with-resources совместно с блоком finally?<br/>
**Ответ.** Да.<br/>
**Источник.** 

25. Какое ключевое слово используется в сигнатуре метода, чтобы указать на возможность выбрасывания им исключения?<br/>
**Ответ.** throws .<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/declaring.html> 

26. Сколько исключений может выбрасывать метод?<br/>
**Ответ.**  Количество исключений ограничено 64K – 65536 штук .<br/>
**Источник.** <http://www.skipy.ru/technics/exceptions.html>

27. Какое ключевое слово используется для гарантированного выбрасывания исключения?<br/>
**Ответ.**  throw.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/throwing.html>
 
28. Можно ли создавать свои собственные классы исключений?<br/>
**Ответ.** Да.<br/>
**Источник.** <http://www.skipy.ru/technics/exceptions.html>
 
29. Приведите примеры наиболее известных подклассов класса Exception.<br/>
**Ответ.**  
IOException (например, чтение файла конфигурации не найдено), 
SQLException (например, ошибка подключения к базе данных),
ClassNotFoundException (класс драйвера базы данных не найден), 
FileNotFoundExcetion (файл не найден)<br/>
**Источник.** <http://www.skipy.ru/technics/exceptions.html>

30. Приведите примеры наиболее известных подклассов класса RuntimeException.<br/>
**Ответ.**  
ArithmeticException Арифметическая ошибка: деление на ноль и др.  <br/>
ArrayIndexOutOfBoundsException Индекс массива находится вне его границ<br/>
ArrayStoreException Назначение элементу массива несовместимого типа<br/>
ClassCastException Недопустимое приведение типов<br/>
ConcurrentModificationException Некорректный способ модификации коллекции<br/>
IllegalArgumentException При вызове метода использован некорректный аргумент<br/>
IllegalMonitorStateException Незаконная операция монитора на разблокированном
объекте<br/>
IllegalStateException Среда или приложение находятся в некорректном
состоянии<br/>
IllegalThreadStateException Требуемая операция не совместима с текущим
состоянием потока<br/>
IndexOutOfBoundsException Некоторый тип индекса находится вне границ коллекции<br/>
NegativeArraySizeException Попытка создания массива с отрицательным размером<br/>
NullPointerException Недопустимое использование ссылки на null<br/>
NumberFormatException Невозможное преобразование строки в числовой формат<br/>
StringIndexOutOfBoundsException Попытка индексации вне границ строки<br/>
MissingResourceException Отсутствие файла ресурсов properties или имени
ресурса в нем<br/>
EnumConstantNotPresentException Несуществующий элемент перечисления<br/>
UnsupportedOperationException Встретилась неподдерживаемая операция .<br/>
**Источник.** <http://www.skipy.ru/technics/exceptions.html>
 
31. Что такое сцепление исключений?<br/>
**Ответ.**  Вид трансляции исключений, при котором исключение нижнего уровня передается при создании исключения верхнего уровня.Конструктор исключений верхнего уровня передаёт параметр cause конструктору суперкласса, учитывающему сцепление (chaining-aware), так что в конце концов он передаётся конструктору класса Throwable, учитывающему сцепление.<br/>
**Источник.** Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.5
 
32. Приведите пример кода со сцеплением исключений. <br/>
**Ответ.**  

```
try {
// Используем абстракцию нижнего уровня
// для выполнения нашей задачи
...
} catch (LowerLevelException cause) {
throw new HigherLevelException(cause);
}
```

**Источник.** <http://www.skipy.ru/technics/exceptions.html>
 
33. Какая информация приводится при трассировке стека во время исключения?<br/>
**Ответ.**  Трассировка стека предоставляет информацию об истории выполнения текущего потока и перечисляет имена классов и методов, которые были вызваны в момент возникновения исключения.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/chained.html>
 
34. В каких случаях целесообразно создавать собственный класс исключения?<br/>
**Ответ.**   Вам следует написать свои собственные классы исключений, если вы ответите утвердительно на любой из следующих вопросов; в противном случае вы, вероятно, можете использовать чужой.<br/>
Вам нужен тип исключения, который не представлен на платформе Java?<br/>
Помогло бы пользователям, если бы они могли отличить ваши исключения от исключений, создаваемых классами, написанными другими поставщиками?<br/>
Выдает ли ваш код более одного связанного исключения?<br/>
Если вы используете чужие исключения, будут ли пользователи иметь доступ к этим исключениям?<br/> Аналогичный вопрос: должен ли ваш пакет быть независимым и автономным?<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/creating.html>
 
35. Какой класс исключений может использоваться в качестве суперкласса для собственного исключения?<br/>
**Ответ.**  Для проверяемых исключений Exception. Для непроверяемых исключений RuntimeException.<br/>
**Источник.**  

36. Какие исключения целесообразно делать проверяемыми, а какие – непроверяемыми?<br/>
**Ответ.** Если можно обоснованно ожидать, что клиент восстановится после исключения, сделайте его проверенным исключением. Если клиент не может ничего сделать для восстановления после исключения, сделайте его непроверяемым исключением.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html>  
 
37. Какие преимущества в механизме выбрасывания и обработки исключений?<br/>
**Ответ.**  Исключения позволяют отделить детали того, что делать, когда происходит что-то необычное, от основной логики программы. Без существования механзима обработчика исключений приложение становится сложнее и теряется логическая последовательность кода. <br/>
Второе преимущество исключений — возможность распространять сообщения об ошибках вверх по стеку вызовов методов. <br/>
Третье преимущество - группировка и дифференциация типов ошибок.<br/>
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html>
 
38. Можно ли выбрасывать исключения в конструкторах?<br/>
**Ответ.**  Да, можно <br/>

```
	...
	public Student(String name,int age){
		if (name.trim().isEmpty() || age < 0){
			throw new IllegalArgumentException(...);
		}
	} 
```
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/advantages.html> 

39. Может ли произойти потеря исключения? Если да – приведите пример.<br/>
**Ответ.** Да. Например если в блоке finally бросать исключение.<br/>

```
 try {
            try {
                throw new Exception("a");
            } finally {
                if (true) {
                    throw new IOException("b");
                }
                System.err.println("c");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println("d");
            System.err.println(ex.getMessage());
        }
    }
```
**Источник.** <http://www.skipy.ru/technics/exceptions.html> 

40. Обладают ли исключения свойством транзакционности?<br/>
**Ответ.** Cвойством транзакционности исключения не обладают – действия, произведенные в блоке try до возникновения исключения, не отменяются поcле его возникновения.<br/>
**Источник.** <http://www.skipy.ru/technics/exceptions.html> 

41. Является ли данный код антипаттерном? <br/>
Обоснуйте ответ. <br/>

```
void methodCatchesSomeException() {
        	...
        	try {
                    	...
        	} catch (SomeException ex) {
                    	...
        	}
        	...
}
```
**Ответ.** Нет.<br/>
**Источник.**  


42. Является ли данный код антипаттерном? <br/>
Обоснуйте ответ. 

```
void methodThrowsSomeCheckedException() {
        	...
        	throw new SomeCheckedException();
        	...
}
```
**Ответ.** Да. Метод должен либо обрабатывать проверяемое исключение либо сообщать о выбросе.<br/>
**Источник.**  
 
43. Является ли данный код верным? Укажите почему.<br/>

```
void methodThrowsSomeCheckedException() throws SomeCheckedException {
        	...
        	throw new SomeCheckedException();
        	...
}
```
**Ответ.** Да.<br/>
**Источник.** 
 
44. Приведите примеры кода с выбросом RuntimeException явным образом и в случае программной ошибки.<br/>
**Ответ.** 

```
if(some < 0){
	throw new IllegalArgumentException("Wrong some");// явным образом
}
SomeObject ob = null;
ob.someMethod;// NPE программная ошибка
```
**Источник.** 
 
45. Можно ли отрефакторить данный код? Если да, то выполните.<br/>

```
void methodThrowsSomeRuntimeException() {
        	...
        	throw new SomeRuntimeException();
        	...
}
```
**Ответ.** Нет. <br/>
**Источник.**
 
46. Является ли данный код антипаттерном? <br/>
Обоснуйте ответ. 

```
void methodThrowsSomeRuntimeException() throws SomeRuntimeException {
        	...
        	throw new SomeRuntimeException();
        	...
}
```
**Ответ.** Сообщать о выбросе непроверяемого исключения это антипаттерн.<br/>
**Источник.**
 
47. В какой версии Java появился оператор try-with-resources? Приведите пример кода с использованием указанного оператора.
**Ответ.** Java SE 7.0

```
 try (FileReader fr = new FileReader(path);
	         BufferedReader br = new BufferedReader(fr)) {
	         return br.readLine();
	    }
```
**Источник.** <https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html>
 
48. Является ли данный код антипаттерном? 
Обоснуйте ответ. 

```
void methodWithAutocloseableInstance() {
        	...
        	try(SomeResource res = new SomeResource(...)) {
                    	...
        	}
        	...
}
```
**Ответ.** Нет.<br/>
**Источник.**

49. Является ли данный код антипаттерном? 
Обоснуйте ответ. 

```
void methodWithAutocloseableInstance() {
        	...
        	try(SomeResource res = new SomeResource(...)) {
                    	...
        	} catch (CorrectCheckedException ex) {
                    	…
        	}
        	...
```
}
**Ответ.** Нет.<br/>
**Источник.** 
 
50. Является ли данный код антипаттерном? 
Обоснуйте ответ. 

```
try {
           …
}  catch (SomeException е) {
        	//no code
}
```
**Ответ.** Пустой блок catch лишает смысла механизм исключений, ведь смысл как раз и состоит
в том, чтобы заставить вас обрабатывать исключительную ситуацию.Блок catch обязан содержать, по крайней мере, комментарий, объясняющий, почему данное исключение следует игнорировать.<br/>
**Источник.** Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.7
 
51. Является ли данный код антипаттерном? 
Обоснуйте ответ. 

```
try {
        	…
}  catch (SomeException е) {
    		System.out.println(“Something went wrong!”);
}
```
**Ответ.** Нет.<br/>
**Источник.** 
 
52. Является ли данный код антипаттерном, если блок catch находится не в конце раннер-метода main()? 

```
Обоснуйте ответ. 
try {
    		…
}  catch (Exception е) {
    		…
}
…
 
или другой вариант
 
try {
        	…
}  catch (Throwable е) {
        	…
}
```
…
**Ответ.** Не следует обрабатывать исключение более общим типом, т.к. не понятно что за исключение могло быть сгенерировано. Это приведет к путанице.<br/>
**Источник.** 

53. Является ли данный код антипаттерном? 
Обоснуйте ответ.
 
```
try {
    	int i = 0;
  	  while(true) {
        	а[i++].f();
        }
} catch(ArraylndexOutOfBoundsException е) {
    	…
}
```
**Ответ.** Это антипаттерн. Идиома цикла, использующая исключения, не просто снижает производительность и делает непонятным программный код. Нет гарантий, что она вообще будет
работать. Исключения должны применяться только в исключительных ситуациях. <br/>
**Источник.** Java: эффективное программирование, 2-е издание» Джошуа Блох, глава 57.
 
54. Есть ли недостатки у API  написанного только с использованием обрабатываемых исключений?
**Ответ.** Да. Злоупотребление проверяемыми исключениями может сделать API менее
удобным для использования. Если метод выбрасывает одно или несколько проверяемых
исключений, то в программном коде, из которого этот метод был вызван, должна
присутствовать обработка этих исключений в виде одного или нескольких блоков catch
либо должно быть объявлено, что этот код сам выбрасывает исключения и передаёт их
дальше. <br/>
**Источник.** Java: эффективное программирование, 2-е издание» Джошуа Блох, глава 57.
 
55. Даны два варианта сеттера.
Какой является более предпочтительным в использовании?
Обоснуйте ответ. 
Вариант 1.

```
void setPositiveValue(int value) {
        	if(value <= 0) {
                    	throw new IllegalArgumentException(...);
        	}
        	this.value = value;
}
```
Вариант 2.

```
void setPositiveValue(int value) throws SomeCheckedException {
        	if(value <= 0) {
                    	throw new SomeCheckedException(...);
        	}
        	this.value = value;
}
```
**Ответ.** Вариант 1. Проверяемые исключения следует использовать для восстановления.Также злоупотребление проверяемыми исключениями может сделать API менее удобным для использования.Если метод генерирует проверяемое исключение, то его следует ловить и обрабатывать. <br/>
**Источник.** 

56. Приведите пример наиболее распространенных из повторно используемых исключений и причины их применения.
**Ответ.** Повторно используемые исключения упрощают освоение и применение API, поскольку
соответствуют установленным соглашениям, с которыми программисты уже знакомы.
С этим же связано второе преимущество, которое заключается в том, что программы,
использующие API, легче читать, поскольку там нет незнакомых, сбивающих с толку
исключений. Наконец, чем меньше классов исключений, тем меньше требуется места в
памяти и времени на их загрузку.<br/>
IllegalAgrumentException - выбрасывается, когда вызываемому методу передаётся аргумент с неправильным значением.<br/>
IllegalStateException - выбрасывается, если в соответствии с состоянием объекта вызов метода является неправомерным.<br/>
NullPointerException - значение параметра равно null,а это запрещено.<br/>
IndexOutOfBoundsException - значение параметра, задающего индекс,выходит за пределы диапазона.<br/>
ConcurrentModificationException - обнаружена параллельная модификация объекта из разных потоков, а это запрещено.<br/>
UnsupportedOperationException - объект не поддерживает вызываемый метод.<br/>
**Источник.** Java: эффективное программирование, 2-е издание» Джошуа Блох, глава 60.

57. Если недостаточно информации для принятия решения и...
1)     исключительная ситуация должна быть исправлена на ближайшем уровне;
2)     исключительная ситуация маловероятна
Экземпляры подклассов каких типов исключений целесообразно сгенерировать в каждом случае?
**Ответ.**  
1) Exception<br/>
2) RuntimeException<br/>
<br/>
**Источник.** Java: эффективное программирование, 2-е издание» Джошуа Блох, глава 58-59.

58. Выделите в блоке try-catch основной и альтернативный сценарии. 
Обоснуйте ответ.

``` 
try {
        	...
        	User user = source.getUser(login, password);
        	if(GUEST_USER.equals(user) {
                    	…
        	} else {
                    	…
        	}
        	...
}  catch (SourceException е) {
    		…
}
class Source {
        	public User getUser(String login, String password) throws SourceException {
                    	...
                    	throw new SourceException(...);
                    	...
                    	return GUEST_USER;	//wrong login or password
                    	...
                    	return new User(...);
        	}
        	...
}
```
**Ответ.** 

```
User user = source.getUser(login, password); //основной вариант
if(GUEST_USER.equals(user) { // альтернатива
                    	…
        	} else {
                    	…
        	}
```
 
Генерируя проверяемое исключение(при неправильном вводе пароля и логина), возможно восстановление работы путем выполнения действий в обработчике исключений.	
**Источник.** 
 
59. Перепишите код предыдущего задания с условием, что ввод неправильных логина или пароля является альтернативным сценарием.
**Ответ.** 

``` 
        	...
        	User user = source.getUser(login, password);
        	if(GUEST_USER.equals(user) {
                    	…
        	} else {
                    	…
        	}
        	...
}
class Source {
        	public User getUser(String login, String password){
                    	...
                    	return GUEST_USER;	//wrong login or password
                    	...
                    	return new User(...);
        	}
        	...
}
```

**Источник.** 
 
60. Что такое трансляция исключения, когда ее используют и какие правила ее использования? Приведите пример трансляции исключения.
**Ответ.**  В ситуациях, когда невозможно предотвратить возникновение исключений на нижних уровнях или изолировать от них верхние уровни, как правило,должен применяться механизм трансляции исключений.<br/>
Верхние уровни приложения должны перехватывать исключения нижних уровней и, в свою очередь, выбрасывать исключения, которые можно объяснить в терминах абстракции верхнего уровня.<br/>

```
try {
// Используем абстракцию нижнего уровня
// для выполнения нашей задачи
...
} catch (LowerLevelException е) {
throw new HigherLevelException(...);
}
```
**Источник.** Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.5
 
61. Как можно избежать использования трансляций и зачем нужно это предпринимать?
**Ответ.** Чем больше исключений перехватывается и генерируется, тем больше код, обрабатывающий исключение, будет изолирован от кода, его генерирующего. То есть становится трудным сопоставить точку перехвата с исходной точкой генерации исключения.<br/>
Хотя трансляция исключений лучше, чем бессмысленная передача наверх исключений
с нижних уровней, злоупотреблять ею не следует. Самый хороший способ работы с
исключениями нижнего уровня — полностью исключить их возможность. Для этого
перед вызовом метода нижнего уровня необходимо убедиться в том, что он будет
выполнен успешно. Иногда добиться этого можно путём явной проверки аргументов
метода верхнего уровня перед их передачей на нижний уровень.<br/>
**Источник.** Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.5
 
62. В каких случаях  надо предпочесть сцепление трансляции?
**Ответ.** Сцепление исключений позволяет выбросить исключение, подходящее для верхнего уровня, и при этом сохранить нижележащую причину этого исключения для анализа ошибки. В тех случаях, когда исключение нижнего уровня может быть полезно при анализе ситуации, вызвавшей исключение, лучше использоват сцепление исключений <br/>
**Источник.** Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.5

63. Можно ли для исключительной ситуации определить, класс, который не является подклассом Exception, RuntimeException, Error. 
Если да, то как он будет себя проявлять (как checked-exception или как unchecked-exception)?
**Ответ.** Можно определить подкласс класса Throwable, который не наследует ни от одного из
классов Exception, RuntimeException и Error. В спецификации языка Java такие классы
напрямую не оговариваются, однако неявно подразумевается, что они будут вести
себя так же, как обычные проверяемые исключения (которые являются подклассами
класса Exception, но не RuntimeException). Когда же имеет смысл объявлять такие
классы? Если одним словом, то никогда. Не имея никаких преимуществ перед обычными
проверяемыми исключениеми, они только запутают пользователей вашего API.<br/>
**Источник.**  Java: эффективное программирование, 2-е издание» Джошуа Блох, глава 58.

64. Обязательно ли информацию об исключительное ситуации представлять строковым полем?
Если нет, то какой альтернативный способ создания строкового представления исключения?
**Ответ.** Для фиксации сбоя строковое представление исключения должно содержать значения
всех параметров и полей, «способствовавших появлению этого исключения».Один из приёмов, гарантирующих, что строковое представление исключения будет содержать информацию, достаточную для описания сбоя, состоит в том, чтобы эта информация запрашивалась в конструкторах исключения, а не в строке описания.<br/>

```
public IndexOutOfBoundsException(int lowerBound, int upperBound,int index){
	// Генерируем описание исключения,
	// фиксирующее обстоятельства отказа
	super("Lower bound: " + lowerBound +
		", Upper bound: " + upperBound +
		", Index: " + index);
}
// Сохраняем информацию об ошибке для программного доступа
...
```
**Источник.** 
 
65. Если метод завершается сбоем, что нужно сделать с объектом, на котором был вызван этот метод?
**Ответ.** вызов метода, завершившийся сбоем, должен оставлять обрабатываемый объект в том же
состоянии, в каком тот был перед вызовом.<br/>
**Источник.**  Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.8

66. Приведите способы достижения атомарности по отношению к сбоям.
**Ответ.** 
1.Создание неизменяемых объектов. Если операция заканчивается сбоем, это
может помешать созданию нового объекта, но никогда не оставит уже имеющийся
объект в неопределённом состоянии, поскольку состояние каждого неизменяемого
объекта согласуется в момент его создания и после этого уже не меняется.
Для методов, работающих с изменяемыми объектами, атомарность по отношению к сбою чаще всего достигается путём проверки правильности параметров перед выполнением операции.<br/>
2.Упорядочение вычислений таким образом, чтобы все фрагменты кода, способные повлечь сбой, предшествовали первому фрагменту,который модифицирует объект.<br/>
3.Написание специального кода восстановления (recovery code), который перехватывает сбой, возникающий в ходе выполнения операции, и заставляет объект вернуться в то состояние, в котором он находился в момент, предшествующий началу операции.<br/>
4.Выполнение операции на временной копии объекта и, как только операция будет завершена, замещать содержимое объекта содержимым его временной копии.<br/>
**Источник.**  Джошуа Блох. Java: эффективное программирование, 3-е издание. Глава 10.8

67. Приведите пример, когда отсутствие транзакционности в исключениях, приводит к сохранению ссылки на объект в неверном состоянии.
**Ответ.**

```
public class PartialInitTest{

    static PartialInitTest self;

    private int field1 = 0;
    private int field2 = 0;

    public PartialInitTest(boolean fail) throws Exception{
        self = this;
        field1 = 1;
        if (fail) {
            throw new Exception();
        }
        field2 = 1;
    }

    public boolean isConsistent(){
        return field1 == field2;
    }

    public static void main(String[] args){
        PartialInitTest pit = null;
        try {
            pit = new PartialInitTest(true);
        } catch (Exception ex){
            // do nothing
        }
        System.out.println("pit: "+pit);
        System.out.println("PartialInitTest.self reference: "+PartialInitTest.self);
        System.out.println("PartialInitTest.self.isConsistent(): "+PartialInitTest.self.isConsistent());
    }
}
```
**Источник.** <http://www.skipy.ru/technics/exceptions.html>

68. Необходимо создать коллекцию из результатов тестов, находящихся в валидном файле src/in.csv.
Пример файла
cool;75;90
clever;68;95
looser;30;48
Является ли код, реализующий задание, антипаттерном? 
Обоснуйте ответ. 

```
public class Runner {
        	public static void main(String[] args) {
                    	List<Trial> trials = new ArrayList<Trial>();
                    	try(Scanner sc = new Scanner(new FileReader("src/in.csv"))) {
                               	while(sc.hasNext()) {
                                           	Trial trial = getTrial(sc);
                                           	trials.add(trial);
                               	}
                               	printTrials(trials);
                    	} catch (FileNotFoundException e) {
                               	System.out.println(Constants.ERROR_FILE_FOUND);
                    	}
        	}
        	private static Trial getTrial(Scanner sc) {
                    	String csvLine = sc.nextLine();
                    	String[] values = csvLine.split(Constants.DELIMETER);   	
                    	try {
                               	String name = values[Constants.NAME_INDEX];
                               	int mark1 = Integer.parseInt(values[Constants.MARK1_INDEX]);
                               	int mark2 = Integer.parseInt(values[Constants.MARK2_INDEX]);
                   		return new Trial(name, mark1, mark2);
                    	} catch (CsvLineException e) {
                               	System.out.println(Constants.ERROR_WRONG_DATA);
                    	}
        	}
```
**Ответ.** Исключения следует генерировать на нижних уровнях.<br/>

```
private static Trial getTrial(Scanner sc) {
                    	String csvLine = sc.nextLine();
                    	String[] values = csvLine.split(Constants.DELIMETER);   	
                    	try {
                   		return new Trial(values);
                    	} catch (CsvLineException e) {
                               	System.out.println(Constants.ERROR_WRONG_DATA);
                    	}
        	}
```


**Источник.** 
 
69. Приведите пример кода собственного исключения (реализация в одном классе всего нижеперечисленного). Класс исключения содержит:
-         поле, которым является неправильная строка, считанная из файла (имя csvLine);
-         конструктор по умолчанию, вызывающий конструктор суперкласса;
-         параметризованный конструктор, принимающий экземпляр исключения и неправильную строку, считанную из файла;
-         параметризованный конструктор, принимающий строку с указанием причины исключения и  неправильную строку, считанную из файла;
-         геттер с возвратом неправильной строки;
-         переопределенного метода toString с указанием неправильной строки и метода вывода сообщения об ошибки.


**Ответ.** 
<br/>

```
public class CsvLineException extends Exception {
    private String csvLine;

    public CsvLineException(String csvLine) {
	this.csvLine = csvLine;
    }

    public CsvLineException() {
    }

    public CsvLineException(String csvLine, Throwable cause) {
	super(csvLine,cause);
	this.csvLine = csvLine; 
    }

    public CsvLineException(String csvLine, String causeMessage) {
	super(causeMessage);
	this.csvLine = csvLine;
    }
    
    public String getCsvLine() {
	return csvLine;
    }
    
    @Override
    public String toString() {
        return csvLine + ";" + getMessage();
    }
 }
```
**Источник.** 
  
70. Необходимо создать метод для экспорта csv-файла в коллекцию.
При наличии хотя бы одной ошибки в исходных данных “отменить” создание 
коллекции.
Какие антипаттерны содержит следующий код? 
Предложите варианты по избавлению от них.

```
private static List<Trial> getTrials(Scanner sc) {
List<Trial> trials = new ArrayList<Trial>();
try {
while(sc.hasNext()) {
Trial trial = getTrial(sc);
trials.add(trial);
}
} catch (CsvLineException e) {
System.err.println(e);
}
return trials;
}
```
**Ответ.** При валидных обьектах trial, список trials будет заполняться. Как только обьект trial окажется некорректным и сгенерируется исключение,тот список trials с обьектами trial, возвратиться методом.Для избавления от этого можно в блоке catch вернуть null или пустой список.<br/>
**Источник.** 

72. Создать метод для экспорта данных в коллекцию из последовательного источника экземпляров Trial.
См. код ниже.
Его необходимо дополнить, чтобы происходила обработка следующих исключительных ситуаций:
1. 	Файл не найден.
2. 	Ошибка в csv строке.

```
interface TrialProvidable {
	boolean hasTrial();
	Trial getTrial();
}
 
public class TrialCsvImpl implements TrialProvidable {
	private Scanner sc;
	public CsvImpl(String csvName) {            	
		sc = new Scanner(new FileReader(csvName));
	}
	public boolean hasTrial() {             	
		return sc.hasNextLine();
	}
	public Trial getTrial() {
		// get Trial instance from csv line
		return trial;
	}
}
 
public class Runner {
	private static List<Trial> getTrials(TrialProvidable trialProvider) {
		List<Trial> trials = new ArrayList<Trial>();
		while(trialProvider.hasTrial()) {
			Trial trial = trialProvider.getTrial();
			trials.add(trial);
		}
		return trials;
	}
 
	public static void main(String[] args) {
		TrialProvidable trialProvider = null;
		try {
			if("csv".equals(args[0])) {
				trialProvider = new TrialCsvImpl("src/in.csv");
			} else {
				trialProvider = new TrialDBImpl();
			}
		List<Trial> trials = getTrials(trialProvider);
…
		} finally {
			if (trialProvider != null) {
				trialProvider.close();
			}
		}
	}
}

```
**Ответ.**

```
 interface TrialProvidable {
	boolean hasTrial();
	Trial getTrial();
}
 
public class TrialCsvImpl implements TrialProvidable {
	private Scanner sc;
	public CsvImpl(String csvName)throws FileNotFoundException {            	
		sc = new Scanner(new FileReader(csvName));
	}
	public boolean hasTrial() {             	
		return sc.hasNextLine();
	}
	public Trial getTrial() throws CsvLineException{
		try{
			Trial trial = new Trial(...);
			return trial;
		catch(SomeException e){
			throw new CsvLineException(...);
		}
	}
}
 
public class Runner {
	private static List<Trial> getTrials(TrialProvidable trialProvider) {
		List<Trial> trials = new ArrayList<Trial>();
		try{
			while(trialProvider.hasTrial()) {
				Trial trial = trialProvider.getTrial();
				trials.add(trial);
			}
		}catch(CsvLineException e){
			System.err.println(...);
		}
		return trials;
	}
 
public static void main(String[] args) {
TrialProvidable trialProvider = null;
	try {
		if("csv".equals(args[0])) {
			trialProvider = new TrialCsvImpl("src/in.csv");
		} else {
			trialProvider = new TrialDBImpl();
		}
		List<Trial> trials = getTrials(trialProvider);
	}catch(FileNotFoundException e){
		System.out.println("File is not found");
	}finally {
		if (trialProvider != null) {
			trialProvider.close();
		}
	}
}
}
```
**Источник.**