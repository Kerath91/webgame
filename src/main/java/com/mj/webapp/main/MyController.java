package com.mj.webapp.main;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mj.webapp.dao.SystemContractDAO;
import com.mj.webapp.dao.SystemDAO;
import com.mj.webapp.model.SystemContract;
import com.mj.webapp.view.SystemContractView;
//import com.mj.webapp.model.System;

@Controller
public class MyController {
	
   @Autowired
   private SystemDAO systemDAO;
   @Autowired
   private SystemContractDAO systemContractDAO;
   
   private final static int cellsInRow = 10;
   
   @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
   public ModelAndView homePage(Model model) {
	   Map<String,List<SystemContractView>> systemViews = new HashMap<String, List<SystemContractView>>();
       List<SystemContract> list = systemContractDAO.list();
       List<SystemContractView> viewList = new ArrayList<SystemContractView>();
       for(SystemContract sc : list){
           SystemContractView scv = new SystemContractView();
           com.mj.webapp.model.System system = systemDAO.getSystemById(sc.getSystemId());
           scv.setSystemName(system.getName());
           scv.setRequest(sc.getRequest());
           scv.setOrderNumber(sc.getOrderNumber());
           scv.setFromDate(sc.getFromDate());
           scv.setToDate(sc.getToDate());
           scv.setAmount(sc.getAmount());
           scv.setAmountType(sc.getAmountType());
           scv.setAmountPeriod(sc.getAmountPeriod());
           scv.setAuthorizationPercent(sc.getAuthorizationPercent());
           scv.setActive(sc.getActive());
           viewList.add(scv);
       }
       systemViews.put("systemViews", viewList);
       
       for(SystemContractView scv : viewList) {
    	   System.out.println(scv.toString());
       }

       return new ModelAndView("systemViewsList", systemViews);
   }
   
   @RequestMapping(value = { "/add" }, method = RequestMethod.POST)
   public String addContract(@ModelAttribute("systemView") SystemContractView contract) {
	   System.out.println(contract.getAmountType());
       return "importPage";
   }
    
   @RequestMapping(value = { "/import" }, method = RequestMethod.GET)
   public String importPage(Model model) {
	   
       return "importPage";
   }
   
   @RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
   public String upload(@RequestParam("file") MultipartFile file) throws IOException {
	   File convertedFile = new File(file.getOriginalFilename());
	   convertedFile.createNewFile();
	   FileOutputStream fos = new FileOutputStream(convertedFile);
	   fos.write(file.getBytes());
	   fos.close();
       FileInputStream fis = new FileInputStream(convertedFile);
       Iterator<Row> rowIterator;
       
       if(convertedFile.getName().contains(".xlsx")) {
    	   XSSFWorkbook xssfWorkBook = new XSSFWorkbook (fis);
           XSSFSheet mySheet = xssfWorkBook.getSheetAt(0);
           rowIterator = mySheet.iterator();
           if(!rowIterator.hasNext()) {		//empty sheet
        	   xssfWorkBook.close();
        	   return "importFail";
           }
        	   
           rowIterator.next(); //skipping table header
           if(!rowIterator.hasNext()){		//no entries to add
        	   xssfWorkBook.close();
        	   return "importFail";
           }
           importData(rowIterator);
           xssfWorkBook.close();
           return "importSuccess";
       } else if(convertedFile.getName().contains(".xls")){
    	   HSSFWorkbook hssfWorkBook = new HSSFWorkbook (fis);
    	   HSSFSheet mySheet = hssfWorkBook.getSheetAt(0);
           rowIterator = mySheet.iterator();
           if(!rowIterator.hasNext()) {		//empty sheet
        	   hssfWorkBook.close();
        	   return "importFail";
           }
        	   
           rowIterator.next(); //skipping table header
           if(!rowIterator.hasNext()){		//no entries to add
        	   hssfWorkBook.close();
        	   return "importFail";
           }
           importData(rowIterator);
           hssfWorkBook.close();
           return "importSuccess";
       } else {
    	   fis.close();
    	   return "importFail";
       }
   }
   
