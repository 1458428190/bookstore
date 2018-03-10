package cn.gdufe.bookstore.user.demo;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapOwner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class BaseDao<T>
{
	private QueryRunner qr = new TxQueryRunner();
	//得到T的class
	private Class beanClass = null;
	private String tableName = "";
	private Field[] fields = null;
	private String priName = "";
	private List<String> columnNames = null;
	
	//每当new一个子类时,便同时得到该bean和对应数据库表的一些信息(比如表名,主键名,非主键列名,还有类的字段名)
	public BaseDao()
	{
		//得到T的class,得到表名,以及属性名
		beanClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Table table = (Table) beanClass.getAnnotation(Table.class);
		tableName = table.value();
		//得到该类的所有字段
		fields = beanClass.getDeclaredFields();
		
		
		//得到主键名称
		ID id = (ID)fields[0].getAnnotation(ID.class);
		priName = id.value();
		
		//得到其他列的名称
		columnNames = new ArrayList<String>();
		for(int i=1; i<fields.length; i++)
		{
			//得到列的注解
			Column column = (Column)fields[i].getAnnotation(Column.class);
			columnNames.add(column.value());
		}
	}
	
	//添加tableName表记录
	public void add(T bean) throws IllegalArgumentException, IllegalAccessException
	{
		//SQL语句
		String sql = "insert into "+ tableName +" values(";
		//参数
		Object[] params = new String[fields.length];
		//给参数赋值,已经填补sql语句
		for(int i=0; i<fields.length; i++)
		{
			sql += "?";
			if(i<fields.length-1)
				sql += ",";
			Field f = fields[i];
			//设置属性值可以访问,必须设置
			f.setAccessible(true);
			Object val = f.get(bean);
			params[i] = val;
		}
		sql += ")";
		try
		{
			qr.update(sql, params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	//更新tableName表
	public void update(T bean) throws IllegalArgumentException, IllegalAccessException
	{
		//与添加相似
		String sql = "update "+ tableName +" set ";
		for(int i=0; i<columnNames.size(); i++)
		{
			sql += columnNames.get(i)+"=?";
			if(i<columnNames.size()-1)
				sql+=",";
		}
		sql += " where "+priName+"=?";
		Object[] params = new Object[fields.length];
		
		for(int i=0; i<fields.length; i++)
		{
			Field f = fields[i];
			f.setAccessible(true);
			Object val = f.get(bean);
			if(i>0)
				params[i-1] = val;        //赋值非主键
			else
				params[fields.length-1] = val;          //最后赋值主键
		}
		
		try
		{
			qr.update(sql, params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	//通过uuid删除
	public void delete(String uuid)
	{
		String sql = "delete from "+tableName+" where "+priName+"=?";
		try
		{
			qr.update(sql, uuid);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	//载入
	public T load(String uuid)
	{
		String sql = "select * from "+tableName+" where "+priName+" =?";
		try {
			//得到Map
			Map<String, Object> map = qr.query(sql, new MapHandler(), uuid);
			//得到该实例
			T object = (T) beanClass.newInstance();
			//给object的属性赋值
			for(int i=1; i<fields.length; i++)
			{
				Field f = fields[i];
				//设置属性可以访问
				f.setAccessible(true);
				//columnNames中不包含主键名
				f.set(object, map.get(columnNames.get(i-1)));
			}
			//必须设置为可以访问,否则会抛出异常,因为该属性是private
			//给主键对应的属性赋值
			fields[0].setAccessible(true);
			fields[0].set(object, uuid);
			return object;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	//查找所有
	public List<T> findAll()
	{
		String sql = "select * from "+tableName;
		try
		{
			//使用maplistHandler
			List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
			List<T> list = new ArrayList<T>();
			for(int i=0; i<mapList.size(); i++)
			{
				//创建每一个bean实例,并使用map中的值为bean赋值
				Map<String, Object> map = mapList.get(i);
				T object = (T) beanClass.newInstance();
				
				//为object的每一个属性赋值
				for(int j=1; j<fields.length; j++)
				{
					Field f = fields[j];
					//设置可以访问,也就是可以调用set和get
					f.setAccessible(true);
					//为object属性赋值,因为columnNames存的只是列名(不包含主键名,也就是第一个没有包含)
					f.set(object, map.get(columnNames.get(j-1)));
				}
				//为object对象对应的主键赋值
				fields[0].setAccessible(true);
				fields[0].set(object, map.get(priName));
				//加进list中
				list.add(object);
			}
			return list;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
