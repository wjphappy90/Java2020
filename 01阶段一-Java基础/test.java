public class test {
    public static void main(String[] args) throws IOException {
        //new FileWriter()
        File f1 = new File("D:\\IdeaProjects\\myExtends\\number1.txt");
        if (f1.isFile()){
            BufferedReader br = new BufferedReader(new FileReader(f1));

            HashSet<String> hs = new HashSet<>();

            String line;
            while ((line=br.readLine())!=null){
                hs.add(line);
            }

            List<Integer>list=new ArrayList<Integer>();
            for (String s:hs){
                list.add(Integer.parseInt(s));
            }

            Collections.sort(list);
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\IdeaProjects\\myExtends\\number2.txt"));


            for (Integer i:list){
                bw.write(i+"\r\n");
            }
            br.close();
            bw.close();
        }
    }
}