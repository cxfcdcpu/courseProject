package dfs_scc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fun {

	public static int[][] inv(int[][] arr)
	{   
		int a= arr.length;
		int[][] b =new int[a][a];
		for (int i=0;i<a;i++)
		{
			for(int j=0;j<a;j++)
			{
				b[j][i]=arr[i][j];
							
			}
					
		}
		return b;
	}
	
	public static int dfs(int[][] a,int i,int[][] vec,int time,int len,List<Integer> seq){
		
	     time=time+1;
	     //System.out.println(time);
	     seq.add(i);
	      vec[i][0]=1;
	      vec[i][1]=time;
	      int j=0;
	    	  while((j<len)&&(a[i][j]==0 || vec[j][0]==1))
	    	  {
	    		 
	    		 j++;
	    	  }  
	      if(j<len)
	    	  {time=Fun.dfs(a, j, vec, time, len,seq);}
	      return time;
	      
	}
	
	public static int dfs_1(int[][] a,int i,int[][] vec,int time,int len,List<Integer> seq)
	{
		time=Fun.dfs(a, i, vec, time, len,seq);	
		System.out.println("seq :"+seq);
		seq.remove(seq.size()-1);
		if(seq.size()>=1)
		{
	    
		i=seq.get(seq.size()-1);
		seq.remove(seq.size()-1);
		time=Fun.dfs_1(a, i, vec, time, len, seq);		
		}
		return time;
	}
	
	
	
	public static void dfs_t(int[][] b,int i, int[] v,int[] u, int len, List<Integer> seq)
	{
		seq.add(i);
		v[i]=1;
		int j=0;
		int jj=u[j];
		System.out.println("seq :"+seq);
			while((j<len)&&(b[i][jj]==0 || v[jj]==1))
	    	  {
				jj=u[j];
	    		 j++;
	    		 
	    	  }
			//System.out.println(jj);
		if(b[i][jj]==1 && v[jj]==0)
			{Fun.dfs_t(b, jj, v, u, len,seq);}
	
	}
	
	public static List<Integer> c_a(List<Integer> a,List<Integer> b)
	{
		for(int i=0;i<a.size();i++){
		if(!b.contains(a.get(i)))
		{
			b.add(a.get(i));
		}
			
		}
					
		return b;
	}
	
	public static List<Integer> dfs_t_1(int[][] b,int i, int[] v,int[] u, int len, List<Integer> seq,List<Integer> r)
	{
		Fun.dfs_t(b, i, v, u, len, seq);
		r=Fun.c_a(seq, r);
		//System.out.println(r);
		seq.remove(seq.size()-1);
		if(seq.size()>=1)
		{
	    
		i=seq.get(seq.size()-1);
		//System.out.println(i);
		seq.remove(seq.size()-1);
		Fun.dfs_t_1(b, i, v,u, len, seq,r);		
		}
		
		
		
		return r;
	}
	
	
	
	
	
	
	
	public static int[] sort(int[] a)
	{
		int len=a.length;
		int[] r=new int[len];
		int[] d=new int[len];
		int j=0;
		Arrays.fill(d, 0);
		Arrays.fill(r, 0);
		for(int i=0;i<len;i++)
		{
		   j=0;
			while(d[j]>a[i])
			{
			  j++;
			}
			if(j!=len-1)
			{
			for(int t=len-2;t>=j;t--)
			{
				d[t+1]=d[t];
				r[t+1]=r[t];
			}
			
			}
			d[j]=a[i];
			r[j]=i;
			
		}
		
		return r;
	}
	
	public static List<Integer> find_scc(List<int[]> a)
	{   int b=0;
	   List<Integer> scc=new ArrayList<Integer>();
		for(int i=0;i<a.size();i++)
		{
			if(b==0||b>a.get(i).length)
			{scc.clear();
				b=a.get(i).length;
				for(int j=0;j<b;j++)
				{
					scc.add(a.get(i)[j]);
				}
			}
			
			
		}
		
		
		return scc;
	}
	
	public static List<Integer> not_scc(int[][] top,List<Integer> scc,List<Integer> in)
	{
		List<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<top.length;i++)
		{
			if(!(scc.contains(i)||in.contains(i)))
			{
				a.add(i);
			}
		}
		
		
		
		return a;
	}
	
	public static List<Integer> find_in( List<Integer> scc, int[][] top,List<Integer> in)
	{
		
		List<Integer> a=Fun.not_scc(top, scc, in);
		//System.out.println(a);
		int b=0;
		for(int u=0;u<a.size();u++)
		{
			for(int j=0;j<scc.size();j++)
			{
			  if(top[a.get(u)][scc.get(j)]==1&&(!in.contains(a.get(u))))
			  {
				  b=a.get(u);
				 // System.out.println(b);
				  in.add(b);
			  }
			}	
		}
		
		int t=0;
		
		while(t!=in.size())
		{
			
			t=in.size();
		    a.clear();
			a=Fun.not_scc(top, scc, in);
			
		for(int i=0;i<a.size();i++)
		{
			for(int j=0;j<t;j++)
			{
			  if(top[a.get(i)][in.get(j)]==1&&(!in.contains(a.get(i))))
			  {
				  b=a.get(i);
				  in.add(b);
			  }
			}	
		}
		}
		return in;
		
	}
	
	public static List<Integer> find_out(List<Integer> scc, int[][] top,List<Integer> out)
	{
		List<Integer> a=Fun.not_scc(top, scc, out);
		//System.out.println(a);
		int b=0;
		for(int u=0;u<a.size();u++)
		{
			for(int j=0;j<scc.size();j++)
			{
			  if(top[scc.get(j)][a.get(u)]==1&&(!out.contains(a.get(u))))
			  {
				  b=a.get(u);
				 // System.out.println(b);
				  out.add(b);
			  }
			}	
		}
		
		int t=0;
		
		while(t!=out.size())
		{
			
			t=out.size();
		    a.clear();
			a=Fun.not_scc(top, scc, out);
			
		for(int i=0;i<a.size();i++)
		{
			for(int j=0;j<t;j++)
			{
			  if(top[out.get(j)][a.get(i)]==1&&(!out.contains(a.get(i))))
			  {
				  b=a.get(i);
				  out.add(b);
			  }
			}	
		}
		}
		
	
		
		return out;
	}
	
	public static List<Integer> T_in(List<Integer> scc, int[][] top,List<Integer> out,List<Integer> in)
	{
		List<Integer> tin=new ArrayList<Integer>();
		List<Integer> o=new ArrayList<Integer>();
		for(int i=0;i<in.size();i++)
		{
			o.add(in.get(i));
		}
		for(int i=0;i<out.size();i++)
		{
			o.add(out.get(i));
		}
		
		List<Integer> a=Fun.not_scc(top, scc, o);
		//System.out.println(a);
		int b=0;
		for(int u=0;u<a.size();u++)
		{
			for(int j=0;j<in.size();j++)
			{
			  if(top[in.get(j)][a.get(u)]==1&&(!tin.contains(a.get(u))))
			  {
				  b=a.get(u);
				 // System.out.println(b);
				  tin.add(b);
			  }
			}	
		}
		//System.out.println(tin);
		
       int t=0;
		
		while(t!=tin.size())
		{
			
			t=tin.size();
		    
			
		for(int i=0;i<a.size();i++)
		{
			for(int j=0;j<t;j++)
			{
			  if(top[tin.get(j)][a.get(i)]==1&&(!tin.contains(a.get(i))))
			  {
				  b=a.get(i);
				 tin.add(b);
			  }
			}	
		}
		}
		return tin;
	
	}
	
	
	public static List<Integer> T_out(List<Integer> scc, int[][] top,List<Integer> out,List<Integer> in)
	{
		List<Integer> tout=new ArrayList<Integer>();
		List<Integer> o=new ArrayList<Integer>();
		for(int i=0;i<in.size();i++)
		{
			o.add(in.get(i));
		}
		for(int i=0;i<out.size();i++)
		{
			o.add(out.get(i));
		}
		
		List<Integer> a=Fun.not_scc(top, scc, o);
		//System.out.println(a);
		int b=0;
		for(int u=0;u<a.size();u++)
		{
			for(int j=0;j<out.size();j++)
			{
			  if(top[a.get(u)][out.get(j)]==1&&(!tout.contains(a.get(u))))
			  {
				  b=a.get(u);
				 // System.out.println(b);
				  tout.add(b);
			  }
			}	
		}
		//System.out.println(tin);
		
       int t=0;
		
		while(t!=tout.size())
		{
			
			t=tout.size();
		    
			
		for(int i=0;i<a.size();i++)
		{
			for(int j=0;j<t;j++)
			{
			  if(top[a.get(i)][tout.get(j)]==1&&(!tout.contains(a.get(i))))
			  {
				  b=a.get(i);
				 tout.add(b);
			  }
			}	
		}
		}
		return tout;
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
