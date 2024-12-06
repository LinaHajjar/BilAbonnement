package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.Skader;
import com.example.bilabonnement.Repository.SkaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkaderService {

   @Autowired
   SkaderRepo skaderRepo;


   public List<Skader> getAllSkader() {
       return skaderRepo.getAllSkader();
   }

   public void addSkader(Skader skader){
       skaderRepo.addSkader(skader);
   }

   public List<Skader> getSkaderById(int lejekontraktid) {
       return skaderRepo.seSkaderById(lejekontraktid);
   }







}
