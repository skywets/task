import model.Patient;
import model.Root;

import java.util.Comparator;
import java.util.List;

public class SortingByData {
    Root root;

    public SortingByData(Root root) {
        this.root = root;
    }

    public List<Patient> sortByName(Root root) {
        List<Patient> getByName = root.getPatientList();
        getByName.sort(new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.getLast_name(), o2.getLast_name());
            }
        });
        return getByName;

    }

    public List<Patient> sortByBirthday(Root root) {
        List<Patient> getByBirthday = root.getPatientList();
        getByBirthday.sort(new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        });
        return getByBirthday;

    }
}
