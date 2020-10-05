package com.azkz.lambda.function

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.S3Event
import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.usecase.csv.AnnualIncomeStatementCSVReader
import com.azkz.usecase.csv.AnnualIncomeStatementCSVReaderImpl
import java.io.File

class SampleFunction : RequestHandler<S3Event, String> {

    /**
     * Handles a Lambda Function request
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return The Lambda Function output
     */
    override fun handleRequest(input: S3Event?, context: Context?): String {
        if (input == null || context == null)
            return "NULLです"

        val logger: LambdaLogger = context.logger

        val record = input.records.get(0)
//        val bucketName:String = record.s3.bucket.name
//        val bucketKey:String = record.s3.`object`.key
//        val s3Client = AmazonS3ClientBuilder.defaultClient()
//        val s3Object = s3Client.getObject(bucketName,bucketKey)
//        val file = File(s3Object.key)

        // 直指定でできるなら、上の記述はいらないと思う
        val file = File(record.s3.`object`.key)

        logger.log("FILENAME:${file.name}")

        val reader: AnnualIncomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(file)
        val breakdowns: Set<MonthlyAccountBreakdown> = reader.parseToBreakdowns()

        var returnStr: String = ""
        breakdowns.forEach {
            val str = """
                    |YEAR MONTH:${it.yearMonth}
                    |ACCOUNT TITLE:${it.accountTitle.japanese}
                    |AMOUNT:${it.amount}
                    """

            logger.log(str)

            returnStr += str
        }

        return returnStr
    }
}