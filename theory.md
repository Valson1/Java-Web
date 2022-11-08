Что такое лямбда-выражение?<br/>
**Ответ** Лямбда-выражения позволяют более компактно выражать экземпляры классов с одним методом.<br/>
**Источник**  <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>

Из каких элементов состоит объявление лямбда-выражения?<br/>
**Ответ** Лямбда-выражение состоит из следующего:
Разделенный запятыми список формальных параметров, заключенный в круглые скобки; <br/>
Cтрелка,-> ; <br/>
Тело, состоящее из одного выражения или блока операторов. <br/>
Пример
p -> p.getName() == "Genrih"; <br/>
или <br/>
(a,b) -> a + b; <br/>
**Источник**  <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>

При объявлении лямбда-выражения какие структуры можно опустить?<br/>
**Ответ** Можно опустить скобки(), если параметр лямбды один. Можно опустить скобки {}, если выполняемых операторов в теле лямбды один.<br/>
**Источник** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>

Что такое поток и конвейер в контексте лямбда-выражения?<br/>
**Ответ** Конвейер — это последовательность агрегатных операций.Поток — это последовательность элементов. В отличие от коллекции, это не структура данных, в которой хранятся элементы. Вместо этого поток переносит значения из источника по конвейеру.<br/>
**Источник** <https://docs.oracle.com/javase/tutorial/collections/streams/index.html>

Какие компоненты содержит конвейер? <br/>
**Ответ** 
Источник: это может быть коллекция, массив, функция-генератор или канал ввода-вывода. <br/>
Ноль или более промежуточных операций (intermediate operations). <br/>
Терминальная операция. <br/> 
**Источник** <https://docs.oracle.com/javase/tutorial/collections/streams/index.html>
 
Что такое агрегатные операции? Приведите примеры агрегатных операций. <br/>
**Ответ** <br/> Агрегатные операции - потоковые операции, которые используются для манипулирования элементами потока. Агрегатные операции обрабатывают элементы из потока, а не непосредственно из коллекции.<br/>
Пример: filter(Predicate<? super T> predicate) - фильтрация по предикату.<br/>
map(Function<? super T,? extends R> mapper) - преобразование одного типа элемента в другой.<br/>
sorted(Comparator<? super T> comparator) - сортировка элементов в потоке.<br/>
**Источник** https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html.

Какие различия между агрегатными операциями и итераторами? <br/>
**Ответ** Агрегированные операции, такие как forEach, похожи на итераторы. Однако они имеют несколько принципиальных отличий:<br/>
Они используют внутреннюю итерацию : агрегатные операции не содержат метода, подобного next указанию им обработать следующий элемент коллекции. При внутреннем делегировании ваше приложение определяет, какую коллекцию оно итерирует, но JDK определяет, как итерировать коллекцию. С внешней итерацией, ваше приложение определяет и то, какую коллекцию оно итерирует, и то, как оно это делает. Однако внешняя итерация может выполнять итерацию только по элементам коллекции последовательно. Внутренняя итерация не имеет этого ограничения. Он может легче использовать преимущества параллельных вычислений, которые включают разделение проблемы на подзадачи, одновременное решение этих проблем и последующее объединение результатов решений подзадач. <br/>
Они обрабатывают элементы из потока : агрегатные операции обрабатывают элементы из потока, а не непосредственно из коллекции. Следовательно, их также называют потоковыми операциями .<br/>
Они поддерживают поведение в качестве параметров : вы можете указать лямбда-выражения в качестве параметров для большинства агрегатных операций. Это позволяет настроить поведение конкретной агрегатной операции. <br/>
**Источник** <https://docs.oracle.com/javase/tutorial/collections/streams/index.html>

Какие имеются ограничения на локальные переменные, которые используются в лямбда-выражениях?<br/>
**Ответ** Лямбда-выражения имеют лексическую область видимости. Это означает, что они не наследуют имена от супертипа и не вводят новый уровень области видимости. Объявления в лямбда-выражении интерпретируются так же, как и во внешней среде. <br/>
Подобно локальным и анонимным классам, лямбда-выражение может обращаться только к локальным переменным и параметрам включающего блока, которые являются окончательными или фактически окончательными. <br/>
**Источник** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>

Что такое целевой тип лямбда-выражения и как компилятор определяет целевой тип?<br/>
**Ответ** Целевым типом лямбда-выражения называется тип контекста, в котором это выражение встречается, – например, тип локальной переменной, которой оно присваивается, или тип параметра метода, вместо которого оно передается.<br/>
Компилятор Java определяет целевой тип с помощью двух других функций языка: разрешения перегрузки и вывода аргумента типа. <br/>
**Источник** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>

