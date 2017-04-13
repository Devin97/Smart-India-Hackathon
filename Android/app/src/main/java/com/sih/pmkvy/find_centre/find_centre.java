package com.sih.pmkvy.find_centre;



/**
 * Created by ASUS on 2/25/2017.
 */

public class find_centre {
    private String centre_name;
    private String centre_address;
    private String centre_phone;
    private String centre_info;
   // private String call_button;

   /* public String getCall_button() {
        return call_button;
    }

    public void setCall_button(String call_button) {
        this.call_button = call_button;
    }

   */
   public find_centre(String centre_name, String centre_address, String centre_phone, String centre_info) {
        this.centre_name = centre_name;
        this.centre_address = centre_address;
        this.centre_phone = centre_phone;
        this.centre_info=centre_info;
        //this.call_button=call_button;

    }

    public String getCentre_name() {
        return centre_name;
    }

    public String getCentre_info() {
        return centre_info;
    }

    public void setCentre_info(String centre_info) {
        this.centre_info = centre_info;
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

    public String getCentre_phone() {
        return centre_phone;
    }

    public void setCentre_phone(String centre_phone) {
        this.centre_phone = centre_phone;
    }
}
