public class Appointment {
    private String date;
    private String time;
    private String doctor;
    private String specialization;

    public Appointment(String date, String time, String doctor, String specialization) {
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.specialization = specialization;
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

    @Override
    public String toString() {
        return "Doctor: Dr. " + doctor + ", Specialization: " + specialization +
                ", Date: " + date + ", Time: " + time;
    }
}
