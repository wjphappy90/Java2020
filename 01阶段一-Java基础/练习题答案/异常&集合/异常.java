��ϰһ���쳣����ϵ
���⣺
1.	�������쳣�ļ̳���ϵ
2.	��������Դ���(Error)�����
3.	����������쳣(Expection�����)
4.	�������������ʱ�쳣(RuntimeException)�����

��

1.	�쳣�̳���ϵΪ���쳣�ĸ����� java.lang.Throwable���������������ࣺ
java.lang.Error �� java.util.Exception ����Exception�ַ�Ϊ����ʱ���쳣��checked�쳣��������ʱ���쳣��runtime�쳣�� 
2.	Error:��ʾ�����޸��Ķ��ԵĴ���ֻ��ͨ���޸Ĵ����ܴ���Ĳ�����ͨ����ϵͳ����ģ����Ժ����ء�
3.	Exception:��ʾ���޸������ԣ�����ڴ��󣩵��쳣���쳣���������Ա���Բ���Ӧ��ͨ������ķ�ʽ������ʹ����������У��Ǳ���Ҫ����ġ�
4.	����ʱ���쳣:runtime�쳣��������ʱ��,����쳣.�ڱ���ʱ��,�����쳣������������(������)��

��ϰ����throw��throws������
���⣺
1. ������throw��ʹ��λ��,������ʲô?
2. ������throws��ʹ��λ��,������ʲô?

��
1.	throw�ؼ���ͨ�����ڷ������У������׳�һ���쳣���󡣳�����ִ�е�throw���ʱ����ֹͣ�����������䶼��ִ�С�
2.	throws�ؼ���ͨ����Ӧ������������ʱ������ָ�������׳����쳣������쳣����ʹ�ö��Ÿ����������������е��ø÷���ʱ����������쳣���ͻὫ�쳣�����׸��������ô���

��ϰ�����쳣�Ĵ���ʽ
���⣺
      1. �쳣����ʽ�м���,�ֱ���ʲô?
      2. ��ϸ����ÿ�ַ�ʽ���쳣����δ����
��
1.	�쳣�Ĵ���ʽ������,�ֱ���ʹ��throws��try...catch...finally
2.	throws���ڷ����������Ϻ���쳣����,�ǰ��쳣�׸������߽��д���
3.	try...catch...finally�ǲ����쳣,�Լ�����,������Ϻ���ĳ�����Լ�������
a)	try��������ǿ��ܳ����쳣�Ĵ���
b)	catch�����,�������쳣,���쳣���д���Ĵ���
c)	finally������������Ƿ����쳣,������ִ�еĴ���,�����ͷ���Դ.


��ϰ�ģ������쳣��������ԭ��
���⣺���оٳ����쳣����˵������ԭ��

��
NullPointerException:��ָ���쳣��
	��Ӧ����ͼ��Ҫ��ʹ�ö���ĵط�ʹ����nullʱ���׳����쳣��Ʃ�磺����null�����ʵ������������null��������ԡ�����null����ĳ��ȵȵȡ�
ArrayIndexOutOfBoundsException:��������Խ���쳣��
�������������ֵΪ��������ڵ��������Сʱ�׳����쳣��
ArithmeticException:���������쳣��
	�����г����˳���������������ͻ���������쳣���������쳣����Ҿ�Ҫ�úü��һ���Լ��������漰����ѧ����ĵط�����ʽ�ǲ����в����ˡ�
NumberFormatException:���ָ�ʽ�쳣��
	����ͼ��һ��Stringת��Ϊָ�����������ͣ������ַ���ȷ��������������Ҫ��ĸ�ʽʱ���׳����쳣��

ע�⣺�𰸲�Ψһ�������ɡ�


��ϰ�壺�����룬�������
���⣺���ݸ�������Ӧ���룬�������ܲ����Ľ����

1.������
public static void main(String[]args){

 String str=null;
System.out.println(str.length());

}

