package com.casit.service;

public interface RabbitmqService {

	void snedToQueue(String content);
	
	default int gogogo() {return 1;};

}
