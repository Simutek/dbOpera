package com.example.dbopera;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.database.AssetsDatabaseManager;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private SQLiteDatabase db1 = null;
    private SQLiteDatabase db2 = null;
    private String SQL_INSERT = null;
	private String tag = null;
	
	
	private String sCID = null;
	private String sProductType = null;
	private String pTempSN = null;
	private String RFIDid = null;
	private String ProductCategoryid = null;
	private Date sDate = null;
	
	private Cursor cSIMU = null;
	private Cursor cRFID = null;
	private Cursor cProduct = null;
	private Cursor cProductCategory = null;
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 初始化，只需要调用一次  
        AssetsDatabaseManager.initManager(getApplication());  
        // 获取管理对象，因为数据库需要通过管理对象才能够获取  
        AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
        // 通过管理对象获取数据库  
        SQLiteDatabase db1 = mg.getDatabase("handset1.db");
        SQLiteDatabase db2 = mg.getDatabase("handset2.db");
        SQLiteDatabase simu = mg.getDatabase("SimuLocal.db");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
       
        //查询手持机1数据库smallTags表中ProductType不为空的记录
        Cursor c1 = db1.rawQuery("select * from smallTags where " +
        		"ProductType is not '' and ProductType is not null", null);
        
		Cursor c2 = db2.rawQuery("select * from smallTags where " +
				"ProductType is not '' and ProductType is not null", null);
        
        Log.d(tag, "手持机1中ProductType不为空的记录有" + c1.getCount() + "条");
        
        Log.d(tag, "手持机2中ProductType不为空的记录有" + c2.getCount() + "条");
        
        //测试代码,看看Product表中现在有多少数据
//        cSIMU = simu.rawQuery("select * from Product;", null);
//        Log.d(tag, "Product表中共有 " + cSIMU.getCount() + "条记录"); 
        
        //测试代码，用insert()方法尝试向Product表插入数据
//        ContentValues contentValues = new ContentValues();
//		contentValues.put("vendor", "contentvalues_test");
//		simu.insert("Product", null, contentValues);
//		Log.d(tag, "成功使用insert()方法向Product表插入数据");
        
        
//        while (c1.moveToNext()) {
//        	//#1	获取smallTags表中的CID的值
//			sCID = c1.getString(c1.getColumnIndex("CID"));
//			//#2	根据smallTags.CID查询SimuLocal.RFID中对应记录
//			cRFID = simu.rawQuery("select * from RFID where cid = ?",
//					new String[]{String.valueOf(sCID)});
//			//		获取RFID对象(RFID表的主键)，保存到RFIDid中
//			cRFID.moveToFirst();
//			RFIDid = cRFID.getString(cRFID.getColumnIndex("objectId"));
//			
//			cProduct = simu.rawQuery("select * from Product where rfid = ?",
//					new String[]{String.valueOf(RFIDid)});
//			
//			if (cProduct.getCount() >= 1) {
//				//如果SimuLocal.RFID中是否有cid与之对应,则不做任务操作
//				Log.d(tag, "Product表中有" + cProduct.getCount() + "条记录与之对应");
//			}else{
//				sProductType = c1.getString(c1.getColumnIndex("ProductType"));
//				String spd = c1.getString(c1.getColumnIndex("ProductionDate"));
//				try {
//					sDate = format.parse(spd);
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				cProductCategory = simu.rawQuery("select * from ProductCategory where tempSN = ?",
//						new String[]{String.valueOf(sProductType)});
//				cProductCategory.moveToNext();
//				ProductCategoryid = cProductCategory.getString(cProductCategory.getColumnIndex("objectId"));
//				//所有要准备的字段都已经获取，准备构建新Product数据
//				Log.d(spd, "需要构建的一个Product的数据为:ProductDate = " + spd +
//						"status = 1" + "rfid = " + RFIDid +"Vendor = 四川中兴机械");
//				//插入数据有两种方法，一种是调用
//				//insert(String table, String nullColumnHack, ContentValues values)
//				//需要构造一个ContentValues对象，并将数据和列名封装起来，
//				//另外一种是通过构造SQL语句
//				
//				//1.构造SQL语句
////				SQL_INSERT = "insert into Product (rfid,vendor,productDate) values ('" + RFIDid + "','GhRj3SamEr','" + sDate + "')";
//				SQL_INSERT = "insert into Product values (1,'objid','cagory',1,'rfid','vendor',NULL,1,1,'abc')";
//				simu.execSQL(SQL_INSERT);
//				Log.d(tag, "插入数据成功");
//				
//				//2.调用inset()方法
//				ContentValues contentValues = new ContentValues();
//				contentValues.put("vendor", "contentvalues_test");
//				simu.insert("Product", null, contentValues);
//			}
//		}
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
