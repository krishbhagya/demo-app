package com.example.demo.domain;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="spices")
public class Spice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int spiceId;

    private String name;
    private String specialName;
    private Date packetDate;
    private Date expDate;
    private double price;
    private double weight;

    public Spice(){}

    public Spice(String name, String specialName, double price,
                 double weight, Date expDate, Date packetDate
    ){
        this.name = name;
        this.specialName = specialName;
        this.price = price;
        this.weight = weight;
        this.expDate = expDate;
        this.packetDate = packetDate;
    }
    public int getSpiceId() {
        return spiceId;
    }

    public void setSpiceId(int spiceId) {
        this.spiceId = spiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public Date getPacketDate() {
        return packetDate;
    }

    public void setPacketDate(Date packetDate) {
        this.packetDate = packetDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString(){
        return "";

    }
}
