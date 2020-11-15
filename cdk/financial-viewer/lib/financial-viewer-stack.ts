import * as core from '@aws-cdk/core';
import * as lambda from '@aws-cdk/aws-lambda'
import * as event from '@aws-cdk/aws-lambda-event-sources';
import * as s3 from '@aws-cdk/aws-s3';
import * as iam from '@aws-cdk/aws-iam'
import * as cognito from '@aws-cdk/aws-cognito'
import { Duration } from '@aws-cdk/core';
import { ManagedPolicy } from '@aws-cdk/aws-iam';

export class FinancialViewerStack extends core.Stack {
  constructor(scope: core.Construct, id: string, props?: core.StackProps) {
    super(scope, id, props);

    /* ====================== S3 ====================== */
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

    /* ====================== Lambda ====================== */
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
      code: lambda.Code.fromAsset('../../backend/financial-viewer/build/distributions/financial-viewer-1.0-SNAPSHOT.zip'),
      handler: 'com.azkz.lambda.handler.IncomeStatementCsvReadHandler',
      role: lambdaRole,
      events: [s3EventObjectCreated],
      memorySize: 192,
      timeout: core.Duration.minutes(1)
    })

    /* ====================== Cognito ====================== */
    // アプリユーザーのロール
    // TODO:もし、ロールとUserPoolの紐付をコードでできるなら、その方法に変更する。※現在はコンソールで手動割当
    // TODO:S3フルアクセスではなく、適切なポリシーを作成して付与する。
    const userRole = new iam.Role(this, 'FinancialViewerUserRole', {
      assumedBy: new iam.WebIdentityPrincipal('cognito-identity.amazonaws.com'),
      managedPolicies: [
        iam.ManagedPolicy.fromAwsManagedPolicyName('AmazonS3FullAccess')
      ]
    })

    // User Pool
    const userPool = new cognito.UserPool(this,'FinancialViewerUserPool',{
      userPoolName:'FinancialViewer-UserPool',
      selfSignUpEnabled:false,
      signInAliases:{
        email:true,
      },
      standardAttributes:{
        fullname:{
          required:true,
          mutable:true,
        },
      },
      passwordPolicy:{
        minLength:8,
        requireDigits:true,
        requireLowercase:true,
        requireUppercase:true,
        requireSymbols:true,
      },
      accountRecovery:cognito.AccountRecovery.EMAIL_ONLY
    })

    const appCliant = userPool.addClient('FinancialViewerAppCliant')
    const cliantId = appCliant.userPoolClientId

  }
}
