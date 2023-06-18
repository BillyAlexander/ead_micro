package com.ead.authServer.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ead.authServer.services.UtilsService;

@Service
public class UtilsServiceImpl implements UtilsService {
	
	
	@Override
	public String createUrl(UUID userId, Pageable pageable) {
		return "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
    			+ pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
	}
	

}
