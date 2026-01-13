package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML private TextField tfEmployerName;
    @FXML private TextField tfEmpRef;
    @FXML private TextField tfEmpName;
    @FXML private TextField tfEmpAddress;
    @FXML private TextField tfPostCode;
    @FXML private TextField tfICW;             // Inner City Weighting
    @FXML private TextField tfBasicSalary;
    @FXML private TextField tfOvertime;
    @FXML private TextField tfGrossPay;
    @FXML private TextField tfPensionablePay;
    @FXML private TextField tfNetPay;

    // RIGHT SECTION (Pay + Deductions)
    @FXML private TextField tfPayDate;
    @FXML private ComboBox<String> cbTaxPeriod;
    @FXML private ComboBox<String> cbTaxCode;
    @FXML private ComboBox<String> cbNICode;
    @FXML private TextField tfNINumber;
    @FXML private TextField tfTax;
    @FXML private TextField tfPension;
    @FXML private TextField tfStudentLoan;
    @FXML private TextField tfNIPayment;
    @FXML private TextField tfTaxablePay;
    @FXML private TextField tfDeductions;

    // PAYSLIP AREA + BUTTONS
    @FXML private TextArea taPayslip;
    @FXML private Button btnNetWages;
    @FXML private Button btnPaySlip;
    @FXML private Button btnReset;
    @FXML private Button btnExit;


    @FXML
    private void initialize() {
        cbTaxPeriod.getItems().addAll(
                "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12"
        );

        cbTaxPeriod.setPromptText("Select Period");

        cbTaxCode.getItems().addAll(
                "1257L", "BR", "D0", "D1", "NT"
        );
        cbTaxCode.setPromptText("Select Tax Code");

        cbNICode.getItems().addAll(
                "A", "B", "C", "H", "J", "M", "Z"
        );
        cbNICode.setPromptText("Select NI Code");
    }

    @FXML
    private void handleNetWages() {
        // This method calculates Gross Pay, Deductions, and Net Pay based on input fields.

        double basicSalary   = parseDoubleSafe(tfBasicSalary.getText());
        double overtime      = parseDoubleSafe(tfOvertime.getText());
        double icw           = parseDoubleSafe(tfICW.getText());// Inner City Weighting
        double tax           = parseDoubleSafe(tfTax.getText());
        double pension       = parseDoubleSafe(tfPension.getText());
        double studentLoan   = parseDoubleSafe(tfStudentLoan.getText());
        double niPayment     = parseDoubleSafe(tfNIPayment.getText());

        // Gross = basic + overtime + inner city weighting
        double grossPay = basicSalary + overtime + icw;

        // Deductions = tax + pension + student loan + national insurance
        double totalDeductions = tax + pension + studentLoan + niPayment;

        // Net Pay = Gross Pay - Total Deductions
        double netPay = grossPay - totalDeductions;

        tfGrossPay.setText(String.format("%.2f", grossPay));
        tfDeductions.setText(String.format("%.2f", totalDeductions));
        tfNetPay.setText(String.format("%.2f", netPay));
        tfTaxablePay.setText(String.format("%.2f", grossPay)); // optional placeholder
        tfPensionablePay.setText(String.format("%.2f", grossPay)); // optional placeholder
    }

    @FXML
    private void handlePaySlip() {

        String payslip = String.format("""
            %s
            Emp.Ref: %s              Date: %s
            Emp.Name: %s             Paid: %.2f
            ========================================================
            Inner City: %.2f                  Student Loan: %.2f
            Basic Salary: %.2f                NI Payment: %.2f
            Over Time: %.2f                   Pensionable Pay: %.2f
            ========================================================
            Gross Pay: %.2f                      Taxable Pay: %.2f
            Pension Paid: %.2f                    Deductions: %.2f
            Net Pay: %.2f                            Tax paid: %.2f
            """,

                tfEmployerName.getText(),
                tfEmpRef.getText(),
                tfPayDate.getText(),
                tfEmpName.getText(),
                parseDoubleSafe(tfNetPay.getText()),

                parseDoubleSafe(tfICW.getText()),
                parseDoubleSafe(tfStudentLoan.getText()),
                parseDoubleSafe(tfBasicSalary.getText()),
                parseDoubleSafe(tfNIPayment.getText()),
                parseDoubleSafe(tfOvertime.getText()),
                parseDoubleSafe(tfPensionablePay.getText()),

                parseDoubleSafe(tfGrossPay.getText()),
                parseDoubleSafe(tfTaxablePay.getText()),
                parseDoubleSafe(tfPension.getText()),
                parseDoubleSafe(tfDeductions.getText()),
                parseDoubleSafe(tfNetPay.getText()),
                parseDoubleSafe(tfTax.getText())
        );

        taPayslip.setText(payslip);
    }

    @FXML
    private void handleReset() {

        tfEmployerName.clear();
        tfEmpRef.clear();
        tfEmpName.clear();
        tfEmpAddress.clear();
        tfPostCode.clear();
        tfICW.clear();
        tfBasicSalary.clear();
        tfOvertime.clear();
        tfGrossPay.clear();
        tfPensionablePay.clear();
        tfNetPay.clear();

        tfPayDate.clear();
        cbTaxPeriod.getSelectionModel().clearSelection();
        cbTaxCode.getSelectionModel().clearSelection();
        cbNICode.getSelectionModel().clearSelection();
        tfNINumber.clear();
        tfTax.clear();
        tfPension.clear();
        tfStudentLoan.clear();
        tfNIPayment.clear();
        tfTaxablePay.clear();
        tfDeductions.clear();

        taPayslip.clear();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }


    private double parseDoubleSafe(String text) {
        try {
            return Double.parseDouble(text.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}