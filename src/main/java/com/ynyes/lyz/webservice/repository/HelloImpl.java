package com.ynyes.lyz.webservice.repository;

import javax.jws.WebService;

import com.ynyes.lyz.webservice.impl.Hello;

@WebService(endpointInterface = "com.ynyes.lyz.webservice.impl.Hello",serviceName="HelloImpl")
public class HelloImpl implements Hello {

	@Override
	public String sayHi(String name) {
		return name + "，你好";
	}

}