В каких ситуациях может использоваться лямбда-выражение?<br/>
**Ответ** Передача функциональности в агрегатные операции;<br/>
Использование стандартных функциональных интерфейсов с лямбда-выражениями;<br/>
**Источник** <https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html>

Могут ли лямбда-выражения ссылаться на другие существующие методы? Если да - приведите пример.<br/>
**Ответ** Иногда лямбда-выражение не делает ничего, кроме вызова существующего метода. В таких случаях часто проще обратиться к существующему методу по имени. Ссылки на методы позволяют это сделать; это компактные, легко читаемые лямбда-выражения для методов, у которых уже есть имя.<br/>
System.out :: println <br/>
**Источник** <https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html>

Укажите виды ссылок на методы в контексте лямбда-выражений и приведите соответствующие пример.<br/>
**Ответ** Ссылка на статический метод: ContainingClass::staticMethodName; (Person::compareByAge,MethodReferencesExamples::appendStrings)<br/>
Ссылка на метод экземпляра конкретного объекта: containingObject::instanceMethodName (myComparisonProvider::compareByName,myApp::appendStrings2)<br/>
Ссылка на метод экземпляра произвольного объекта определенного типа:ContainingType::methodName	(String::compareToIgnoreCase,String::concat)<br/>
Ссылка на конструктор:	ClassName::new	(HashSet::new) <br/>
**Источник** <https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html>

Что такое операции сокращения в контексте лямбда-выражений?<br/>
**Ответ** Операции редукции или операции сокращения - терминальные операции, которые возвращают одно значение путем объединения содержимого потока. <br/>
JDK предоставляет операции сокращения общего назначения reduce и collect.<br/>
**Источник** <https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html>

Чем метод reduce отличается от метода collect в контексте лямбда-выражений? <br/>
**Ответ** Операция reduce всегда возвращает новое значение. <br/>
 Операция collect изменяет или видоизменяет существующее значение. <br/>
Следовательно collect() предназначен работать только с изменяемыми объектами результатов, reduce() предназначен для работы с неизменяемыми объектами результатов.
reduce предназначена для объединения двух неизменяемых значений, таких как int, double и т. д., и создания нового; это неизменное сокращение. Напротив, метод collect предназначен для изменения контейнера для накопления ожидаемого результата.<br/>
**Источник** <https://stackoverflow.com/questions/22577197/java-8-streams-collect-vs-reduce>

Укажите, какие преимущества дает использование класса Optional?<br/>
**Ответ** Класс java.util.Optional<T> представляет собой оболочку для любого объектного типа. Смысл класса в обозначении проблемы возвращения значения null, методом осуществляющим поиск/обработку/генерацию какого-либо объекта в ситуации, если это действие не дало приемлемого результата.Когда программист использует метод, возвращающий некоторую ссылку на значение, то он планирует использовать полученный объект. Если метод возвращает null, то в этом случае его использование гарантированно приведет к генерации NullPointerException. Программисту придется вводить в код дополнительную проверку на null, чтобы защитить свой код от исключения.<br/>
Класс Optional<T> не решает проблему возвращения null, но его использование в качестве возвращаемого значения ясно обозначает ее. <br/>
**Источник** Java from EPAM : учеб.-метод. пособие / И. Н. Блинов, В. С. Романчик стр. 78

Выполните вывод каждого элемента коллекции collect с помощью метода forEach (), применяя:<br/>
итератор <br/>
поток <br/>
**Ответ** 1)collect.forEach(System.out :: println); <br/>
2)collect.stream().forEach(System.out :: println); <br/>
**Источник** <>

Выполните вывод каждого элемента Map collect с помощью java 8.
**Ответ** map.entrySet().stream().forEach(System.out :: println);<br/>
**Источник** <>

Допишите сортировку коллекции users по имени с помощью метода getName() и вывод всех элементов коллекции в консоль (класс User приводить не обязательно).<br/>

```java
public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
            users.add(new User("Nick", "Boll"));
            users.add(new User("Jan", "Nicky"));
            users.add(new User("Bot", "Smart"));
...
    }
}
```

**Ответ** <br/>
users.stream().sorted((user,otherUser) -> name.getName().compareTo(otherUser.getName())).forEach(System.out :: println); <br/>
**Источник** 

