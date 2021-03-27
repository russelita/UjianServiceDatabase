package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.BonusRowMapper;
import com.juaracoding.main.model.CariDuplikat;
import com.juaracoding.main.model.DepartmentRowMapper;
import com.juaracoding.main.model.DuplikatRowMapper;
import com.juaracoding.main.model.InfoDepartment;
import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;


@RestController
@RequestMapping("/ujianservicedb")
public class ServiceDBController {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<Bonus> getBonus() {

		String sql = "Select * from bonus";

		List<Bonus> bonus = jdbc.query(sql, new BonusRowMapper());

		return bonus;

	}
	
	public List<Title> getTitle() {

		String sql = "Select * from title";

		List<Title> title = jdbc.query(sql, new TitleRowMapper());

		return title;

	}
	
	public List<Worker> getWorker() {

		String sql = "Select * from worker";

		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}
	
	public List<Worker> getNilaiTertinggi() {

		String sql = "SELECT * FROM worker ORDER BY salary DESC LIMIT 5";

		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());

		return worker;

	}	
	
	public List<CariDuplikat> getDuplicate() {

		String sql = "SELECT first_name, last_name, salary, COUNT(salary) AS jumlah FROM worker GROUP BY salary HAVING jumlah>1";

		List<CariDuplikat> duplikat = jdbc.query(sql, new DuplikatRowMapper());

		return duplikat;

	}
	
	public List<InfoDepartment> getSum() {

		String sql = "SELECT department, COUNT(department)AS result FROM `worker` GROUP BY department HAVING COUNT(department)>1 ORDER BY department ASC";

		List<InfoDepartment> info = jdbc.query(sql, new DepartmentRowMapper());

		return info;

	}
	
	public int insertBonus (Bonus dataBonus) {
		
		return jdbc.update("insert into bonus(worker_ref_id,bonus_date,bonus_amount) values (" + dataBonus.getWorker_ref_id() + ",'" + dataBonus.getBonus_date() + "', " + dataBonus.getBonus_amount() + ")");
		
	}
	
	public int insertTitle (Title dataTitle) {
		
		return jdbc.update("insert into title(worker_ref_id,worker_title,affected_from) values (" + dataTitle.getWorker_ref_id() + ",'" + dataTitle.getWorker_title() + "', '" + dataTitle.getAffected_from() + "')");
		
	}
	
	public int insertWorker (Worker dataWorker) {
		
		return jdbc.update("insert into worker(worker_id,first_name,last_name,salary,joining_date,department) values (" + dataWorker.getWorker_id() + ",'" + dataWorker.getFirst_name() + "', '" + dataWorker.getLast_name() + "', " + dataWorker.getSalary() + ", '" + dataWorker.getJoining_date() + "', '" + dataWorker.getDepartment() + "')");
		
	}
	
	public int updateBonus(int worker_ref_id, Bonus updatedataBonus) {

		return jdbc.update("UPDATE bonus SET `bonus_date`= '" + updatedataBonus.getBonus_date() + "', `bonus_amount`=" 
		+ updatedataBonus.getBonus_amount() + " WHERE worker_ref_id = " + updatedataBonus.getWorker_ref_id() + "");
		

	}
	
	public int updateTitle(int worker_ref_id, Title updatedataTitle) {

		return jdbc.update("UPDATE title SET `worker_title`= '" + updatedataTitle.getWorker_title() + "', `affected_from`='" 
		+ updatedataTitle.getAffected_from() + "' WHERE worker_ref_id = " + updatedataTitle.getWorker_ref_id() + "");
		

	}
	
	public int updateWorker(int worker_id, Worker updatedataWorker) {

		return jdbc.update("UPDATE worker SET `first_name`= '" + updatedataWorker.getFirst_name() + "', `last_name`= '" + updatedataWorker.getLast_name() + "', `salary`=" 
		+ updatedataWorker.getSalary() + ", `joining_date`= '" + updatedataWorker.getJoining_date() + "', `department`= '" + updatedataWorker.getDepartment() + "' WHERE worker_id = " + updatedataWorker.getWorker_id() + "");
		

	}
	
	public int deleteBonus(int worker_ref_id) {
		return jdbc.update("DELETE FROM bonus WHERE worker_ref_id = " + worker_ref_id + ";");
	}
	
	public int deleteTitle(int worker_ref_id) {
		return jdbc.update("DELETE FROM title WHERE worker_ref_id = " + worker_ref_id + ";");
	}
	
	public int deleteWorker(int worker_id) {
		return jdbc.update("DELETE FROM worker WHERE worker_id = " + worker_id + ";");
	}
	
	@GetMapping("/bonus")
    public List<Bonus> listBonus() {
        return getBonus();
    }
	
	@GetMapping("/title")
    public List<Title> listTitle() {
        return getTitle();
    }
	
	@GetMapping("/worker")
    public List<Worker> listWorker() {
        return getWorker();
    }
	
	@GetMapping("/worker/tertinggi")
    public List<Worker> listWorkerTertinggi() {
        return getNilaiTertinggi();
    }
	
	@GetMapping("/worker/sama")
    public List<CariDuplikat> listWorkerGajiSama() {
        return getDuplicate();
	}
        
    @GetMapping("/worker/jabatan")
    public List<InfoDepartment> listWorkerJabatanSama() {
    return getSum();
        
    }
	
	@PostMapping("/bonus")
    public String add(@RequestBody Bonus dataBonus) {
	 

		if (this.insertBonus(dataBonus) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
    }
	
	@PostMapping("/title")
    public String add(@RequestBody Title dataTitle) {
	 

		if (this.insertTitle(dataTitle) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
    }
	
	@PostMapping("/worker")
    public String add(@RequestBody Worker dataWorker) {
	 

		if (this.insertWorker(dataWorker) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
    }
	
	@PutMapping("/bonus/{work_ref_id}")
	public ResponseEntity<?> update(@RequestBody Bonus updatedataBonus, @PathVariable int work_ref_id) {
		 try {
	            updateBonus(work_ref_id, updatedataBonus);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }		 
	}
	
	@PutMapping("/title/{work_ref_id}")
	public ResponseEntity<?> update(@RequestBody Title updatedataTitle, @PathVariable int work_ref_id) {
		 try {
	            updateTitle(work_ref_id, updatedataTitle);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	}
	
	@PutMapping("/worker/{worker_id}")
	public ResponseEntity<?> update(@RequestBody Worker updatedataWorker, @PathVariable int worker_id) {
		 try {
	            updateWorker(worker_id, updatedataWorker);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	}
	
	@DeleteMapping("/bonus/{worker_ref_id}")
    public String deletedataBonus(@PathVariable int worker_ref_id) {
	 	deleteBonus(worker_ref_id);
	 	
	 	int hasil = 1;
	 	if (hasil == 1) {
			return "Delete data berhasil";
		} else {
			return "Delete data gagal";
		}
 	}
	
	@DeleteMapping("/title/{worker_ref_id}")
    public String deletedataTitle(@PathVariable int worker_ref_id) {
	 	deleteTitle(worker_ref_id);
	 	
	 	int hasil = 1;
	 	if (hasil == 1) {
			return "Delete data berhasil";
		} else {
			return "Delete data gagal";
		}
 	}
	
	@DeleteMapping("/worker/{worker_id}")
    public String deletedataWorker(@PathVariable int worker_id) {
	 	deleteWorker(worker_id);
	 	
	 	int hasil = 1;
	 	if (hasil == 1) {
			return "Delete data berhasil";
		} else {
			return "Delete data gagal";
		}
 	}
	

}
