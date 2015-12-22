class T1
{
	int a = 5;
	public int get(){return a;}
}
class T2 extends T1
{
	int a = 4;
}
public class test
{
	public static void main(String args[])
	{
		T2 t2 = new T2();
		System.out.println(t2.get());
	}
}