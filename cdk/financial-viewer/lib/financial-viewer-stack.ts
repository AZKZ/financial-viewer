import * as core from '@aws-cdk/core';
import * as lambda from '@aws-cdk/aws-lambda'
import * as event from '@aws-cdk/aws-lambda-event-sources';
import * as s3 from '@aws-cdk/aws-s3';
import * as iam from '@aws-cdk/aws-iam'

export class FinancialViewerStack extends core.Stack {
  constructor(scope: core.Construct, id: string, props?: core.StackProps) {
    super(scope, id, props);

    // S3バケット
    const storage = new s3.Bucket(this, 'FinancialViewerStorage', {
      // TODO:Handlerで復号を実装したら、暗号化を有効にする
      // encryption: s3.BucketEncryption.S3_MANAGED,
      removalPolicy: core.RemovalPolicy.DESTROY
    })

    // S3イベント OBJECT_CREATED
    const s3EventObjectCreated = new event.S3EventSource(storage, {
      events: [s3.EventType.OBJECT_CREATED],
      filters: [{ suffix: '.csv' }]
    })

    // Lambda実行用ロール
    const lambdaRole = new iam.Role(this, 'LambdaRole', {
      assumedBy: new iam.ServicePrincipal('lambda.amazonaws.com'),
      managedPolicies: [
        iam.ManagedPolicy.fromAwsManagedPolicyName('AmazonS3ReadOnlyAccess'),
        iam.ManagedPolicy.fromAwsManagedPolicyName('service-role/AWSLambdaBasicExecutionRole'),
      ]
    })

    // Lambda関数 損益計算書CSV読み込み
    const incomeStatementCsvReder = new lambda.Function(this, 'IncomeStatementCsvReader', {
      runtime: lambda.Runtime.JAVA_11,
      code: lambda.Code.fromAsset('../../backend/build/distributions/financial-viewer-1.0-SNAPSHOT.zip'),
      handler: 'com.azkz.lambda.handler.IncomeStatementCsvReadHandler',
      role: lambdaRole,
      events: [s3EventObjectCreated],
      memorySize: 192,
      timeout: core.Duration.minutes(1)
    })

  }
}
