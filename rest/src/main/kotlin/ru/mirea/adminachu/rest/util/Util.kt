package ru.mirea.adminachu.rest.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

fun getSpringUserUserName(): String {
    val authentication = SecurityContextHolder.getContext().authentication
    val userDetails = authentication.principal as UserDetails
    return userDetails.username
}
