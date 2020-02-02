package com.cts.stockview.dao;

import java.util.List;

import com.cts.stockview.model.IPO;

public interface IPODAO {

	public boolean addIPO(IPO ipo);

	public boolean updateIPO(IPO ipo);

	public boolean deleteIPO(IPO ipo);

	public IPO getIPOById(int id);

	public List<IPO> getAllIPOs();
}
