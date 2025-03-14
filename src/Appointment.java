public class Appointment {
    private String date;
    private String time;
    private String doctor;
    private String specialization;
    private String uid;
    private String buid;
    private String status;
    private String treatmentname;
    public Appointment(String date, String time, String doctor, String specialization,String uid,String buid,String status,String treatmentname) {
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.specialization = specialization;
        this.uid = uid;
        this.buid = buid;
        this.status = status;
        this.treatmentname = treatmentname;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getSpecialization() {
        return specialization;
    }
    public String getUid() {
        return uid;
    }
    public String getBuid() {
        return buid;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTreatmentname() {
        return treatmentname;
    }
    @Override
    public String toString() {
        return "Doctor: Dr. " + doctor + ", Specialization: " + specialization +
                ", Date: " + date + ", Time: " + time;
    }
}
