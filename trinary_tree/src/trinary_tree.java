import java.util.*;
import java.text.*;
import java.io.*;

public class trinary_tree {

	
	private Node root;
	
	public trinary_tree()
	{
		
	}
	
	public void insert(int value)
	{
		Node insert_node=new Node();
		insert_node.Node(value);
		//now s is the inserted number
		if(root==null)
			root=insert_node;
		else
		{
			Node current=root;
			Node parent=current;
			while(true)
			{
				parent=current;
				if (current.value==insert_node.value)
				{
					current=current.middlechild;
					if (current==null)
					{
						parent.middlechild=insert_node;
						return;
					}
				}
				else if(current.value>insert_node.value) 
				{
					current=current.leftchild;
					if (current==null)
					{
						parent.leftchild=insert_node;
						return;
					}
				}
				else if(current.value<insert_node.value) 
				{
					current=current.rightchild;
					if (current==null)
					{
						parent.rightchild=insert_node;
						return;
					}
				}			
			}
		}
	}
	
	public boolean delete(int value)
	{
		Node s=new Node();
		s.Node(value);
		Node current=root;
		Node parent=root;
		
		while(current.value != s.value)
		{
			parent=current;
			if(s.value<current.value)
				current=current.leftchild;
			if(s.value>current.value)
				current=current.rightchild;
			if (current == null)
                return false;
		}
		while(current.middlechild!=null)
		{
			parent=current;
			current=current.middlechild;
		}//now we can make sure that the node we found has no middlechild

		if(current.leftchild==null && current.rightchild==null)//leaf
		{
			if(parent.value==current.value)
				parent.middlechild=null;
			else if(parent.value>current.value)
				parent.leftchild=null;
			else
				parent.rightchild=null;
		}
		
		else if(current.leftchild==null)//only having left child
		{
			if(parent.value>current.value)
				parent.leftchild=current.rightchild;
			else if(parent.value<=current.value)
				parent.rightchild=current.rightchild;
		}
		
		else if(current.rightchild==null)//only having right child
		{
			if(parent.value>current.value)
				parent.leftchild=current.leftchild;
			else if(parent.value<=current.value)
				parent.rightchild=current.leftchild;
		}
		else 					//having both right and left child
		{
			s=current;//make s as the place of number we want to delete
			parent=current;
			current=current.rightchild;
			while(current.leftchild!=null)
			{
				parent=current;
				current=current.leftchild;
			}
			while(current.middlechild!=null)
			{
				parent=current;
				current=current.middlechild;
			}
			//find the succeed,whose leftchild and middlechild will be definitely null
			//moreover,we can prove that if it's the middlechild of its parent,then it must be a leaf
			if(parent.leftchild.value==current.value)
				parent.leftchild=current.rightchild;
			else
				parent.rightchild=current.rightchild;
			s.value=current.value;
		}
		return true;
	}
	
	public static int countdepth(Node treenode)
	{
		int depth=0;
		if(treenode==null)
			return 0;
		else {
			depth=Math.max(countdepth(treenode.middlechild),
					Math.max(countdepth(treenode.leftchild),
							countdepth(treenode.rightchild)))+1;
			return depth;
			}
	}
	
	public int print(Node s,int layer)
	{
		int depth=0;
		Node w=s;
		int middlechild_length=0;
		if(s==null)
			return 0;
		else {
			print(s.rightchild,layer+1);
			
			depth=countdepth(s);
			while(w.middlechild!=null)
			{
				middlechild_length++;
				w=w.middlechild;
				}
			for(int k=0;k<layer;k++)
				System.out.print(" ");
			for(int j=0;j<middlechild_length+1;j++)
				System.out.print(s.value);
			System.out.print("\n");
			middlechild_length=0;
			
			print(s.leftchild,layer+1);
			}
		return 0;
	}


	public static void main(String[] args) {
		System.out.println("please insert a sequence of numbers:");
		Scanner scanner = new Scanner(System.in);
		trinary_tree tree=new trinary_tree();
		while(true)
		{
			if(scanner.hasNextInt())
			{
				Node s=new Node();
				int i=scanner.nextInt();
				tree.insert(i);
			}
			else
			{
				scanner.next();
				break;
			}
		}
		tree.print(tree.root,0);//insert
		
		System.out.println("please list a sequence of numbers you want to delete:");
		scanner = new Scanner(System.in);
			while(true)
			{
				if(scanner.hasNextInt())
				{
					int i=scanner.nextInt();
					tree.delete(i);
				}
				else
				{
					scanner.next();
					break;
				}
			}
			tree.print(tree.root,0);//delete
	}
}