�𣺱���str��ֵΪnull�����÷���ʱ������ָ���쳣NullPointerException



2.������
public static void main(String[]args){
int arr[]={1,2};
System.out.println(arr[2]);
}

������ֵ2���ڵ�������arr�ĳ���ʱ������������Խ���쳣ArrayIndexOutOfBoundsException



3.������
public static void main(String[]args){

System.out.println(1/0);
}

������0���˷�ĸ�������������쳣ArithmeticException:/by zero



4.������
public static void main(String[]args){

System.out.println(Integer.parseInt("itcast"));

}

��
���ַ�����itcast��ת��ΪInteger����ʱ����Ȼ�ᱨ���ָ�ʽ���쳣��NumberFormatException


5.������
public static void main(String[] args) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    
    try {
        Date date = format.parse("2018-04-03");
        System.out.println("��������");
        
    } catch (ParseException e) {
        System.out.println("�����쳣");
    }
}


��
	��ӡ���������������.try������в�û�в����쳣��catch������еĴ��벻��ִ�С�dateΪ2018��1��3��00��04��00�롣



��ϰ�����Զ����쳣��
���⣺
��ʹ�ô���ʵ��
ÿһ��ѧ��(Student)����ѧ��,�����ͷ���,������Զ����Ϊ����
�����ʦ��ѧ����ֵһ������,�׳�һ���Զ��쳣

��
/*
 1.�����쳣��NoScoreException,�̳�RuntimeException
a)�ṩ�ղκ��вι��췽��
 */

public class NoScoreException extends RuntimeException {
//  �ղι���
public NoScoreException() {
super();
 }
// �вι���
public NoScoreException(String message) {
super(message);
 }
}

/*
  2.����ѧ����(Student)
 a)����:name,score
 b)�ṩ�ղι���
 c)�ṩ�вι���;
  i.ʹ��setXxx���������ƺ�score��ֵ
 d)�ṩsetter��getter����
  i.��setScore(int score)������
   1.�����ж�,���scoreΪ����,���׳�NoScoreException,�쳣��ϢΪ:��������Ϊ����:xxx.
   2.Ȼ���ڸ���Աscore��ֵ.
 */

public class Student {
private String name;
private int score;
// �ղι���
public Student() {
super();
 }
// c)�ṩ�вι���;
// i.ʹ��setXxx���������ƺ�score��ֵ
public Student(String name,int score){
  setName(name);
  setScore(score);
 }
// d)�ṩsetter��getter����

public String getName() {
return name;
 }

public void setName(String name) {
this.name = name;
 }

public int getScore() {
return score;
 }
// i.��setScore(int score)������
public void setScore(int score) {
// 1.�����ж�,���scoreΪ����,���׳�NoScoreException,�쳣��ϢΪ:��������Ϊ����:xxx.
if(score <0){
throw new NoScoreException(":��������Ϊ����:"+score);
  }
// 2.Ȼ���ڸ���Աscore��ֵ.
this.score = score;
 }
}


/*
3.���������Test9
 a)�ṩmain����,��main������
  i.ʹ�����ι��췽������Student����,��������һ������,���г���
  ii.����һ�������쳣,����Ĵ���Ľ�����ִ��,������Ҫע�͵�����Ĵ���
  iii.ʹ�ÿղι��촴��Student����
  iv.����setScore(int score)����,����һ������,���г���
  v.����setScore(int score)����,����һ������,���г���
 */

public class Test9 {
public static void main(String[] args) {
//  i.ʹ�����ι��췽������Student����,��������һ������,���г���
//  Student s = new Student("����", -10);
//  ii.����һ�������쳣,����Ĵ���Ľ�����ִ��,������Ҫע�͵�����Ĵ���

//  iii.ʹ�ÿղι��촴��Student����
Student s = new Student();
//  iv.����setScore(int score)����,����һ������,���г���
s.setScore(100);
//  v.����setScore(int score)����,����һ������,���г���
s.setScore(-5);
 }
}
