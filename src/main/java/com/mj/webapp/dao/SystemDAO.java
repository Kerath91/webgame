package com.mj.webapp.dao;

import java.util.List;
import com.mj.webapp.model.System;

public interface SystemDAO {
	
	public List<System> list();
	public System getSystemByName(String name);
	public System getSystemById(long id);
}
