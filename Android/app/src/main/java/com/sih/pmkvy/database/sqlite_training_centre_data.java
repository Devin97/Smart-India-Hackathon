package com.sih.pmkvy.database;

/**
 * Created by ASUS on 3/4/2017.
 */

public class sqlite_training_centre_data {
    int centre_phone_no;
    String centre_name;
    String centre_address;
    String centre_info;
    int centre_ID;


    public sqlite_training_centre_data( String centre_info, String centre_address, String centre_name, int centre_phone_no) {
        //this.centre_ID = centre_ID;
        this.centre_info = centre_info;
        this.centre_address = centre_address;
        this.centre_name = centre_name;
        this.centre_phone_no = centre_phone_no;
    }

    public sqlite_training_centre_data() {

    }


    public int getCentre_ID() {
        return centre_ID;
    }

    public void setCentre_ID(int centre_ID) {
        this.centre_ID = centre_ID;
    }

    public int getCentre_phone_no() {

        return centre_phone_no;
    }

    public void setCentre_phone_no(int centre_phone_no) {
        this.centre_phone_no = centre_phone_no;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public String getCentre_address() {
        return centre_address;
    }

    public void setCentre_address(String centre_address) {
        this.centre_address = centre_address;
    }

    public String getCentre_info() {
        return centre_info;
    }

    public void setCentre_info(String centre_info) {
        this.centre_info = centre_info;
    }
}
