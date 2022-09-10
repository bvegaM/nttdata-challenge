package com.example.nttdata.controller;

import com.example.nttdata.dto.MovementDTO;
import com.example.nttdata.dto.MovementInsertDTO;
import com.example.nttdata.dto.ReportDTO;
import com.example.nttdata.service.MovementServiceImpl;
import com.example.nttdata.util.Constant;
import com.example.nttdata.util.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
@Api(tags = "Movement API")
public class MovementController {

    @Autowired
    private MovementServiceImpl movementService;

    @GetMapping("/dni/{dni}")
    @ApiOperation(value = "Get Movements by client dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getByAccountClientDni(@PathVariable("dni") String dni){
        try{
            List<MovementDTO> movements = movementService.getByAccountClientDni(dni);
            if(movements.isEmpty()){
                return ResponseHandler.generateOperation("No data found", Constant.ACCEPTED,
                        movements);
            }else {
                return ResponseHandler.generateOperation("Data recovered!", Constant.ACCEPTED,movements);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }

    }

    @GetMapping("/accountNumber/{accountNumber}")
    @ApiOperation(value = "Get Movements by account number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getByAccountNumberAccount(@PathVariable("accountNumber") String accountNumber){
        try{
            List<MovementDTO> movements = movementService.getByAccountNumberAccount(accountNumber);
            if(movements.isEmpty()){
                return ResponseHandler.generateOperation("No data found", Constant.ACCEPTED,
                        movements);
            }else {
                return ResponseHandler.generateOperation("Data recovered!", Constant.ACCEPTED,movements);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @GetMapping("/filter/")
    @ApiOperation(value = "Get movements by date range and client dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getByAccountClientDniAndMovementDateIsBetween(@RequestParam String dni,@RequestParam String initDate,
                                                                                @RequestParam String endDate){
        try{
            List<ReportDTO> movements = movementService.getByAccountClientDniAndMovementDateIsBetween(dni,initDate,endDate);
            if(movements.isEmpty()){
                return ResponseHandler.generateOperation("No data found", Constant.ACCEPTED,
                        movements);
            }else {
                return ResponseHandler.generateOperation("Data recovered!", Constant.ACCEPTED,movements);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Save movement")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data saved successfully"),
            @ApiResponse(code = 404, message = "Error saving data")
    })
    public ResponseEntity<Object> save(@RequestBody MovementInsertDTO movementInsertDTO){
        try{
            MovementDTO movement = movementService.save(movementInsertDTO);
            if(!ObjectUtils.isEmpty(movement)){
                return ResponseHandler.generateOperation("Data saved successfully",Constant.CREATED,movement);
            }else {
                return ResponseHandler.generateOperation("Error saving data",Constant.BAD_REQUEST,movement);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

}