Допишите код, чтобы вывести только четные элементы коллекции, используя метод filter().<br/>

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
…
    }
}
```

**Ответ** 
numbers.stream().filter(number -> number % 2 == 0).forEach(System.out :: println);<br/>
**Источник** 

Допишите код, чтобы вывести количество элементов коллекции, длина которых больше 4, используя методы filter() и count(). <br/>

```java
public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jan", "Tirion", "Marry", "Nikolas");
…
    }
}
```

**Ответ** <br/>
System.out.println(names.stream().filter(name -> name.length() > 4).count());<br/>
**Источник** 

Допишите код, чтобы вывести каждый элемент коллекции, умножив его на 2, используя метод map().<br/>

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7);
 …
    }
}
```

**Ответ** numbers.stream().map(num -> num * 2).forEach(System.out :: println);
**Источник** <>

Создайте новую коллекцию ArrayList и выведите в консоль список четных чисел из коллекции numbers с использованием методов filter() и collect().<br/>

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = ...
 …
    }
}
```

**Ответ** List<Integer> evenNumbers = numbers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
**Источник** <>

Создайте новую коллекцию LinkedList (имплементация Queue) и выведите в консоль НЕ пустые строки из коллекции ArrayList names с использованием методов filter() и collect().

```java
public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
        Queue<String> queue = ….
 …
    }
}
```

**Ответ** 

```java
Queue<String> queue = names.stream().filter(str -> !str.isEmpty()).collect(Collectors.toCollection(LinkedList :: new));
queue.forEach(System.out :: println);
```

**Источник** <>

Выведите имена домашних животных, объединив их в новую коллекцию List<String> petNames из коллекции их хозяев humans, у которых имена домашних животных являются полями класса (в виде отдельных коллекций), используя метод flatMap().

```java
public class Main {
    public static void main(String[] args) {
        List<Human> humans = asList(
            new Human("Sam", asList("Buddy", "Lucy")),
            new Human("Bob", asList("Frankie", "Rosie")),
            new Human("Marta", asList("Simba", "Tilly")));
        List<String> petNames = ...
 …
    }
}
```

**Ответ** List<String> petNames = humans.stream().flatMap(human -> human.getPets().stream()).collect(Collectors.toList());
**Источник** <https://vertex-academy.com/tutorials/ru/java-8-stream-flatmap/>

Найдите и выведите первое по счету число, которое больше 10, используя методы filter() и findFirst(). <br/>

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
        Optional<Integer> first = ...
 …
    }
}
```

**Ответ** 
Optional<Integer> first = numbers.stream().filter(num -> num > 10).findFirst(); <br/>
Для последовательных и параллельных стримов результат будет одинаковый(12).<br/>
**Источник** <https://vertex-academy.com/tutorials/ru/java-8-stream-find/>

Найдите и выведите первую попавшуюся фразу (с учетом возможного многопоточного процесса), которая содержит фрагмент "Java", используя методы filter() и findAny().<br/>

```java
public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
        Optional<String> java = …
...
     }
}
```

**Ответ**  Optional<String> java = strings.parallelStream().filter(str -> str.contains("Java")).findAny();<br/>
Вывод для stream(): "Java is the best" <br/>
Вывод для parallelStream(): разный <br/>
Для обычных Stream-ов (последовательных) при нескольких запусках результат будет один и тот же. Для параллельных же - всегда разный <br/>
**Источник** <https://vertex-academy.com/tutorials/ru/java-8-stream-find/>

Выведите boolean, имеется ли в коллекции хотя бы одно четное значение, используя метод anyMatch().

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
        boolean match = ...
 ...
     }
}
```

**Ответ**  boolean match = numbers.stream().anyMatch(number -> number % 2 == 0);<br/>
Вывод: false <br/>
**Источник** <https://vertex-academy.com/tutorials/ru/java-8-stream-match/>

Выведите boolean, являются ли все числа коллекции положительным, используя метод allMatch().<br/>

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        boolean match = ...
 ...
     }
}
```

**Ответ** boolean match = numbers.stream().allMatch(number -> number % 2 == 0);<br/>
Вывод: false <br/>
**Источник** <https://vertex-academy.com/tutorials/ru/java-8-stream-match/>

