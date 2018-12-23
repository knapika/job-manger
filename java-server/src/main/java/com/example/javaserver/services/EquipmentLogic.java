package com.example.javaserver.services;

import com.example.javaserver.dtos.EquipmentDTO;
import com.example.javaserver.entities.Equipment;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentLogic {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public void addEquipment(EquipmentDTO eq, Offer offer) {
        Equipment equipment = new Equipment();
        equipment.setComputer(eq.getComputer());
        equipment.setOffer(offer);
        equipment.setMonitors(eq.getMonitors());
        equipment.setLin(eq.getOperatingSystem().isLin());
        equipment.setMac(eq.getOperatingSystem().isMac());
        equipment.setWin(eq.getOperatingSystem().isWin());

        this.equipmentRepository.save(equipment);
    }
}
