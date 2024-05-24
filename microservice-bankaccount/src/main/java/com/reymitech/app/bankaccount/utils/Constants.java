package com.reymitech.app.bankaccount.utils;

public class Constants {

    // Exceptions for Account
    public static final String LIMIT_ACCOUNT_CREATED = "Límite de cuenta alcanzado para el tipo de cliente [%s]";
    public static final String EXCEPTION_BUSINESS_ACCOUNT = "No puede crear cuentas de tipo ahorro o de plazo fijo, para el tipo de cliente [%s]";
    public static final String EXCEPTION_NOT_FOUND_ACCOUNT = "No se encontró la cuenta [%s]";
    public static final String EXCEPTION_NOT_FOUND_CUSTOMER = "No se encontró el cliente [%s]";
    public static final String EXCEPTION_NOT_FOUND_PERSONAL_ACCOUNT = "No se encontró la cuenta personal [%s]";
    public static final String EXCEPTION_NOT_FOUND_BUSINESS_ACCOUNT = "No se encontró la cuenta empresarial [%s]";
    public static final String EXCEPTION_NOT_FOUND_CUSTOMER_ACCOUNT_BY_CUSTOMER_ID = "No se encontró la cuenta del cliente [%s]";
    public static final String EXCEPTION_INVALID_ACCOUNT_TYPE = "El tipo de cuenta no es valido: [%s]";
    public static final String EXCEPTION_INVALID_CUSTOMER_TYPE_PERSONAL = "El tipo de cliente no es valido para la cuenta personal: [%s]";
    public static final String EXCEPTION_INVALID_CUSTOMER_TYPE_BUSINESS = "El tipo de cliente no es valido para la cuenta empresarial: [%s]";
    public static final String EXCEPTION_NOT_FOUND_SIGNATORY = "No se encontró el firmante [%s]";

    // Exceptions for Bank
    public static final String EXCEPTION_BANK_NOT_FOUND = "No se encontró el banco activo";
    public static final String EXCEPTION_BANK_ALREADY_EXISTS = "El banco ya existe";
    public static final String EXCEPTION_BANK_CREATE = "No se pudo crear el banco [%s]";
    public static final String EXCEPTION_BANK_UPDATE = "No se pudo actualizar el banco [%s]";
    public static final String EXCEPTION_BANK_DELETE = "No se pudo desactivar el banco";

    // Constants for credit card
    public static final String BIN_CREDIT_CARD_PERSONAL = "295963";
    public static final String BIN_CREDIT_CARD_BUSINESS = "253780";
    public static final Integer MAX_LENGTH_CREDIT_CARD = 16;
}
