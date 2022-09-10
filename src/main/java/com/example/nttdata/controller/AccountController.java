package com.example.nttdata.controller;

import com.example.nttdata.dto.AccountDTO;
import com.example.nttdata.service.AccountServiceImpl;
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
@RequestMapping("/accounts")
@Api(tags = "Account API")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;


    @GetMapping("/dni/{dni}")
    @ApiOperation(value = "Get accounts by dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getByDni(@PathVariable("dni") String dni){
        try{
            List<AccountDTO> accountDTOS = accountService.getByDni(dni);
            if (accountDTOS.isEmpty()){
                return ResponseHandler.generateOperation("No Data Found", Constant.ACCEPTED,accountDTOS);
            }else{
                return ResponseHandler.generateOperation("Data recovered!", Constant.ACCEPTED,accountDTOS);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @GetMapping("/{accountNumber}")
    @ApiOperation(value = "Get account by number account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data recovered or No Data Found"),
            @ApiResponse(code = 404, message = "Error")
    })
    public ResponseEntity<Object> getByNumberAccount(@PathVariable("accountNumber") String accountNumber){
        try{
            AccountDTO account = accountService.getByNumberAccount(accountNumber);
            if(!ObjectUtils.isEmpty(account)){
                return ResponseHandler.generateOperation("Data recovered!",Constant.ACCEPTED,account);
            }else{
                return ResponseHandler.generateOperation("Data not found",Constant.ACCEPTED,account);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Save account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data saved successfully"),
            @ApiResponse(code = 404, message = "Error saving data")
    })
    public ResponseEntity<Object> save(@RequestBody AccountDTO accountDTO){
        try{
            AccountDTO account = accountService.save(accountDTO);
            if(!ObjectUtils.isEmpty(account)){
                return ResponseHandler.generateOperation("Data saved successfully",Constant.CREATED,account);
            }else{
                return ResponseHandler.generateOperation("Error saving data",Constant.BAD_REQUEST,account);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @PutMapping("/{numberAccount}")
    @ApiOperation(value = "Update account by numberAccount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data updated successfully"),
            @ApiResponse(code = 404, message = "Error updating data")
    })
    public ResponseEntity<Object> update(@PathVariable("numberAccount") String numberAccount, @RequestBody AccountDTO accountDTO) {
        try{
            AccountDTO account = accountService.update(numberAccount,accountDTO);
            if(!ObjectUtils.isEmpty(account)){
                return ResponseHandler.generateOperation("Data updated successfully",Constant.ACCEPTED,account);
            }else{
                return ResponseHandler.generateOperation("Error updating data",Constant.BAD_REQUEST,account);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }

    @DeleteMapping("/{numberAccount}")
    @ApiOperation(value = "Delete account by numberAccount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data deleted successfully"),
            @ApiResponse(code = 404, message = "Error deleting data")
    })
    public ResponseEntity<Object> deleteByNumberAccount(@PathVariable("numberAccount") String numberAccount){
        try{
            Boolean delete = accountService.deleteByNumberAccount(numberAccount);
            if (delete){
                return ResponseHandler.generateOperation("Data deleted successfully",Constant.ACCEPTED, true);
            }else{
                return ResponseHandler.generateOperation("Error deleting data",Constant.BAD_REQUEST, false);
            }
        }catch (Exception ex){
            return ResponseHandler.generateOperation(ex.getMessage(), Constant.BAD_REQUEST,ex);
        }
    }
}
