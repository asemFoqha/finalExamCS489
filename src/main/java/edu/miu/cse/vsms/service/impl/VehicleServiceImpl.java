package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.model.VService;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.repository.VehicleServiceRepository;
import edu.miu.cse.vsms.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleServiceRepository vehicleServiceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public VehicleServiceResponseDto assignService(ServiceRequestDto request) {
        // Write your code here

        Employee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + request.employeeId()));

        VService vService = new VService(
                request.serviceName(),
                request.cost(),
                request.vehicleType(),
                employee
        );

        employee.setVServices(List.of(vService));

        employeeRepository.save(employee);

        VService saved = vehicleServiceRepository.save(vService);
        VehicleServiceResponseDto vehicleServiceResponseDto = new VehicleServiceResponseDto(
                saved.getId(),
                saved.getServiceName(),
                saved.getCost(),
                saved.getVehicleType()
        );

        return vehicleServiceResponseDto;
    }
}
