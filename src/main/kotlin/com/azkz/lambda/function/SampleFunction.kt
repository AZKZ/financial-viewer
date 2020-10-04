package com.azkz.lambda.function

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.azkz.usecase.csv.AnnualIncomeStatementCSVReader
import com.azkz.usecase.csv.AnnualIncomeStatementCSVReaderImpl
import java.io.File

class SampleFunction : RequestHandler<File, String> {

    /**
     * Handles a Lambda Function request
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    override fun handleRequest(input: File?, context: Context?): String {
        if (input == null)
            return "NULLです"

        val reader: AnnualIncomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(input)

        return ""
    }
}