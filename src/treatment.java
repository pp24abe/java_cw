import java.util.Date;

public class treatment {

    private String treatment_name;
    private Date date;
    private String doc_name;

    public void Neural_mobilisation(){
        treatment_name="Neural mobilisation";
    }
    public void Acupuncture() {
        treatment_name="Acupuncture";
    }
    public void Massage() {
        treatment_name="Massage";
    }
    public void Mobilisation(){
        treatment_name="Mobilisation of the spine and joints";
    }
    public void Pool_rehabilitation(){
        treatment_name="Pool rehabilitation";
    }

    public String getTreatment_name() {
        return treatment_name;
    }
}
