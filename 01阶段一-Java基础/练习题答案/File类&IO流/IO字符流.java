��Ŀ�������û��ӿ���̨������Ϣ��������Ϣ�洢���ļ�Info.txt�С��������������Ϣ��ÿ����Ϣ�洢һ�С����û����룺��886��ʱ�����������
��
��������:
1.	����MainAPP��,������main()����
2.	��������Ҫ��ʵ�ֳ���
����:
public class Test01_07 {
	public static void main(String[]args) throws IOException {
		//1. ָ��������� ��Ӧ���ļ�Info.txt
		FileWriter bw= new  FileWriter("Info.txt");
		//2.����ѭ���ķ�ʽ����ÿ����Ϣ�洢һ�е�Info.txt��
		Scanner sc= new Scanner(System.in);
		while(true){
			//��ȡ���������һ������
			System.out.print("���������ݣ�");
			String str= sc.nextLine();
			//���û����룺��886��ʱ�����������
			if ("886".equals(str)) {
				break;//����ѭ��
			}
			//������д�뵽Info.txt�ļ���
			bw.write(str);
			//����
			bw.write(System.lineSeparator());
		}
		//�ر���
		bw.close();
	}
}
