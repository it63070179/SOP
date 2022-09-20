package com.example.lab4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;


@Route(value = "index2")
public class CashierView extends VerticalLayout {
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
    private Button btn1;

    public CashierView(){
        tf1 = new TextField("เงินทอน");
        tf2 = new TextField();
        tf3 = new TextField();
        tf4 = new TextField();
        tf5 = new TextField();
        tf6 = new TextField();
        tf7 = new TextField();
        tf8 = new TextField();
        btn1 = new Button("คำนวณเงินทอน");

        tf1.setPrefixComponent(new Span("$"));
        tf2.setPrefixComponent(new Span("$1000:"));
        tf3.setPrefixComponent(new Span("$500:"));
        tf4.setPrefixComponent(new Span("$100:"));
        tf5.setPrefixComponent(new Span("$20:"));
        tf6.setPrefixComponent(new Span("$10:"));
        tf7.setPrefixComponent(new Span("$5:"));
        tf8.setPrefixComponent(new Span("$1:"));

        add(tf1, btn1, tf2, tf3, tf4, tf5, tf6, tf7, tf8);

        btn1.addClickListener(event ->{
            int money = Integer.parseInt(tf1.getValue());
            Change out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getChange/"+money)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            tf2.setValue(String.valueOf(out.getB1000()));
            tf3.setValue(String.valueOf(out.getB500()));
            tf4.setValue(String.valueOf(out.getB100()));
            tf5.setValue(String.valueOf(out.getB20()));
            tf6.setValue(String.valueOf(out.getB10()));
            tf7.setValue(String.valueOf(out.getB5()));
            tf8.setValue(String.valueOf(out.getB1()));
        });
    }
}
