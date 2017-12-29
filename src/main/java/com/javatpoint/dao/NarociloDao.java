package com.javatpoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.javatpoint.beans.Narocilo;
import com.javatpoint.beans.Soba;

public class NarociloDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// insert into narcolio
	public int save(Narocilo n) {
		String sql = "INSERT INTO narocilo(sobaId,opis,datumPopravila) VALUES(" + n.getSobaId() + ",'" + n.getOpis()
				+ "','" + n.getDatumPopravila() + "')";
		System.out.println(" sqlllllll " + sql);
		return template.update(sql);
	}

	// check where sobaid exist or not in narcolio table
	public boolean isSobaidExists(int sobaid) {

		String sql = "SELECT count(*) FROM narocilo WHERE sobaid = ?";
		boolean result = false;

		int count = template.queryForObject(sql, new Object[] { sobaid }, Integer.class);

		if (count > 0) {
			result = true;
		}

		return result;
	}
	
	
	public boolean isSobaidExistsInSobaTable(int sobaid) {

		String sql = "SELECT count(*) FROM soba WHERE SobaId = ?";
		boolean result = false;

		int count = template.queryForObject(sql, new Object[] { sobaid }, Integer.class);

		if (count > 0) {
			result = true;
		}

		return result;
	}

	// check narcolioid exist or not
	public boolean isNarcolioidExists(int narociloId) {

		String sql = "SELECT count(*) FROM narocilo WHERE narociloId = ?";

		int count = template.queryForObject(sql, new Object[] { narociloId }, Integer.class);

		return (count > 0) ? true : false;
	}
	
	public int delete(int id){  
	    String sql="DELETE FROM narocilo where narociloId="+id+"";  
	    return template.update(sql);  
	}  


	// this method returns all order from database
	public List<Narocilo> getNarocilo() {
		return template.query(" select * from narocilo ", new RowMapper<Narocilo>() {
			public Narocilo mapRow(ResultSet rs, int row) throws SQLException {

				Narocilo narocilo = new Narocilo();
				narocilo.setNarociloId(rs.getInt(1));
				narocilo.setSobaId(rs.getInt(2));
				narocilo.setOpis(rs.getString(3));
				narocilo.setDatumPopravila(rs.getTimestamp(4));

				return narocilo;
			}
		});
	}
	
	// insert into Soba
		public int saveSoda(Soba soba) {
			String sql = "INSERT INTO soba(sobaId,skoda,opis) VALUES(?,?,?)";
		    return template.update(sql,new Object[] { soba.getSobaId(), soba.getSkoda(),soba.getOpis() });  
			
		}
		
		// this method returns all order from database
		public List<Soba> getSoba() {
			return template.query(" select * from soba ", new RowMapper<Soba>() {
				public Soba mapRow(ResultSet rs, int row) throws SQLException {

					Soba soba = new Soba();
					soba.setSobaId((rs.getInt(1)));
					soba.setSkoda(rs.getDouble(2));
                    soba.setOpis(rs.getString(3));
					return soba;
				}
			});
		}
	

}