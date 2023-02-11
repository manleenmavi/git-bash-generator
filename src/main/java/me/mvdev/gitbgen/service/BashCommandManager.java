package me.mvdev.gitbgen.service;

import me.mvdev.gitbgen.model.FlagData;
import me.mvdev.gitbgen.model.VariableData;

import java.util.ArrayList;

/**
 * Stores, modifies bash command.
 * VariableData, FlagData will be stored
 *
 * @author manleenmavi
 * @version 0.0.2.20230210
 */
public class BashCommandManager {
    String syntax;
    ArrayList<VariableData> variables; //Variable List
    ArrayList<FlagData> flags;         // Flag List

    public BashCommandManager() {
        syntax = "";
        variables = new ArrayList<>();
        flags = new ArrayList<>();
    }

    public BashCommandManager(String syntax) {
        this();
        this.syntax = syntax;
    }

    public BashCommandManager(String syntax, ArrayList<VariableData> variables, ArrayList<FlagData> flags) {
        this.syntax = syntax;
        this.variables = variables;
        this.flags = flags;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public ArrayList<VariableData> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<VariableData> variables) {
        this.variables = variables;
    }

    public ArrayList<FlagData> getFlags() {
        return flags;
    }

    public void setFlags(ArrayList<FlagData> flags) {
        this.flags = flags;
    }

    /**
     * Get the variable on the basis of index
     * @param index Index of variable we want
     * @return VariabeData from list
     */
    public VariableData getVariableData(int index) {
        return variables.get(index);
    }

    /**
     * Get the flag on the basis of index
     * @param index Index of flag we want
     * @return FlagData from list
     */
    public FlagData getFlagData(int index) {
        return flags.get(index);
    }

    /**
     * Add variable to the list
     * @param variableData VariableData to be added
     */
    public void addVariable(VariableData variableData) {
        variables.add(variableData);
    }

    /**
     * Add flag to the list
     * @param flagData FlagData to be added
     */
    public void addFlag(FlagData flagData) {
        flags.add(flagData);
    }

    /**
     * Remove variable from the list
     * @param index Index of variable to be removed
     */
    public void removeVariable(int index) {
        variables.remove(index);
    }

    /**
     * Remove flag from the list
     * @param index Index of flag to be removed
     */
    public void removeFlag(int index) {
        flags.remove(index);
    }

    /**
     * Get the bash command
     * @return Bash command
     */
    public String getBashCommand() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("git");

        if (syntax != null && !syntax.isEmpty()) {
            stringBuilder.append(" ").append(syntax);
        }

        for (VariableData variableData : variables) {
            stringBuilder.append(" ").append(variableData.getVariableSyntax());
        }
        for (FlagData flagData : flags) {
            stringBuilder.append(" ").append(flagData.getFlagSyntax());
        }
        return stringBuilder.toString();
    }
}
