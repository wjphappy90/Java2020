��ϰһ��Date���ʹ��
һ�����ô���ʵ��:��ȡ��ǰ������,�����������ת��Ϊָ����ʽ���ַ���,��2088-08-08 08:08:08��
public class DateTest {
    public static void main(String[] args) {
        //��ȡ��ǰ���ڶ��� now;
        Date now = new Date();
        //����SimpleDateFormat���� df,���ƶ����ڸ�ʽ
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //����df��format(Date  date) ����,����now; ���շ��ص��ַ���
        String datestr = df.format(now);
        //��ӡ����ַ���
        System.out.println(datestr);
    }
}
��ϰ����DateFormat�෽����ʹ��
һ��ʹ��SimpleDateFormat��,��2018-03-04ת��Ϊ2018��03��04�ա� 
public class DateFormatTest {
    public static void main(String[] args) throws ParseException {
        //����SimpleDateFormat����df1,ָ������ģʽΪyyyy-MM-dd
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        //����df1��parse(String str)��������2018-03-04,�õ���Ӧ��������
        Date date = df1.parse("2018-03-04");
        //�������ڸ�ʽ������df2,�ڻ�ȡ��ʽ������ʱ����ָ�����
        DateFormat df2 = new SimpleDateFormat("yyyy��MM��dd��");
        //����df2��format(Date date) ����ղ�ת��������
        String str = df2.format(date);
        System.out.println(str);
    }
}
