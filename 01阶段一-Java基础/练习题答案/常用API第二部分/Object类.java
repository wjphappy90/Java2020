��ϰһ��Object��equals����
һ������String���е�equals������Object���е�equals�����Ĳ�ͬ�㡣
��String���е�equals�����������ж���������������Ƿ���ͬ����Object ���е�equals�����������ж����������Ƿ���ͬһ��������νͬһ������ָ�����ڴ��е�ͬһ��洢�ռ䡣

��ϰ����Object���toString����
���������д��룬ֱ��˵����ӡ�����������ԭ��
public class ToStringTest{
    static int i = 1;
    public static void main(String args[]){
        System.out.println("love " + new ToStringTest());//love java
        ToStringTest a = new ToStringTest();
        a.i++;
        System.out.println("me " + a.i);//me 2
    }
    public String toString(){
        System.out.print("I ");//I
        return "java ";
    }
}
���н����I love java    me 2
ԭ�򣺵�ִ�д����ʱ�����ȼ��ؾ�̬������Ȼ��ִ��main����������main�����ڲ���һ�д���Ϊ�����䣬����new�˴�����󣬵�ִ�д��д���ʱ���ȴ����˱���Ķ������ڴ�����д��toString����������ִ��toString�����Ĵ�ӡ�����Ȼ�󷵻ء�java ������ִ��main������һ�д�ӡ�������Java�С�System.out.println(�������);��ʵ��������Ǹö����toString()�������ص��ַ������������е����ݵȼ����������.toString(),toString�����ĺô���������println������ʱ��ᱻ�Զ����ã�������ʾ��д������


��ϰ����Object��equals����
���������г��򣬲�����˵�����д���𰸺󣬲���IntelliJ IDEA�����п����Լ����Ĵ������н���Ƿ���ȷ��������ԭ��
	(1)
		String s1 = new String("abc");
		String s2 = "abc";
		System.out.println(s1 == s2);     	//false
		System.out.println(s1.equals(s2));     	//true
	(2)
		String s1 = "abc";
      		String s2 = "abc";
		System.out.println(s1 == s2);     	//true
		System.out.println(s1.equals(s2)); 	//true
	(3)
		String s1 = "a" + "b" + "c";
      		String s2 = "abc";
		System.out.println(s1 == s2);    		 //true
		System.out.println(s1.equals(s2));	 //true
	(4)
		String s1 = "ab";
     		String s2 = "abc";
     		String s3 = s1 + "c";
		System.out.println(s3 == s2);     	//false
      		System.out.println(s3.equals(s2));   	//true