   private void importData(Iterator<Row> rowIterator) {
	      while (rowIterator.hasNext()) {
	    	   SystemContract systemContract = new SystemContract();
	    	   Row row = rowIterator.next();
	    	   Iterator<Cell> cellIterator = row.cellIterator();
	    	   List<Object> readCells = new ArrayList<Object>();
	    	   while(cellIterator.hasNext()) {
	        	   Cell cell = cellIterator.next();
	    		   switch (cell.getCellType()) {
	    	   		case Cell.CELL_TYPE_STRING:
	    	   			readCells.add(cell.getStringCellValue());
	    	   			break;
	    	   		case Cell.CELL_TYPE_NUMERIC: 
	    	   			readCells.add(cell.getNumericCellValue());
	    	   			break; 
	    	   		case Cell.CELL_TYPE_BOOLEAN: 
	    	   			readCells.add(cell.getBooleanCellValue());
	    	   			break; 
	    	   		default :
	    		   }
			   }
	    	   if(readCells.size() != cellsInRow ) {//ignore not complete data
	    		   continue;
	    	   }
	    	   
	    	   Object cellValue;
	    	   String stringTemp;
	    	   BigDecimal bdTemp;
	    	   
	    	   //	SYSTEM ID
	    	   cellValue = readCells.get(0);
	    	   if(cellValue instanceof Double || cellValue instanceof String) {
	    		   stringTemp = String.valueOf(cellValue);
	    	   } 
	    	   else {
	    		   continue;
	    	   }
	    	   com.mj.webapp.model.System systemOfContract = systemDAO.getSystemByName(stringTemp);
	    	   systemContract.setSystemId(systemOfContract.getId());
	    	   
	    	   //	REQUEST
	    	   cellValue = readCells.get(1);
	    	   if(cellValue instanceof Double) {
	    		   double tmpDouble = (Double)cellValue;
	    		    if(tmpDouble == (long) tmpDouble)
	    		        stringTemp = String.format("%d",(long)tmpDouble);
	    		    else
	    		    	stringTemp =  String.format("%s",tmpDouble);
	    		    systemContract.setRequest(stringTemp);
	    	   } else if( cellValue instanceof String) {
	    		   systemContract.setRequest((String)cellValue);
	    	   }
	    	   else {
	    		   continue;
	    	   }
	    	   
	    	   // ORDER NUMBER
	    	   cellValue = readCells.get(2);
	    	   if(cellValue instanceof Double || cellValue instanceof String) {
	    		   stringTemp = String.valueOf(cellValue);
	    	   } 
	    	   else {
	    		   continue;
	    	   }
	    	   systemContract.setOrderNumber(stringTemp);
	    	   
	    	   //FROM_DATE
	    	   cellValue = readCells.get(3);
	    	   if(cellValue instanceof Double) {
	    		   long dateLong = (long)(((Double)cellValue - 25569)*1000*60*60*24);
	    		   Date date = new Date(dateLong);
	    		   systemContract.setFromDate(date);
	    	   } 
	    	   else {
	    		   continue;
	    	   }
	    	   
	    	   //TO_DATE
	    	   cellValue = readCells.get(4);
	    	   if(cellValue instanceof Double) {
	    		   long dateLong = (long)(((Double)cellValue - 25569)*1000*60*60*24);
	    		   Date date = new Date(dateLong);
	    		   systemContract.setToDate(date);
	    	   } 
	    	   else {
	    		   continue;
	    	   }
	    	   
	    	   //AMOUNT
	    	   cellValue = readCells.get(5);
	    	   if(cellValue instanceof Double) {
	    		   bdTemp = BigDecimal.valueOf((Double)cellValue);
	    		   systemContract.setAmount(bdTemp);
	    	   } 
	    	   else if(cellValue instanceof String){
	    		   bdTemp = new BigDecimal((String)cellValue);
	    		   systemContract.setAmount(bdTemp);
	    	   } else
	    		   continue;
	    	   
	    	   //AMOUNT_TYPE
	    	   cellValue = readCells.get(6);
	    	   if(cellValue instanceof Double || cellValue instanceof String) {
	    		   stringTemp = String.valueOf(cellValue);
	    	   } 
	    	   else {
	    		   continue;
	    	   }
	    	   systemContract.setAmountType(stringTemp);
	    	   
	    	   //AMOUNT_PERIOD
	    	   cellValue = readCells.get(7);
	    	   if(cellValue instanceof Double || cellValue instanceof String) {
	    		   stringTemp = String.valueOf(cellValue);
	    	   } 
	    	   else {
	    		   continue;
	    	   }
	    	   systemContract.setAmountPeriod(stringTemp);
	    	   
	    	   
	    	   //AUTHORIZATION_PERCENT
	    	   cellValue = readCells.get(8);
	    	   if(cellValue instanceof Double) {
	    		   bdTemp = BigDecimal.valueOf((Double)cellValue);
	    		   systemContract.setAuthorizationPercent(bdTemp);
	    	   } 
	    	   else if(cellValue instanceof String){
	    		   bdTemp = new BigDecimal((String)cellValue);
	    		   systemContract.setAuthorizationPercent(bdTemp);
	    	   } else
	    		   continue;
	    	   
	    	   //ACTIVE
	    	   cellValue = readCells.get(9);
	    	   if(cellValue instanceof String) {
	    		   stringTemp = (String)cellValue;
	    		   if(stringTemp.equalsIgnoreCase("true"))
	    				   systemContract.setActive(true);
	    		   else if(stringTemp.equalsIgnoreCase("false"))
					   systemContract.setActive(false);
	    		   else continue;
	    	   } 
	    	   else if(cellValue instanceof Boolean){
	    		   systemContract.setActive((Boolean) cellValue);
	    	   } else
	    		   continue;
	    	   
	    	   systemContract = systemContractDAO.generateId(systemContract);
	    	   systemContractDAO.add(systemContract);
	       }
   }
   
}