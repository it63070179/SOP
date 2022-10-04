package com.example.lab6.view;

import com.example.lab6.pojo.Wizards;
import com.example.lab6.pojo.Wizard;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField tf1, tf2;
    private RadioButtonGroup<String> rb1;
    private ComboBox<String> cb1, cb2, cb3;
    private Button btn1, btn2, btn3, btn4, btn5;

    private Wizards wizards;
    private int index = -1;

    public MainWizardView() {
        tf1 = new TextField();
        tf2 = new TextField("Dollars");
        rb1 = new RadioButtonGroup<>("Gender :");
        cb1 = new ComboBox<>();
        cb2 = new ComboBox<>();
        cb3 = new ComboBox<>();
        btn1 = new Button("<<");
        btn2 = new Button("Create");
        btn3 = new Button("Update");
        btn4 = new Button("Delete");
        btn5 = new Button(">>");
        HorizontalLayout hl = new HorizontalLayout();

        tf1.setPlaceholder("Fullname");
        tf2.setPrefixComponent(new Span("$"));
        rb1.setItems("Male", "Female");
        cb1.setPlaceholder("Position");
        cb1.setItems("Student", "Teacher");
        cb2.setPlaceholder("School");
        cb2.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        cb3.setPlaceholder("House");
        cb3.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slyther");
        hl.add(btn1, btn2, btn3, btn4, btn5);
        add(tf1, rb1, cb1, tf2, cb2, cb3, hl);

        this.wizards = this.getWizard();

        btn1.addClickListener(event -> {

            if (this.index >= 1){
                this.index -= 1;
                this.callWizard();
            }else if (this.index <= 0){
                this.index = 0;
            }

        });

        btn2.addClickListener(event -> {
            Wizard wizard = new Wizard();
            wizard.setSex(rb1.getValue().equals("Male")?"m":"f");
            wizard.setName(tf1.getValue());
            wizard.setSchool(cb2.getValue());
            wizard.setHouse(cb3.getValue());
            wizard.setMoney(Integer.parseInt(tf2.getValue()));
            wizard.setPosition(cb1.getValue());

            Boolean out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addWizard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(wizard)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (out) {
                new Notification("Wizard has been Created", 1000).open();
                this.wizards = this.getWizard();
                this.index += 1;
                this.callWizard();
            }
            else {
                new Notification("Wizard not has been Created", 1000).open();
            }
        });

        btn3.addClickListener(event -> {
            Wizard wizard = this.wizards.getModel().get(this.index);
            wizard.setSex(rb1.getValue().equals("Male")?"m":"f");
            wizard.setName(tf1.getValue());
            wizard.setSchool(cb2.getValue());
            wizard.setHouse(cb3.getValue());
            wizard.setMoney(Integer.parseInt(tf2.getValue()));
            wizard.setPosition(cb1.getValue());

            Boolean out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/updateWizard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(wizard)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (out) {
                new Notification("Wizard has been Updated", 1000).open();
                this.wizards = this.getWizard();
            }
            else {
                new Notification("Wizard not has been Updated", 1000).open();
            }
        });


        btn4.addClickListener(event -> {
            Wizard wizard = this.wizards.getModel().get(this.index);
            Boolean out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(wizard)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (out) {
                new Notification("Wizard has been Removed", 1000).open();
                this.index -= 1;
                this.wizards = this.getWizard();
                this.callWizard();
            }
            else {
                new Notification("Wizard not has been Removed", 1000).open();
            }
        });

        btn5.addClickListener(event -> {
            this.index += 1;
            if (this.index >= this.wizards.getModel().size()-1){
                this.index = this.wizards.getModel().size()-1;
            }

            this.callWizard();
        });

    }

    public Wizards getWizard() {
        Wizards out = WebClient.create()
                .get()
                .uri("http://localhost:8080/wizards")
                .retrieve()
                .bodyToMono(Wizards.class)
                .block();
        return out;
    }
    public void callWizard(){
        Wizard wizards =  this.wizards.getModel().get(this.index);
        tf1.setValue(wizards.getName());
        if (wizards.getSex().equals("m")){
            rb1.setValue("Male");
        }else{
            rb1.setValue("Female");
        }

        cb1.setValue(wizards.getPosition());
        tf2.setValue(String.valueOf(wizards.getMoney()));
        cb2.setValue(wizards.getSchool());
        cb3.setValue(wizards.getHouse());
    }
}
