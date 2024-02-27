package com.springmvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MainDao {

		private JdbcTemplate jdbcTemplate;

		public JdbcTemplate getJdbcTemplate() {
			return jdbcTemplate;
		}

		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		public int save(RegistrationModel reg) {
			// TODO Auto-generated method stub
			String sql= "insert into employee_details(emp_name,emp_gender,emp_branch,emp_password) values('"+reg.getEmp_name()+"','"+reg.getEmp_gender()+"','"+reg.getEmp_branch()+"','"+reg.getEmp_password()+"')";
			return jdbcTemplate.update(sql);
		}

		public List<RegistrationModel> doLogin(LoginModel log) {
			// TODO Auto-generated method stub
			String sql="select * from employee_details where emp_name='"+log.getEmp_name()+"' and emp_password='"+log.getEmp_password()+"'";
			List<RegistrationModel> emp = jdbcTemplate.query(sql, new RowMapper<RegistrationModel>() {

				@Override
				public RegistrationModel mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					RegistrationModel reg= new RegistrationModel();
					reg.setEmp_name(rs.getString("emp_name"));
					reg.setEmp_password(rs.getString("emp_password"));
					return reg;
				}
				
			}); 
			 List<RegistrationModel> list = emp.size()>0? emp:null;
			  return list;
			
		}

		public List<RegModel2> doLogin2(LoginModel2 log2) {
			// TODO Auto-generated method stub
			String sql="select * from admin_log where ad_name='"+log2.getAd_name()+"' and ad_password='"+log2.getAd_password()+"'";
			List<RegModel2> emp2 = jdbcTemplate.query(sql, new RowMapper<RegModel2>() {

				@Override
				public RegModel2 mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					RegModel2 reg2 = new RegModel2();
					reg2.setAd_name(rs.getString("ad_name"));
					reg2.setAd_password(rs.getString("ad_password"));
					return reg2;
				}
			});
			List<RegModel2> list2 = emp2.size()>0? emp2:null;
			  return list2;
		}

		public int savetask(AssigntaskModel atask) {
			// TODO Auto-generated method stub
			String sql= "insert into tasks(task_name,task_assignedto,task_description,task_assigndate,task_duedate,task_currentstatus) values('"+atask.getTask_name()+"','"+atask.getTask_assignedto()+"','"+atask.getTask_description()+"','"+atask.getTask_assigndate()+"','"+atask.getTask_duedate()+"','"+atask.getTask_currentstatus()+"')";
			return jdbcTemplate.update(sql);
		}

		public List<AssigntaskModel> getTask() {
			// TODO Auto-generated method stub
			String sql ="select * from tasks";
			return jdbcTemplate.query(sql, new RowMapper<AssigntaskModel >() {

				@Override
				public AssigntaskModel mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					AssigntaskModel atm=new AssigntaskModel();
					atm.setTask_id(rs.getInt(1));
					atm.setTask_name(rs.getString(2));
					atm.setTask_assignedto(rs.getString(3));
					atm.setTask_description(rs.getString(4));
					atm.setTask_assigndate(rs.getString(5));
					atm.setTask_duedate(rs.getString(6));
					atm.setTask_currentstatus(rs.getString(7));
					return atm;
				}
			
			});
		}

		public List<AssigntaskModel> getTask1(LoginModel log) {
			// TODO Auto-generated method stub
			String sql="select * from tasks where task_assignedto='"+log.getEmp_name()+"'";
			
			return jdbcTemplate.query(sql, new RowMapper<AssigntaskModel >() {

				@Override
				public AssigntaskModel mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					AssigntaskModel atm1=new AssigntaskModel();
					atm1.setTask_id(rs.getInt(1));
					atm1.setTask_name(rs.getString(2));
					atm1.setTask_assignedto(rs.getString(3));
					atm1.setTask_description(rs.getString(4));
					atm1.setTask_assigndate(rs.getString(5));
					atm1.setTask_duedate(rs.getString(6));
					atm1.setTask_currentstatus(rs.getString(7));
					return atm1;
				}
			
			});
		}

		public AssigntaskModel updateStatus(int task_id) {
			// TODO Auto-generated method stub
			String sql="select * from tasks where task_id='"+task_id+"'";
			return jdbcTemplate.queryForObject(sql, new Object[] {},new BeanPropertyRowMapper<AssigntaskModel>(AssigntaskModel.class));
					  
		}

		
		  public int Update_stat(AssigntaskModel update) { 
			  // TODO Auto-generated method stub
			  String sql="update tasks set task_currentstatus='"+update.getTask_currentstatus()+"' where task_id='"+update.getTask_id()+"'"; 
			  return jdbcTemplate.update(sql); }
		 

		

}