Выведите boolean, НЕ являются ли все числа коллекции четными, используя метод noneMatch(). <br/>

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        boolean match = ...
  ...
     }
}
```

**Ответ** boolean match = numbers.stream().noneMatch(number -> number % 2 == 0); <br/> 
Вывод : true <br/>
**Источник** <https://vertex-academy.com/tutorials/ru/java-8-stream-match/>

Какие из ниже приведенных ламбда-выражений не скомпилируется и почему?

```java
(int x, int y) -> x+y  
(x, y) -> x+y          
(x, int y) -> x+y   
(x, final y) -> x+y 
int x -> x;
x -> y -> x + y;
x -> (final int y) -> y + x;
x -> x -> 5;
```

**Ответ** 
(x, int y) -> x+y; - оба параметра должны быть с типом или без него <br/>
(x, final y) -> x+y - параметр y без указания типа. <br/>
int x -> x; - (int x) -> x; <br/>
x -> x -> 5; - Параметр x лямбда-выражения не может повторно объявить другую локальную переменную, определенную во внешней области. <br/>
**Источник** <>

Скомпилируется ли следующий код и почему? <br/>

```java
for (byte b : "Java".getBytes()) {
            foo(() -> b);
 }
```

**Ответ** Да. <br/>
**Источник** <>

Дана матрица 3х3 используя Java 8 преобразуйте ее в одномерный массив. <br/>

```java
int[][] matrix = {   {1, 2, 3}
                           , {4, 5, 6}
                           , {7, 8, 9}};
int[] array = ...
```

**Ответ** int[] array = Arrays.stream(matrix).flatMap(Arrays :: stream).toArray(); <br/>
**Источник** 

Даны классы:

```java
class BlogPost {
    String title;
    String author;
    BlogPostType type;
    int likes; 
}
enum BlogPostType {
    NEWS,
    REVIEW,
    GUIDE
}
List<BlogPost> posts = Arrays.asList( ... );
```

Определите:
Все уникальные статьи относящиеся к каждому типу статей. <br/>
Для каждого типа статьи определите статью с максимальным количеством лайков. <br/>
Все статьи относящиеся к каждому типу статей, список статей должен представлять собой строку формата: “Post titles: [title1, title2, …..] “ <br/>
**Ответ** 

```java
 2)Map<BlogPostType, Optional<Integer>>map = posts.stream().collect(Collectors.groupingBy(BlogPost :: getType,(Collectors.mapping(BlogPost :: getLikes, Collectors.maxBy(Integer :: max)))));
	    System.out.println(map);
 3)Map<BlogPostType, List<BlogPost>>map1 = posts.stream().collect(Collectors.groupingBy(BlogPost :: getType));
	    map1.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
```

**Источник** <>

Приведите два способа получения последнего элемента в потоке, в чем особенности вычисления этого значения в потоках. <br/>
**Ответ** books.stream().skip(books.size() - 1).findFirst().get(); <br/>
stream.reduce((first, second) -> second).get(); <br/>
**Источник** <>

Дан код, можно ли его как-то отрефакторить? Если да, то сделайте это.<br/>

```java
class Book {
private String name;
private String author;

// getters and setters
…
}

List<Book> books = new ArrayList<>();

books.add(new Book("Effective Java", "Joshua Bloch"));
books.add(new Book("Thinking in Java", "Bruce Eckel"));
books.add(new Book("Java: The Complete Reference", "Herbert Schildt"));

Map<String, String> bookMap = books.stream().collect(
Collectors.toMap(Book::getAuthor, Book::getName));
bookMap.forEach((author, book) -> 
System.out.println("Author: " + author + " Books: " + book));
```

**Ответ** Map<String, String> bookMap = books.stream().collect(Collectors.toMap(Book::getAuthor, Book::getName, (s,a) -> s + ", " + a));<br/>
**Источник** <>

Дан код

```java
class Employee {
    Integer employeeId;
    String employeeName;
 
    // getters and setters
}
 
class Department {
    Integer employeeId;
    String department;
 
    // getters and setters
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        List<Department> departments = new ArrayList<>();

        populate(employees, departments);

       List<Employee> salesEmpoyees = ...
    }
}
```

Замените многоточие, чтобы определить сотрудников находящихся в отделе “sales”<br/>
**Ответ** 
**Источник** <>

Дан код

```java
class Tuple<T1, T2> {
	private T1 item1;
	private T2 item2;
	// getters and setters
}
List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis")); 
List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));
List<Tuple<String, Integer>> namesAndAges = … 
```

Выполните операцию ‘zip’ для коллекций ages и names.<br/>
Zip: операция «zip» немного отличается от стандартной «concat» или «merge». В то время как операции «concat» или «merge» просто добавят новую коллекцию в конец существующей коллекции, операция «zip» возьмет элемент из каждой коллекции и объединит их.<br/>
Например, в результате выполнения этого задания должна получиться коллекция: <br/>
[John;24, Jane;25, Jack;27] <br/>
**Ответ** 

```java
IntStream
  .range(0, Math.min(names.size(), ages.size()))
  .mapToObj(i -> names.get(i) + ":" + ages.get(i));
