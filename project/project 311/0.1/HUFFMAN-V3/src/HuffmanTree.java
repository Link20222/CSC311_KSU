class HuffmanTree {
    String codes[]=new String[256];
    String codeword="";
    int bitCount=0;
    int charCount=0;
    int fcount=0;


    void printBinaryTree(Node root, int level)
    {
        if(root==null)
            return;
        printBinaryTree(root.right, level+1);
        if(level!=0)
        {
            for(int i=0;i<level-1;i++)
                System.out.print("|\t");
            if(root.ch=='\n')
            {
                System.out.println("|-------"+"\\n"+"-"+root.count);
            }
            else if(root.ch!='\0')
            {
                System.out.println("|-------"+root.ch+"-"+root.count);
            }
            else
            {
                System.out.println("|-------"+root.count);
            }
        }
        else
        {
            if(root.ch=='\n')
            {
                System.out.println("|-------"+"\\n"+"-"+root.count);
            }
            else if(root.ch!='\0')
            {
                System.out.println(root.ch+"-"+root.ch);
            }
            else
            {
                System.out.println(root.count);
            }
        }
        printBinaryTree(root.left, level+1);
    }
    //------------------------------------------------

    String decode()
    {
        Node temp=root;
        String decode="";
        for(int i=0;i<codeword.length();i++)
        {
            if(codeword.charAt(i)=='0')
                temp=temp.left;
            else if(codeword.charAt(i)=='1')
                temp=temp.right;
            if(temp.left==null && temp.right==null)
            {
                decode+=temp.ch;
                temp=root;
            }
        }
        return decode;
    }
    //------------------------------------------------


    String pCodes(String name)
    {
        for(int i=0;i<name.length();i++)
        {
            int index=(int)name.charAt(i);
            //codeword=codeword+codes[index]+"-";
            codeword+=codes[index];
        }
        return codeword;
    }
    //------------------------------------------------


    void TreeCodes(Node temp)
    {
        boolean flag=true;
        if(temp==null)
        {
            return;
        }
        if(temp.left!=null)
        {
            codeword+="0";
            TreeCodes(temp.left);
        }
        if(temp.left==null && temp.right==null)
        {
            temp.code=codeword;
            temp.cc=codeword.length();
            bitCount+=temp.count*temp.cc;
            charCount+=1;
            fcount+=temp.count;
            int position=(int)temp.ch;
            codes[position]=codeword;
            codeword=codeword.substring(0,codeword.length()-1);
            flag=false;
        }
        if(temp.right!=null)
        {
            codeword=codeword+"1";
            TreeCodes(temp.right);
        }
        if(flag && codeword.length()>0)
            codeword=codeword.substring(0,codeword.length()-1);
    }
    //------------------------------------------------


    void gCodes()
    {
        String codeword="";
        TreeCodes(root);
        System.out.println();
        pCodes(root);
        float bits=0.0f;
        for(float i=1; ;i++)
        {
            if(charCount<=Math.pow(2,i))
            {
                bits=i;
                break;
            }
        }
        System.out.println("\n\nPrinting the Binary Tree:");
        printTree(root);
        bits=bits*fcount;
        float cr=((bits - bitCount)/bits)*100;
        cr= Math.round( cr * 100)/100;
        System.out.println("\n\n\nThe Compression percentage is:"+ cr +"%\n");
        printBinaryTree(root,0);
    }
    //------------------------------------------------


    void pCodes(Node temp)
    {
        if(temp==null)
            return;
        if(temp.left!=null)
        {
            pCodes(temp.left);
        }
        if(temp.left==null && temp.right==null)
        {
            System.out.println("Character: "+temp.ch+" Frequency: "+temp.count+" Code: "+temp.code);
        }
        if(temp.right!=null)
        {
            pCodes(temp.right);
        }
    }
    Node root=null;

    //------------------------------------------------

    void formTree()
    {
        boolean done=true;
        while(done)
        {
            Node l=pop();
            Node r=pop();
            if(l!=null && r!=null)
            {
                insertr(l,r);
            }
            else
            {
                pushI(l);
                done=false;
                root=l;
            }
        }
    }
    //------------------------------------------------

    void printTree(Node temp)
    {
        if(temp==null)
            return;
        if(temp.left!=null)
            printTree(temp.left);
        if(temp.ch=='\0')
            System.out.print(temp.count);
        else
        {
            System.out.print(temp.ch);
        }
        if(temp.right!=null)
            printTree(temp.right);
    }
    //------------------------------------------------


    void insertr(Node l,Node r)
    {
        Node temp=new Node();
        temp.ch='\0';
        temp.count=l.count+r.count;
        temp.left=l;
        temp.right=r;
        l.parent=temp;
        r.parent=temp;
        pushI(temp);
    }
    //------------------------------------------------

    Node pop()
    {
        if(front==null)
        {
            return null;
        }
        else
        {
            pqueue t=new pqueue();
            t=front;
            front=front.next;
            return t.n;
        }
    }
    //------------------------------------------------


    void singleton(char element,int c)
    {
        Node n=new Node();
        n.ch=element;
        n.count=c;
        pushI(n);
    }
    //------------------------------------------------


    pqueue front=null;
    pqueue rear=null;

    void pushI(Node temp)
    {
        pqueue temp_node=new pqueue();
        temp_node.n=temp;
        if(front==null)
            front=temp_node;
        else
        {
            pqueue tf=new pqueue();
            tf=front;
            if(tf.n.count>temp_node.n.count )
            {
                temp_node.next=front;
                front=temp_node;
            }
            else if(tf.next==null)
            {
                tf.next=temp_node;
            }
            else
            {
                pqueue tn=new pqueue();
                tn=tf.next;
                boolean done=false;
                while(tn.next!=null && !done)
                {
                    if(tn.n.count>temp_node.n.count)
                    {
                        temp_node.next=tn;
                        tf.next=temp_node;
                        done=true;
                    }
                    else
                    {
                        tf=tf.next;
                        tn=tn.next;
                    }
                }
                if(tn.next==null && !done)
                {
                    if(tn.n.count>temp_node.n.count)
                    {
                        temp_node.next=tn;
                        tf.next=temp_node;
                    }
                    else
                    {
                        tn.next=temp_node;
                    }
                }
            }
        }
    }
}


