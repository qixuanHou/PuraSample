package com.androidopentutorials.sqlite.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.androidopentutorials.sqlite.to.Employee;

public class EmployeeDAO extends EmployeeDBDAO {

	private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_COLUMN
			+ " =?";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.ENGLISH);

	public EmployeeDAO(Context context) {
		super(context);
	}

	public long save(Employee employee) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.NAME_COLUMN, employee.getName());
		Log.d("dob", employee.getDateOfBirth().getTime() + "");
		values.put(DataBaseHelper.EMPLOYEE_DOB, formatter.format(employee.getDateOfBirth()));
		values.put(DataBaseHelper.EMPLOYEE_SALARY, employee.getSalary());

		return database.insert(DataBaseHelper.EMPLOYEE_TABLE, null, values);
	}

	public long update(Employee employee) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.NAME_COLUMN, employee.getName());
		values.put(DataBaseHelper.EMPLOYEE_DOB, formatter.format(employee.getDateOfBirth()));
		values.put(DataBaseHelper.EMPLOYEE_SALARY, employee.getSalary());

		long result = database.update(DataBaseHelper.EMPLOYEE_TABLE, values,
				WHERE_ID_EQUALS,
				new String[] { String.valueOf(employee.getId()) });
		Log.d("Update Result:", "=" + result);
		return result;

	}

	public int delete(Employee employee) {
		return database.delete(DataBaseHelper.EMPLOYEE_TABLE, WHERE_ID_EQUALS,
				new String[] { employee.getId() + "" });
	}

	//USING query() method
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();

		Cursor cursor = database.query(DataBaseHelper.EMPLOYEE_TABLE,
				new String[] { DataBaseHelper.ID_COLUMN,
						DataBaseHelper.NAME_COLUMN,
						DataBaseHelper.EMPLOYEE_DOB,
						DataBaseHelper.EMPLOYEE_SALARY }, null, null, null,
				null, null);

		while (cursor.moveToNext()) {
			Employee employee = new Employee();
			employee.setId(cursor.getInt(0));
			employee.setName(cursor.getString(1));
			try {
				employee.setDateOfBirth(formatter.parse(cursor.getString(2)));
			} catch (ParseException e) {
				employee.setDateOfBirth(null);
			}
			employee.setSalary(cursor.getDouble(3));

			employees.add(employee);
		}
		return employees;
	}
	
	//USING rawQuery() method
	/*public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();

		String sql = "SELECT " + DataBaseHelper.ID_COLUMN + ","
				+ DataBaseHelper.NAME_COLUMN + ","
				+ DataBaseHelper.EMPLOYEE_DOB + ","
				+ DataBaseHelper.EMPLOYEE_SALARY + " FROM "
				+ DataBaseHelper.EMPLOYEE_TABLE;

		Cursor cursor = database.rawQuery(sql, null);

		while (cursor.moveToNext()) {
			Employee employee = new Employee();
			employee.setId(cursor.getInt(0));
			employee.setName(cursor.getString(1));
			try {
				employee.setDateOfBirth(formatter.parse(cursor.getString(2)));
			} catch (ParseException e) {
				employee.setDateOfBirth(null);
			}
			employee.setSalary(cursor.getDouble(3));

			employees.add(employee);
		}
		return employees;
	}*/
	
	//Retrieves a single employee record with the given id
	public Employee getEmployee(long id) {
		Employee employee = null;

		String sql = "SELECT * FROM " + DataBaseHelper.EMPLOYEE_TABLE
				+ " WHERE " + DataBaseHelper.ID_COLUMN + " = ?";

		Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

		if (cursor.moveToNext()) {
			employee = new Employee();
			employee.setId(cursor.getInt(0));
			employee.setName(cursor.getString(1));
			try {
				employee.setDateOfBirth(formatter.parse(cursor.getString(2)));
			} catch (ParseException e) {
				employee.setDateOfBirth(null);
			}
			employee.setSalary(cursor.getDouble(3));
		}
		return employee;
	}
}