```

**Источник** <>

Дан код, замените  {code} и {type} так, чтобы получить нужные результаты <br/>

```java
Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");
// Удалить все дубликаты
List<String> unique= strings.stream().{code}
// напечатает unique= [a1, b2, c3]
System.out.println("unique = " + unique); 
// Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>
String join = strings.stream().collect({code});
// напечатает <b> a1 : b2 : c3 : a1 </b>
System.out.println("join = " + join); 
// Преобразовать в map, сгруппировав по первому символу строки
Map<String, List<String>> groups = strings.stream().collect({code});
// напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}
System.out.println("groups = " + groups); 
// Преобразовать в map, сгруппировав по первому символу строки и в качестве значения взять второй символ, если ключ повторяется, то значения объединить через “:” 
Map<String, String> groupJoin = strings.stream()
     .collect(Collectors.groupingBy({code}));
// напечатает groupJoin = groupJoin = {a=1:1, b=2, c=3}
System.out.println("groupJoin = " + groupJoin);

Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
// Получить сумму нечетных чисел
{type} sumOdd = numbers.stream().collect({code});
// напечатает sumEven = 4
System.out.println("sumOdd = " + sumOdd); 
 // Вычесть из каждого элемента 1 и получить среднее
 double average = numbers.stream().collect({code});
 // напечатает average = 1.5
System.out.println("average = " + average); 
// Прибавить к числам 3 и получить статистику: количество элементов, их сумму, макс и мин. значения, а также их среднее.
{type} statistics = numbers.stream().collect({code});
// напечатает statistics = … {count=4, sum=22, min=4, average=5.500000, max=7}
System.out.println("statistics = " + statistics);
// Разделить числа на четные и нечетные
Map<Boolean, List<Integer>> parts = numbers.stream().collect({code});
// напечатает parts = {false=[1, 3], true=[2, 4]}
System.out.println("parts = " + parts); 
```

**Ответ** 

```java
Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");
	List<String> unique= strings.stream().distinct().collect(Collectors.toList());
	String join = strings.stream().collect(Collectors.joining(":", "<b>","</b>"));
	Map<String, List<String>> groups = strings.stream().collect(Collectors.groupingBy(str -> str.substring(0, 1),Collectors.toList()));
	Map<String, String> groupJoin = strings.stream()
		     .collect(Collectors.groupingBy(str -> str.substring(0, 1),Collectors.mapping(str -> str.substring(str.length() - 1 , str.length()), Collectors.joining(":"))));
	Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
	long sumOdd = numbers.stream().collect(Collectors.summarizingInt(value -> value % 2 != 0 ? value : 0)).getSum();
	double average = numbers.stream().collect(Collectors.averagingDouble(value -> value - 1));
	IntSummaryStatistics statistics = numbers.stream().collect(Collectors.summarizingInt(value -> value + 3));
	Map<Boolean, List<Integer>> parts = numbers.stream().collect(Collectors.partitioningBy(value -> value % 2 == 0));
```

**Источник** <>


Дан поток, определите количество вхождений каждого из символов, составляющих поток.<br/>

```java
Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");
```

**Ответ** words.flatMap(w -> Arrays.stream(w.split(""))).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())); <br/>
**Источник** <>

Дан код, как он будет выглядеть если modem обернуть в Optional? <br/>

```java
   boolean isInRange = false;
    if (modem != null && modem.getPrice() != null
      && (modem.getPrice() >= 10
        && modem.getPrice() <= 15)) { 
        isInRange = true;
    }
    return isInRange;
```

**Ответ** 

```java
return Optional.ofNullable(modem)
       .map(Modem::getPrice)
       .filter(p -> p >= 10 && p <= 15)
       .isPresent();
```

**Источник** <>

Дан код, замените {code}, чтобы получить первый объект, которые не null, если такого нет вернуть “default’

```java
private Optional<String> getEmpty() {
    return Optional.empty();
}
 
private Optional<String> getHello() {
    return Optional.of("hello");
}
 
private Optional<String> getBye() {
    return Optional.of("bye");
}
String firstNonNull = Stream.of(getEmpty(), getHello(), getBye()).{code};
```

**Ответ** String firstNonNull = Stream.of(getEmpty(), getHello(), getBye()).filter(Optional :: isPresent).findFirst().get().orElse("default");<br/>
**Источник** <>
