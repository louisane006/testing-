package com.example.malumukendi.assignment6activities.domain;

import android.content.Intent;
import android.os.IBinder;

import java.io.Serializable;

/**
 * Created by louisane Malu on images3/28/2016.
 */
public abstract class Designer implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long identification;
    private String name;
    private String surname;
    private String taskNum;

    private Brochure broch;
    private Brand brand;

    public Designer(Builder b){
        this.name = b.name;
        this.surname = b.surname;
        this.taskNum = b.taskNum;
        this.identification = b.identification;
        this.broch = b.broch;
        this.brand = b.brand;
    }

    public Designer() {

    }

    public abstract IBinder onBind(Intent intent);

    public static class Builder{
        private Long identification;
        private String name;
        private String surname;
        private String taskNum;
        private Brochure broch;
        private Brand brand;

        public Builder(String taskNum) {
            this.taskNum = taskNum;
        }

        public Builder() {

        }

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder identification(Long identification){
            this.identification = identification;
            return this;
        }
        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }
        public Builder brand(Brand brand){
            this.brand = brand;
            return this;
        }
        public Builder brochure(Brochure broch){
            this.broch = broch;
            return this;
        }
        public Designer design(){
            return new Designer(this) {
                @Override
                public IBinder onBind(Intent intent) {
                    return null;
                }
            };
        }
        public Builder Designer(Designer designer){
            identification = designer.getIdentification();
            name = designer.getName();
            surname = designer.getSurname();
            taskNum = designer.getTaskNum();
            broch = designer.getBrochure();
            brand = designer.getFrame();
            return this;
        }
        public Designer build(){
            return new Designer(this) {
                @Override
                public IBinder onBind(Intent intent) {
                    return null;
                }
            };
        }

        public Builder taskNumber(String t) {
            return this;
        }

        public Builder copy(Designer value) {
            this.identification = value.identification;
            this.name=value.name;
            this.surname=value.surname;
            this.taskNum= value.taskNum;
            return  this;
        }
    }
    public void setIdentification(Long identification) {
        this.identification = identification;
    }
    public Long getIdentification() {
        return identification;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }
    public void setTaskNumber(String taskNumber) {
        this.taskNum = taskNumber;
    }
    public String getTaskNum() {
        return taskNum;
    }
    public void setBrochure(Brochure broch) {
        this.broch = broch;
    }
    public Brochure getBrochure() {
        return broch;
    }
    public void setFrame(Brand frame) {
        this.brand = frame;
    }
    public Brand getFrame() {
        return brand;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identification != null ? identification.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Designer)) {
            return false;
        }
        Designer other = (Designer) object;
        if ((this.identification == null && other.identification != null) || (this.identification != null && !this.identification.equals(other.identification)))
        {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Designer {" + "Identification =" + identification + ", Name =" + name + ", Surname =" + surname + ", Task Number =" + taskNum + '}';
    }
}
