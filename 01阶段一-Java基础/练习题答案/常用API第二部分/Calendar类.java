��ϰһ��Calendar�෽����ʹ��
һ���ó����ж�2018��2��14�������ڼ���
public class CalendarTest01 {
    public static void main(String[] args) {
        //����Calendar����
        Calendar c = Calendar.getInstance();
        //�������������ֶ����õ�Calendar������
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 14);
        //������
        int year = c.get(Calendar.YEAR);
        //������
        int month = c.get(Calendar.MONTH)+1;
        //������
        int date = c.get(Calendar.DATE);
        //��������
        char week = getWeek(c.get(Calendar.DAY_OF_WEEK));
        //������
        System.out.println(year+"��"+month+"��"+date+"��������"+week);
    }
    //���巽������ȡ���ں���
    public static char getWeek(int a){
        char[] c = {' ','��','һ','��','��','��','��','��'};
        return c[a];
    }
}
