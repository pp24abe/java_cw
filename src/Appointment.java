public class Appointment {
    private String date;
    private String time;
    private String doctor;
    private String specialization;
    private String uid;

    public Appointment(String date, String time, String doctor, String specialization,String uid) {
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.specialization = specialization;
        this.uid = uid;
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
    @Override
    public String toString() {
        return "Doctor: Dr. " + doctor + ", Specialization: " + specialization +
                ", Date: " + date + ", Time: " + time;
    }
}
