package com.example.nttdata.controller;

import com.example.nttdata.dto.ClientDTO;
import com.example.nttdata.service.ClientServiceImpl;
import com.example.nttdata.util.Constant;
import com.example.nttdata.util.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Api(tags = "Client API")
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService){
        this.clientService = clientService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getAll(){
        try{
            List<ClientDTO> clientDTOs = clientService.getAll();

            if(clientDTOs.isEmpty()){
                return ResponseHandler.generateOperation("No Data Found", Constant.ACCEPTED,clientDTOs);
            }else {
                return ResponseHandler.generateOperation("Data recovered!", Constant.ACCEPTED,clientDTOs);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }

    }

    @GetMapping("/{dni}")
    @ApiOperation(value = "Get client by dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getOneByDni(@PathVariable("dni") String dni){
        try{
            ClientDTO client = clientService.getOneByDni(dni);
            if(!ObjectUtils.isEmpty(client)){
                return ResponseHandler.generateOperation("Data found",Constant.ACCEPTED,client);
            }else{
                return ResponseHandler.generateOperation("Data not found",Constant.ACCEPTED,client);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }


    }

    @PostMapping("/")
    @ApiOperation(value = "Save client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data saved successfully"),
            @ApiResponse(code = 404, message = "Error saving data")
    })
    public ResponseEntity<Object> save(@RequestBody ClientDTO clientDTO){
        try{
            ClientDTO client = clientService.save(clientDTO);
            if(!ObjectUtils.isEmpty(client)){
                return ResponseHandler.generateOperation("Data saved successfully",Constant.CREATED,client);
            }else{
                return ResponseHandler.generateOperation("Error saving data",Constant.BAD_REQUEST,client);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }

    }

    @PutMapping("/{dni}")
    @ApiOperation(value = "Update client by dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data updated successfully"),
            @ApiResponse(code = 404, message = "Error updating data")
    })
    public ResponseEntity<Object> update(@PathVariable("dni") String dni, @RequestBody ClientDTO clientDTO) {
        try{
            ClientDTO client = clientService.update(dni,clientDTO);
            if(!ObjectUtils.isEmpty(client)){
                return ResponseHandler.generateOperation("Data updated successfully",Constant.ACCEPTED,client);
            }else{
                return ResponseHandler.generateOperation("Error updating data",Constant.BAD_REQUEST,client);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @DeleteMapping("/{dni}")
    @ApiOperation(value = "Delete client by dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data deleted successfully"),
            @ApiResponse(code = 404, message = "Error deleting data")
    })
    public ResponseEntity<Object> deleteByDni(@PathVariable("dni") String dni){
        try{
            Boolean delete = clientService.deleteByDni(dni);
            if(delete){
                return ResponseHandler.generateOperation("Data deleted successfully",Constant.ACCEPTED, true);
            }else{
                return ResponseHandler.generateOperation("Error deleting data",Constant.BAD_REQUEST, false);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }
}
