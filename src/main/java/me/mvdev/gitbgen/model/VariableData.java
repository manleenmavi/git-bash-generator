package me.mvdev.gitbgen.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Stores, modifies the variable name and value.
 * Preference will be given to date value, if is date set to true, when toString is called.
 *
 * @author manleenmavi
 * @version 0.0.1.20230208
 */
public class VariableData {
    private String name;
    private String value;
    private boolean isDate;
    private Date dateValue;

    /**
     * Non arg constructor, initialize the object with default global values.
     */
    public VariableData() {
    }

    /**
     * Accepts the name of the variable to be used in command.
     *
     * @param name Actual name of the variable for bash command.
     */
    public VariableData(String name) {
        this.name = name;
    }

    /**
     * Sets the name, value of the variable with isDate set to false.
     *
     * @param name
     * @param value
     */
    public VariableData(String name, String value) {
        this.name = name;
        this.value = value;
        this.isDate = false;
    }

    /**
     * Set the name, and the date value of the variable. isDate will be set to true.
     *
     * @param name
     * @param date
     */
    public VariableData(String name, Date date) {
        this.name = name;
        this.dateValue = date;
        this.isDate = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDate() {
        return isDate;
    }

    public void setIsDate(boolean date) {
        isDate = date;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    /**
     * Return the variable syntax, date will be in iso format.
     * Preference will be given to date over value. If isDate is false or dateValue is null, then it will return value.
     */
    public String getVariableSyntax() {

        //Checking if isDate and date is not null
        if (isDate && dateValue != null) {
            //Converting to iso format
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            return name + " " + formatter.format(dateValue.toInstant());

        } else if (value != null && !value.isEmpty()) { //Checking value
            return name + " " + value;
        } else {
            return name;
        }

    }

}
