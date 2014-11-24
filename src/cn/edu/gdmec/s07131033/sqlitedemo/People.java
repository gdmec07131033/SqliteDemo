package cn.edu.gdmec.s07131033.sqlitedemo;

public class People {
	public int Id = 0;
	public String Name;
	public int Age;
	public float Height;
	
	People()
	{
		
	}
	
	People(int id,String name,int age,float height)
	{
		this.Id = id;
		this.Name = name;
		this.Age = age;
		this.Height = height;		
	}
	
	public String toString()
	{
		String result = "";
		result = "Id:"+Id+" ,Name:"+Name+" ,Age:"+Age+" ,Height:"+Height;
		return result;
	}
	
}
