package com.demo.xyzmc.service;

import com.demo.xyzmc.dto.ConnectionRequestResponseDTO;
import com.demo.xyzmc.dto.RequestDTO;
import com.demo.xyzmc.entity.PlanType;

public interface RequestService {

	ConnectionRequestResponseDTO addRequest(RequestDTO requestDto,PlanType planType);

}
