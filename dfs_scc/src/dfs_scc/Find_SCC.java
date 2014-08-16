package dfs_scc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class Find_SCC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
        int[][] a={
        	    //1,2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19
				{0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//1
				{1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//2
				{1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//3
				{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//4
				{0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//5
				{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},//6
				{0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},//7
				{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},//8
				{0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},//9
				{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//10
				{0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//11
				{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},//12
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1},//13
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},//14
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},//15
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},//16
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},//17
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0},//18
				{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}//19,
				};
      	*/
		int[][] a={
				{0,1,1,1,0,0},
				{1,0,1,0,0,0},
				{1,1,0,0,0,0},
				{1,0,0,0,1,1},
				{0,0,0,1,0,1},
				{0,0,0,1,1,0},
		};
		int[][] b= Fun.inv(a);
		/*
		for(int i=0;i<a.length;i++)
		{
			System.out.println(Arrays.toString(a[i]));
		    
		  }
		System.out.println("finish");
		for(int i=0;i<a.length;i++)
		{
			System.out.println(Arrays.toString(b[i]));
		    
		  }
		*/
		int len=a.length;
		int time=0;
		
		int[][] vec=new int[len][2];
		for(int p=0;p<len;p++)
		{
		Arrays.fill(vec[p], 0);
		}
		List<Integer> seq=new ArrayList<Integer>();
		
		for (int i=0;i<len;i++)
		{   
		    if( vec[i][0]==0)
		    { 
		    	seq.clear();
		    	time=Fun.dfs_1(a, i, vec, time, len,seq);		    	
		   
		   // System.out.println(seq);
		   // System.out.println(time);
				 }
		}
		
		
		for(int p=0;p<len;p++)
		{
			System.out.println(Arrays.toString(vec[p]));
		}
		
		
		
		int[] s=new int[len];
		for(int p=0;p<len;p++)
		{
			s[p]=vec[p][1];
		}
		
		int[][] back=new int[len][2];
		for(int q=0;q<len;q++)
		{
		Arrays.fill(back[q], 0);
		}
		int[] so=Fun.sort(s);
		//System.out.println(Arrays.toString(s));
		//System.out.println(Arrays.toString(so));
	
		Arrays.fill(s, 0);
		List<int[]> r=new ArrayList<int[]>();
		seq.clear();
		List<Integer> tran=new ArrayList<Integer>();
		//System.out.println(Arrays.toString(s));
		int aaa=0;
		for(int q=0;q<len;q++)
		{
			//System.out.println(s[so[q]-1]);
			if(s[so[q]]==0)
			{  
				
				seq.clear();
				tran.clear();
				tran=Fun.dfs_t_1(b, so[q], s,so, len, seq,tran);
				
				
				aaa=tran.size();
				//System.out.println(tran);
				int[] bbb =new int[aaa];
				for (int i=0;i<aaa;i++)
				{
					bbb[i]=tran.get(i);
				}
				r.add(bbb);
				
			}
		
		}
		
		List<Integer> scc=new ArrayList<Integer>();
		List<Integer> in=new ArrayList<Integer>();
		List<Integer> out=new ArrayList<Integer>();
		List<Integer> T_in=new ArrayList<Integer>();
		List<Integer> T_out=new ArrayList<Integer>();
		scc=Fun.find_scc(r);
		System.out.println("scc is:"+scc);
		in=Fun.find_in(scc, a, in);
		System.out.println("in is:"+in);
		out=Fun.find_out(scc, a,out);
		System.out.println("out is:"+out);
		T_in=Fun.T_in(scc, a, out, in);
		System.out.println("T of in is :"+T_in);
		T_out=Fun.T_out(scc, a, out, in);
		System.out.println("T of out is :"+T_out);
	}

}
