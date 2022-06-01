import model.Patient;
import model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String TAG_MAIN_NAME = "name";
    private static final String TAG_PATIENT = "patients";
    private static final String TAG_ELEMENT = "patient";
    private static final String TAG_FIRST_NAME = "first_name";
    private static final String TAG_MIDDLE_NAME = "middle_name";
    private static final String TAG_LAST_NAME = "last_name";
    private static final String TAG_BIRTHDAY = "birthday";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";

    private List<Patient> patientList;

    public List<Patient> getPatientList() {
        return patientList;
    }

    public Root parse(String scanner) {
        String fileName = scanner;
        Root root = new Root();
        Document doc;

        try {
            doc = buildDocument(fileName);
        } catch (Exception e) {
            System.out.println("Open parsing error! " + e.toString());
            return null;
        }

        Node rootNode = doc.getFirstChild();

        NodeList nodeList = rootNode.getChildNodes();

        String mainName = null;
        Node patientNode = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (nodeList.item(i).getNodeName()) {
                case TAG_MAIN_NAME:
                    mainName = nodeList.item(i).getTextContent();
                    break;
                case TAG_PATIENT:
                    patientNode = nodeList.item(i);
                    break;
            }
        }


        if (patientNode == null) {
            return null;
        }

        List<Patient> patientList = parsePatient(patientNode);
        root.setPatientList(patientList);
        root.setName(mainName);
        return root;
    }

    private Document buildDocument(String fileNmae) throws Exception {
        File file = new File(fileNmae);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

    private List<Patient> parsePatient(Node patientNode) {
        patientList = new ArrayList<>();
        NodeList patientChilds = patientNode.getChildNodes();

        for (int i = 0; i < patientChilds.getLength(); i++) {
            if (patientChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!patientChilds.item(i).getNodeName().equals(TAG_ELEMENT)) {
                continue;
            }
            Patient patient = parseElement(patientChilds.item(i));
            patientList.add(patient);
        }
        return patientList;
    }

    private Patient parseElement(Node elementNode) {
        String first_name = "";
        String middle_name = "";
        String last_name = "";
        LocalDate birthday = null;
        String gender = "";
        String phone = "";
        NodeList elementChilds = elementNode.getChildNodes();
        for (int j = 0; j < elementChilds.getLength(); j++) {
            if (elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (elementChilds.item(j).getNodeName()) {
                case TAG_FIRST_NAME:
                    first_name = elementChilds.item(j).getTextContent();
                    break;
                case TAG_MIDDLE_NAME:
                    middle_name = elementChilds.item(j).getTextContent();
                    break;
                case TAG_LAST_NAME:
                    last_name = elementChilds.item(j).getTextContent();
                    break;
                case TAG_BIRTHDAY:
                    birthday = LocalDate.parse(elementChilds.item(j).getTextContent());
                    break;
                case TAG_GENDER:
                    gender = elementChilds.item(j).getTextContent();
                    break;
                case TAG_PHONE:
                    phone = elementChilds.item(j).getTextContent();
                    break;
            }
        }
        Patient patient = new Patient(first_name, middle_name, last_name, birthday, gender, phone);
        return patient;
    }
}
