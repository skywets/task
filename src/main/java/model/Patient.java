package model;

import java.time.LocalDate;

public class Patient {
    private String first_name;
    private String middle_name;
    private String last_name;
    private LocalDate birthday;
    private String gender;
    private String phone;

    public Patient(String first_name, String middle_name, String last_name, LocalDate birthday, String gender, String phone) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
    }

    public Patient() {
    }

    public String toString() {
        return "1.ФИО-" + first_name + " " + middle_name + " " + last_name + '\n' +
                "2. Возраст-" + birthday + '\n' +
                "3. Пол-" + gender + '\n' +
                "4. Телефон-" + phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
