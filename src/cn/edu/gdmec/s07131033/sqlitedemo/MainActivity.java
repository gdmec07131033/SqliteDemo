package cn.edu.gdmec.s07131033.sqlitedemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText etid,etname,etage,ethei;
	TextView show;
	DbAdapter dbadapter;
	People people = new People();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etid = (EditText) findViewById(R.id.editText1);
        etname = (EditText) findViewById(R.id.editText2);
        etage = (EditText) findViewById(R.id.editText3);
        ethei = (EditText) findViewById(R.id.editText4);
        show = (TextView) findViewById(R.id.textView5);
        dbadapter = new DbAdapter(this, null, null, 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public People getValues()
    {
    	people.Name = etname.getText().toString();
    	people.Age = Integer.parseInt(etage.getText().toString()) ;
    	people.Height = Float.parseFloat(ethei.getText().toString());
    	if(people.Name.equals(""))
    	{
    		return null;
    	}else 
    	{
    		return people;
    	}
    }
    public void Insert(View v)
    {
    	if(getValues()!=null)
    		
    	{
    		long id = dbadapter.Insert(getValues());
    		show.setText("people's id:"+id+"has inserted");
    	}
    }

    public void RetrieveById(View v)
    {
    	long id = Long.parseLong(etid.getText().toString());
    	People[] ps = dbadapter.RetrieveById(id);
    	show.setText(ps[0].toString());
    }

    public void RetrieveAll(View v)
    {
    	People[] ps = dbadapter.RetrieveAll();
    	if(ps.length==0||ps==null)
    	{
    		return;
    	}
    	String msg = "";
    	for (int i = 0;i<ps.length;i++)
    	{
    		msg += ps[i].toString()+"\n";
    	}
    	show.setText(msg);
    }
    
    public void Update(View v)
    {
    	long id = Long.parseLong(etid.getText().toString());   	
    	Long endid=dbadapter.Update(id, getValues());
    	show.setText("Id:"+endid+" has updated");
    }
    
    public void DeleteById(View v)
    {
    	long id = Long.parseLong(etid.getText().toString());   	
    	int affectRows = dbadapter.DeleteById(id);
    	show.setText(affectRows+" rows has deleted.");
    }
    
    public void DeleteAll(View v)
    {
    	int affectRows = dbadapter.DeleteAll();
    	show.setText(affectRows+" rows has deleted.");
    }
    
}
