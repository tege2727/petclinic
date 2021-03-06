package com.javaegitimleri.petclinic.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaegitimleri.petclinic.dao.OwnerRepositoryDAO;
import com.javaegitimleri.petclinic.model.Owner;


@Repository
public class OwnerRepositoryJDBCImpl implements OwnerRepositoryDAO {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private RowMapper<Owner > rowMapper=new RowMapper<Owner>() {
		
		@Override
		public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Owner owner=new Owner();
			owner.setId(rs.getLong("id"));
			owner.setFirstName(rs.getString("first_name"));
			owner.setLastName(rs.getString("last_name"));
			
			return owner;
		}
	};

	@Override
	public List<Owner> findAll() {
		String sql="Select id,first_name,last_name from t_owner ";
		
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Owner> findByLastName(String lastname) {
		String sql="Select id,first_name,last_name from t_owner where last_name like ?";
		return jdbcTemplate.query(sql, rowMapper,"%"+lastname+"%");
	}

	@Override
	public Owner findById(Long id) {
		String sql="Select id,first_name,last_name from t_owner where id=?";
		
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper,id));
	}

	@Override
	public void create(Owner owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		
		String sql="delete from t_owner where id=?";
		
		jdbcTemplate.update(sql, id);
		
	}

}
