package com.devmode.clientservice.client;

import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

/**
 * Валидатор клиентов.
 * <br>В случае надобности добавления кастомной валидации её нужно добавлять иенно в этот класс.
 */
@Component
public class ClientValidator extends CustomValidatorBean {

}
