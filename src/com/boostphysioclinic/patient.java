package com.boostphysioclinic;

import java.util.Objects;

public class patient {


    private String pat_name;
    int  pat_id;
    private  String pat_address;
    private  String pat_phno;
    private String biud;
    public patient(String name, String address, String phno,int pat_id,String buid) {
        this.pat_name = name;
        this.pat_id = pat_id;
        this.pat_address = address;
        this.pat_phno = phno;
        this.biud = buid;
    }



    public String get_pat_name() {
        return pat_name;
    }
    public int get_pat_id() {
        return pat_id;
    }
    public String get_pat_address() {
        return pat_address;
    }
    public String getBiud(){
        return biud;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        patient other = (patient) obj;
        return Objects.equals(pat_name, other.pat_name) && Objects.equals(pat_phno, other.pat_phno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pat_name, pat_phno);
    }
}



