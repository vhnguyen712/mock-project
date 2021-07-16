package com.lms.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lms.commom.entity.User;

public class UserCsvExporter {
	
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String timestamp = dateFormat.format(new Date());
		String fileName = "users_" + timestamp + ".csv";
		
		response.setContentType("text/csv");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.setHeader(headerKey, headerValue);
		
		ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"UserId", "Email", "Name", "Phone", "Create Time","Authentication Type" , "Status"};
		String[] fieldMapping = {"id", "email", "name", "phone", "createTime", "authenticationType", "status"};
		
		csvBeanWriter.writeHeader(csvHeader);
		
		for (User user : listUsers) {
			csvBeanWriter.write(user, fieldMapping);
		}
		
		csvBeanWriter.close();
	}
}
