package com.javatpoint.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javatpoint.beans.Narocilo;
import com.javatpoint.beans.Soba;
import com.javatpoint.dao.NarociloDao;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Controller
public class NarociloController {

	@Autowired
	NarociloDao dao;// will inject dao from xml file

	/**
	 * 
	 * @param sobaId->
	 * @param opis->
	 * @return
	 */
	@RequestMapping(value = "/saveNarocilo", method = RequestMethod.POST)
	public @ResponseBody String save(@RequestParam("sobaId") int sobaId, @RequestParam("opis") String opis) {
		JSONObject jsonObject = new JSONObject();

		Narocilo narocilo = new Narocilo();
		narocilo.setSobaId(sobaId);
		narocilo.setOpis(opis);

		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		narocilo.setDatumPopravila(ourJavaTimestampObject);

		if(!dao.isSobaidExistsInSobaTable(sobaId)){
			jsonObject.put("status", false);
			jsonObject.put("message", "sobaid does not exist in soba table");
		}
		else if (dao.isSobaidExists(sobaId)) {

			jsonObject.put("status", false);
			jsonObject.put("message", "sobaid already added ");

		}  else {

			int val = dao.save(narocilo);
			if (val == 1) {

				jsonObject.put("status", true);
				jsonObject.put("message", "Sucessfully store into db");
			} else {
				jsonObject.put("status", false);
				jsonObject.put("message", "Error During insertion");
			}

		}

		return jsonObject.toString();

	}

	/**
	 * Fetch all the Narocilos list from db
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/listOfNarocilos",method = RequestMethod.POST)
	public @ResponseBody String listOfNarocilos() throws IOException {
		System.out.println("Reach here11");
		List<Narocilo> listNarocilo = dao.getNarocilo();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0;i<listNarocilo.size();i++) {
			
			JSONObject jsonObject1=new JSONObject();
			
			jsonObject1.put("narociloId",listNarocilo.get(i).getNarociloId());
			jsonObject1.put("sobaId",listNarocilo.get(i).getSobaId());
			jsonObject1.put("opis",listNarocilo.get(i).getOpis().toString());
			jsonObject1.put("datumPopravila",listNarocilo.get(i).getDatumPopravila().toString());
			
			System.out.println(listNarocilo.get(i).getDatumPopravila());
			jsonArray.add(jsonObject1);
			
		}
		

		if (listNarocilo != null) {
			jsonObject.put("status", true);
			jsonObject.put("narocilo_list", jsonArray);
			jsonObject.put("message", "Sucessfully Fetched");

		} else {
			jsonObject.put("status", false);
			jsonObject.put("message", "Not found");

		}

		return jsonObject.toString();
	}
	
	
	
	@RequestMapping(value = "/deleteNarocilosById",method = RequestMethod.POST)
	public @ResponseBody String deleteNarocilosById(@RequestParam("narociloId") int narociloId)  {
		
		
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			if(dao.isNarcolioidExists(narociloId)) {
				
				if(dao.delete(narociloId)>0) {
		            jsonObject.put("status",true);
		            jsonObject.put("message", "Sucessfully deleted from db");
				}else {
					  jsonObject.put("status",true);
			          jsonObject.put("message", "Error found");
				}
			} else {
				
				 jsonObject.put("status",false);
		         jsonObject.put("message", "id does not exist");
				
			}
			
		}catch(DataAccessException dae) {
			dae.printStackTrace();
		}
		
		
		
		
		return jsonObject.toString();
	}
	
	
	/**
	 * Inserted into soba db
	 * @param sobaId
	 * @param opis
	 * @param skoda
	 * @return
	 */
	@RequestMapping(value = "/saveSoba",method = RequestMethod.POST)
	public @ResponseBody String insertIntoSoba(@RequestParam("sobaId") int sobaId,@RequestParam("opis") String opis,@RequestParam("skoda") String skoda)  {
		
		
		
		JSONObject jsonObject = new JSONObject();
		try{
		
		Soba soba=new Soba();
		soba.setOpis(opis);
		soba.setSobaId(sobaId);
		soba.setSkoda(Double.parseDouble(skoda));
		
		if(dao.saveSoda(soba)>0) {
			jsonObject.put("status",true);
			jsonObject.put("message","sucessfully inserted");
		}else {
			jsonObject.put("status",false);
			jsonObject.put("message","something went wrong ");
		}
		}catch(NumberFormatException e){
			jsonObject.put("status",false);
			jsonObject.put("message","invalid format sobaId");
		}catch(Exception e1){
			jsonObject.put("status",false);
			jsonObject.put("message","already exist sobaId");
		}
		
		
		
		
		return jsonObject.toString();
	}

	/**
	 * fetch list Of Sobas
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/listOfSobas",method = RequestMethod.POST)
	public @ResponseBody String listOfSobas() throws IOException {
		System.out.println("Reach here11");
		List<Soba> listSoba = dao.getSoba();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0;i<listSoba.size();i++) {
			
			JSONObject jsonObject1=new JSONObject();
			
			jsonObject1.put("sobaId",listSoba.get(i).getSobaId());
			jsonObject1.put("skoda",listSoba.get(i).getSkoda());
			jsonObject1.put("opis",listSoba.get(i).getOpis());
			jsonArray.add(jsonObject1);
			
		}
		

		if (listSoba != null) {
			jsonObject.put("status", true);
			jsonObject.put("soba_list", jsonArray);
			jsonObject.put("message", "Sucessfully Fetched");

		} else {
			jsonObject.put("status", false);
			jsonObject.put("message", "Not found");

		}

		return jsonObject.toString();
	}
	
	
	

}