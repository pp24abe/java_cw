public class Appointment {
    private String date;
    private String time;
    private String doctor;
    private String specialization;
    private String buid;
    private String status;
    private String treatmentname;
    private int changed;
    private int pid;

    public Appointment(String date, String time, String doctor, String specialization, int pid, String buid, String status, String treatmentname, int changed) {
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.specialization = specialization;
        this.buid = buid;
        this.status = status;
        this.treatmentname = treatmentname;
        this.changed = changed;
        this.pid = pid;
    }

    public void changed(int changed) {
        this.changed = changed + 2;
    }

    // Update date and time for rescheduling
    public void reschedule(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
        this.changed++;
        this.status = "Rescheduled";
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

    public int getchanged() {
        return changed;
    }

    public int getpid() {
        return pid;
    }

    @Override
    public String toString() {
        return "Doctor: Dr. " + doctor + ", Specialization: " + specialization +
                ", Date: " + date + ", Time: " + time + ", Status: " + status;
    }
}