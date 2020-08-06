��ϰһ�����Ͽ��
һ����������Ͽ�ܡ�
���ϰ�����洢�ṹ���Է�Ϊ�����࣬�ֱ��ǵ��м���java.util.Collection��˫�м���java.util.Map��
Collection�����м�����ĸ��ӿڣ����ڴ洢һϵ�з���ĳ�ֹ����Ԫ�أ�����������Ҫ���ӽӿڣ��ֱ���java.util.List��java.util.Set�����У�List���ص���Ԫ������Ԫ�ؿ��ظ���Set���ص���Ԫ�����򣬶��Ҳ����ظ���List�ӿڵ���Ҫʵ������java.util.ArrayList��java.util.LinkedList��Set�ӿڵ���Ҫʵ������java.util.HashSet��java.util.TreeSet��
��ϰ����Collection����ͳ��Ԫ�س��ִ���

��ϰ����Collection���ϼ���ת����
��������һ�����ϣ����Ѽ���(���������Ԫ����Integer)ת�ɴ�����ͬԪ�ص����飬�����������ڿ���̨��������ʹ��Object[]�������ͽ���ת�������飩
public class CollectionTest03 {
    public static void main(String[] args) {
        //���弯��,�������
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(100);
        list.add(200);
        list.add(300);
        //Object[] toArray()ת����һ��Object����
        Object[] obj =  list.toArray();
        // ��������
        for (int i = 0; i < obj.length; i++) {
            System.out.println(obj[i]);
        }
    }
}


��ϰ����Collection����contains()����ʹ��
��������һ������listTest(ArrayList<String> al, String s),Ҫ��ʹ��contains()�����ж�al���������Ƿ����s��
public class CollectionTest04 {
    public static void main(String[] args) {
        //���弯�ϣ��������
        ArrayList<String> list = new ArrayList<String>();
        list.add("itcast");
        list.add("itheima");
        list.add("java");
        System.out.println(listTest(list,"java"));
    }

    public static boolean listTest(ArrayList<String> al, String s) {
        //�ж�s�Ƿ��ڼ����д���,���ڷ���true�������ڷ���false
        if (al.contains(s)) {
            return true;
        }
       return false;
    }
}


��ϰ�ģ�Collection����isEmpty()������ʹ��
�ġ�����һ������listTest(ArrayList<String> al), Ҫ��ʹ��isEmpty()�ж�al�����Ƿ���Ԫ�ء�
public class CollectionTest05 {
    public static void main(String[] args) {
        //���弯�ϣ��������
        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        System.out.println(listTest(list));
    }

    public static boolean listTest(ArrayList<String> al) {
        //�ж�al�����Ƿ�Ϊ��,Ϊ�շ���true����Ϊ�շ���false
        if(al.isEmpty()){
            return true;
        }
        return false;
    }
}
��ϰ�壺������������ʵ��ԭ��
�塢�������������ʵ��ԭ��
����������ʱ������ͨ�����ü��ϵ�iterator()������õ���������Ȼ��ʹ��hashNext()�����жϼ������Ƿ������һ��Ԫ�أ�������ڣ������next()������Ԫ��ȡ��������˵���ѵ����˼���ĩβ��ֹͣ����Ԫ�ء�
Iterator�����������ڱ�������ʱ���ڲ�����ָ��ķ�ʽ�����ټ����е�Ԫ�أ��ڵ���Iterator��next()����֮ǰ��������������λ�ڵ�һ��Ԫ��֮ǰ����ָ���κ�Ԫ�أ�����һ�ε��õ�������next�����󣬵�����������������ƶ�һλ��ָ���һ��Ԫ�ز�����Ԫ�ط��أ����ٴε���next����ʱ����������������ָ��ڶ���Ԫ�ز�����Ԫ�ط��أ��������ƣ�ֱ��hasNext��������false����ʾ�����˼��ϵ�ĩβ����ֹ��Ԫ�صı�����




��ϰ����Collection���Ϸ����״γ�������
��������һ������listTest(ArrayList<Integer> al, Integer s)��Ҫ�󷵻�s��al�����һ�γ��ֵ����������sû���ֹ�����-1��
public class CollectionTest06 {
    public static void main(String[] args) {
        //���弯�ϣ��������
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(listTest(list, 5));
    }

    public static int listTest(ArrayList<Integer> al, Integer s) {
        //�������ϣ���ȡԪ�أ��ж�Ԫ���Ƿ���s��ȣ���ȷ�������
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).equals(s)) {
                return i;
            }
        }
        return -1;
    }
}
