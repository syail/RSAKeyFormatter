package com.syail

import java.math.BigInteger
import java.security.KeyFactory
import java.security.spec.RSAPublicKeySpec
import java.util.Base64
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val rsaFormatterArgs = loadArguments(args)
    val keySpec = RSAPublicKeySpec(
        BigInteger(rsaFormatterArgs.moduleHex, 16),
        BigInteger(rsaFormatterArgs.exponentHex, 16)
    );
    val key = KeyFactory.getInstance("RSA").generatePublic(keySpec);

    println("-----BEGIN PUBLIC KEY-----")
    println(String((Base64.getEncoder().encode(key.encoded))))
    println("-----END PUBLIC KEY-----")
}

fun loadArguments(args: Array<String>): RSAFormatterArgs {
    if (args.size != 2) {
        println("Usage: java -jar <jarfile> <moduleHex> <exponentHex>")
        exitProcess(1)
    }
    return RSAFormatterArgs(args[0], args[1])
}

data class RSAFormatterArgs(
    var moduleHex: String,
    var exponentHex: String
)