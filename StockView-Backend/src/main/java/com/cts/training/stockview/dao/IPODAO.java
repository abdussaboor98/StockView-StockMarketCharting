package com.cts.training.stockview.dao;

import java.util.List;

import com.cts.training.stockview.model.IPOEntity;

public interface IPODAO {

	public boolean addIPO(IPOEntity ipo);

	public boolean updateIPO(IPOEntity ipo);

	public boolean deleteIPO(IPOEntity ipo);

	public IPOEntity getIPOById(int id);

	public List<IPOEntity> getAllIPOs();
}
