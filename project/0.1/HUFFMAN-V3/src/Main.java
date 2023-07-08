import java.io.*;
class main {
    public static void main(String[] args) throws IOException
    {
        System.out.println("\n*****************************");
        String name="";
        FileReader fileReader=new FileReader("C:\\Users\\faisa\\Desktop\\ud282-master\\ud282-master\\HUFFMAN-V3\\src\\test.txt");
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String temp="";
        while((temp=bufferedReader.readLine())!=null)
            name+=temp+"\n";

        bufferedReader.close();
        int ch[]=new int[256];
        for(int i=0;i<256;i++)
        {
            ch[i]=0;
        }

        for(int i=0;i<name.length();i++)
        {
            int index=(int)name.charAt(i);

            ch[index]+=1;
        }
        HuffmanTree b=new HuffmanTree();

        for(int i=0;i<256;i++)
        {
            if(ch[i]>0)
            {

                b.singleton((char)i,ch[i]);
            }
        }

        b.formTree();

        b.gCodes();
        System.out.println("Printing Code words for each Character:");
        String code=b.pCodes(name);
        System.out.println("From Main the code is:"+code);
        FileWriter w=new FileWriter("encoded.txt");
        BufferedWriter bufferedWriter=new BufferedWriter(w);
        w.write(code);
        w.close();
        String decode=b.decode();
        FileWriter w1=new FileWriter("decoded.txt");
        BufferedWriter bw1=new BufferedWriter(w1);
        w1.write(decode);
        w1.close();
    }
}