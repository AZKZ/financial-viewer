package com.azkz.lambda.handler

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.S3Event
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.azkz.domain.accountbreakdown.MonthlyAccountBreakdown
import com.azkz.usecase.csv.AnnualIncomeStatementCSVReader
import com.azkz.usecase.csv.AnnualIncomeStatementCSVReaderImpl

/**
 * S3イベントで起動するLambda関数のサンプルハンドラクラス
 *
 * 損益計算書（年間推移）のCSVファイルを読み込む
 */
class IncomeStatementCsvReadHandler : RequestHandler<S3Event, String> {

    /**
     * Handles a Lambda Function request
     * @param input S3イベント
     * @param context Lambda関数の実行環境コンテキスト
     * @return The Lambda Function output
     */
    override fun handleRequest(input: S3Event?, context: Context?): String {
        if (input == null || context == null)
            return "NULLです"

        val logger: LambdaLogger = context.logger

        val record = input.records[0]
        val bucketName: String = record.s3.bucket.name

        val bucketKey: String = record.s3.`object`.urlDecodedKey
        val s3Client = AmazonS3ClientBuilder.defaultClient()
        val s3Object = s3Client.getObject(bucketName, bucketKey)

        // 「.csv」が含まれていないものは処理しない
        if (!s3Object.key.contains(".csv")){
            return "422 Unprocessable Entity"
        }

        val inputStream = s3Object.objectContent
        val reader: AnnualIncomeStatementCSVReader = AnnualIncomeStatementCSVReaderImpl(inputStream)
        val breakdowns: Set<MonthlyAccountBreakdown> = reader.parseToBreakdowns()

        var str = ""
        breakdowns.forEach {
            str += """
                    YEAR MONTH:${it.yearMonth}
                    ACCOUNT TITLE:${it.accountTitle.japanese}
                    AMOUNT:${it.amount.value}
                    """
        }

        logger.log(str)

        return "200 OK"
    }
}