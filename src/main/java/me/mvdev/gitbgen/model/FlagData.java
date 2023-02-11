package me.mvdev.gitbgen.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Stores, modifies the flag name and value.
 * Preference will be given to date value, if is date set to true, when toString is called.
 *
 * @author manleenmavi
 * @version 0.0.2.20230210
 */
public class FlagData {
    private String name;
    private String value;
    private boolean isDate;
    private Date dateValue;

    /**
     * Non arg constructor, initialize the object with default global values.
     */
    public FlagData() {
    }

    /**
     * Accepts the name of the variable to be used in command.
     *
     * @param name Actual name of the variable for bash command.
     */
    public FlagData(String name) {
        this.name = name;
    }

    /**
     * Sets the name, value of the variable with isDate set to false.
     *
     * @param name
     * @param value
     */
    public FlagData(String name, String value) {
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
    public FlagData(String name, Date date) {
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
     * Return the code, date will be in iso format.
     * Preference will be given to date over value. If isDate is false or dateValue is null, then it will return value.
     */
    public String getFlagSyntax() {

        //Checking if isDate and date is not null
        if (isDate && dateValue != null) {
            //Converting to iso format
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            return name + " " + formatter.format(dateValue.toInstant());

        } else if (value != null && !value.isEmpty()) { //Checking value
            return name + " \"" + value + "\""; //Adding quotes to value
        } else {
            return name;
        }

    }
}
