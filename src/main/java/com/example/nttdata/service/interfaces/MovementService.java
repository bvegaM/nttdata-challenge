package com.example.nttdata.service.interfaces;

import com.example.nttdata.dto.MovementDTO;
import com.example.nttdata.dto.MovementInsertDTO;
import com.example.nttdata.dto.ReportDTO;

import java.text.ParseException;
import java.util.List;

public interface MovementService {

    List<MovementDTO> getByAccountNumberAccount(String numberAccount);
    List<MovementDTO> getByAccountClientDni(String dni);
    List<ReportDTO> getByAccountClientDniAndMovementDateIsBetween(String dni, String initDate, String endDate) throws ParseException;
    MovementDTO save(MovementInsertDTO movementInsertDTO);
}
