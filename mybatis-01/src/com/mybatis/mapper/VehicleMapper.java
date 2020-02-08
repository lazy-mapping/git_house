package com.mybatis.mapper;

import com.mybatis.vo.Vehicle;

public interface VehicleMapper {
	Vehicle selectVehicleById(Integer id);
}
