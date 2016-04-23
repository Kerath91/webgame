package com.mj.webapp.dao;

import java.util.List;
import com.mj.webapp.model.SystemContract;

public interface SystemContractDAO {
	
	public List<SystemContract> list();
	public void add(SystemContract entity);
	public void delete(SystemContract entity);
	public int generate();
	public SystemContract generateId(SystemContract sc);
}
