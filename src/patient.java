import java.util.Objects;

public class patient {


    private String pat_name;
    private  String pat_id;
    private  String pat_address;
    private  String pat_phno;

    public patient(String name, String address, String phno,String pat_id) {
        this.pat_name = name;
        this.pat_id = pat_id;
        this.pat_address = address;
        this.pat_phno = phno;


    }
    public  void set_pat_name(String pat_name){
        this.pat_name=pat_name;
    }
    public  void set_pat_id(String pat_id){
        this.pat_id=pat_id;
    }
    public  void set_pat_address(String pat_address){
        this.pat_address=pat_address;
    }
    public  void set_pat_phno(String pat_phno){
        this.pat_phno=pat_phno;
    }
    public String get_pat_name() {
        return pat_name;
    }
    public String get_pat_id() {
        return pat_id;
    }
    public String get_pat_address() {
        return pat_address;
    }
    public String get_pat_phno() {
        return pat_phno;
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



