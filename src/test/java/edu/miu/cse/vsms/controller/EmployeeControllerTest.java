package edu.miu.cse.vsms.controller;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @Test
    void createUser_validInput_returnsCreatedUser() {

        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(
                "John Doe",
                "john.doe@example.com",
                "1234567890",
                LocalDate.parse("2024-01-10")
        );
        List<VehicleServiceResponseDto> vehicleServiceResponseDtos = new ArrayList<>();
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(
                1L,
                "John Doe",
                "john.doe@example.com",
                "1234567890",
                LocalDate.parse("2024-01-10"),
                vehicleServiceResponseDtos
        );

        Mockito.when(employeeService.addEmployee(employeeRequestDto)).thenReturn(employeeResponseDto);
        ResponseEntity<EmployeeResponseDto> responseEntity = employeeController.addEmployee(employeeRequestDto);
        assert responseEntity.getBody().equals(employeeResponseDto);
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
    }

}