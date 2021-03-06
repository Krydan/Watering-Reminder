package com.example.plantsneedwater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Plant {

    private String plantName;
    private Bitmap plantImage;
    private LocalDate plantLastWatered;
    private int plantPeriodIncrement;
    private Period plantNextWater;
    private String plantNextWaterString;

    LocalDate today = LocalDate.now();

    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Plant(String pName, Bitmap pImage, LocalDate pLastW, int pPeriodIncrement) {
        setPlantName(pName);
        setPlantImage(pImage);
        setPlantLastWatered(pLastW);
        setPlantPeriodIncrement(pPeriodIncrement);

        setPlantNextWater(Period.between(today, pLastW.plusDays(pPeriodIncrement)));
        setPlantNextWaterString(plantNextWater);
    }

    public Plant() { }

    //Getters
    public String getPlantName() { return plantName; }

    public Bitmap getPlantImage() { return plantImage; }

    public LocalDate getPlantLastWatered() { return plantLastWatered; }

    public int getPlantPeriodIncrement() { return plantPeriodIncrement; }

    public Period getPlantNextWater() { return plantNextWater; }

    public String getPlantNextWaterString() { return plantNextWaterString; }



    //Setters
    public void setPlantName(String plantName) { this.plantName = plantName; }

    public void setPlantImage(Bitmap plantImage) { this.plantImage = plantImage; }

    public void setPlantLastWatered(LocalDate plantLastWatered) { this.plantLastWatered = plantLastWatered; }

    public void setPlantPeriodIncrement(int plantPeriodIncrement) { this.plantPeriodIncrement = plantPeriodIncrement; }

    public void setPlantNextWater(Period plantNextWater) { this.plantNextWater = plantNextWater; }

    public void setPlantNextWaterString(Period plantNextWater) {

        int months = plantNextWater.getMonths();
        int days = plantNextWater.getDays();

        if (months == 0 && days >= 1) {
            plantNextWaterString = days + " Days until next water";
        }
        else if (months == 1) {
            plantNextWaterString = months + " Month, " + days + " Days until next water";
        }
        else if (months == 0 && days <= 0) {
            plantNextWaterString = "Water " + getPlantName() + " today!";
        }
        else {
            plantNextWaterString = months + " Months, " + days + " Days until next water";
        }
    }


    public void waterPlant() {
        setPlantLastWatered(today);
        setPlantNextWater(Period.between(today, plantLastWatered.plusDays(plantPeriodIncrement)));
        setPlantNextWaterString(getPlantNextWater());
    }

}
